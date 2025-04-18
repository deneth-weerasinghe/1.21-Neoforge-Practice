package com.algolrithm.practicemod.datagen;

import com.algolrithm.practicemod.PracticeMod;
import com.algolrithm.practicemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PracticeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.MYSTERIOUS_CRYSTAL.get());
        basicItem(ModItems.UNREFINED_ORB.get());
        basicItem(ModItems.ORB.get());
        basicItem(ModItems.GEM_APPLE.get());
        basicItem(ModItems.CHISEL_WAND.get());
    }
}
