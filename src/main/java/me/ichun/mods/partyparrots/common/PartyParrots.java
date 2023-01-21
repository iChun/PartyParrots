package me.ichun.mods.partyparrots.common;

import com.mojang.logging.LogUtils;
import me.ichun.mods.partyparrots.common.core.Config;
import me.ichun.mods.partyparrots.common.core.EventHandlerClient;
import org.slf4j.Logger;

public abstract class PartyParrots
{
    public static final String MOD_ID = "partyparrots";
    public static final String MOD_NAME = "Party Parrots";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static PartyParrots modProxy;

    public static Config config;

    public static EventHandlerClient eventHandlerClient;

    public abstract boolean isFabricEnv();
}
