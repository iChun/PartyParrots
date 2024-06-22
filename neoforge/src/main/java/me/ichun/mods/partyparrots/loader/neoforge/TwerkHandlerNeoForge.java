package me.ichun.mods.partyparrots.loader.neoforge;

import me.ichun.mods.partyparrots.common.core.TwerkHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

public class TwerkHandlerNeoForge extends TwerkHandler
{
    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        onRenderLivingPre(event.getEntity());
    }

    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load event)
    {
        onLevelLoad();
    }
}
