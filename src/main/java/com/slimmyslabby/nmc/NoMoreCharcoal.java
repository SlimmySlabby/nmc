package com.slimmyslabby.nmc;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(modid=NoMoreCharcoal.MODID, name=NoMoreCharcoal.NAME, version=NoMoreCharcoal.VERSION)
public class NoMoreCharcoal {
    
    public static final String MODID = "nmc";
    public static final String NAME = "No More Charcoal";
    public static final String VERSION = "1.2";

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModRecipes.modifyCharcoalRecipes();
    }
}
