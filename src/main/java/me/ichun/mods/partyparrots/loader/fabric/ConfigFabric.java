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

        @ConfigEntry(nameKey = "config.partyparrots.prop.partyShoulder.name", descriptionKey = "config.partyparrots.prop.partyShoulder.desc", comment = "Do parrots party when they're on your shoulder?")
        public boolean partyShoulder = true;

        @ConfigEntry(nameKey = "config.partyparrots.prop.partyFlying.name", descriptionKey = "config.partyparrots.prop.partyFlying.desc", comment = "Do parrots party when they're flying?")
        public boolean partyFlying = true;

        @ConfigEntry(nameKey = "config.partyparrots.prop.partyStanding.name", descriptionKey = "config.partyparrots.prop.partyStanding.desc", comment = "Do parrots party when they're standing?")
        public boolean partyStanding = true;

        @ConfigEntry(nameKey = "config.partyparrots.prop.partySitting.name", descriptionKey = "config.partyparrots.prop.partySitting.desc", comment = "Do parrots party when they're sitting?")
        public boolean partySitting = false;

        @ConfigEntry(nameKey = "config.partyparrots.prop.partyTwerk.name", descriptionKey = "config.partyparrots.prop.partyTwerk.desc", comment = "Do parrots party when players twerk?")
        public boolean partyTwerk = false;

        @ConfigEntry(nameKey = "config.partyparrots.prop.partyTwerkRange.name", descriptionKey = "config.partyparrots.prop.partyTwerkRange.desc", comment = "How far do players have to be twerking for parrots to PARTY?!", requiresRestart = true)
        @ConfigEntry.BoundedDouble(min = 2D, max = 32D)
        public double partyTwerkRange = 5D;
    }
}
