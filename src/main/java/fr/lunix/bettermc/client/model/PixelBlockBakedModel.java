package fr.lunix.bettermc.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PixelBlockBakedModel implements BakedModel {
    private final TextureAtlasSprite texture;
    public static final ModelProperty<boolean[][][]> PIXELS_PROPERTY = new ModelProperty<>();

    public PixelBlockBakedModel(TextureAtlasSprite texture) {
        this.texture = texture;
    }

    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData) {
        boolean[][][] pixels = extraData.get(PIXELS_PROPERTY);
        if (pixels == null) return Collections.emptyList();
        List<BakedQuad> quads = new ArrayList<>();
        // Générer un cube de 1x1x1 pour chaque pixel présent
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 16; y++)
                for (int z = 0; z < 16; z++)
                    if (pixels[x][y][z])
                        quads.addAll(PixelQuadUtil.makeVoxelQuads(x, y, z, texture));
        return quads;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand) {
        return getQuads(state, side, rand, ModelData.EMPTY);
    }

    @Override
    public boolean useAmbientOcclusion() { return true; }
    @Override
    public boolean isGui3d() { return true; }
    @Override
    public boolean usesBlockLight() { return true; }
    @Override
    public boolean isCustomRenderer() { return false; }
    @Override
    public TextureAtlasSprite getParticleIcon() { return texture; }
    @Override
    public ItemTransforms getTransforms() { return ItemTransforms.NO_TRANSFORMS; }
    @Override
    public ItemOverrides getOverrides() { return ItemOverrides.EMPTY; }
}
