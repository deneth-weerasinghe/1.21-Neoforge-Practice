package com.algolrithm.practicemod.datagen;

import com.algolrithm.practicemod.PracticeMod;
import com.algolrithm.practicemod.blocks.ModBlocks;
import com.algolrithm.practicemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ORB_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.ORB.get())
                .unlockedBy("has_orb", has(ModItems.ORB))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CHISEL_WAND.get())
                .pattern("  X")
                .pattern(" # ")
                .pattern("#  ")
                .define('X', ModItems.ORB.get())
                .define('#', Items.BREEZE_ROD)
                .unlockedBy("has_chisel_wand", has(ModItems.CHISEL_WAND.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GEM_APPLE.get())
                .requires(ModItems.ORB.get())
                .requires(Items.APPLE)
                .unlockedBy("has_gem_apple", has(ModItems.GEM_APPLE))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.UNREFINED_ORB.get())
                .requires(ModItems.MYSTERIOUS_CRYSTAL.get(), 4)
                .requires(ModBlocks.LUSTER_STONE.get(), 4)
                .unlockedBy("has_unrefined_orb", has(ModItems.UNREFINED_ORB))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ORB.get(), 9)
                .requires(ModBlocks.ORB_BLOCK.get())
                .unlockedBy("has_orb_block", has(ModBlocks.ORB_BLOCK))
                .save(recipeOutput);

        oreSmelting(recipeOutput, List.of(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get()), RecipeCategory.MISC, ModItems.MYSTERIOUS_CRYSTAL, 0.25F, 200, "mysterious_crystal");
        oreBlasting(recipeOutput, List.of(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get()), RecipeCategory.MISC, ModItems.MYSTERIOUS_CRYSTAL, 0.25F, 200, "mysterious_crystal");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new,
                pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new,
                pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput,
                                                                       RecipeSerializer<T> pCookingSerializer,
                                                                       AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients,
                                                                       RecipeCategory pCategory,
                                                                       ItemLike pResult,
                                                                       float pExperience,
                                                                       int pCookingTime,
                                                                       String pGroup,
                                                                       String pRecipeName) {
        for (ItemLike itemLike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory)
                    .group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike))
                    .save(recipeOutput, PracticeMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }
}
