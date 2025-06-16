package com.algolrithm.practicemod.event;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class HUDRenderEvent extends Event implements ICancellableEvent {
    public int x;
    public int y;
    public GuiGraphics guiGraphics;

    public HUDRenderEvent(int x, int y, GuiGraphics guiGraphics) {
        this.x = x;
        this.y = y;
        this.guiGraphics = guiGraphics;
    }

    public static class MagicCircle extends HUDRenderEvent {
        public MagicCircle(int x, int y, GuiGraphics guiGraphics) {
            super(x, y, guiGraphics);
        }
    }

    public static class ManaBar extends HUDRenderEvent {
        public float mana;
        public ManaBar(float mana, int x, int y, GuiGraphics guiGraphics) {
            super(x, y, guiGraphics);
            this.mana = mana;
        }
    }
}
