package com.slimmyslabby.nmc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ModRecipes {

    public static void modifyCharcoalRecipes() {
        
        final FurnaceRecipes furnaceRecipeManager = FurnaceRecipes.instance();
        final Map<ItemStack, ItemStack> allRecipes = furnaceRecipeManager.getSmeltingList();

        final Map<ItemStack, Float> charcoalRecipes = new HashMap<ItemStack, Float>();
        for (Map.Entry<ItemStack, ItemStack> recipe : allRecipes.entrySet()) {
            final ItemStack output = recipe.getValue();
            if (output.getItem() == Items.COAL && output.getMetadata() == 1) {
                final ItemStack input = recipe.getKey();
                final Float xp = furnaceRecipeManager.getSmeltingExperience(output);
                charcoalRecipes.put(input, xp);
            }
        }

        for (ItemStack input : charcoalRecipes.keySet()) {
            allRecipes.remove(input);
        }

        final ItemStack output = new ItemStack(Items.COAL, 1, 0);
        for (Map.Entry<ItemStack, Float> recipe : charcoalRecipes.entrySet()) {
            final ItemStack input = recipe.getKey();
            final Float xp = recipe.getValue();
            furnaceRecipeManager.addSmeltingRecipe(input, output, xp);
        }   
    }
}
