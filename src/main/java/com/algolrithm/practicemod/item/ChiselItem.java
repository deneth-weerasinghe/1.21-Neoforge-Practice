package com.algolrithm.practicemod.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE,
                    Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE,
                    Blocks.CRACKED_STONE_BRICKS, Blocks.STONE_BRICKS,
                    Blocks.CRACKED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS,
                    Blocks.CRACKED_DEEPSLATE_TILES, Blocks.DEEPSLATE_TILES,
                    Blocks.CRACKED_NETHER_BRICKS, Blocks.NETHER_BRICKS,
                    Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS
            );
    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block interactedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(interactedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(interactedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
