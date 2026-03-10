package fr.lunix.bettermc.init;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraft.world.level.LevelAccessor;


import javax.annotation.Nullable;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

@Mod.EventBusSubscriber
public class EggHitGround {
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Entity entity = event.getProjectile();
        execute(event, entity);
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof ThrownEgg _tEgg) {
            executec(_tEgg.level, _tEgg.xo, _tEgg.yo, _tEgg.zo);
        }

    }

    public static void executec(LevelAccessor world, double x, double y, double z) {
        if (world instanceof Level _level && !_level.isClientSide()) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(ModItems.EGG_LIQUID.get()));
            entityToSpawn.setPickUpDelay(10);
            entityToSpawn.setUnlimitedLifetime();
            _level.addFreshEntity(entityToSpawn);
            ItemEntity entityToSpawnEggShell = new ItemEntity(_level, x, y, z, new ItemStack(ModItems.EGGSHELL.get()));
            entityToSpawnEggShell.setPickUpDelay(10);
            entityToSpawnEggShell.setUnlimitedLifetime();
            _level.addFreshEntity(entityToSpawnEggShell);
        }
    }
}