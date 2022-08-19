package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class McdwWhip extends McdwCustomWeaponBase implements Vanishable {

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    private final ToolMaterial material;
    private final float attackDamage;
    String[] repairIngredient;

    public McdwWhip(ToolMaterial material, int attackDamage, float attackSpeed, String[] repairIngredient) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
        this.material = material;
        this.attackDamage = attackDamage + material.getAttackDamage();
        this.repairIngredient = repairIngredient;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID,
                "Tool modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
                "Tool modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        if (CompatibilityFlags.isReachEntityAttributeEnabled) {
            builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier("Attack range",
                    Mcdw.CONFIG.mcdwNewStatsConfig.extraAttackReachOfWhips,
                    EntityAttributeModifier.Operation.ADDITION));
            builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier("Attack range",
                    Mcdw.CONFIG.mcdwNewStatsConfig.extraAttackReachOfWhips,
                    EntityAttributeModifier.Operation.ADDITION));
        }
        this.attributeModifiers = builder.build();
    }

    @Override
    public ToolMaterial getMaterial() {
        return this.material;
    }

    @Override
    public int getEnchantability(){
        return this.material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        List<Item> potentialIngredients = new ArrayList<>(List.of());
        AtomicBoolean isWood = new AtomicBoolean(false);
        AtomicBoolean isStone = new AtomicBoolean(false);
        Arrays.stream(repairIngredient).toList().forEach(repIngredient -> {
            if (repIngredient.contentEquals("minecraft:planks"))
                isWood.set(true);
            else if (repIngredient.contentEquals("minecraft:stone_crafting_materials"))
                isStone.set(true);
            potentialIngredients.add(
                    Registry.ITEM.get(new Identifier(repIngredient)));
        });

        return potentialIngredients.contains(ingredient.getItem())
                || (isWood.get() && ingredient.isIn(ItemTags.PLANKS)
                || (isStone.get() && ingredient.isIn(ItemTags.STONE_CRAFTING_MATERIALS)));
    }

    public float getAttackDamage(){
        return this.attackDamage;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner){
        return !miner.isCreative();
    }

    // Damage to tool upon usage
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }
    // Double Damage to tool upon improper usage
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner){
        if (state.getHardness(world, pos) != 0.0F){
            stack.damage(2, miner, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? attributeModifiers :
                super.getAttributeModifiers(equipmentSlot);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        int i = 1;
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(15);
        String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
        while (I18n.hasTranslation(translationKey + i)) {
            tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
    }
}