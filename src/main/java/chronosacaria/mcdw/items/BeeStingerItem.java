package chronosacaria.mcdw.items;

import net.minecraft.item.Item;

public class BeeStingerItem extends Item {
    public BeeStingerItem(Settings settings){
        super(settings);
    }

    /*@Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("item.mcdw.item_bee_stinger.tooltip"));
        tooltip.add(new TranslatableText("item.mcdw.moreinfo.tooltip"));
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340)){
            tooltip.remove(new TranslatableText("item.mcdw.moreinfo.tooptip"));
            tooltip.add(new TranslatableText("item.mcdw.item_bee_stinger.tooltip2"));
        }
    }*/
}
