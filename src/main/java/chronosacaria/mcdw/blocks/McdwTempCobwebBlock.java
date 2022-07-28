package chronosacaria.mcdw.blocks;

import chronosacaria.mcdw.api.util.CleanlinessHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.checkerframework.checker.units.qual.A;


public class McdwTempCobwebBlock extends CobwebBlock {
    public static final IntProperty AGE;

    public McdwTempCobwebBlock(Settings settings) {
        super(settings);
        settings.dropsNothing();
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));

    }

    private void removeCobweb(BlockState state, World world, BlockPos pos) {
        world.removeBlock(pos, true);
        System.out.print("NOW REMOVING COBWEB");
    }

    //DEPRICATE CRINGE
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        System.out.print("TICK NOW RUNNING");
        if ((random.nextInt(3) == 0)){
            System.out.print("NOW INCREASING AGE OF: " + this);
            increaseAge(state, world, pos);
        }

        world.createAndScheduleBlockTick(pos, this, MathHelper.nextInt(random, 20, 40));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        System.out.print("RANDOM TICK NOW RUNNING");
        this.scheduledTick(state, world, pos, random);
    }

    private void increaseAge(BlockState state, World world, BlockPos pos) {
        int i = state.get(AGE);
        if (i < 3) {
            world.setBlockState(pos, state.with(AGE, i + 1), 2);
        } else {
            removeCobweb(state, world, pos);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    static {
        AGE = Properties.AGE_3;
    }
}
