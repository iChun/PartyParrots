package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.partyparrots.common.PartyParrots;
import me.lortseam.completeconfig.data.Config;
import net.fabricmc.api.ClientModInitializer;

public class LoaderFabricClient extends PartyParrots
    implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        modProxy = this;

        //register config
        ConfigFabric configFabric = new ConfigFabric();
        config = configFabric;
        Config modConfig = new Config(MOD_ID, new String[]{}, configFabric);
        modConfig.load();
        Runtime.getRuntime().addShutdownHook(new Thread(modConfig::save));
        configFabric.onConfigChange();

        PartyParrots.eventHandlerClient = new EventHandlerClientFabric();
    }

    @Override
    public boolean isFabricEnv()
    {
        return true;
    }
}
