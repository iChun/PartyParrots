package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.partyparrots.common.PartyParrots;
import me.ichun.mods.partyparrots.mixin.ParrotAccessorMixin;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;

public abstract class EventHandlerClient
{
    public void onRenderLivingPre(LivingEntity living)
    {
        if(living instanceof Parrot parrot)
        {
            if(parrot.isFlying() && PartyParrots.config.partyFlying ||
                    !parrot.isFlying() && (!parrot.isInSittingPose() && PartyParrots.config.partyStanding ||
                            parrot.isInSittingPose() && PartyParrots.config.partySitting)) //isInSittingPose() = isSitting() (1.16.3+ = isSleeping?? [more like client-side flag])
            {
                ((ParrotAccessorMixin)parrot).setPartyParrot(true);
            }
        }
    }
}
