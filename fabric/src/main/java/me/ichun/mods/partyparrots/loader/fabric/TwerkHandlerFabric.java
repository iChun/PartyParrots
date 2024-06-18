package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.ichunutil.api.fabric.event.FabricEvents;
import me.ichun.mods.ichunutil.api.fabric.event.client.FabricClientEvents;
import me.ichun.mods.partyparrots.common.core.TwerkHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class TwerkHandlerFabric extends TwerkHandler
{
    public TwerkHandlerFabric()
    {
        FabricClientEvents.LIVING_RENDER_PRE.register((living, renderer, partialTick) -> onRenderLivingPre(living));
        FabricEvents.PLAYER_TICK_END.register(this::onPlayerTickEnd);
        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTickEnd);
        FabricClientEvents.CLIENT_LEVEL_LOAD.register(level -> onLevelLoad());
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> onClientDisconnected());
    }
}
