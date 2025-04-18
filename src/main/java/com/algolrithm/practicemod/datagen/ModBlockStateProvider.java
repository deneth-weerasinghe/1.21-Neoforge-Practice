package com.algolrithm.practicemod.datagen;

import com.algolrithm.practicemod.PracticeMod;
import com.algolrithm.practicemod.blocks.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PracticeMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ORB_BLOCK);
        blockWithItem(ModBlocks.LUSTER_STONE);
        blockWithItem(ModBlocks.MYSTERIOUS_CRYSTAL_ORE);

        topBlockWithItem(ModBlocks.TRANSMUTATION_SURFACE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void topBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeTop(deferredBlock.get()));
    }
    private ModelFile cubeTop(Block block) {
        return models().cubeTop(BuiltInRegistries.BLOCK.getKey(block).getPath(), blockTexture(block),getTopTexture(block));
    }
    private ResourceLocation getTopTexture(Block block) {
        ResourceLocation rl = blockTexture(block);
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + "_top");
    }
}
