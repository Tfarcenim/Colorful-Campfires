package tfar.colorfulcampfires.mixin;

import net.minecraft.block.CampfireBlock;
import net.minecraft.client.particle.CampfireParticle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.colorfulcampfires.MixinEvents;

@Mixin(CampfireParticle.class)
abstract class CampfireParticleMixin extends SpriteTexturedParticle {
	protected CampfireParticleMixin(ClientWorld world, double d, double e, double f) {
		super(world, d, e, f);
	}

	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void tintParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, boolean bl, CallbackInfo ci) {
		MixinEvents.spawnCampfireSmokeParticleEvent((CampfireParticle)(Object)this,world,x,y,z);
	}
}
