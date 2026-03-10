package fr.lunix.bettermc.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import fr.lunix.bettermc.entity.BetterSalmon;
import fr.lunix.bettermc.BetterMC;

public class BetterSalmonRenderer extends MobRenderer<BetterSalmon, SalmonModel<BetterSalmon>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(BetterMC.MODID, "textures/mob/better_salmon.png");

    public BetterSalmonRenderer(EntityRendererProvider.Context context) {
        super(context, new SalmonModel<>(context.bakeLayer(net.minecraft.client.model.geom.ModelLayers.SALMON)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(BetterSalmon entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(BetterSalmon entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        // Empêche le saumon d'être couché sur le côté hors de l'eau
        if (!entity.isInWater()) {
            poseStack.translate(0.0D, 0.15F, 0.0D);
            poseStack.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(0.0F)); // Pas de rotation couchée
        }
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
    }
}
