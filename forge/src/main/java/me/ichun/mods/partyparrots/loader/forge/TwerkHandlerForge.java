package me.ichun.mods.partyparrots.loader.forge;

import me.ichun.mods.partyparrots.common.core.TwerkHandler;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TwerkHandlerForge extends TwerkHandler
{
    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        onRenderLivingPre(event.getEntity());
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        onLevelLoad();
    }
}
