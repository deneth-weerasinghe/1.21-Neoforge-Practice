package com.algolrithm.practicemod.datagen;

import com.algolrithm.practicemod.blocks.ModBlocks;
import com.algolrithm.practicemod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ORB_BLOCK.get());
        dropSelf(ModBlocks.LUSTER_STONE.get());
        dropSelf(ModBlocks.TRANSMUTATION_SURFACE.get());

        add(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get(),
                block -> createOreDrop(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get(), ModItems.MYSTERIOUS_CRYSTAL.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
