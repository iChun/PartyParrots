package me.ichun.mods.partyparrots.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.model.ParrotModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParrotModel.class)
public abstract class ParrotModelMixin
{
    @Inject(method = "renderOnShoulder", at = @At("HEAD"), cancellable = true)
    private void partyparrots_renderOnShoulder(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch, int tickCount, CallbackInfo ci)
    {
        if(PartyParrots.config.partyShoulder.get())
        {
            ((ParrotModelInvokerMixin)((ParrotModel)(Object)this)).invokePrepare(ParrotModel.State.PARTY);
            ((ParrotModelInvokerMixin)((ParrotModel)(Object)this)).invokeSetupAnim(ParrotModel.State.PARTY, tickCount, limbSwing, limbSwingAmount, 0.0f, netHeadYaw, headPitch);
            ((ParrotModel)(Object)this).root().render(poseStack, buffer, packedLight, packedOverlay);

            ci.cancel();
        }
    }
}
