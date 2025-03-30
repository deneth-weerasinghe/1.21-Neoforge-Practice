package com.algolrithm.practicemod.item;

import com.algolrithm.practicemod.PracticeMod;
import com.algolrithm.practicemod.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PracticeMod.MOD_ID);

    public static final Supplier<CreativeModeTab> CRYSTAL_ITEMS_TAB = CREATIVE_MODE_TAB.register("crystal_items_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(PracticeMod.MOD_ID, "crystal_blocks_tab"))
                    .icon(() ->new ItemStack(ModItems.ORB.get()))
                    .title(Component.translatable("creativetab.algolpracticemod.crystal_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ORB);
                        output.accept(ModItems.MYSTERIOUS_CRYSTAL);
                    })
                    .build()
    );

    public static final Supplier<CreativeModeTab> CRYSTAL_BLOCKS_TAB = CREATIVE_MODE_TAB.register("crystal_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() ->new ItemStack(ModBlocks.ORB_BLOCK.get()))
                    .title(Component.translatable("creativetab.algolpracticemod.crystal_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.LUSTER_STONE);
                        output.accept(ModBlocks.MYSTERIOUS_CRYSTAL_ORE);
                        output.accept(ModBlocks.ORB_BLOCK);
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
