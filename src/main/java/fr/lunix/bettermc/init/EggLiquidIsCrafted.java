package fr.lunix.bettermc.init;

/* imports omitted */

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EggLiquidIsCrafted {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Entity entity = event.getEntity();
        execute(event, event.getEntity(), event.getCrafting().getItem());
    }

    public static void execute(Entity entity) {
        execute(null, entity, null);
    }

    private static void execute(@Nullable Event event, Entity entity, Item itemCrafted) {
        Item eggLiquid = ModItems.EGG_LIQUID.get();
        if (entity == null)
            return;
        if (entity instanceof Player _player && itemCrafted == eggLiquid) {
                ItemStack _setstack = new ItemStack(ModItems.EGGSHELL.get());
                _setstack.setCount(1);
                ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
        }
        else return;
    }
}