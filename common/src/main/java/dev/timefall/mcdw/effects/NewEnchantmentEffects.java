/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.effects;

import dev.timefall.mcdw.component.McdwEffectComponentTypes;
import dev.timefall.mcdw.enchantment.effect.EntityAwareValueEffect;
import dev.timefall.mcdw.mixin.EnchantmentAccessor;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.TargetedEnchantmentEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.apache.commons.lang3.mutable.MutableFloat;

public class NewEnchantmentEffects {

    //ACCELERATE_CHARGE_TIME: effects for the accelerate charge time
    public static float mcdw$accelerateChargeTimeHook(ItemStack stack, LivingEntity user, float baseDrawSpeedMultiplier) {
        //mutable instance of original charge time value
        MutableFloat mutableFloat = new MutableFloat(baseDrawSpeedMultiplier);
        //for each enchantment, apply the accelerate charge time effect
        forEachEnchantment(stack, (RegistryEntry<Enchantment> enchantment, int level) -> mcdw$accelerateChargeTimeEffect(enchantment, user, level, mutableFloat));
        //return the final result of the modifications
        return Math.max(0.0f, mutableFloat.floatValue());
    }
    private static void mcdw$accelerateChargeTimeEffect(RegistryEntry<Enchantment> enchantment, LivingEntity user, int level, MutableFloat mutableFloat) {
       EntityAwareValueEffect.modifyValue(enchantment.value(), McdwEffectComponentTypes.ACCELERATE_CHARGE_TIME, user, level, mutableFloat);
    }

    //ACCELERATE_STATUS_EFFECT: apply the accelerate status effect here
    public static void mcdw$accelerateFullyChargedHook(ItemStack stack, LivingEntity user) {
        //for each relevant enchantment.
        forEachEnchantment(stack, EquipmentSlot.MAINHAND, user, (RegistryEntry<Enchantment> enchantment, int level, EnchantmentEffectContext context) -> mcdw$accelerateFullyChargedEffect(enchantment, user, level, context));
    }
    private static void mcdw$accelerateFullyChargedEffect(RegistryEntry<Enchantment> enchantment, LivingEntity user, int level, EnchantmentEffectContext context) {
        World world = user.getWorld();
        if (!(world instanceof ServerWorld)) return;
        EnchantmentEntityEffect enchantmentEntityEffect = ((EnchantmentAccessor) enchantment).getEffects().get(McdwEffectComponentTypes.ACCELERATE_BOW_CHARGED);
        if (enchantmentEntityEffect == null) return;
        enchantmentEntityEffect.apply((ServerWorld) world, level, context, user, user.getPos());
    }

    //ON_DEATH: hook for effects that happen when the target dies.
    public static void mcdw$onDeathHook(Entity attacker, ServerWorld world, LivingEntity victim, DamageSource damageSource) {
        //first run the statusEffect in VICTIM mode on any enchantments that the victim may have
        forEachEnchantment(victim,(RegistryEntry<Enchantment> enchantment, int level, EnchantmentEffectContext context) -> mcdw$onDeathEffect(enchantment.value(),world,level,context,EnchantmentEffectTarget.VICTIM, victim, damageSource));
        //if the killer is a living entity
        if (attacker instanceof LivingEntity livingAttacker) {
            // run the statusEffect in ATTACKER mode on attackers enchants.
            forEachEnchantment(livingAttacker,(RegistryEntry<Enchantment> enchantment, int level, EnchantmentEffectContext context) -> mcdw$onDeathEffect(enchantment.value(),world,level,context,EnchantmentEffectTarget.ATTACKER, victim, damageSource));

        }
    }
    private static void mcdw$onDeathEffect(Enchantment enchantment,ServerWorld world, int level, EnchantmentEffectContext context, EnchantmentEffectTarget target, Entity user, DamageSource damageSource) {
        for (TargetedEnchantmentEffect<EnchantmentEntityEffect> targetedEnchantmentEffectType : enchantment.getEffect(McdwEffectComponentTypes.POST_DEATH)) {
            if (target != targetedEnchantmentEffectType.enchanted()) continue;
            Enchantment.applyTargetedEffect(targetedEnchantmentEffectType, world, level, context, user, damageSource);
        }
    }


    //ON_JUMP: effects applied when the user jumps
    public static void mcdw$onJumpHook(ServerPlayerEntity serverPlayerEntity) {
        forEachEnchantment(serverPlayerEntity,(RegistryEntry<Enchantment> enchantment, int level, EnchantmentEffectContext context) -> mcdw$onJumpEffect(enchantment.value(),serverPlayerEntity,serverPlayerEntity.getServerWorld(),level,context));
    }
    private static void mcdw$onJumpEffect(Enchantment enchantment, ServerPlayerEntity serverPlayerEntity, ServerWorld world, int level, EnchantmentEffectContext context){
        EnchantmentAccessor.callApplyEffects(
                enchantment.getEffect(McdwEffectComponentTypes.ON_JUMP),
                EnchantmentAccessor.callCreateEnchantedEntityLootContext(world,level, serverPlayerEntity,serverPlayerEntity.getPos()),
                effect -> effect.apply(world,level,context,serverPlayerEntity,serverPlayerEntity.getPos())
        );
    }


    //STATUS_SCALED_DAMAGE: damage modifier event that applies status-effect-level-based damage multipliers
    public static float mcdw$entityAwareDamageHook(ServerWorld world, ItemStack stack, Entity target, DamageSource damageSource, float baseDamage) {
        MutableFloat mutableFloat = new MutableFloat(baseDamage);
        forEachEnchantment(stack, (enchantment, level) -> mcdw$entityAwareDamageEffect(enchantment.value(),level,world,target,damageSource,mutableFloat));
        return mutableFloat.floatValue() - baseDamage;
    }
    private static void mcdw$entityAwareDamageEffect(Enchantment enchantment, int level, ServerWorld world, Entity target, DamageSource damageSource, MutableFloat value){
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof LivingEntity livingEntity) {
            LootContext context = Enchantment.createEnchantedDamageLootContext(world, level, target, damageSource);
            for (EnchantmentEffectEntry<EntityAwareValueEffect> entry : enchantment.getEffect(McdwEffectComponentTypes.ENTITY_AWARE_DAMAGE)) {
                if (!entry.test(context)) continue;
                value.setValue(entry.effect().apply(level, value.getValue(), livingEntity));
            }
        }
    }

    ///////////////////// Copying EnchantmentHelper methods that are private ////////////////

    // Should probably be replaced with AW at some point, but I'm just getting things working.

    private static void forEachEnchantment(ItemStack stack, EnchantmentHelper.Consumer consumer) {
        ItemEnchantmentsComponent itemEnchantmentsComponent = (ItemEnchantmentsComponent)stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, (Object)ItemEnchantmentsComponent.DEFAULT);
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
            consumer.accept(entry.getKey(), entry.getIntValue());
        }
    }

    private static void forEachEnchantment(ItemStack stack, EquipmentSlot slot, LivingEntity entity, ContextAwareConsumer contextAwareConsumer) {
        if (stack.isEmpty()) {
            return;
        }
        ItemEnchantmentsComponent itemEnchantmentsComponent = stack.get(DataComponentTypes.ENCHANTMENTS);
        if (itemEnchantmentsComponent == null || itemEnchantmentsComponent.isEmpty()) {
            return;
        }
        EnchantmentEffectContext enchantmentEffectContext = new EnchantmentEffectContext(stack, slot, entity);
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
            RegistryEntry<Enchantment> registryEntry = entry.getKey();
            if (!(registryEntry.value()).slotMatches(slot)) continue;
            contextAwareConsumer.accept(registryEntry, entry.getIntValue(), enchantmentEffectContext);
        }
    }

    private static void forEachEnchantment(LivingEntity entity, ContextAwareConsumer contextAwareConsumer) {
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            forEachEnchantment(entity.getEquippedStack(equipmentSlot), equipmentSlot, entity, contextAwareConsumer);
        }
    }

    @FunctionalInterface
    interface ContextAwareConsumer {
        void accept(RegistryEntry<Enchantment> var1, int var2, EnchantmentEffectContext var3);
    }

    // Hooks live here
}