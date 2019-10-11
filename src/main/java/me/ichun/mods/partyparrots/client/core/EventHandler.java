package me.ichun.mods.partyparrots.client.core;

import me.ichun.mods.partyparrots.client.model.ParrotShoulderPartyModel;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.ParrotVariantLayer;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = PartyParrots.MOD_ID, value = Dist.CLIENT)
public class EventHandler
{
    private static final String parrotModel = "field_215346_a";

    @SubscribeEvent
    public static void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        if(event.getEntity() instanceof ParrotEntity)
        {
            ParrotEntity parrot = (ParrotEntity)event.getEntity();
            if(parrot.isFlying() && PartyParrots.config.partyFlying.get() ||
                    !parrot.isFlying() && (!parrot.isSitting() && PartyParrots.config.partyStanding.get() ||
                            parrot.isSitting() && PartyParrots.config.partySitting.get()))
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
            for(LayerRenderer renderer : event.getRenderer().layerRenderers)
            {
                if(renderer instanceof ParrotVariantLayer)
                {
                    ParrotVariantLayer layer = (ParrotVariantLayer)renderer;
                    if(layer.parrotModel.getClass() != ParrotShoulderPartyModel.class)
                    {
                        ObfuscationReflectionHelper.setPrivateValue(ParrotVariantLayer.class, layer, new ParrotShoulderPartyModel(), parrotModel);
                    }
                }
            }
        }
    }
}
