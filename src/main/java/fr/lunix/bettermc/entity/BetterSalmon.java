package fr.lunix.bettermc.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;
import net.minecraft.world.level.Level;
    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;

    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;
import java.lang.reflect.Method;
    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;
import java.util.EnumSet;
    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;
import java.util.List;
    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;

    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;
public class BetterSalmon extends Salmon {
    @ObjectHolder(registryName = "", value = "bettermc:better_salmon")
    public static final EntityType<BetterSalmon> TYPE = null;

    public BetterSalmon(EntityType<? extends BetterSalmon> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimToBobberGoal(this));
    }

    static class SwimToBobberGoal extends Goal {
        private final BetterSalmon salmon;
        private FishingHook targetBobber;

        public SwimToBobberGoal(BetterSalmon salmon) {
            this.salmon = salmon;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            // Recherche tous les bouchons dans un rayon de 16 blocs autour du saumon
            List<FishingHook> bobbers = salmon.level.getEntitiesOfClass(FishingHook.class, salmon.getBoundingBox().inflate(16));
            for (FishingHook bobber : bobbers) {
                if (bobber.isAlive() && bobber.getOwner() instanceof Player) {
                    // Détection d'une "zone d'intérêt" autour du bouchon (ex: 5 blocs)
                    if (bobber.getBoundingBox().inflate(5).intersects(salmon.getBoundingBox())) {
                        targetBobber = bobber;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void start() {
            // rien à faire ici
        }

        @Override
        public void stop() {
            targetBobber = null;
        }

        @Override
        public void tick() {
            if (targetBobber != null && targetBobber.isAlive()) {
                salmon.getNavigation().moveTo(targetBobber.getX(), targetBobber.getY(), targetBobber.getZ(), 1.2);
                // Si le saumon est très proche du bouchon, il se fait "piquer" via collision simulée
                if (salmon.distanceTo(targetBobber) < 1.0F) {
                    try {
                        Method onHitEntity = FishingHook.class.getDeclaredMethod("onHitEntity", EntityHitResult.class);
                        onHitEntity.setAccessible(true);
                        EntityHitResult hitResult = new EntityHitResult(salmon);
                        onHitEntity.invoke(targetBobber, hitResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
