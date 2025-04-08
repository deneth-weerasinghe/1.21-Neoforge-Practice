package com.algolrithm.practicemod.item;

import com.algolrithm.practicemod.PracticeMod;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PracticeMod.MOD_ID);

    public static final DeferredItem<Item> ORB = ITEMS.register("orb",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> MYSTERIOUS_CRYSTAL = ITEMS.register("mysterious_crystal",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> UNREFINED_ORB = ITEMS.register("unrefined_orb",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> CHISEL_WAND = ITEMS.register("chisel_wand",
            () -> new ChiselItem(
                    new Item.Properties()
                            .rarity(Rarity.RARE)
                            .durability(2048)
                            .component(DataComponents.TOOL,
                                    new Tool(List.of(), 1.0F, 2))
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
