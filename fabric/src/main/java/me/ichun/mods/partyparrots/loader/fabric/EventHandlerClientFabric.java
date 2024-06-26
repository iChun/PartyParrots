package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.ichunutil.loader.fabric.event.client.FabricClientEvents;
import me.ichun.mods.partyparrots.common.core.EventHandlerClient;

public class EventHandlerClientFabric extends EventHandlerClient
{
    public EventHandlerClientFabric()
    {
        FabricClientEvents.LIVING_RENDER_PRE.register((living, renderer, partialTick) -> onRenderLivingPre(living));
    }

    @Override
    public void registerTwerkHandler()
    {
        new TwerkHandlerFabric();
    }
}
