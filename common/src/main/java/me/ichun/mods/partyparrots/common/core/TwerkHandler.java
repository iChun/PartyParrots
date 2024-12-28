package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.ichunutil.common.iChunUtil;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.WeakHashMap;

public class TwerkHandler
{
    public static WeakHashMap<Player, TwerkInfo> playerTwerks = new WeakHashMap<>();

    public TwerkHandler()
    {
        iChunUtil.eS().registerPlayerTickEndListener(this::onPlayerTickEnd);
        iChunUtil.eC().registerClientTickEndListener(this::onClientTickEnd);

        iChunUtil.eC().registerOnClientDisconnectListener(client -> onClientDisconnected());

        iChunUtil.eC().registerLivingRenderPreListener(event -> onRenderLivingPre(event.livingEntity(), event.renderState()));
        iChunUtil.eC().registerClientLevelLoadListener(level -> onLevelLoad());
    }

    public void onRenderLivingPre(LivingEntity living, LivingEntityRenderState renderState)
    {
        if(living instanceof Parrot parrot && renderState instanceof ParrotRenderState parrotRenderState)
        {
            if(PartyParrots.config.partyTwerk && withinTwerkRange(parrot))
            {
                parrotRenderState.pose = ParrotModel.Pose.PARTY;
            }
        }
    }

    public void onPlayerTickEnd(Player player)
    {
        if(player.level().isClientSide)
        {
            if(player.isShiftKeyDown() && !playerTwerks.containsKey(player))
            {
                playerTwerks.put(player, new TwerkInfo());
            }
        }
    }

    public void onClientTickEnd(Minecraft client)
    {
        if(!client.isPaused())
        {
            playerTwerks.entrySet().removeIf(e -> !e.getValue().tick(e.getKey()));
        }

    }

    public void onLevelLoad()
    {
        Minecraft.getInstance().execute(this::clean);
    }

    public void onClientDisconnected()
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
            if(e.getValue().isTwerking() && parrot.distanceTo(e.getKey()) < PartyParrots.config.partyTwerkRange)
            {
                return true;
            }
        }
        return false;
    }

    public static class TwerkInfo
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
