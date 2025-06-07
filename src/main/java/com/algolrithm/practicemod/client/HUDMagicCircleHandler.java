package com.algolrithm.practicemod.client;

import com.algolrithm.practicemod.PracticeMod;
import com.algolrithm.practicemod.event.HUDMagicCircleEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

@OnlyIn(Dist.CLIENT)
public class HUDMagicCircleHandler {
    static final ResourceLocation SPRITES = ResourceLocation.fromNamespaceAndPath(PracticeMod.MOD_ID, "textures/icons/sprite_sheet.png");
    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EFFECTS, CircleOverlay.ID, new CircleOverlay());
    }

    public static abstract class Overlay implements LayeredDraw.Layer {
        public abstract void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int right, int left, int top, int guiTicks);

        @Override
        public final void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player == null || !shouldRenderOverlay(mc, mc.player, guiGraphics, mc.gui.getGuiTicks())) return;

            int top = 30;
            int left = guiGraphics.guiWidth() - 60;
            int right = guiGraphics.guiWidth() - 30;

            render(mc, mc.player, guiGraphics, left, right, top, mc.gui.getGuiTicks());
        }

        public boolean shouldRenderOverlay(Minecraft mc, Player player, GuiGraphics guiGraphics, int guiTicks) {
            return !mc.options.hideGui && mc.gameMode != null && mc.gameMode.canHurtPlayer();
        }
    }
    public static class CircleOverlay extends Overlay {
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(PracticeMod.MOD_ID, "circle_overlay");

        @Override
        public void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int right, int left, int top, int guiTicks) {
            HUDMagicCircleEvent magicCircleEvent = new HUDMagicCircleEvent(right, top, guiGraphics);

            if (!magicCircleEvent.isCanceled()) NeoForge.EVENT_BUS.post(magicCircleEvent);
            if (magicCircleEvent.isCanceled()) return;

            drawCircle(player, guiGraphics, right, top, guiTicks);
        }
    }

    public static void drawCircle(Player player, GuiGraphics guiGraphics, int right, int top, int guiTicks) {
        int iconSize = 80;
        guiGraphics.blit(SPRITES, right, top, 0, 0, 18, 18,iconSize, iconSize);
    }
}
