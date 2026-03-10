package fr.lunix.bettermc.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import fr.lunix.bettermc.blockentity.PixelBlockEntity;
import fr.lunix.bettermc.BetterMC;

public class PixelBlockEntityRenderer implements BlockEntityRenderer<PixelBlockEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(BetterMC.MODID, "textures/block/pixel.png");

    public PixelBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(PixelBlockEntity tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        // System.out.println("[PixelBlockEntityRenderer] Appel du renderer pour PixelBlockEntity à la position " + tile.getBlockPos());
        BlockState pixelState = net.minecraft.world.level.block.Blocks.WHITE_WOOL.defaultBlockState();
        boolean[][][] pixels = tile.getPixels();
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 16; y++)
                for (int z = 0; z < 16; z++) {
                    if (!pixels[x][y][z]) continue;
                    // Pour chaque direction, ne dessiner la face QUE si le voisin n'existe pas
                    for (var dir : net.minecraft.core.Direction.values()) {
                        int nx = x + dir.getStepX();
                        int ny = y + dir.getStepY();
                        int nz = z + dir.getStepZ();
                        boolean neighbor = (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && nz >= 0 && nz < 16) && pixels[nx][ny][nz];
                        if (!neighbor) {
                            poseStack.pushPose();
                            poseStack.translate(x / 16.0, y / 16.0, z / 16.0);
                            poseStack.scale(1f/16f, 1f/16f, 1f/16f);
                            // Dessine uniquement la face exposée
                            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                                pixelState, poseStack, buffer, combinedLight, OverlayTexture.NO_OVERLAY
                            );
                            poseStack.popPose();
                            break; // On ne dessine qu'un cube par pixel si au moins une face est exposée
                        }
                    }
                }
    }
}
