package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.partyparrots.common.core.EventHandlerClient;
import me.ichun.mods.partyparrots.loader.fabric.events.FabricClientEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class EventHandlerClientFabric extends EventHandlerClient
{
    public EventHandlerClientFabric()
    {
        FabricClientEvents.LIVING_RENDER_PRE.register((living, renderer, partialTick) -> onRenderLivingPre(living));
    }

    @Override
    public void fireLivingRenderPreEvent(LivingEntity living, LivingEntityRenderer renderer, float partialTick)
    {
        FabricClientEvents.LIVING_RENDER_PRE.invoker().onLivingRenderPre(living, renderer, partialTick);
    }

    @Override
    public void firePlayerTickEndEvent(Player player)
    {
        FabricClientEvents.PLAYER_TICK_END.invoker().onPlayerTickEnd(player);
    }

    @Override
    public void fireClientLevelLoad(ClientLevel level)
    {
        FabricClientEvents.CLIENT_LEVEL_LOAD.invoker().onClientLevelLoad(level);
    }
}
