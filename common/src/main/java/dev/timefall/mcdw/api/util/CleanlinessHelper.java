/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.util;

import dev.timefall.mcdw.api.interfaces.IMcdwEnchantedArrow;
import dev.timefall.mcdw.api.interfaces.IOffhandAttack;
import dev.timefall.mcdw.bases.McdwLongbowItem;
import dev.timefall.mcdw.bases.McdwShortbowItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.enums.EnchantmentsID;
import dev.timefall.mcdw.registries.EnchantmentRegistry;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class CleanlinessHelper {
    @SuppressWarnings("deprecation")
    public static final Random random = Random.createThreadSafe();

    public static boolean percentToOccur (int chance) {
        return random.nextInt(100) < chance;
    }

    public static boolean canRepairCheck(String[] repairIngredient, ItemStack ingredient) {

        List<Item> potentialIngredients = new ArrayList<>(List.of());
        AtomicBoolean isWood = new AtomicBoolean(false);
        AtomicBoolean isStone = new AtomicBoolean(false);
        if (repairIngredient != null && repairIngredient.length > 0) {
            Arrays.stream(repairIngredient).toList().forEach(repIngredient -> {
                if (repIngredient != null) {
                    if (repIngredient.contentEquals("minecraft:planks"))
                        isWood.set(true);
                    else if (repIngredient.contentEquals("minecraft:stone_crafting_materials"))
                        isStone.set(true);
                    potentialIngredients.add(
                            Registries.ITEM.get(Identifier.of(repIngredient)));
                }
            });
        }

        return potentialIngredients.contains(ingredient.getItem())
                || (isWood.get() && ingredient.isIn(ItemTags.PLANKS)
                || (isStone.get() && ingredient.isIn(ItemTags.STONE_CRAFTING_MATERIALS)));
    }

    public static void playCenteredSound (LivingEntity center, SoundEvent soundEvent, float volume, float pitch) {
        playCenteredSound(center, soundEvent, SoundCategory.PLAYERS, volume, pitch);
    }

    public static void playCenteredSound (LivingEntity center, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        center.getWorld().playSound(null,
                center.getX(), center.getY(), center.getZ(),
                soundEvent, soundCategory,
                volume, pitch);
    }

    public static void mcdw$dropItem(LivingEntity le, Item item) {
        mcdw$dropItem(le, item, 1);
    }

    public static void mcdw$dropItem(LivingEntity le, ItemStack itemStack) {
        ItemEntity it = new ItemEntity(
                le.getWorld(), le.getX(), le.getY(), le.getZ(),
                itemStack);
        le.getWorld().spawnEntity(it);
    }

    public static void mcdw$dropItem(LivingEntity le, Item item, int amount) {
        mcdw$dropItem(le, new ItemStack(item, amount));
    }

    public static void mcdw$tooltipHelper(ItemStack stack, List<Text> tooltip, int subStringIndex) {
        int i = 1;
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(subStringIndex);
        String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
        //while (
        //        I18n.hasTranslation(translationKey + i)
        //)
        {
            tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
        if (stack.getItem() instanceof IOffhandAttack) {
            if (CompatibilityFlags.noOffhandConflicts) {
                tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
                tooltip.add(Text.translatable("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
            }
        }
        if (stack.getItem() instanceof McdwShortbowItem) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_note_item.mcdw.shortbow").formatted(Formatting.GREEN));
        }
        if (stack.getItem() instanceof McdwLongbowItem) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_note_item.mcdw.longbow").formatted(Formatting.GREEN));
        }

    }

    public static void addPPEEnchantments(ItemStack itemStack, IMcdwEnchantedArrow ppe) {
        int chainReactionLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.CHAIN_REACTION), itemStack);
        if (chainReactionLevel > 0) {
            ppe.mcdw$setChainReactionLevel(chainReactionLevel);
        }
        int chargeLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.CHARGE), itemStack);
        if (chargeLevel > 0) {
            ppe.mcdw$setChargeLevel(chargeLevel);
        }
        int cobwebShotLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.COBWEB_SHOT), itemStack);
        if (cobwebShotLevel > 0) {
            ppe.mcdw$setCobwebShotLevel(cobwebShotLevel);
        }
        int dynamoLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.DYNAMO), itemStack);
        if (dynamoLevel > 0) {
            ppe.mcdw$setDynamoLevel(dynamoLevel);
        }
        int enigmaResonatorLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.ENIGMA_RESONATOR), itemStack);
        if (enigmaResonatorLevel > 0) {
            ppe.mcdw$setEnigmaResonatorLevel(enigmaResonatorLevel);
        }
        int fuseShotLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.FUSE_SHOT), itemStack);
        if (fuseShotLevel > 0) {
            ppe.mcdw$setFuseShotLevel(fuseShotLevel);
        }
        int freezingLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.FREEZING), itemStack);
        if (freezingLevel > 0) {
            ppe.mcdw$setFreezingLevel(freezingLevel);
        }
        int gravityLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.GRAVITY), itemStack);
        if (gravityLevel > 0) {
            ppe.mcdw$setGravityLevel(gravityLevel);
        }
        int growingLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.GROWING), itemStack);
        if (growingLevel > 0) {
            ppe.mcdw$setGrowingLevel(growingLevel);
        }
        int levitationShotLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.LEVITATION_SHOT), itemStack);
        if (levitationShotLevel > 0) {
            ppe.mcdw$setLevitationShotLevel(levitationShotLevel);
        }
        int phantomsMarkLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.PHANTOMS_MARK), itemStack);
        if (phantomsMarkLevel > 0) {
            ppe.mcdw$setPhantomsMarkLevel(phantomsMarkLevel);
        }
        int poisonCloudLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.POISON_CLOUD), itemStack);
        if (poisonCloudLevel > 0) {
            ppe.mcdw$setPoisonCloudLevel(poisonCloudLevel);
        }
        int radianceLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.RADIANCE), itemStack);
        if (radianceLevel > 0) {
            ppe.mcdw$setRadianceLevel(radianceLevel);
        }
        int replenishLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.REPLENISH), itemStack);
        if (replenishLevel > 0) {
            ppe.mcdw$setReplenishLevel(replenishLevel);
        }
        int ricochetLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.RICOCHET), itemStack);
        if (ricochetLevel > 0) {
            ppe.mcdw$setRicochetLevel(ricochetLevel);
        }
        int shadowShotLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.SHADOW_SHOT), itemStack);
        if (shadowShotLevel > 0) {
            ppe.mcdw$setShadowShotLevel(shadowShotLevel);
        }
        int tempoTheftLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.TEMPO_THEFT), itemStack);
        if (tempoTheftLevel > 0) {
            ppe.mcdw$setTempoTheftLevel(tempoTheftLevel);
        }
        int thunderingLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.THUNDERING), itemStack);
        if (thunderingLevel > 0) {
            ppe.mcdw$setThunderingLevel(thunderingLevel);
        }
        int voidShotLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.VOID_SHOT), itemStack);
        if (voidShotLevel > 0) {
            ppe.mcdw$setVoidShotLevel(voidShotLevel);
        }
        int wildRageLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.enchantments.get(EnchantmentsID.WILD_RAGE), itemStack);
        if (wildRageLevel > 0){
            ppe.mcdw$setWildRageLevel(wildRageLevel);
        }
    }


    public static String materialToString(ToolMaterial toolMaterial) {
        return switch (toolMaterial) {
            case ToolMaterials.WOOD -> "wood";
            case ToolMaterials.STONE -> "stone";
            case ToolMaterials.GOLD -> "gold";
            case ToolMaterials.IRON -> "iron";
            case ToolMaterials.DIAMOND -> "diamond";
            case ToolMaterials.NETHERITE -> "netherite";
            case null, default -> "none";
        };
    }

    public static ToolMaterial stringToMaterial(String material) {
        return switch (material) {
            case "wood" -> ToolMaterials.WOOD;
            case "stone" -> ToolMaterials.STONE;
            case "gold" -> ToolMaterials.GOLD;
            case "diamond" -> ToolMaterials.DIAMOND;
            case "netherite" -> ToolMaterials.NETHERITE;
            default -> ToolMaterials.IRON;
        };
    }

    public static AttributeModifiersComponent createBaseAttributeModifiers(ToolMaterial material, int attackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (float) attackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static AttributeModifiersComponent createBaseWithRangeAttributeModifiers(ToolMaterial material, int attackDamage, float attackSpeed, double additionalAttackReach) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (float) attackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                Identifier.ofVanilla("player.entity_interaction_range"),
                                additionalAttackReach,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static AttributeModifiersComponent createDualWieldWithRangeAttributeModifiers(ToolMaterial material, int attackDamage, float attackSpeed, double additionalAttackReach) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (float) attackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (float) attackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.OFFHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.OFFHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                Identifier.ofVanilla("player.entity_interaction_range"),
                                additionalAttackReach,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                Identifier.ofVanilla("player.entity_interaction_range"),
                                additionalAttackReach,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.OFFHAND
                )
                .build();
    }

    public static TagKey<Item> mcdw$getItemTagKey(String tag) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(tag));
    }

}
