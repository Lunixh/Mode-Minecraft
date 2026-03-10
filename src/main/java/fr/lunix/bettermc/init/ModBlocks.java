package fr.lunix.bettermc.init;

import fr.lunix.bettermc.block.RockBlock;
import fr.lunix.bettermc.block.PixelBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "bettermc");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "bettermc");

    public static final RegistryObject<Block> ROCK = BLOCKS.register("rock", RockBlock::new);
    public static final RegistryObject<Item> ROCK_ITEM = ITEMS.register("rock", () -> new BlockItem(ROCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Block> PIXEL_BLOCK = BLOCKS.register("pixel_block", PixelBlock::new);
    public static final RegistryObject<Item> PIXEL_BLOCK_ITEM = ITEMS.register("pixel_block", () -> new BlockItem(PIXEL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}

