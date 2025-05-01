/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.data;

import dev.timefall.mcdw.component.McdwEffectComponentTypes;
import dev.timefall.mcdw.enchantment.EnchantmentIds;
import dev.timefall.mcdw.enchantment.effect.entity.AOEEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.ApplyStackingMobEffectEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.LeechMobEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.RemoveMobEffectEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.relative.DamageTakenRelativeEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity_aware.MultiplyStatusValueEffect;
import dev.timefall.mcdw.registries.SoundEventsRegistry;
import dev.timefall.mcdw.registries.StatusEffectsRegistry;
import dev.timefall.mcdw.registries.tag.McdwEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.block.Block;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AllOfEnchantmentEffects;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.PlaySoundEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.enchantment.effect.value.MultiplyEnchantmentEffect;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class McdwEnchantmentGenerator extends FabricDynamicRegistryProvider {

    public McdwEnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture){
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Mcdw Enchantment Generation";
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {

        RegistryEntryLookup<DamageType> damageTypeLookup = registries.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE);
        RegistryEntryLookup<Enchantment> enchantmentLookup = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> itemLookup = registries.getWrapperOrThrow(RegistryKeys.ITEM);
        RegistryEntryLookup<Block> blockLookup = registries.getWrapperOrThrow(RegistryKeys.BLOCK);



        // DYNAMO
        register(
            entries,
            EnchantmentIds.DYNAMO,
            Enchantment.builder(
                Enchantment.definition(
                    itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                    1,
                    3,
                    Enchantment.leveledCost(1, 10),
                    Enchantment.leveledCost(16, 10),
                    2,
                    AttributeModifierSlot.HAND
                )
            ).exclusiveSet(
                enchantmentLookup.getOrThrow(McdwEnchantmentTags.DAMAGE_EXCLUSIVE)
            ).addEffect(
                McdwEffectComponentTypes.ENTITY_AWARE_DAMAGE,
                new MultiplyStatusValueEffect(StatusEffectsRegistry.DYNAMO, EnchantmentLevelBasedValue.linear(0.1f,0.025f),false,Optional.empty())
            ).addEffect(
                McdwEffectComponentTypes.ON_JUMP,
                new ApplyStackingMobEffectEnchantmentEntityEffect(
                    StatusEffectsRegistry.DYNAMO,
                    EnchantmentLevelBasedValue.constant(120000f),
                    EnchantmentLevelBasedValue.constant(19f)
                )
            ).addEffect(
                EnchantmentEffectComponentTypes.POST_ATTACK,
                EnchantmentEffectTarget.ATTACKER,
                EnchantmentEffectTarget.ATTACKER,
                new RemoveMobEffectEnchantmentEntityEffect(StatusEffectsRegistry.DYNAMO)
            )
        );

        // ECHO
        register(
                entries,
                EnchantmentIds.ECHO,
                Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                1,
                                3,
                                Enchantment.leveledCost(1, 10),
                                Enchantment.leveledCost(16, 10),
                                2,
                                AttributeModifierSlot.MAINHAND
                        )
                ).exclusiveSet(
                        enchantmentLookup.getOrThrow(McdwEnchantmentTags.AOE_AND_DAMAGE_EXCLUSIVE)
                ).addEffect(
                        EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,
                        EnchantmentEffectTarget.VICTIM,
                        AllOfEnchantmentEffects.allOf(
                            new PlaySoundEnchantmentEffect(
                                SoundEventsRegistry.ECHO_SOUND_EVENT,
                                ConstantFloatProvider.create(0.5f),
                                ConstantFloatProvider.create(1f)
                            ),
                            new AOEEnchantmentEntityEffect(
                                EnchantmentLevelBasedValue.constant(3f),
                                new DamageTakenRelativeEnchantmentEntityEffect(
                                    EnchantmentLevelBasedValue.constant(1f),
                                    damageTypeLookup.getOrThrow(DamageTypes.GENERIC)
                                ),
                                Optional.of(EnchantmentLevelBasedValue.linear(1f)),
                                true
                            )
                        ),
                        () -> new RandomChanceWithEnchantedBonusLootCondition(
                            0f,
                            EnchantmentLevelBasedValue.linear(0.15f),
                            enchantmentLookup.getOrThrow(EnchantmentIds.ECHO)
                        )
                )
        );

        // LEECHING
        register(
                entries,
                EnchantmentIds.LEECHING,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(16, 10),
                        2,
                        AttributeModifierSlot.HAND
                    )
                ).exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                ).addEffect(
                    McdwEffectComponentTypes.POST_DEATH,
                    EnchantmentEffectTarget.ATTACKER,
                    EnchantmentEffectTarget.VICTIM,
                    new LeechMobEnchantmentEntityEffect(EnchantmentLevelBasedValue.linear(0.4f, 0.2f))
                )
        );

        // PAIN_CYCLE
        register(
                entries,
                EnchantmentIds.PAIN_CYCLE,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(16, 10),
                        2,
                        AttributeModifierSlot.MAINHAND
                    )
                ).exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.DAMAGE_EXCLUSIVE)
                ).addEffect(
                    EnchantmentEffectComponentTypes.DAMAGE,
                    new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.linear(2f,1f)),
                    EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.ATTACKER,
                        EntityPredicate.Builder.create().effects(
                            EntityEffectPredicate.Builder.create().addEffect(
                                StatusEffectsRegistry.PAIN_CYCLE,
                                new EntityEffectPredicate.EffectData(
                                    NumberRange.IntRange.exactly(4),
                                    NumberRange.IntRange.ANY,
                                    Optional.empty(),
                                    Optional.empty()
                                )
                            )
                        )
                    )
                ).addEffect(
                    EnchantmentEffectComponentTypes.POST_ATTACK,
                    EnchantmentEffectTarget.ATTACKER,
                    EnchantmentEffectTarget.ATTACKER,
                    new ApplyStackingMobEffectEnchantmentEntityEffect(
                        RegistryEntryList.of(StatusEffectsRegistry.PAIN_CYCLE),
                        EnchantmentLevelBasedValue.constant(120000f),
                        EnchantmentLevelBasedValue.constant(0f),
                        EnchantmentLevelBasedValue.constant(4f),
                        true
                    )
                )
        );


        // SOUL_DEVOURER
        register(
            // the enchantment registerable
            entries,
            // the RegistryKey of SOUL_DEVOURER, kept in the Ids class
            EnchantmentIds.SOUL_DEVOURER,
            //the enchantment builder itself
            Enchantment.builder(
                // first, the definitions. This is the "basic stats" of the enchantment.
                Enchantment.definition(
                    // tag of valid items. I used WEAPON, but another could be used, or a custom tag made. Old way uses Sword, Axe, or MCDW Custom, so maybe needs to be custom.
                    // As is, this would become enchantable on tridents, maces, etc. as well as swords/axes.
                    itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                    // weight of the enchantment, previously the "Rarity". Used the old "VERY_RARE".
                    1,
                    // max level, self-explanatory
                    3,
                    //minimum enchanting cost. old style did not customize this, so I used the "default" values for these
                    Enchantment.leveledCost(1, 10),
                    //maximum enchanting cost. defaults used again
                    Enchantment.leveledCost(16, 10),
                    //Anvil Cost. I set this middle of the road, but not really sure what to do with it tbh, this is a new thing.
                    2,
                    //valid slots the weapon can be in. I used "HAND", which enables off-hand holding. Might need to play with.
                    AttributeModifierSlot.HAND
                )
            // next we add the exclusions. I made new tag holder class, and this is exclusive with the other "Experience" enchants. The tag includes this, by design.
            ).exclusiveSet(
                enchantmentLookup.getOrThrow(McdwEnchantmentTags.EXPERIENCE_EXCLUSIVE)
            //Finally, the statusEffect.
            ).addEffect(
                //the Mob Experience modification hook.
                EnchantmentEffectComponentTypes.MOB_EXPERIENCE,
                //we will multiply the drops by 1 + (level / 3.0), which is the same as "1.33333 + 0.33333 for each level beyond the first".
                new MultiplyEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.33333f, 0.33333f))
            )
        );

        // SOUL_SIPHON
        register(
                entries,
                EnchantmentIds.SOUL_SIPHON,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(16, 10),
                        2,
                        AttributeModifierSlot.MAINHAND
                    )
                ).exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.EXPERIENCE_EXCLUSIVE)
                ).addEffect(
                    EnchantmentEffectComponentTypes.MOB_EXPERIENCE,
                    new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(3f)),
                    //this addition happens 10% of the time
                    RandomChanceLootCondition.builder(0.1f)
                )
        );


        // Thundering Enchantment
    }

    private void register(Entries entries,RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        register(entries, key, builder, new ConfigEnchantmentEnabledCondition(key.getValue()));
    }
    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition...resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }
}