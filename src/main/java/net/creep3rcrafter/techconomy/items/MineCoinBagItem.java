package net.creep3rcrafter.techconomy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class MineCoinBagItem extends BundleItem {

    private static final int BAR_COLOR = Mth.color(1F, 1F, 0F);

    public MineCoinBagItem(Properties properties, int storage) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (true/*Do thing and return sucess or fail*/) {
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack itemStack, Slot slot, ClickAction clickAction, Player player) {
        if (clickAction != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemstack2 = slot.getItem();
            if (itemstack2.isEmpty()) {
                playRemoveOneSound(player);
                removeOne(itemStack).ifPresent((p_150740_) -> {
                    add(itemStack, slot.safeInsert(p_150740_));
                });
            } else if (itemstack2.getItem().canFitInsideContainerItems() && itemstack2.getItem() instanceof MineCoinItem) {
                int i = (64 - getContentWeight(itemStack)) / getWeight(itemstack2);
                int j = add(itemStack, slot.safeTake(itemstack2.getCount(), i, player));
                if (j > 0) {
                    playInsertSound(player);
                }
            }
            return true;
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        int worth = 0;
        if (!compoundTag.contains("Items")) {
            worth = 0;
        } else {
            ListTag listtag = compoundTag.getList("Items", 10);
            if (listtag.isEmpty()) {
                worth = 0;
            } else {
                for (int i = 0; i < listtag.size(); i++) {
                    CompoundTag compoundTag1 = listtag.getCompound(i);
                    ItemStack itemStack1 = ItemStack.of(compoundTag1);
                    if (itemStack1.getItem() instanceof MineCoinItem) {
                        MineCoinItem.initWorth(itemStack1, ((MineCoinItem) itemStack1.getItem()).baseWorth);
                        worth += itemStack1.getTag().getInt("worth") * itemStack1.getCount();
                    } else {
                        worth = 0;
                    }
                }
            }
        }
        componentList.add(1, Component.translatable("Coins: " + worth).withStyle(ChatFormatting.GOLD));
        super.appendHoverText(itemStack, level, componentList, tooltipFlag);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return BAR_COLOR;
    }
}
