package me.ichun.mods.partyparrots.loader.neoforge;

import me.ichun.mods.ichunutil.client.gui.config.WorkspaceConfigs;
import me.ichun.mods.ichunutil.common.iChunUtil;
import me.ichun.mods.partyparrots.common.PartyParrots;
import me.ichun.mods.partyparrots.common.core.Config;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(PartyParrots.MOD_ID)
public class LoaderNeoForge extends PartyParrots
{
    public LoaderNeoForge(IEventBus modEventBus)
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
    }

    @OnlyIn(Dist.CLIENT)
    private void initClient(IEventBus modEventBus)
    {
        NeoForge.EVENT_BUS.register(eventHandlerClient = new EventHandlerClientNeoForge());

        //register config
        config = iChunUtil.d().registerConfig(new Config(), modEventBus);

        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (minecraft, screen) -> new WorkspaceConfigs(screen));
    }
}
