package fr.lunix.bettermc.client.model;

import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockElementFace;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.BlockElementRotation;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import java.util.EnumMap;
import java.util.ArrayList;
import java.util.List;

public class PixelQuadUtil {
    // Génère les 6 faces d'un voxel (cube 1x1x1 dans une grille 16x16x16)
    public static List<BakedQuad> makeVoxelQuads(int x, int y, int z, TextureAtlasSprite texture) {
        List<BakedQuad> quads = new ArrayList<>();
        float min = x / 16.0f;
        float minY = y / 16.0f;
        float minZ = z / 16.0f;
        float max = (x + 1) / 16.0f;
        float maxY = (y + 1) / 16.0f;
        float maxZ = (z + 1) / 16.0f;
        FaceBakery bakery = new FaceBakery();
        // Pour chaque face du cube
        for (Direction dir : Direction.values()) {
            Vector3f from = new Vector3f(min, minY, minZ);
            Vector3f to = new Vector3f(max, maxY, maxZ);
            BlockFaceUV uv = new BlockFaceUV(new float[]{0, 0, 16, 16}, 0);
            BlockElementFace face = new BlockElementFace(null, 1, "#all", uv);
            quads.add(bakery.bakeQuad(from, to, face, texture, dir, null, null, true, null));
        }
        return quads;
    }
}
