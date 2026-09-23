package com.slimmyslabby.nmc;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.init.Items;

import java.util.Map;
import java.util.HashMap;

@Mod(modid=NMC.MODID, name=NMC.NAME, version=NMC.VERSION)
public class NMC {
    
    public static final String MODID = "nmc";
    public static final String NAME = "No More Charcoal";
    public static final String VERSION = "1.1";

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event) {

        // Grabs the furnace recipes instance
        final FurnaceRecipes furnaceRecipeManager = FurnaceRecipes.instance();

        // Creates a hashmap of all existing recipes
        final Map<ItemStack, ItemStack> allRecipes = furnaceRecipeManager.getSmeltingList();

        // Goes through all existing recipes and finds any that output charcoal
        final Map<ItemStack, Float> charcoalRecipes = new HashMap<ItemStack, Float>();
        for (Map.Entry<ItemStack, ItemStack> recipe : allRecipes.entrySet()) {
            final ItemStack output = recipe.getValue();
            if (output.getItem() == Items.COAL && output.getMetadata() == 1) {
                final ItemStack input = recipe.getKey();
                final Float xp = furnaceRecipeManager.getSmeltingExperience(output);
                charcoalRecipes.put(input, xp);
            }
        }

        // Removes all the recipes that output charcoal
        for (ItemStack input : charcoalRecipes.keySet()) {
            allRecipes.remove(input);
        }

        // Creates the new recipes that output coal instead
        final ItemStack output = new ItemStack(Items.COAL, 1, 0);
        for (Map.Entry<ItemStack, Float> recipe : charcoalRecipes.entrySet()) {
            final ItemStack input = recipe.getKey();
            final Float xp = recipe.getValue();
            furnaceRecipeManager.addSmeltingRecipe(input, output, xp);
        }

    }

}
