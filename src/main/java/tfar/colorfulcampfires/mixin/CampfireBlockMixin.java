package tfar.colorfulcampfires.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.colorfulcampfires.Captures;

import java.util.Random;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {

	@Inject(method = "animateTick",at = @At("HEAD"))
	private void captureArgs(BlockState stateIn, World worldIn, BlockPos pos, Random rand, CallbackInfo ci) {
		Captures.world = worldIn;
		Captures.pos = pos;
		Captures.state = stateIn;
	}

	@Inject(method = "spawnSmokeParticles",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/World;addOptionalParticle(Lnet/minecraft/particles/IParticleData;ZDDDDDD)V"))
	private static void getColor(World worldIn, BlockPos pos, boolean isSignalFire, boolean spawnExtraSmoke, CallbackInfo ci) {
		Captures.hasColor = worldIn.isBlockPowered(pos);
		Captures.color = worldIn.getBlockState(pos.down()).getMaterialColor(worldIn,pos.down()).colorValue;
	}
}
