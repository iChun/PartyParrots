package me.ichun.mods.partyparrots.loader.fabric;

import me.ichun.mods.ichunutil.common.iChunUtil;
import me.ichun.mods.partyparrots.common.PartyParrots;
import me.ichun.mods.partyparrots.common.core.Config;
import net.fabricmc.api.ClientModInitializer;

public class LoaderFabricClient extends PartyParrots
    implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        modProxy = this;

        eventHandlerClient = new EventHandlerClientFabric();

        //register config
        config = iChunUtil.d().registerConfig(new Config());
    }
}
