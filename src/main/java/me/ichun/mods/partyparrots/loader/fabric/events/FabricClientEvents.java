package me.ichun.mods.partyparrots.loader.fabric.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class FabricClientEvents
{
    private FabricClientEvents(){}//no init!

    public static final Event<LivingRenderPre> LIVING_RENDER_PRE = EventFactory.createArrayBacked(LivingRenderPre.class, callbacks -> (living, renderer, partialTick) -> {
        for(LivingRenderPre callback : callbacks)
        {
            callback.onLivingRenderPre(living, renderer, partialTick);
        }
    });

    public static final Event<PlayerTickEnd> PLAYER_TICK_END = EventFactory.createArrayBacked(PlayerTickEnd.class, callbacks -> player -> {
        for(PlayerTickEnd callback : callbacks)
        {
            callback.onPlayerTickEnd(player);
        }
    });

    public static final Event<ClientLevelLoad> CLIENT_LEVEL_LOAD = EventFactory.createArrayBacked(ClientLevelLoad.class, callbacks -> level -> {
        for(ClientLevelLoad callback : callbacks)
        {
            callback.onClientLevelLoad(level);
        }
    });

    @FunctionalInterface
    public interface LivingRenderPre
    {
        void onLivingRenderPre(LivingEntity living, LivingEntityRenderer renderer, float partialTick);
    }

    @FunctionalInterface
    public interface PlayerTickEnd
    {
        void onPlayerTickEnd(Player player);
    }

    @FunctionalInterface
    public interface ClientLevelLoad
    {
        void onClientLevelLoad(ClientLevel level);
    }
}
