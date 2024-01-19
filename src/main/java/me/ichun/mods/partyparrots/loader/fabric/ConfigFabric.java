package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.partyparrots.common.core.Config;
import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;

public class ConfigFabric extends Config
        implements ConfigContainer
{
    public static Party PARTY = null;

    public me.lortseam.completeconfig.data.Config configInstance;

    public ConfigFabric()
    {
        partyShoulder = new ConfigWrapper<>(() -> PARTY.partyShoulder, v -> PARTY.partyShoulder = v);
        partyFlying = new ConfigWrapper<>(() -> PARTY.partyFlying, v -> PARTY.partyFlying = v);
        partyStanding = new ConfigWrapper<>(() -> PARTY.partyStanding, v -> PARTY.partyStanding = v);
        partySitting = new ConfigWrapper<>(() -> PARTY.partySitting, v -> PARTY.partySitting = v);
        partyTwerk = new ConfigWrapper<>(() -> PARTY.partyTwerk, v -> PARTY.partyTwerk = v);
        partyTwerkRange = new ConfigWrapper<>(() -> PARTY.partyTwerkRange, v -> PARTY.partyTwerkRange = v);
    }

    @Override
    public void registerTwerkHandler()
    {
        new TwerkHandlerFabric();
    }


    @Transitive
    @ConfigEntries(includeAll = true)
    public static class Party implements ConfigGroup
    {
        public Party()
        {
            PARTY = this;
        }

        @Override
        public String getComment()
        {
            return "General configs that don't fit any other category.";
        }

        @ConfigEntry(nameKey = "prop.partyShoulder.name", descriptionKey = "prop.partyShoulder.desc", comment = Reference.PARTY_SHOULDER_COMMENT)
        public boolean partyShoulder = true;

        @ConfigEntry(nameKey = "prop.partyFlying.name", descriptionKey = "prop.partyFlying.desc", comment = Reference.PARTY_FLYING_COMMENT)
        public boolean partyFlying = true;

        @ConfigEntry(nameKey = "prop.partyStanding.name", descriptionKey = "prop.partyStanding.desc", comment = Reference.PARTY_STANDING_COMMENT)
        public boolean partyStanding = true;

        @ConfigEntry(nameKey = "prop.partySitting.name", descriptionKey = "prop.partySitting.desc", comment = Reference.PARTY_SITTING_COMMENT)
        public boolean partySitting = false;

        @ConfigEntry(nameKey = "prop.partyTwerk.name", descriptionKey = "prop.partyTwerk.desc", comment = Reference.PARTY_TWERK_COMMENT)
        public boolean partyTwerk = false;

        @ConfigEntry(nameKey = "prop.partyTwerkRange.name", descriptionKey = "prop.partyTwerkRange.desc", comment = Reference.PARTY_TWERK_RANGE_COMMENT, requiresRestart = true)
        @ConfigEntry.BoundedDouble(min = 2D, max = 32D)
        public double partyTwerkRange = 5D;
    }
}
