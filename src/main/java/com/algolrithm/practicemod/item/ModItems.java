package com.algolrithm.practicemod.item;

import com.algolrithm.practicemod.PracticeMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
