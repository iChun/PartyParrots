package me.ichun.mods.partyparrots.loader.forge;

import me.ichun.mods.partyparrots.common.core.EventHandlerClient;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandlerClientForge extends EventHandlerClient
{
    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        onRenderLivingPre(event.getEntity());
    }

    @Override
    public void fireLivingRenderPreEvent(LivingEntity living, LivingEntityRenderer renderer, float partialTick){}//Noop

    @Override
    public void firePlayerTickEndEvent(Player player){}//Noop

    @Override
    public void fireClientLevelLoad(ClientLevel level){}//Noop
}
