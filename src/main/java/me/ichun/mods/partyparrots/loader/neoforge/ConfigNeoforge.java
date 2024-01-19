package me.ichun.mods.partyparrots.loader.neoforge;

import me.ichun.mods.partyparrots.common.core.Config;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;

public class ConfigNeoforge extends Config
{
    public ConfigNeoforge(ModConfigSpec.Builder builder, IEventBus modEventBus)
    {
        builder.comment("Party settings").push("party");

        final ModConfigSpec.BooleanValue cPartyShoulder = builder.comment(Reference.PARTY_SHOULDER_COMMENT)
            .translation("config.partyparrots.prop.partyShoulder.desc")
            .define("partyShoulder", true);
        partyShoulder = new ConfigWrapper<>(cPartyShoulder::get, cPartyShoulder::set, cPartyShoulder::save);

        final ModConfigSpec.BooleanValue cPartyFlying = builder.comment(Reference.PARTY_FLYING_COMMENT)
            .translation("config.partyparrots.prop.partyFlying.desc")
            .define("partyFlying", true);
        partyFlying = new ConfigWrapper<>(cPartyFlying::get, cPartyFlying::set, cPartyFlying::save);

        final ModConfigSpec.BooleanValue cPartyStanding = builder.comment(Reference.PARTY_STANDING_COMMENT)
            .translation("config.partyparrots.prop.partyStanding.desc")
            .define("partyStanding", true);
        partyStanding = new ConfigWrapper<>(cPartyStanding::get, cPartyStanding::set, cPartyStanding::save);

        final ModConfigSpec.BooleanValue cPartySitting = builder.comment(Reference.PARTY_SITTING_COMMENT)
            .translation("config.partyparrots.prop.partySitting.desc")
            .define("partySitting", false);
        partySitting = new ConfigWrapper<>(cPartySitting::get, cPartySitting::set, cPartyStanding::save);

        final ModConfigSpec.BooleanValue cPartyTwerk = builder.comment(Reference.PARTY_TWERK_COMMENT)
            .translation("config.partyparrots.prop.partyTwerk.desc")
            .define("partyTwerk", false);
        partyTwerk = new ConfigWrapper<>(cPartyTwerk::get, cPartyTwerk::set, cPartyTwerk::save);

        final ModConfigSpec.DoubleValue cPartyTwerkRange = builder.comment(Reference.PARTY_TWERK_RANGE_COMMENT)
            .translation("config.partyparrots.prop.partyTwerkRange.desc")
            .defineInRange("partyTwerkRange", 5D, 2D, 32D);
        partyTwerkRange = new ConfigWrapper<>(cPartyTwerkRange::get, cPartyTwerkRange::set, cPartyTwerkRange::save);

        builder.pop();

        modEventBus.addListener(this::onConfigLoad);
        modEventBus.addListener(this::onConfigReload);
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
        NeoForge.EVENT_BUS.register(new TwerkHandlerNeoforge());
    }
}
