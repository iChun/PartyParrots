package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.ichunutil.loader.fabric.event.client.FabricClientEvents;
import me.ichun.mods.partyparrots.common.core.TwerkHandler;

public class TwerkHandlerFabric extends TwerkHandler
{
    public TwerkHandlerFabric()
    {
        FabricClientEvents.LIVING_RENDER_PRE.register((living, renderer, partialTick) -> onRenderLivingPre(living));
        FabricClientEvents.CLIENT_LEVEL_LOAD.register(level -> onLevelLoad());
    }
}
