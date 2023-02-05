package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.partyparrots.common.PartyParrots;
import me.ichun.mods.partyparrots.mixin.ParrotAccessorMixin;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;

public abstract class EventHandlerClient
{
    public void onRenderLivingPre(LivingEntity living)
    {
        if(living instanceof Parrot parrot)
        {
            if(parrot.isFlying() && PartyParrots.config.partyFlying.get() ||
                    !parrot.isFlying() && (!parrot.isInSittingPose() && PartyParrots.config.partyStanding.get() ||
                            parrot.isInSittingPose() && PartyParrots.config.partySitting.get())) //isInSittingPose() = isSitting() (1.16.3+ = isSleeping?? [more like client-side flag])
            {
                ((ParrotAccessorMixin)parrot).setPartyParrot(true);
            }
        }
    }

    public abstract void fireLivingRenderPreEvent(LivingEntity living, LivingEntityRenderer renderer, float partialTick);
    public abstract void firePlayerTickEndEvent(Player player);
    public abstract void fireClientLevelLoad(ClientLevel level);
}
