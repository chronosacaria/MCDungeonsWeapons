/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin.old_mixins.mcdw;

import dev.timefall.mcdw.registries.items.McdwShieldItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ShieldPlayerEntityMixin extends LivingEntity {
    @Shadow public abstract void incrementStat(Stat<?> stat);

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    @SuppressWarnings("unused")
    protected ShieldPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    //@Inject(method = "damageShield", at = @At("HEAD"))
    //private void mcdw$damageMcdwShield(float amount, CallbackInfo ci) {
    //    if (this.activeItemStack.getItem() instanceof McdwShieldItem) {
    //        if (!this.getWorld().isClient) {
    //            this.incrementStat(Stats.USED.getOrCreateStat(this.activeItemStack.getItem()));
    //        }
//
    //        if (amount >= 3.0F) {
    //            int i = 1 + MathHelper.floor(amount);
    //            Hand hand = this.getActiveHand();
    //            this.activeItemStack.damage(i, this, (Consumer<LivingEntity>) ((playerEntity) -> playerEntity.sendToolBreakStatus(hand)));
    //            if (this.activeItemStack.isEmpty()) {
    //                if (hand == Hand.MAIN_HAND) {
    //                    this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    //                } else {
    //                    this.equipStack(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
    //                }
//
    //                this.activeItemStack = ItemStack.EMPTY;
    //                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.getWorld().getRandom().nextFloat() * 0.4F);
    //            }
    //        }
    //    }
    //}

    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V"))
    public void mcdw$disableMcdwShield(CallbackInfo ci){
        this.getItemCooldownManager().set(McdwShieldItemRegistry.SHIELD_ROYAL_GUARD_SHIELD, 100);
        this.getItemCooldownManager().set(McdwShieldItemRegistry.SHIELD_TOWER_GUARD_SHIELD, 100);
        this.getItemCooldownManager().set(McdwShieldItemRegistry.SHIELD_VANGUARD_SHIELD, 100);
    }
}
