package me.ichun.mods.partyparrots.common.core;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Config
{
    public ConfigWrapper<Boolean> partyShoulder;
    public ConfigWrapper<Boolean> partyFlying;
    public ConfigWrapper<Boolean> partyStanding;
    public ConfigWrapper<Boolean> partySitting;
    public ConfigWrapper<Boolean> partyTwerk;
    public ConfigWrapper<Double> partyTwerkRange;

    protected static class Reference
    {
        public static final String PARTY_SHOULDER_COMMENT = "Do parrots party when they're on your shoulder?";
        public static final String PARTY_FLYING_COMMENT = "Do parrots party when they're flying?";
        public static final String PARTY_STANDING_COMMENT = "Do parrots party when they're standing?";
        public static final String PARTY_SITTING_COMMENT = "Do parrots party when they're sitting?";
        public static final String PARTY_TWERK_COMMENT = "Do parrots party when players twerk?";
        public static final String PARTY_TWERK_RANGE_COMMENT = "How far do players have to be twerking for parrots to PARTY?!";

    }

    private boolean twerkHandlerRegistered = false;
    public void onConfigChange()
    {
        regTwerkHandlerInternal();
    }

    public void regTwerkHandlerInternal()
    {
        if(!twerkHandlerRegistered)
        {
            twerkHandlerRegistered = true;
            registerTwerkHandler();
        }
    }

    public abstract void registerTwerkHandler();

    public static class ConfigWrapper<T>
    {
        public final Supplier<T> getter;
        public final Consumer<T> setter;
        public final Runnable saver;

        public ConfigWrapper(Supplier<T> getter, Consumer<T> setter) {
            this.getter = getter;
            this.setter = setter;
            this.saver = null;
        }

        public ConfigWrapper(Supplier<T> getter, Consumer<T> setter, Runnable saver) {
            this.getter = getter;
            this.setter = setter;
            this.saver = saver;
        }

        public T get()
        {
            return getter.get();
        }

        public void set(T obj)
        {
            setter.accept(obj);
        }

        public void save()
        {
            if(saver != null)
            {
                saver.run();
            }
        }
    }
}
