package fr.lunix.bettermc.entity;

import fr.lunix.bettermc.BetterMC;
import fr.lunix.bettermc.entity.BetterSalmon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobs {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BetterMC.MODID);

    public static final RegistryObject<EntityType<BetterSalmon>> BETTER_SALMON = ENTITIES.register("better_salmon",
            () -> EntityType.Builder.of(BetterSalmon::new, MobCategory.WATER_CREATURE)
                    .sized(0.7F, 0.4F)
                    .build("better_salmon"));
}

