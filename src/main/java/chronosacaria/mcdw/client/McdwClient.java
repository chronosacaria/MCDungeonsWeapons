package chronosacaria.mcdw.client;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.bases.McdwShield;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRenderRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class McdwClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SummonedEntityRegistry.register();
        SummonedEntityRenderRegistry.register();

        for (String itemID : ItemRegistry.BOWS) {
            registerBowPredicates((McdwBow) ItemRegistry.getItem(itemID));
        }

        for (String itemID : ItemRegistry.CROSSBOWS) {
            registerCrossbowPredicates((McdwCrossbow) ItemRegistry.getItem(itemID));
        }

        //for (String itemID : ItemRegistry.SHIELDS){
        //    registerShieldPredicates((McdwShield) ItemRegistry.getItem(itemID));
        //}
    }
    public static void registerBowPredicates(McdwBow bow) {
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),(itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / bow.getMaxDrawTime();
            }
        });

        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), (itemStack, clientWorld, livingEntity) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }

    public static void registerCrossbowPredicates(McdwCrossbow crossbow) {
        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("pull"),(itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) ? 0.0F :
                        (float) (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / (float)  McdwCrossbow.getPullTime(itemStack);
            }
        });

        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("pulling"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
            }
        });

        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("charged"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
            }
        });

        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("firework"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) && McdwCrossbow.hasProjectile(itemStack,
                        Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
            }
        });
    }

    //public static void registerShieldPredicates(McdwShield shield){
    //    FabricModelPredicateProviderRegistry.register(shield, new Identifier("blocking"), (itemStack, clientWorld,
    //            livingEntity) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem()
    //            == itemStack ? 1.0F : 0.0F );
    //}
}
