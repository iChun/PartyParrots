package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.partyparrots.common.PartyParrots;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class PartyParrotsConfigGui extends GuiConfig
{
    public PartyParrotsConfigGui(GuiScreen parentScreen)
    {
        super(parentScreen,
                new ConfigElement(PartyParrots.config.getCategory("general")).getChildElements(),
                PartyParrots.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(PartyParrots.config.toString()));
    }
}
