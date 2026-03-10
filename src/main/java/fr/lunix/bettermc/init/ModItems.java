package fr.lunix.bettermc.init;

import fr.lunix.bettermc.BetterMC;
import net.minecraft.world.entity.schedule.Timeline;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Items.WOODEN_SWORD;


public class ModItems
{

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterMC.MODID);
    public static final RegistryObject<Item> EGG_LIQUID = ITEMS.register("egg_liquid", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(64).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.9F).alwaysEat()
            .meat().build())));
    public static final RegistryObject<Item> OMELETTE = ITEMS.register("omelette", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(64).food(new FoodProperties.Builder().nutrition(5).saturationMod(5.6F).alwaysEat()
            .meat().build())));
    public static final RegistryObject<Item> EGGSHELL = ITEMS.register("eggshell", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(64)));

    public static final RegistryObject<Item> WOODEN_SCYTHE = ITEMS.register("wooden_scythe", () -> new ScytheItem(Tiers.WOOD, 1, -1.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> STONE_SCYTHE = ITEMS.register("stone_scythe", () -> new ScytheItem(Tiers.STONE, 4, -1.6F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> IRON_SCYTHE = ITEMS.register("iron_scythe", () -> new ScytheItem(Tiers.IRON, 6, -2.7F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> GOLDEN_SCYTHE = ITEMS.register("golden_scythe", () -> new ScytheItem(Tiers.GOLD, 5, -3.5F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> DIAMOND_SCYTHE = ITEMS.register("diamond_scythe", () -> new ScytheItem(Tiers.DIAMOND, 7, -2.2F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
}
