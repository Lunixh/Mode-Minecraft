/*package fr.lunix.bettermc.init;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        System.out.println("ENTREE 1");
        if (!level.isClientSide()) {
            System.out.println("ENTREE 2");
            Player player = event.getEntity();
            BlockPos pos = event.getPos();

            // Vérifier que le joueur a les mains vides et qu'il ne soulève pas déjà un bloc
            if (player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty() && !liftedBlocks.containsKey(player.getUUID())) {
                System.out.println("ENTREE 3");
                liftBlock(player, level, pos);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.isUsingItem())
        {
            System.out.println("ENTREE isUsingItem");
        }
        if (event.player.isUsingItem() && event.player.getUsedItemHand() == InteractionHand.MAIN_HAND)
        {
            System.out.println("ENTREE isUsingItem et autres");
        }
        if (event.phase == TickEvent.Phase.END && !event.player.level.isClientSide()) {
            UUID playerUUID = event.player.getUUID();
            if (liftedBlocks.containsKey(playerUUID)) {
                System.out.println("ENTREE 6");
                updateLiftedBlockPosition(event.player);
            }
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
        System.out.println("ENTREE 11");
        FallingBlockEntity fallingBlock = liftedBlocks.get(player.getUUID());
        if (fallingBlock != null && !fallingBlock.isRemoved()) { // Vérifie si l'entité existe toujours
            System.out.println("ENTREE 12");
            Vec3 lookVec = player.getLookAngle();
            double distance = 2.0; // Distance devant le joueur
            double x = player.getX() + lookVec.x * distance;
            double y = player.getY() + player.getEyeHeight() + lookVec.y * distance;
            double z = player.getZ() + lookVec.z * distance;

            // Met à jour la position de l'entité pour qu'elle suive le curseur du joueur
            fallingBlock.setPos(x, y, z);
            fallingBlock.setDeltaMovement(Vec3.ZERO); // Empêche tout mouvement involontaire
        }
    }

    private static void dropLiftedBlock(Player player) {
        System.out.println("ENTREE 13");
        FallingBlockEntity fallingBlock = liftedBlocks.remove(player.getUUID());
        if (fallingBlock != null && !fallingBlock.isRemoved()) { // Vérifie si l'entité existe toujours
            System.out.println("ENTREE 14");
            fallingBlock.setNoGravity(false); // Réactive la gravité
            fallingBlock.setInvulnerable(false); // Rend l'entité vulnérable à nouveau
        }
    }
}*/
