package me.ichun.mods.partyparrots.loader.neoforge;

import me.ichun.mods.partyparrots.common.core.EventHandlerClient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.common.NeoForge;

public class EventHandlerClientNeoForge extends EventHandlerClient
{
    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        onRenderLivingPre(event.getEntity());
    }

    @Override
    public void registerTwerkHandler()
    {
        NeoForge.EVENT_BUS.register(new TwerkHandlerNeoForge());
    }
}
