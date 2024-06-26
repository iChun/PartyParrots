package me.ichun.mods.partyparrots.mixin;

import net.minecraft.client.model.ParrotModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ParrotModel.class)
public interface ParrotModelInvokerMixin
{
    @Invoker
    void invokePrepare(ParrotModel.State state);

    @Invoker
    void invokeSetupAnim(ParrotModel.State state, int tickCount, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);
}
