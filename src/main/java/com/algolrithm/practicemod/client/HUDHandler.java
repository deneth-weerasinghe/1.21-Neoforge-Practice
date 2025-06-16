package com.algolrithm.practicemod.client;

import com.algolrithm.practicemod.PracticeMod;
import com.algolrithm.practicemod.event.HUDRenderEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

@OnlyIn(Dist.CLIENT)
public class HUDHandler {

    static final ResourceLocation SPRITES = ResourceLocation.fromNamespaceAndPath(PracticeMod.MOD_ID, "textures/icons/sprite_sheet.png");
    static final int SPRITE_SHEET_WIDTH = 81;
    static final int SPRITE_SHEET_HEIGHT = 80;

    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EFFECTS, CircleOverlay.ID, new CircleOverlay());
        event.registerAbove(VanillaGuiLayers.FOOD_LEVEL, ManaBarOverlay.ID, new ManaBarOverlay());
    }

    public static abstract class Overlay implements LayeredDraw.Layer {
        public abstract void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int right, int left, int top);

        @Override
        public final void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player == null || !shouldRenderOverlay(mc, mc.player, guiGraphics)) return;

            int top = guiGraphics.guiHeight();
            int left = guiGraphics.guiWidth() - 60;
            int right = guiGraphics.guiWidth();

            render(mc, mc.player, guiGraphics, right, left, top);
        }

        public boolean shouldRenderOverlay(Minecraft mc, Player player, GuiGraphics guiGraphics) {
            return !mc.options.hideGui && mc.gameMode != null && mc.gameMode.canHurtPlayer();
        }
    }
    public static class CircleOverlay extends Overlay {
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(PracticeMod.MOD_ID, "circle_overlay");

        @Override
        public void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int right, int left, int top) {
            int circle_right = right - 30;
            HUDRenderEvent.MagicCircle magicCircleEvent = new HUDRenderEvent.MagicCircle(circle_right, 30, guiGraphics);

            if (!magicCircleEvent.isCanceled()) NeoForge.EVENT_BUS.post(magicCircleEvent);
            if (magicCircleEvent.isCanceled()) return;

            drawCircle(magicCircleEvent, player);
        }
    }
    public static class ManaBarOverlay extends Overlay {
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(PracticeMod.MOD_ID, "mana_overlay");

        @Override
        public void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int right, int left, int top) {
            float mana = 5.0F;
            int x = right / 2 + 10;
            int y = top - 49;

            HUDRenderEvent.ManaBar manaBarEvent = new HUDRenderEvent.ManaBar(mana, x, y, guiGraphics);
            if (player.getVehicle() instanceof LivingEntity) {
                manaBarEvent.setCanceled(true);
            }
            if (!manaBarEvent.isCanceled()) NeoForge.EVENT_BUS.post(manaBarEvent);
            if (manaBarEvent.isCanceled()) return;


            drawManaBar(manaBarEvent, player);
        }
    }

    private static void drawCircle(HUDRenderEvent.MagicCircle event, Player player) {
        drawCircle(player, event.guiGraphics, event.x, event.y);
    }
    public static void drawCircle(Player player, GuiGraphics guiGraphics, int right, int top) {
        guiGraphics.blit(SPRITES, right, top, 0, 0, 18, 18, SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
    }
    private static void drawManaBar(HUDRenderEvent.ManaBar event, Player player) {
        drawManaBar(player, event.guiGraphics, event.x, event.y, event.mana);
    }
    public static void drawManaBar(Player player, GuiGraphics guiGraphics, int right, int top, float mana) {
        guiGraphics.blit(SPRITES, right, top, 0, 18, 81, 9, SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
    }
}
