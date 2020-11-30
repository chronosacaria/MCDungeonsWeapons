package chronosacaria.mcdw.enchants.summons.render;

import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BeeEntityModel;
import net.minecraft.util.Identifier;

public class SummonedBeeRenderer extends MobEntityRenderer<SummonedBeeEntity, BeeEntityModel<SummonedBeeEntity>> {
    public SummonedBeeRenderer(EntityRenderDispatcher entityRenderDispatcher){
        super(entityRenderDispatcher, new BeeEntityModel<>(),1);
    }

    @Override
    public Identifier getTexture(SummonedBeeEntity entity){
        if (!entity.hasStung()){
            return new Identifier("textures/entity/bee/bee_angry.png");
        }
        else if (entity.hasStung()){
            return new Identifier("textures/entity/bee/bee.png");
        }
        return new Identifier("textures/entity/bee/bee.png");
    }
}
