package fr.lunix.bettermc.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import fr.lunix.bettermc.blockentity.PixelBlockEntity;
import fr.lunix.bettermc.init.ModBlockEntities;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import fr.lunix.bettermc.client.model.PixelBlockBakedModel;
import net.minecraft.world.level.block.EntityBlock;

public class PixelBlock extends Block implements EntityBlock {
    public PixelBlock() {
        super(BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE).strength(1.5f, 6.0f));
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        System.out.println("[PixelBlock] Création d'un PixelBlockEntity à la position " + pos);
        return new PixelBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        System.out.println("[PixelBlock] Clic droit détecté sur PixelBlock à la position " + pos);
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PixelBlockEntity pixelBe) {
                net.minecraft.world.item.ItemStack held = player.getItemInHand(hand);
                boolean isPixel = held.getItem() == fr.lunix.bettermc.init.ModBlocks.PIXEL_ITEM.get();
                double ox = player.getX() - pos.getX() + player.getEyePosition().x - player.getX();
                double oy = player.getEyeY() - pos.getY();
                double oz = player.getZ() - pos.getZ() + player.getEyePosition().z - player.getZ();
                double dx = hit.getLocation().x - player.getEyePosition().x;
                double dy = hit.getLocation().y - player.getEyePosition().y;
                double dz = hit.getLocation().z - player.getEyePosition().z;
                double len = Math.sqrt(dx*dx + dy*dy + dz*dz);
                if (len == 0) len = 1;
                dx /= len; dy /= len; dz /= len;
                boolean found = false;
                int px = 0, py = 0, pz = 0;
                if (isPixel) {
                    for (double t = 0; t < 1.5; t += 0.01) {
                        double bx = ox + dx * t;
                        double by = oy + dy * t;
                        double bz = oz + dz * t;
                        int ix = (int)(bx * 16);
                        int iy = (int)(by * 16);
                        int iz = (int)(bz * 16);
                        if (ix < 0 || ix > 15 || iy < 0 || iy > 15 || iz < 0 || iz > 15) continue;
                        if (pixelBe.hasPixel(ix, iy, iz)) {
                            px = ix; py = iy; pz = iz;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        int nx = px + hit.getDirection().getStepX();
                        int ny = py + hit.getDirection().getStepY();
                        int nz = pz + hit.getDirection().getStepZ();
                        if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && nz >= 0 && nz < 16) {
                            if (!pixelBe.hasPixel(nx, ny, nz)) {
                                System.out.println("[PixelBlock] Pixel ajouté (face) : (" + nx + ", " + ny + ", " + nz + ")");
                                pixelBe.addPixel(nx, ny, nz);
                                level.sendBlockUpdated(pos, state, state, 3);
                                if (!player.isCreative()) {
                                    held.shrink(1);
                                }
                            } else {
                                System.out.println("[PixelBlock] Impossible d'ajouter le pixel (déjà présent).");
                            }
                        } else {
                            // Hors limites : vérifier si un PixelBlock adjacent existe
                            BlockPos adjPos = pos.offset(hit.getDirection().getStepX(), hit.getDirection().getStepY(), hit.getDirection().getStepZ());
                            BlockState adjState = level.getBlockState(adjPos);
                            BlockEntity adjBe = level.getBlockEntity(adjPos);
                            if (adjState.getBlock() == fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get() && adjBe instanceof PixelBlockEntity adjPixelBe) {
                                int newX = nx, newY = ny, newZ = nz;
                                if (newX < 0) newX = 15; else if (newX > 15) newX = 0;
                                if (newY < 0) newY = 15; else if (newY > 15) newY = 0;
                                if (newZ < 0) newZ = 15; else if (newZ > 15) newZ = 0;
                                if (!adjPixelBe.hasPixel(newX, newY, newZ)) {
                                    adjPixelBe.addPixel(newX, newY, newZ);
                                    level.sendBlockUpdated(adjPos, adjState, adjState, 3);
                                    System.out.println("[PixelBlock] Pixel ajouté dans le bloc adjacent à " + adjPos + " en (" + newX + ", " + newY + ", " + newZ + ")");
                                    if (!player.isCreative()) {
                                        held.shrink(1);
                                    }
                                } else {
                                    System.out.println("[PixelBlock] Impossible d'ajouter le pixel dans le bloc adjacent (déjà présent).");
                                }
                            } else if (level.isEmptyBlock(adjPos)) {
                                // Ancien comportement : créer un nouveau PixelBlock
                                level.setBlock(adjPos, fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get().defaultBlockState(), 3);
                                BlockEntity newBe = level.getBlockEntity(adjPos);
                                if (newBe instanceof PixelBlockEntity newPixelBe) {
                                    int newX = nx, newY = ny, newZ = nz;
                                    if (newX < 0) newX = 15; else if (newX > 15) newX = 0;
                                    if (newY < 0) newY = 15; else if (newY > 15) newY = 0;
                                    if (newZ < 0) newZ = 15; else if (newZ > 15) newZ = 0;
                                    newPixelBe.removeAllPixels();
                                    newPixelBe.addPixel(newX, newY, newZ);
                                    System.out.println("[PixelBlock] Nouveau PixelBlock créé à " + adjPos + " avec pixel (" + newX + ", " + newY + ", " + newZ + ")");
                                    if (!player.isCreative()) {
                                        held.shrink(1);
                                    }
                                }
                            } else {
                                System.out.println("[PixelBlock] Impossible de placer un nouveau PixelBlock : bloc non vide.");
                            }
                        }
                    } else {
                        System.out.println("[PixelBlock] Aucun pixel solide trouvé pour placer sur la face.");
                    }
                } else {
                    // Suppression : trouver le premier pixel non vide
                    for (double t = 0; t < 1.5; t += 0.01) {
                        double bx = ox + dx * t;
                        double by = oy + dy * t;
                        double bz = oz + dz * t;
                        int ix = (int)(bx * 16);
                        int iy = (int)(by * 16);
                        int iz = (int)(bz * 16);
                        if (ix < 0 || ix > 15 || iy < 0 || iy > 15 || iz < 0 || iz > 15) continue;
                        if (pixelBe.hasPixel(ix, iy, iz)) {
                            px = ix; py = iy; pz = iz;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.out.println("[PixelBlock] Pixel ciblé (raycast) : (" + px + ", " + py + ", " + pz + ")");
                        if (pixelBe.removePixel(px, py, pz)) {
                            System.out.println("[PixelBlock] Pixel retiré !");
                            level.sendBlockUpdated(pos, state, state, 3);
                        } else {
                            System.out.println("[PixelBlock] Pixel déjà vide ou hors limites.");
                        }
                    } else {
                        System.out.println("[PixelBlock] Aucun pixel trouvé sur le rayon.");
                    }
                }
            } else {
                System.out.println("[PixelBlock] BlockEntity n'est pas un PixelBlockEntity !");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter world, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof PixelBlockEntity pixelBe) {
            return pixelBe.getShape();
        }
        return super.getShape(state, world, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.level.BlockGetter world, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        // Collision = même forme que le rendu
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof PixelBlockEntity pixelBe) {
            return pixelBe.getShape();
        }
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, net.minecraft.world.level.BlockGetter world, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        // Sélection = même forme que le rendu
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof PixelBlockEntity pixelBe) {
            return pixelBe.getShape();
        }
        return super.getVisualShape(state, world, pos, context);
    }

    public boolean hasBlockEntity(BlockState state) {
        return true;
    }
}
