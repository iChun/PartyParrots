package me.ichun.mods.partyparrots.common;

import me.ichun.mods.partyparrots.client.core.TwerkHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PartyParrots.MOD_ID)
public class PartyParrots
{
    public static final String MOD_ID = "partyparrots";
    public static final String MOD_NAME = "Party Parrots";

    public static final Logger LOGGER = LogManager.getLogger();

    public static Config config;

    public PartyParrots()
    {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> this::setupConfig);
        DistExecutor.runWhenOn(Dist.DEDICATED_SERVER, () -> () -> LOGGER.log(Level.ERROR, "You are loading " + MOD_NAME + " on a server. " + MOD_NAME + " is a client only mod!"));
    }

    private void setupConfig()
    {
        //build the config
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

        config = new Config(configBuilder);

        //register the config. This loads the config for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, configBuilder.build(), MOD_ID + ".toml");
    }

    public class Config
    {
        public final ForgeConfigSpec.BooleanValue partyShoulder;
        public final ForgeConfigSpec.BooleanValue partyFlying;
        public final ForgeConfigSpec.BooleanValue partyStanding;
        public final ForgeConfigSpec.BooleanValue partySitting;
        public final ForgeConfigSpec.BooleanValue partyTwerk;
        public final ForgeConfigSpec.DoubleValue partyTwerkRange;

        private boolean twerkHandlerRegistered = false;

        public Config(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Party settings").push("party");

            partyShoulder = builder.comment("Do parrots party when they're on your shoulder?")
                    .translation("config.partyparrots.prop.partyShoulder.desc")
                    .define("partyShoulder", true);
            partyFlying = builder.comment("Do parrots party when they're flying?")
                    .translation("config.partyparrots.prop.partyFlying.desc")
                    .define("partyFlying", true);
            partyStanding = builder.comment("Do parrots party when they're standing?")
                    .translation("config.partyparrots.prop.partyStanding.desc")
                    .define("partyStanding", true);
            partySitting = builder.comment("Do parrots party when they're sitting?")
                    .translation("config.partyparrots.prop.partySitting.desc")
                    .define("partySitting", false);
            partyTwerk = builder.comment("Do parrots party when players twerk?")
                    .translation("config.partyparrots.prop.partyTwerk.desc")
                    .define("partyTwerk", false);
            partyTwerkRange = builder.comment("How far do players have to be twerking for parrots to PARTY?!")
                    .translation("config.partyparrots.prop.partyTwerkRange.desc")
                    .defineInRange("partyTwerkRange", 5D, 2D, 32D);

            builder.pop();

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigLoad);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigReload);
        }

        private void onConfigLoad(ModConfig.Loading event)
        {
            registerTwerkHandler();
        }

        private void onConfigReload(ModConfig.ConfigReloading event)
        {
            registerTwerkHandler();
        }

        public void registerTwerkHandler()
        {
            if(!twerkHandlerRegistered)
            {
                twerkHandlerRegistered = true;
                MinecraftForge.EVENT_BUS.register(new TwerkHandler());
            }
        }
    }
}
