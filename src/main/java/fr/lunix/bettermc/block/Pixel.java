package fr.lunix.bettermc.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class Pixel extends Block {
    private static final VoxelShape PIXEL_SHAPE = Shapes.box(
            7.5/16.0, 7.5/16.0, 7.5/16.0,
            8.5/16.0, 8.5/16.0, 8.5/16.0
    );

    public Pixel() {
        super(BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE).strength(1.0f, 3.0f));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return PIXEL_SHAPE;
    }
}
