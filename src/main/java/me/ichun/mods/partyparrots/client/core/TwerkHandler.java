package me.ichun.mods.partyparrots.client.core;

import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.WeakHashMap;

public class TwerkHandler
{
    public static WeakHashMap<Player, TwerkInfo> playerTwerks = new WeakHashMap<>();

    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        if(event.getEntity() instanceof Parrot)
        {
            Parrot parrot = (Parrot)event.getEntity();
            if(PartyParrots.config.partyTwerk.get() && withinTwerkRange(parrot))
            {
                parrot.partyParrot = true;
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.player.level.isClientSide && event.phase == TickEvent.Phase.END)
        {
            if(event.player.isShiftKeyDown() && !playerTwerks.containsKey(event.player))
            {
                playerTwerks.put(event.player, new TwerkInfo());
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            if(!Minecraft.getInstance().isPaused())
            {
                playerTwerks.entrySet().removeIf(e -> !e.getValue().tick(e.getKey()));
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        Minecraft.getInstance().execute(this::clean);
    }

    @SubscribeEvent
    public void onLoggedOutEvent(ClientPlayerNetworkEvent.LoggedOutEvent event)
    {
        Minecraft.getInstance().execute(this::clean);
    }

    public void clean()
    {
        playerTwerks.clear();
    }

    public boolean withinTwerkRange(Parrot parrot)
    {
        for(Map.Entry<Player, TwerkInfo> e : playerTwerks.entrySet())
        {
            if(e.getValue().isTwerking() && parrot.distanceTo(e.getKey()) < PartyParrots.config.partyTwerkRange.get())
            {
                return true;
            }
        }
        return false;
    }

    public class TwerkInfo
    {
        public int twerks;
        public boolean playerSneak;
        public int twerkTimeout;

        public TwerkInfo()
        {
            twerks = 1;
            playerSneak = true;
        }

        public boolean tick(Player player)
        {
            if(player.isRemoved())
            {
                return false; //our player is dead. kill.
            }
            if(player.isShiftKeyDown() && !playerSneak) // new twerk
            {
                twerks++;
                twerkTimeout = 0;
            }
            twerkTimeout++;
            if(twerkTimeout > 60) // 3 seconds
            {
                return false; //we're stopped twerking, kill.
            }
            playerSneak = player.isShiftKeyDown();
            return true;
        }

        public boolean isTwerking()
        {
            return twerks >= 5;
        }
    }
}
