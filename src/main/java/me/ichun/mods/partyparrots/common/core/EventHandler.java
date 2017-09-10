package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.renderer.entity.layers.LayerEntityOnShoulder;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EventHandler
{
    public static boolean resetShoulder = false;

    @SubscribeEvent
    public void onPreRenderEntity(RenderLivingEvent.Pre<EntityParrot> event)
    {
        if(event.getEntity() instanceof EntityParrot)
        {
            EntityParrot parrot = (EntityParrot)event.getEntity();
            if(parrot.isFlying() && PartyParrots.partyWhenFlying || !parrot.isFlying() && (!parrot.isSitting() && PartyParrots.partyWhenStanding || parrot.isSitting() && PartyParrots.partyWhenSitting))
            {
                parrot.partyParrot = true;
            }
        }
    }

    @SubscribeEvent
    public void onPreRenderPlayer(RenderPlayerEvent.Pre event)
    {
        if(PartyParrots.partyWhenOnShoulder || resetShoulder)
        {
            for(LayerRenderer renderer : event.getRenderer().layerRenderers)
            {
                if(renderer instanceof LayerEntityOnShoulder)
                {
                    LayerEntityOnShoulder layer = (LayerEntityOnShoulder)renderer;
                    if(layer.leftModel instanceof ModelParrot)
                    {
                        ((ModelParrot)layer.leftModel).state = resetShoulder ? ModelParrot.State.STANDING : ModelParrot.State.PARTY;
                    }
                    if(layer.rightModel instanceof ModelParrot)
                    {
                        ((ModelParrot)layer.rightModel).state = resetShoulder ? ModelParrot.State.STANDING : ModelParrot.State.PARTY;
                    }
                }
            }
            resetShoulder = false;
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equals(PartyParrots.MOD_ID))
        {
            PartyParrots.syncConfig();
        }
    }
}
