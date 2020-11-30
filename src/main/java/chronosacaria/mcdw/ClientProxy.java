package chronosacaria.mcdw;

import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

import java.util.Random;

public class ClientProxy extends CommonProxy{

    @Override
    public void spawnParticles(Entity entity, ParticleEffect particleEffect){
        for (int k = 0; k < 20; ++k){
            Random random = new Random();
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            entity.getEntityWorld().addParticle(particleEffect,
                    entity.getX() + (double)(random.nextFloat() * entity.getWidth()) * 2.0F - (double)entity.getWidth(),
                    entity.getY() + (double)(random.nextFloat() * entity.getHeight()),
                    entity.getZ() + (double)(random.nextFloat() * entity.getWidth() * 2.0F) - (double)entity.getWidth(),d2,d0,d1);

        }
    }
}
