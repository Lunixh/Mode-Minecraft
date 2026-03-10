package fr.lunix.bettermc.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import fr.lunix.bettermc.init.ModBlockEntities;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;

public class PixelBlockEntity extends BlockEntity {
    // 16x16x16 pixels (1 par coordonnée)
    private final boolean[][][] pixels = new boolean[16][16][16];
    private VoxelShape cachedShape = null;
    private boolean shapeDirty = true;

    public PixelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIXEL_BLOCK_ENTITY.get(), pos, state);
        // Initialiser tous les pixels à true (présents)
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 16; y++)
                for (int z = 0; z < 16; z++)
                    pixels[x][y][z] = true;
    }

    // Vérifie si le bloc est vide (aucun pixel)
    public boolean isEmpty() {
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 16; y++)
                for (int z = 0; z < 16; z++)
                    if (pixels[x][y][z]) return false;
        return true;
    }

    public boolean removePixel(int x, int y, int z) {
        if (pixels[x][y][z]) {
            pixels[x][y][z] = false;
            setChanged();
            shapeDirty = true;
            System.out.println("[PixelBlockEntity] Pixel retiré, forme marquée comme sale à " + worldPosition);
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                // Suppression du bloc si vide
                if (isEmpty()) {
                    System.out.println("[PixelBlockEntity] Bloc vide, suppression à " + worldPosition);
                    level.removeBlock(worldPosition, false);
                }
            }
            return true;
        }
        return false;
    }

    // Ajoute un pixel à la position donnée, retourne true si un pixel a été ajouté
    public boolean addPixel(int x, int y, int z) {
        if (!pixels[x][y][z]) {
            pixels[x][y][z] = true;
            setChanged();
            shapeDirty = true;
            System.out.println("[PixelBlockEntity] Pixel ajouté, forme marquée comme sale à " + worldPosition);
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
            return true;
        }
        return false;
    }

    public boolean hasPixel(int x, int y, int z) {
        return pixels[x][y][z];
    }

    public VoxelShape getShape() {
        if (cachedShape == null || shapeDirty) {
            System.out.println("[PixelBlockEntity] Recalcul optimisé de la forme à " + worldPosition);
            VoxelShape shape = Shapes.empty();
            boolean[][][] visited = new boolean[16][16][16];
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    for (int z = 0; z < 16; z++) {
                        if (pixels[x][y][z] && !visited[x][y][z]) {
                            // Chercher la bande maximale sur l'axe Z
                            int z2 = z;
                            while (z2 + 1 < 16 && pixels[x][y][z2 + 1] && !visited[x][y][z2 + 1]) {
                                z2++;
                            }
                            // Marquer comme visité
                            for (int zz = z; zz <= z2; zz++) {
                                visited[x][y][zz] = true;
                            }
                            // Ajouter la box fusionnée
                            shape = Shapes.or(shape, Shapes.box(
                                x / 16.0, y / 16.0, z / 16.0,
                                (x + 1) / 16.0, (y + 1) / 16.0, (z2 + 1) / 16.0
                            ));
                            z = z2; // Sauter les cases déjà traitées
                        }
                    }
                }
            }
            cachedShape = shape;
            shapeDirty = false;
        }
        return cachedShape;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        this.saveAdditional(tag);
        System.out.println("[PixelBlockEntity] getUpdateTag envoyé pour " + worldPosition);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[PixelBlockEntity] handleUpdateTag reçu pour " + worldPosition);
        this.load(tag);
        shapeDirty = true;
        System.out.println("[PixelBlockEntity] shapeDirty=true (handleUpdateTag) à " + worldPosition);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        System.out.println("[PixelBlockEntity] getUpdatePacket envoyé pour " + worldPosition);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        System.out.println("[PixelBlockEntity] onDataPacket reçu pour " + worldPosition);
        this.load(pkt.getTag());
        shapeDirty = true;
        System.out.println("[PixelBlockEntity] shapeDirty=true (onDataPacket) à " + worldPosition);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        System.out.println("[PixelBlockEntity] Chargement NBT côté " + (level != null && level.isClientSide ? "client" : "serveur") + " pour " + worldPosition);
        if (tag.contains("pixels")) {
            long[] arr = tag.getLongArray("pixels");
            for (int i = 0; i < arr.length && i < 4096; i++) {
                int x = (i >> 8) & 15;
                int y = (i >> 4) & 15;
                int z = i & 15;
                pixels[x][y][z] = (arr[i] != 0);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        long[] arr = new long[4096];
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 16; y++)
                for (int z = 0; z < 16; z++)
                    arr[(x << 8) | (y << 4) | z] = pixels[x][y][z] ? 1L : 0L;
        tag.putLongArray("pixels", arr);
    }

    // Ajout d'une méthode pour exposer l'état des pixels côté client
    public boolean[][][] getPixels() {
        return pixels;
    }

    // Vide tous les pixels du bloc
    public void removeAllPixels() {
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 16; y++)
                for (int z = 0; z < 16; z++)
                    pixels[x][y][z] = false;
        setChanged();
        shapeDirty = true;
        System.out.println("[PixelBlockEntity] Tous les pixels retirés à " + worldPosition);
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}
