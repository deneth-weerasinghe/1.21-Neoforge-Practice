package com.algolrithm.practicemod.datagen;

import com.algolrithm.practicemod.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.algolrithm.practicemod.PracticeMod.MOD_ID;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ORB_BLOCK.get())
                .add(ModBlocks.LUSTER_STONE.get())
                .add(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get())
                .add(ModBlocks.TRANSMUTATION_SURFACE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LUSTER_STONE.get())
                .add(ModBlocks.ORB_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get());

        tag(BlockTags.BASE_STONE_OVERWORLD).add(ModBlocks.LUSTER_STONE.get());
        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).add(ModBlocks.LUSTER_STONE.get());
        tag(BlockTags.STONE_ORE_REPLACEABLES).add(ModBlocks.LUSTER_STONE.get());

        tag(Tags.Blocks.STONES).add(ModBlocks.LUSTER_STONE.get());
        tag(Tags.Blocks.ORES).add(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get());
        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ModBlocks.MYSTERIOUS_CRYSTAL_ORE.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).add(ModBlocks.ORB_BLOCK.get());
    }

}
