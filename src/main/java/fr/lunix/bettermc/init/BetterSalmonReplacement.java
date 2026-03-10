package fr.lunix.bettermc.init;

import fr.lunix.bettermc.entity.BetterSalmon;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BetterSalmonReplacement {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = (Level) event.getLevel();
        if (entity instanceof Salmon && !(entity instanceof fr.lunix.bettermc.entity.BetterSalmon)) {
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            BetterSalmon betterSalmon = new BetterSalmon(fr.lunix.bettermc.entity.BetterSalmon.TYPE, level);
            level.addFreshEntity(betterSalmon);
        }
    }
}
