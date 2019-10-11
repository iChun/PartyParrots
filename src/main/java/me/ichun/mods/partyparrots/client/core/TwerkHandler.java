package me.ichun.mods.partyparrots.client.core;

import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.WeakHashMap;

public class TwerkHandler
{
    public static WeakHashMap<PlayerEntity, TwerkInfo> playerTwerks = new WeakHashMap<>();

    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event)
    {
        if(event.getEntity() instanceof ParrotEntity)
        {
            ParrotEntity parrot = (ParrotEntity)event.getEntity();
            if(PartyParrots.config.partyTwerk.get() && withinTwerkRange(parrot))
            {
                parrot.partyParrot = true;
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.player.world.isRemote && event.phase == TickEvent.Phase.END)
        {
            if(event.player.isSneaking() && !playerTwerks.containsKey(event.player))
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
            if(!Minecraft.getInstance().isGamePaused())
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

    public boolean withinTwerkRange(ParrotEntity parrot)
    {
        for(Map.Entry<PlayerEntity, TwerkInfo> e : playerTwerks.entrySet())
        {
            if(e.getValue().isTwerking() && parrot.getDistance(e.getKey()) < PartyParrots.config.partyTwerkRange.get())
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

        public boolean tick(PlayerEntity player)
        {
            if(player.removed)
            {
                return false; //our player is dead. kill.
            }
            if(player.isSneaking() && !playerSneak) // new twerk
            {
                twerks++;
                twerkTimeout = 0;
            }
            twerkTimeout++;
            if(twerkTimeout > 60) // 3 seconds
            {
                return false; //we're stopped twerking, kill.
            }
            playerSneak = player.isSneaking();
            return true;
        }

        public boolean isTwerking()
        {
            return twerks >= 5;
        }
    }
}
