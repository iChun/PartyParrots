package me.ichun.mods.partyparrots.client.core;

import me.ichun.mods.partyparrots.client.model.ParrotShoulderPartyModel;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = PartyParrots.MOD_ID, value = Dist.CLIENT)
public class EventHandler
{
    private static final String parrotModel = "f_117290_";

    @SubscribeEvent
    public static void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        if(event.getEntity() instanceof Parrot parrot)
        {
            if(parrot.isFlying() && PartyParrots.config.partyFlying.get() ||
                    !parrot.isFlying() && (!parrot.isInSittingPose() && PartyParrots.config.partyStanding.get() ||
                            parrot.isInSittingPose() && PartyParrots.config.partySitting.get())) //isInSittingPose() = isSitting() (1.16.3+ = isSleeping?? [more like client-side flag])
            {
                parrot.partyParrot = true;
            }
        }
    }

    @SubscribeEvent
    public static void onPreRenderPlayer(RenderPlayerEvent.Pre event)
    {
        if(PartyParrots.config.partyShoulder.get())
        {
            for(RenderLayer renderer : event.getRenderer().layers)
            {
                if(renderer instanceof ParrotOnShoulderLayer layer && layer.model.getClass() != ParrotShoulderPartyModel.class)
                {
                    ObfuscationReflectionHelper.setPrivateValue(ParrotOnShoulderLayer.class, layer, new ParrotShoulderPartyModel(layer.model.root()), parrotModel);
                }
            }
        }
    }
}
