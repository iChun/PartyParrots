package me.ichun.mods.partyparrots.loader.forge;

import me.ichun.mods.partyparrots.common.core.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ConfigForge extends Config
{
    public ConfigForge(ForgeConfigSpec.Builder builder)
    {
        builder.comment("Party settings").push("party");

        final ForgeConfigSpec.BooleanValue cPartyShoulder = builder.comment("Do parrots party when they're on your shoulder?")
                .translation("config.partyparrots.prop.partyShoulder.desc")
                .define("partyShoulder", true);
        partyShoulder = new ConfigWrapper<>(cPartyShoulder::get, cPartyShoulder::set, cPartyShoulder::save);

        final ForgeConfigSpec.BooleanValue cPartyFlying = builder.comment("Do parrots party when they're flying?")
                .translation("config.partyparrots.prop.partyFlying.desc")
                .define("partyFlying", true);
        partyFlying = new ConfigWrapper<>(cPartyFlying::get, cPartyFlying::set, cPartyFlying::save);

        final ForgeConfigSpec.BooleanValue cPartyStanding = builder.comment("Do parrots party when they're standing?")
                .translation("config.partyparrots.prop.partyStanding.desc")
                .define("partyStanding", true);
        partyStanding = new ConfigWrapper<>(cPartyStanding::get, cPartyStanding::set, cPartyStanding::save);

        final ForgeConfigSpec.BooleanValue cPartySitting = builder.comment("Do parrots party when they're sitting?")
                .translation("config.partyparrots.prop.partySitting.desc")
                .define("partySitting", false);
        partySitting = new ConfigWrapper<>(cPartySitting::get, cPartySitting::set, cPartyStanding::save);

        final ForgeConfigSpec.BooleanValue cPartyTwerk = builder.comment("Do parrots party when players twerk?")
                .translation("config.partyparrots.prop.partyTwerk.desc")
                .define("partyTwerk", false);
        partyTwerk = new ConfigWrapper<>(cPartyTwerk::get, cPartyTwerk::set, cPartyTwerk::save);

        final ForgeConfigSpec.DoubleValue cPartyTwerkRange = builder.comment("How far do players have to be twerking for parrots to PARTY?!")
                .translation("config.partyparrots.prop.partyTwerkRange.desc")
                .defineInRange("partyTwerkRange", 5D, 2D, 32D);
        partyTwerkRange = new ConfigWrapper<>(cPartyTwerkRange::get, cPartyTwerkRange::set, cPartyTwerkRange::save);

        builder.pop();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigLoad);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigReload);
    }

    private void onConfigLoad(ModConfigEvent.Loading event)
    {
        onConfigChange();
    }

    private void onConfigReload(ModConfigEvent.Reloading event)
    {
        onConfigChange();
    }

    @Override
    public void registerTwerkHandler()
    {
        MinecraftForge.EVENT_BUS.register(new TwerkHandlerForge());
    }
}
