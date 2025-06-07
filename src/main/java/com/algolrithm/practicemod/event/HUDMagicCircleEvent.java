package com.algolrithm.practicemod.event;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class HUDMagicCircleEvent extends Event implements ICancellableEvent {
    public int x;
    public int y;
    public GuiGraphics guiGraphics;
    public HUDMagicCircleEvent(int x, int y, GuiGraphics guiGraphics) {
        this.x = x;
        this.y = y;
        this.guiGraphics = guiGraphics;
    }
}
