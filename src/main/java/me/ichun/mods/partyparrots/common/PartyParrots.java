package me.ichun.mods.partyparrots.common;

import me.ichun.mods.partyparrots.common.core.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PartyParrots.MOD_ID, name = PartyParrots.MOD_NAME,
        version = PartyParrots.VERSION,
        clientSideOnly = true,
        guiFactory = "me.ichun.mods.partyparrots.common.core.GuiFactory",
        acceptableRemoteVersions = "*",
        dependencies = "required-after:forge@[13.19.0.2141,)",
        acceptedMinecraftVersions = "[1.12,1.13)"
)
public class PartyParrots
{
    public static final String MOD_ID = "partyparrots";
    public static final String MOD_NAME = "PartyParrots";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static PartyParrots instance;

    public static Configuration config;

    public static boolean partyWhenOnShoulder = true;
    public static boolean partyWhenFlying = true;
    public static boolean partyWhenStanding = true;
    public static boolean partyWhenSitting = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public static void syncConfig()
    {
        config.setCategoryComment("general", "General Settings");
        boolean oldShoulder = partyWhenOnShoulder;
        partyWhenOnShoulder = config.getBoolean("partyWhenOnShoulder", "general", true, "Do parrots party when they're on your shoulder?");
        partyWhenFlying = config.getBoolean("partyWhenFlying", "general", true, "Do parrots party when they're flying?");
        partyWhenStanding = config.getBoolean("partyWhenStill", "general", true, "Do parrots party when they're standing?");
        partyWhenSitting = config.getBoolean("partyWhenSitting", "general", false, "Do parrots party when they're sitting?");

        if(oldShoulder && !partyWhenOnShoulder)
        {
            EventHandler.resetShoulder = true;
        }

        if(config.hasChanged())
        {
            config.save();
        }
    }
}
