package fr.lunix.bettermc.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class RockBlock extends Block {
    private static final VoxelShape ROCK_SHAPE = Shapes.box(2/16.0, 0, 2/16.0, 14/16.0, 8/16.0, 14/16.0);

    public RockBlock() {
        super(BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE).strength(1.5f, 6.0f));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return ROCK_SHAPE;
    }
}
