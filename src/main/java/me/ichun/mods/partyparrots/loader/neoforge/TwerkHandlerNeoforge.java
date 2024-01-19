package me.ichun.mods.partyparrots.loader.neoforge;

import me.ichun.mods.partyparrots.common.core.TwerkHandler;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

public class TwerkHandlerNeoforge extends TwerkHandler
{
    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        onRenderLivingPre(event.getEntity());
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            onPlayerTickEnd(event.player);
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            onClientTickEnd(Minecraft.getInstance());
        }
    }

    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load event)
    {
        onLevelLoad();
    }

    @SubscribeEvent
    public void onLoggedOutEvent(ClientPlayerNetworkEvent.LoggingOut event)
    {
        onClientDisconnected();
    }
}
