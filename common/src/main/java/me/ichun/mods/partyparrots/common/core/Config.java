package me.ichun.mods.partyparrots.common.core;

import me.ichun.mods.ichunutil.common.config.ConfigBase;
import me.ichun.mods.ichunutil.common.config.annotations.Prop;
import me.ichun.mods.partyparrots.common.PartyParrots;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class Config extends ConfigBase
{
    public transient boolean registeredTwerkHandler = false;

    public boolean partyShoulder = true;

    public boolean partyFlying = true;

    public boolean partyStanding = true;

    public boolean partySitting = false;

    public boolean partyTwerk = false;

    @Prop(min = 2D, max = 32D)
    public double partyTwerkRange = 5D;

    public Config()
    {
        super(PartyParrots.MOD_ID + ".toml");
    }

    @NotNull
    @Override
    public String getModId()
    {
        return PartyParrots.MOD_ID;
    }

    @NotNull
    @Override
    public String getConfigName()
    {
        return PartyParrots.MOD_NAME;
    }

    @Override
    public Type getConfigType()
    {
        return Type.CLIENT;
    }

    @Override
    public void onConfigLoaded()
    {
        checkForTwerk();
    }

    @Override
    public void onPropertyChanged(boolean file, String name, Field field, Object oldObj, Object newObj)
    {
        if(field.getName().equals("partyTwerk"))
        {
            checkForTwerk();
        }
    }

    public void checkForTwerk()
    {
        if(partyTwerk && !registeredTwerkHandler)
        {
            registeredTwerkHandler = true;
            PartyParrots.eventHandlerClient.registerTwerkHandler();
        }
    }
}
