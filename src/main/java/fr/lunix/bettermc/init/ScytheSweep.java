package fr.lunix.bettermc.init;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.Event;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.server.ServerLifecycleHooks;


import javax.annotation.Nullable;

import java.util.Collection;
import java.util.Random;


@Mod.EventBusSubscriber
public class ScytheSweep {
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player entity = event.getEntity();
        if (event.getHand() != entity.getUsedItemHand())
            return;
        execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), entity);
    }
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        execute(null, world, x, y, z, entity);
    }


    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        boolean found = false;
        int wheatAge = 0;
        int netherWartAge = 0;
        double sx = 0;
        double sy = 0;
        double sz = 0;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.DIAMOND_SCYTHE.get()
                || (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.WOODEN_SCYTHE.get()
                || (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.STONE_SCYTHE.get()
                || (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.IRON_SCYTHE.get()
                || (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.GOLDEN_SCYTHE.get())
        {
            sx = -3;
            found = false;
            for (int index0 = 0; index0 < (int) (6); index0++) {
                sy = -3;
                for (int index1 = 0; index1 < (int) (6); index1++) {
                    sz = -3;
                    for (int index2 = 0; index2 < (int) (6); index2++) {
                        if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == Blocks.GRASS
                                || (world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == Blocks.TALL_GRASS
                                || (world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == Blocks.FERN) {
                            found = true;
                            if (world instanceof Level) {
                                Block.dropResources(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (Level) world,
                                        new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)));
                                world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
                            }
                        }
                        else if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == Blocks.WHEAT)
                        {
                            wheatAge = Integer.parseInt(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))).getValues().values().toArray()[0].toString());
                            if (wheatAge >= 4){
                                found = true;
                                if (world instanceof Level) {
                                    Block.dropResources(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (Level) world,
                                            new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)));
                                    world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
                                }
                            }
                        }
                        else if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == Blocks.NETHER_WART)
                        {
                            netherWartAge = Integer.parseInt(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))).getValues().values().toArray()[0].toString());
                            if (netherWartAge == 3){
                                found = true;
                                if (world instanceof Level) {
                                    Block.dropResources(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (Level) world,
                                            new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)));
                                    world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
                                }
                            }
                        }
                        sz = sz + 1;
                    }
                    sy = sy + 1;
                }
                sx = sx + 1;
            }
            if (found == true) {
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.SWEEP_ATTACK,
                            (entity.level
                                    .clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(1)),
                                            ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
                                    .getBlockPos().getX()),
                            (entity.getY() + 1),
                            (entity.level
                                    .clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(1)),
                                            ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
                                    .getBlockPos().getZ()),
                            1, 0, 0, 0, 0);
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
                                ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, 1, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")),
                                SoundSource.NEUTRAL, 1, 1, false);
                    }
                }
                if (new Object() {
                    public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                            return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                        } else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
                            return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
                                    .getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
                        }
                        return false;
                    }
                }.checkGamemode(entity)) {
                    {
                        ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
                        if (_ist.hurt(1, new RandomSource() {
                            @Override
                            public RandomSource fork() {
                                return null;
                            }

                            @Override
                            public PositionalRandomFactory forkPositional() {
                                return null;
                            }

                            @Override
                            public void setSeed(long p_216342_) {

                            }

                            @Override
                            public int nextInt() {
                                return 0;
                            }

                            @Override
                            public int nextInt(int p_216331_) {
                                return 0;
                            }

                            @Override
                            public long nextLong() {
                                return 0;
                            }

                            @Override
                            public boolean nextBoolean() {
                                return false;
                            }

                            @Override
                            public float nextFloat() {
                                return 0;
                            }

                            @Override
                            public double nextDouble() {
                                return 0;
                            }

                            @Override
                            public double nextGaussian() {
                                return 0;
                            }
                        }, null)) {
                            _ist.shrink(1);
                            _ist.setDamageValue(0);
                        }
                    }
                }
            }
        }
    }
}