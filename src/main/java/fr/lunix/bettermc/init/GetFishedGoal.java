/*package fr.lunix.bettermc.init;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.FishingHook;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GetFishedGoal extends Goal {
    protected final PathfinderMob mob;
    private final BetterSalmon bSalmon;
    private FishingHook fishinghook;
    private final Level level;
    public GetFishedGoal(BetterSalmon p_25063_, PathfinderMob p_25734_){
        this.bSalmon = p_25063_;
        this.mob = p_25734_;
        this.level = p_25063_.level;
    }
    public boolean canUse() {
        this.player = this.level.getNearestPlayer(this.begTargeting, this.wolf);
        return this.player == null ? false : this.playerHoldingInteresting(this.player);
    }
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone() && !this.mob.isVehicle();
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }

    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }

    public void trigger() {
        this.forceTrigger = true;
    }
}*/
