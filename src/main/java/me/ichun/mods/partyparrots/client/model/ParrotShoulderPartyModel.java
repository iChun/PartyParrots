package me.ichun.mods.partyparrots.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.geom.ModelPart;

public class ParrotShoulderPartyModel extends ParrotModel
{
    public ParrotShoulderPartyModel(ModelPart root)
    {
        super(root);
        this.setupAnim(State.ON_SHOULDER, 0, 0F, 0F, 0F, 0F, 0F);
    }

    @Override
    public void renderOnShoulder(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float p_217161_1_, float p_217161_2_, float p_217161_3_, float p_217161_4_, int p_217161_6_) {
        this.prepare(PartyParrots.config.partyShoulder.get() ? State.PARTY : State.ON_SHOULDER);
        this.setupAnim(PartyParrots.config.partyShoulder.get() ? State.PARTY : State.ON_SHOULDER, p_217161_6_, p_217161_1_, p_217161_2_, 0.0F, p_217161_3_, p_217161_4_);
        this.root().render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}
