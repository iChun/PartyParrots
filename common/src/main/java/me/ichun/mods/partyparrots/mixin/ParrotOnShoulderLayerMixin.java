package me.ichun.mods.partyparrots.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.entity.animal.Parrot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParrotOnShoulderLayer.class)
public abstract class ParrotOnShoulderLayerMixin
{
    @Inject(method = "renderOnShoulder", at = @At("HEAD"))
    private void partyparrots$renderOnShoulder(PoseStack poseStack, MultiBufferSource buffer, int packedLight, PlayerRenderState renderState, Parrot.Variant variant, float yRot, float xRot, boolean leftShoulder, CallbackInfo ci)
    {
        if(PartyParrots.config.partyShoulder)
        {
            ((ParrotOnShoulderLayerAccessorMixin)(Object)this).getParrotState().pose = ParrotModel.Pose.PARTY;
        }
    }
}
