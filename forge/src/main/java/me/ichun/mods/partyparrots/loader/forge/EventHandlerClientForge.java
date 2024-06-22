package me.ichun.mods.partyparrots.loader.forge;

import me.ichun.mods.partyparrots.common.core.EventHandlerClient;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandlerClientForge extends EventHandlerClient
{
    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        onRenderLivingPre(event.getEntity());
    }

    @Override
    public void registerTwerkHandler()
    {
        MinecraftForge.EVENT_BUS.register(new TwerkHandlerForge());
    }
}
