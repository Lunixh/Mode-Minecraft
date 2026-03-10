/*package fr.lunix.bettermc.init;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

public class SalmonInterestFishingBobber {
    @Mod.EventBusSubscriber
    public class SalmonInterestFishingBobberProcedure {
        @SubscribeEvent
        public static void onEntityJoin(EntityJoinWorldEvent event) {
            Entity entity = event.getEntity();
            execute(event, entity);
        }

        public static void execute(Entity entity) {
            execute(null, entity);
        }

        private static void execute(@Nullable Event event, Entity entity) {
            if (entity == null)
                return;
            if (entity instanceof Salmon) {
                ((Salmon) entity).targetSelector.addGoal(1,
                        new NearestAttackableTargetGoal((Salmon) entity, FishingHook.class, false, false));
            }
        }
    }
}*/
