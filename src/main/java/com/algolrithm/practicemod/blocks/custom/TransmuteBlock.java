package com.algolrithm.practicemod.blocks.custom;

import com.algolrithm.practicemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TransmuteBlock extends Block {
    public TransmuteBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().getItem() == ModItems.UNREFINED_ORB.get()) {
                itemEntity.getItem().setCount(itemEntity.getItem().getCount() - 1);
                itemEntity.spawnAtLocation(Items.NAUTILUS_SHELL);
                if ((itemEntity.getItem().getCount() % 9) == 0) {
                    level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS);
                }
            }
        }

        super.stepOn(level, pos, state, entity);
    }
}
