/*package fr.lunix.bettermc.init;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "bettermc") // Remplacez "bettermc" par l'ID de votre mod
public class BlockLiftHandler {

    private static final Map<UUID, FallingBlockEntity> liftedBlocks = new HashMap<>();
    private static int lifted = 0;

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        lifted++;
        System.out.println("lifted"+lifted);
        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();
        net.minecraft.world.item.ItemStack held = player.getItemInHand(hand);
        // Ajout pixel global : clic droit avec un Pixel
        if (!level.isClientSide && held.getItem() == fr.lunix.bettermc.init.ModBlocks.PIXEL_ITEM.get()) {
            BlockPos adjPos = pos.relative(event.getFace());
            BlockState adjState = level.getBlockState(adjPos);
            BlockEntity adjBe = level.getBlockEntity(adjPos);
            int nx = 0, ny = 0, nz = 0;
            boolean pixelPlaced = false;
            // Calculer la position du pixel à placer dans le bloc adjacent
            if (adjState.getBlock() == fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get() && adjBe instanceof fr.lunix.bettermc.blockentity.PixelBlockEntity adjPixelBe) {
                if (event.getFace().getStepX() != 0) nx = (event.getFace().getStepX() > 0) ? 0 : 15;
                else nx = (int)((event.getHitVec().x - adjPos.getX()) * 16);
                if (event.getFace().getStepY() != 0) ny = (event.getFace().getStepY() > 0) ? 0 : 15;
                else ny = (int)((event.getHitVec().y - adjPos.getY()) * 16);
                if (event.getFace().getStepZ() != 0) nz = (event.getFace().getStepZ() > 0) ? 0 : 15;
                else nz = (int)((event.getHitVec().z - adjPos.getZ()) * 16);
                nx = Math.max(0, Math.min(15, nx));
                ny = Math.max(0, Math.min(15, ny));
                nz = Math.max(0, Math.min(15, nz));
                if (!adjPixelBe.hasPixel(nx, ny, nz)) {
                    adjPixelBe.addPixel(nx, ny, nz);
                    level.sendBlockUpdated(adjPos, adjState, adjState, 3);
                    System.out.println("[GlobalPixelPlace] Pixel ajouté dans le bloc adjacent à " + adjPos + " en (" + nx + ", " + ny + ", " + nz + ")");
                    pixelPlaced = true;
                }
            } else if (level.isEmptyBlock(adjPos)) {
                // Créer un nouveau PixelBlock à la position adjacente
                level.setBlock(adjPos, fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get().defaultBlockState(), 3);
                BlockEntity newBe = level.getBlockEntity(adjPos);
                if (newBe instanceof fr.lunix.bettermc.blockentity.PixelBlockEntity newPixelBe) {
                    if (event.getFace().getStepX() != 0) nx = (event.getFace().getStepX() > 0) ? 0 : 15;
                    else nx = (int)((event.getHitVec().x - adjPos.getX()) * 16);
                    if (event.getFace().getStepY() != 0) ny = (event.getFace().getStepY() > 0) ? 0 : 15;
                    else ny = (int)((event.getHitVec().y - adjPos.getY()) * 16);
                    if (event.getFace().getStepZ() != 0) nz = (event.getFace().getStepZ() > 0) ? 0 : 15;
                    else nz = (int)((event.getHitVec().z - adjPos.getZ()) * 16);
                    nx = Math.max(0, Math.min(15, nx));
                    ny = Math.max(0, Math.min(15, ny));
                    nz = Math.max(0, Math.min(15, nz));
                    newPixelBe.removeAllPixels();
                    newPixelBe.addPixel(nx, ny, nz);
                    level.sendBlockUpdated(adjPos, fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get().defaultBlockState(), fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get().defaultBlockState(), 3);
                    System.out.println("[GlobalPixelPlace] Nouveau PixelBlock créé à " + adjPos + " avec pixel (" + nx + ", " + ny + ", " + nz + ")");
                    pixelPlaced = true;
                }
            }
            // Toujours annuler l'event si on a placé un pixel ou tenté de placer (pour empêcher le placement du bloc Pixel vanilla)
            if (pixelPlaced || adjState.getBlock() != fr.lunix.bettermc.init.ModBlocks.PIXEL_BLOCK.get()) {
                if (pixelPlaced && !player.isCreative()) {
                    held.shrink(1);
                }
                event.setCanceled(true);
                event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
            }
        }
        else if (!level.isClientSide() && lifted == 4) {
            //System.out.println("ENTREE 2");
            //Player player = event.getEntity();
            //BlockPos pos = event.getPos();

            // Vérifier que le joueur a les mains vides et qu'il ne soulève pas déjà un bloc
            if (player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty() && !liftedBlocks.containsKey(player.getUUID())) {
                //System.out.println("ENTREE 3");
                liftBlock(player, level, pos);
                event.setCanceled(true);
            }
        }
        else if (!level.isClientSide() && lifted == 8) {
            lifted = 0;
            dropLiftedBlock(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        FallingBlockEntity fallingBlock = liftedBlocks.get(event.player.getUUID());
        if (fallingBlock != null && !fallingBlock.isRemoved()) {
            Vec3 lookVec = event.player.getViewVector(1.0F);
            double distance = 2.0;
            double x = event.player.getX() + lookVec.x * distance;
            double y = event.player.getY() + event.player.getEyeHeight() + lookVec.y * distance;
            double z = event.player.getZ() + lookVec.z * distance;

            // Ajouter une petite variation pour forcer la mise à jour
            double variation = 0.001 * Math.sin(event.player.tickCount * 0.5);
            x += variation;
            y += variation;
            z += variation;

            //System.out.println("x: " + x + ", y: " + y + ", z: " + z);
            //System.out.println("position : "+ player.getPosition(1.0F));
            fallingBlock.setPos(x, y, z);
            fallingBlock.setDeltaMovement(Vec3.ZERO);
        }
    }

    private static void liftBlock(Player player, Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        System.out.println("ENTREE 9");
        if (!state.isAir()) {
            System.out.println("ENTREE 10");
            // Créer une entité FallingBlock et la configurer
            FallingBlockEntity fallingBlock = FallingBlockEntity.fall(level, pos, state);
            fallingBlock.setNoGravity(true); // Empêche la gravité
            fallingBlock.setInvulnerable(true); // Protège l'entité
            level.removeBlock(pos, false); // Supprime le bloc du monde
            level.addFreshEntity(fallingBlock); // Ajoute l'entité au monde
            liftedBlocks.put(player.getUUID(), fallingBlock); // Associe l'entité au joueur
        }
    }

    private static void updateLiftedBlockPosition(Player player) {

    }


    public static void dropLiftedBlock(Player player) {
        System.out.println("ENTREE 13");
        FallingBlockEntity fallingBlock = liftedBlocks.remove(player.getUUID());
        if (fallingBlock != null && !fallingBlock.isRemoved()) { // Vérifie si l'entité existe toujours
            System.out.println("ENTREE 14");
            fallingBlock.remove(Entity.RemovalReason.DISCARDED); // Supprime l'entité

            // Obtient la position du bloc pointé par le joueur
            HitResult hitResult = player.pick(5, 0, false); // Ajustez la portée (5) si nécessaire
            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult; // Cast HitResult en BlockHitResult
                BlockPos targetPos = blockHitResult.getBlockPos().above(); // Obtient la position au-dessus du bloc pointé

                // Récupère l'état du bloc qui était porté
                BlockState blockState = fallingBlock.getBlockState();

                // Place le bloc à la nouvelle position
                player.level.setBlock(targetPos, blockState, 3); // Met à jour le monde avec le nouveau bloc
                System.out.println("Bloc placé à la position: " + targetPos);
            } else {
                System.out.println("Aucun bloc pointé, le bloc a été supprimé.");
            }
        } else {
            System.out.println("fallingBlock était null ou a déjà été supprimé.");
        }
    }
}*/
