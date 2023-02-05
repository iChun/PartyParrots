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
    @ConfigEntries
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

        @ConfigEntry(comment = "Do parrots party when they're on your shoulder?")
        public boolean partyShoulder = true;

        @ConfigEntry(comment = "Do parrots party when they're flying?")
        public boolean partyFlying = true;

        @ConfigEntry(comment = "Do parrots party when they're standing?")
        public boolean partyStanding = true;

        @ConfigEntry(comment = "Do parrots party when they're sitting?")
        public boolean partySitting = false;

        @ConfigEntry(comment = "Do parrots party when players twerk?")
        public boolean partyTwerk = false;

        @ConfigEntry(comment = "How far do players have to be twerking for parrots to PARTY?!", requiresRestart = true)
        @ConfigEntry.BoundedDouble(min = 2D, max = 32D)
        public double partyTwerkRange = 5D;
    }
}
