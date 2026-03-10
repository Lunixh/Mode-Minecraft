package fr.lunix.bettermc.init;
/* imports omitted */

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import static org.openjdk.nashorn.internal.objects.Global.print;

@Mod.EventBusSubscriber
public class WeightConsequenceProcedure {
    final public static double playerNakedMoveSpeed = 0.1;
    final public static double playerWeight = 84.375;
    public static double playerStrength = 1;
    final public static double FLOWER_POTWeight = 0.01;
    final public static double SANDWeight = 1600;
    final public static double STONEWeight = 1000;
    final public static double ARROWWeight = 0.125;
    final public static double IRON_HELMETWeight = 3;
    final public static double IRON_LEGGINGSWeight = 6;
    final public static double IRON_BOOTSWeight = 2;
    final public static double IRON_CHESTPLATEWeight = 9;
    final public static double GRANITEWeight = 2600;
    final public static double POLISHED_GRANITEWeight = 2590;
    final public static double DIORITEWeight = 1500;
    final public static double POLISHED_DIORITEWeight = 1490;
    final public static double ANDESITEWeight = 2600;
    final public static double POLISHED_ANDESITEWeight = 2590;
    final public static double DEEPLATEWeight = 1100;
    final public static double COBBLE_DEEPLATEWeight = 1090;
    final public static double POLISHED_DEEPLATEWeight = 1090;
    final public static double CALSITEWeight = 2700;
    final public static double TUFFWeight = 2000; //???
    final public static double DRIPSTONEWeight = 2800;
    final public static double GRASS_BLOCKWeight = 1300; //???
    final public static double DIRTWeight = 1300;
    final public static double COARSE_DIRTWeight = 1300;
    final public static double PODZOLWeight = 0; //???
    final public static double ROOTED_DIRTWeight = 1300; //???
    final public static double CRIMSON_NYLIUMWeight = 1300; //???
    final public static double WARPED_DIRTWeight = 1300; //???
    final public static double COBBLESTONEWeight = 968; //???
    final public static double OAK_PLANKSWeight = 669.413919414;
    final public static double SPRUCE_PLANKSWeight = 600;
    final public static double BIRCH_PLANKSWeight = 631.25;
    final public static double JUNGLE_PLANKSWeight = 540;
    final public static double ACACIA_PLANKSWeight = 750;
    final public static double DARK_OAK_PLANKSWeight = 750;
    final public static double MANGROVE_PLANKSWeight = 1060;


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player);
        }

    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;

        double playerMoveSpeed = 0;
        double totalWeight = playerWeight;


            if(((Player) entity).getInventory().contains(new ItemStack(Blocks.COMMAND_BLOCK)))
            {
                ((Player) entity).getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(playerNakedMoveSpeed);
            }
            else
            {
                double i = 0;
                double itemNum = 0;

                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.DEEPSLATE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.DEEPSLATE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + DEEPLATEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.POLISHED_ANDESITE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.POLISHED_ANDESITE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + POLISHED_ANDESITEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.ANDESITE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.ANDESITE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + ANDESITEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.POLISHED_DIORITE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.POLISHED_DIORITE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + POLISHED_DIORITEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.DIORITE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.DIORITE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + DIORITEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.POLISHED_GRANITE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.POLISHED_GRANITE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + POLISHED_GRANITEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.GRANITE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.GRANITE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + GRANITEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.IRON_BOOTS)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.IRON_BOOTS == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + IRON_BOOTSWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.IRON_LEGGINGS)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.IRON_LEGGINGS == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + IRON_LEGGINGSWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.IRON_CHESTPLATE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.IRON_CHESTPLATE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + IRON_CHESTPLATEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.IRON_HELMET)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.IRON_HELMET == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + IRON_HELMETWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Blocks.STONE)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.STONE == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + STONEWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Blocks.SAND)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.SAND == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + SANDWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Blocks.FLOWER_POT)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.FLOWER_POT == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + FLOWER_POTWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Blocks.DIRT)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.DIRT == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + DIRTWeight*itemNum;
                }
                if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.ARROW)) : false) {
                    i = 0;
                    itemNum = 0;
                    for (int index0 = 0; index0 < (int) (36); index0++) {
                        if (Items.ARROW == (new Object() {
                            public ItemStack getItemStack(int sltid, Entity entity) {
                                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                    _retval.set(capability.getStackInSlot(sltid).copy());
                                });
                                return _retval.get();
                            }
                        }.getItemStack((int) i, entity)).getItem()) {
                            itemNum = itemNum + ((new Object() {
                                public ItemStack getItemStack(int sltid, Entity entity) {
                                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                                        _retval.set(capability.getStackInSlot(sltid).copy());
                                    });
                                    return _retval.get();
                                }
                            }.getItemStack((int) i, entity))).getCount();
                        }
                        i = i + 1;
                    }
                    totalWeight = totalWeight + ARROWWeight*itemNum;
                }

                playerMoveSpeed = (playerNakedMoveSpeed*playerStrength)-((totalWeight/playerWeight*playerNakedMoveSpeed)-playerNakedMoveSpeed);
                ((Player) entity).getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(playerMoveSpeed);
            }
    }
}