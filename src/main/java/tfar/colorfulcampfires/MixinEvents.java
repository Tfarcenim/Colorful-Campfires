package tfar.colorfulcampfires;

import net.minecraft.client.particle.CampfireParticle;
import net.minecraft.client.world.ClientWorld;

public class MixinEvents {

	public static void spawnCampfireSmokeParticleEvent(CampfireParticle campfireParticle, ClientWorld world, double x, double y, double z) {
		if (Captures.hasColor) {
			float[] rgb = ExampleMod.intToFloatArray(Captures.color);
			campfireParticle.setColor(rgb[0], rgb[1], rgb[2]);
			Captures.hasColor = false;
		}
	}
}
