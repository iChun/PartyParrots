package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.ichunutil.api.fabric.event.client.FabricClientEvents;
import me.ichun.mods.partyparrots.common.core.EventHandlerClient;

public class EventHandlerClientFabric extends EventHandlerClient
{
    public EventHandlerClientFabric()
    {
        FabricClientEvents.LIVING_RENDER_PRE.register((living, renderer, partialTick) -> onRenderLivingPre(living));
    }
}
