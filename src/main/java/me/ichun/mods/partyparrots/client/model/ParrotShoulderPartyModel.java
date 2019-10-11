package me.ichun.mods.partyparrots.client.model;

import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.renderer.entity.model.ParrotModel;

public class ParrotShoulderPartyModel extends ParrotModel
{
    public ParrotShoulderPartyModel()
    {
        super();
        this.setRotationAngles(State.ON_SHOULDER, 0, 0F , 0F ,0F ,0F, 0F);
    }

    @Override
    public void renderOnShoulder(float p_217161_1_, float p_217161_2_, float p_217161_3_, float p_217161_4_, float p_217161_5_, int p_217161_6_) {
        this.setLivingAnimations(PartyParrots.config.partyShoulder.get() ? State.PARTY : State.ON_SHOULDER);
        this.setRotationAngles(PartyParrots.config.partyShoulder.get() ? State.PARTY : State.ON_SHOULDER, p_217161_6_, p_217161_1_, p_217161_2_, 0.0F, p_217161_3_, p_217161_4_);
        this.render(p_217161_5_);
    }
}
