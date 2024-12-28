package me.ichun.mods.partyparrots.mixin;

import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ParrotOnShoulderLayer.class)
public interface ParrotOnShoulderLayerAccessorMixin
{
    @Accessor
    ParrotRenderState getParrotState();
}
