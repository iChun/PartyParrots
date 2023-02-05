package me.ichun.mods.partyparrots.mixin;

import net.minecraft.world.entity.animal.Parrot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Parrot.class)
public interface ParrotAccessorMixin
{
    @Accessor
    void setPartyParrot(boolean flag);
}
