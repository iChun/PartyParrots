package me.ichun.mods.partyparrots.loader.neoforge;

import me.ichun.mods.partyparrots.common.PartyParrots;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;


@Mod(PartyParrots.MOD_ID)
public class LoaderNeoforge extends PartyParrots
{
    public LoaderNeoforge(IEventBus modEventBus)
    {
        modProxy = this;

        if(FMLEnvironment.dist.isClient())
        {
            initClient(modEventBus);
        }
        else
        {
            LOGGER.error("You are loading " + MOD_NAME + " on a server. " + MOD_NAME + " is a client only mod!");
        }

        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
    }

    @OnlyIn(net.neoforged.api.distmarker.Dist.CLIENT)
    private void initClient(IEventBus modEventBus)
    {
        setupConfig(modEventBus);
        NeoForge.EVENT_BUS.register(PartyParrots.eventHandlerClient = new EventHandlerClientNeoforge());
    }

    @OnlyIn(net.neoforged.api.distmarker.Dist.CLIENT)
    private void setupConfig(IEventBus modEventBus)
    {
        //build the config
        ModConfigSpec.Builder configBuilder = new ModConfigSpec.Builder();
        config = new ConfigNeoforge(configBuilder, modEventBus);
        //register the config. This loads the config for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, configBuilder.build(), MOD_ID + ".toml");
    }
}
