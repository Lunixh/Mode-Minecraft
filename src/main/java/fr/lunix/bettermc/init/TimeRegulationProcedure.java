package fr.lunix.bettermc.init;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.GameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TimeRegulationProcedure {

    private static final int TIME_SCALE = 72; // Facteur de ralentissement normal
    private static final int TICKS_FOR_SLEEP_CYCLE = 100; // 5 secondes = 100 ticks (20 ticks/seconde)
    private static int tickCounter = 0;
    private static boolean initialized = false;
    private static boolean isSleeping = false;
    private static long sleepStartTime = 0; // Temps au début du sommeil

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.getServer().overworld());
        }
    }

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event) {
        isSleeping = true;
        tickCounter = 0; // Réinitialiser le compteur quand le joueur commence à dormir
        sleepStartTime = event.getEntity().level.getDayTime(); // Enregistrer l'heure actuelle
    }

    public static void execute(Level world) {
        execute(null, world);
    }

    private static void execute(@Nullable Event event, Level world) {
        if (world instanceof ServerLevel serverWorld) {
            if (!initialized) {
                serverWorld.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, serverWorld.getServer());
                initialized = true;
                System.out.println("doDaylightCycle désactivé");
            }

            tickCounter++;
            long currentTime = serverWorld.getDayTime();
            long newTime = currentTime;

            if (isSleeping) {
                // Calculer combien de ticks il reste pour atteindre le matin
                long ticksLeftToMorning = 24000 - sleepStartTime;

                // Avancer le temps progressivement sur TICKS_FOR_SLEEP_CYCLE ticks
                long timeIncrementPerTick = Math.max(1, ticksLeftToMorning / TICKS_FOR_SLEEP_CYCLE);

                if (tickCounter <= TICKS_FOR_SLEEP_CYCLE && currentTime < 24000) {
                    newTime = Math.min(currentTime + timeIncrementPerTick, 23999); // Avancer le temps
                    serverWorld.setDayTime(newTime);
                } else {
                    isSleeping = false; // Fin du sommeil
                    System.out.println("Sommeil terminé, retour à la vitesse normale");
                }
            } else if (tickCounter >= TIME_SCALE) {
                tickCounter = 0;
                newTime = (currentTime + 1) % 24000; // Avancer normalement
                serverWorld.setDayTime(newTime);
            }

            if (newTime != currentTime) {
                System.out.println("Temps actuel : " + newTime);
            }
        }
    }
}