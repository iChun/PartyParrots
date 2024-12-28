package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.ichunutil.common.iChunUtil;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;

public class EventHandlerClient
{
    public EventHandlerClient()
    {
        iChunUtil.eC().registerLivingRenderPreListener(event -> onRenderLivingPre(event.livingEntity(), event.renderState()));
    }

    public void onRenderLivingPre(LivingEntity living, LivingEntityRenderState renderState)
    {
        if(living instanceof Parrot parrot && renderState instanceof ParrotRenderState parrotRenderState)
        {
            if(parrot.isFlying() && PartyParrots.config.partyFlying ||
                !parrot.isFlying() && (!parrot.isInSittingPose() && PartyParrots.config.partyStanding ||
                    parrot.isInSittingPose() && PartyParrots.config.partySitting)) //isInSittingPose() = isSitting() (1.16.3+ = isSleeping?? [more like client-side flag])
            {
                parrotRenderState.pose = ParrotModel.Pose.PARTY;
            }
        }
    }

    public void registerTwerkHandler()
    {
        new TwerkHandler();
    }
}
