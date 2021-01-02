package chronosacaria.mcdw.client;

//import chronosacaria.mcdw.client.renderer.entity.SpearEntityRenderer;
import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
//import chronosacaria.mcdw.entity.McdwEntities;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRenderRegistry;
import chronosacaria.mcdw.network.S2CEntitySpawnPacket;
import chronosacaria.mcdw.weapons.Bows;
import chronosacaria.mcdw.weapons.Crossbows;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class McdwClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SummonedEntityRegistry.register();
        SummonedEntityRenderRegistry.register();

        //registerEntityRenderers();
        registerClientboundPackets();


        /*private static void registerEntityRenderers(){
        EntityRendererRegistry.INSTANCE.register(McdwEntities.SPEAR_SPEAR,
        (dispatcher, context) -> new SpearEntityRenderer(dispatcher));
        }*/

        registerBowPredicates(Bows.BOW_BONEBOW);
        //registerBowPredicates(Bows.BOW_TWIN_BOW);
        registerBowPredicates(Bows.BOW_HAUNTED_BOW);

        registerBowPredicates(Bows.BOW_LONGBOW);
        registerBowPredicates(Bows.BOW_RED_SNAKE);
        registerBowPredicates(Bows.BOW_GUARDIAN_BOW);

        registerBowPredicates(Bows.BOW_SHORTBOW);
        registerBowPredicates(Bows.BOW_MECHANICAL_SHORTBOW);
        registerBowPredicates(Bows.BOW_PURPLE_STORM);
        registerBowPredicates(Bows.BOW_LOVE_SPELL_BOW);

        registerBowPredicates(Bows.BOW_TRICKBOW);
        registerBowPredicates(Bows.BOW_GREEN_MENACE);
        registerBowPredicates(Bows.BOW_PINK_SCOUNDREL);

        registerBowPredicates(Bows.BOW_POWER_BOW);
        registerBowPredicates(Bows.BOW_ELITE_POWER_BOW);
        registerBowPredicates(Bows.BOW_SABREWING);

        registerBowPredicates(Bows.BOW_HUNTING_BOW);
        registerBowPredicates(Bows.BOW_MASTERS_BOW);
        registerBowPredicates(Bows.BOW_HUNTERS_PROMISE);
        registerBowPredicates(Bows.BOW_ANCIENT_BOW);

        registerBowPredicates(Bows.BOW_SOUL_BOW);
        registerBowPredicates(Bows.BOW_NOCTURNAL_BOW);
        registerBowPredicates(Bows.BOW_LOST_SOULS);
        registerBowPredicates(Bows.BOW_SHIVERING_BOW);

        registerBowPredicates(Bows.BOW_WIND_BOW);
        registerBowPredicates(Bows.BOW_ECHO_OF_THE_VALLEY);
        registerBowPredicates(Bows.BOW_BURST_GALE_BOW);

        //registerBowPredicates(Bows.BOW_SNOW_BOW);
        //registerBowPredicates(Bows.BOW_WINTERS_TOUCH);

        registerCrossbowPredicates(Crossbows.CROSSBOW_AUTO_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_AZURE_SEEKER);
        registerCrossbowPredicates(Crossbows.CROSSBOW_BABY_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_BURST_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_BUTTERFLY_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_CORRUPTED_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_DOOM_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_DUAL_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_EXPLODING_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_FERAL_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_FIREBOLT_THROWER);
        registerCrossbowPredicates(Crossbows.CROSSBOW_HARP_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_HEAVY_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_IMPLODING_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_LIGHTNING_HARP_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_RAPID_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_SCATTER_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_SLAYER_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_THE_SLICER_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_SOUL_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_SOUL_HUNTER_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_SPELLBOUND_CROSSBOW);
        registerCrossbowPredicates(Crossbows.CROSSBOW_VOID_CALLER_CROSSBOW);


    }
    public static void registerBowPredicates(McdwBow bow) {
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),(itemStack, clientWorld,
                                                                                   livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItem() != itemStack ? 0.0F :
                        (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / bow.getMaxDrawTime(itemStack);
            }
        });

        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), (itemStack, clientWorld,
                                                                                       livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ?
                    1.0F : 0.0F;
        });
    }

    public static void registerCrossbowPredicates(McdwCrossbow crossbow) {
        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("pull"),(itemStack, clientWorld,
                                                                                   livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) ? 0.0F :
                        (float) (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / (float)  McdwCrossbow.getPullTime(itemStack);
            }
        });

        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("pulling"), (itemStack, clientWorld,
                                                                                       livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
            }
        });

        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("charged"), (itemStack, clientWorld,
                                                                                            livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
            }
        });

        FabricModelPredicateProviderRegistry.register(crossbow, new Identifier("firework"), (itemStack, clientWorld,
                                                                                            livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) && McdwCrossbow.hasProjectile(itemStack,
                        Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
            }
        });
    }

    private static void registerClientboundPackets() {
        ClientSidePacketRegistry.INSTANCE.register(S2CEntitySpawnPacket.ID, S2CEntitySpawnPacket::onPacket);
    }






}
