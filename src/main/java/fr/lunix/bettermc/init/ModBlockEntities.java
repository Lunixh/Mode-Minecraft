package fr.lunix.bettermc.init;

import fr.lunix.bettermc.blockentity.PixelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterMC.MODID);

    public static final RegistryObject<BlockEntityType<PixelBlockEntity>> PIXEL_BLOCK = BLOCK_ENTITIES.register(
    public static final RegistryObject<BlockEntityType<PixelBlockEntity>> PIXEL_BLOCK = BLOCK_ENTITIES.register(
            "pixel_block",
            () -> BlockEntityType.Builder.of(PixelBlockEntity::new, ModBlocks.PIXEL_BLOCK.get()).build(null)
}

