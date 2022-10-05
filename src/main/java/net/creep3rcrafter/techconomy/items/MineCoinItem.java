package net.creep3rcrafter.techconomy.items;

import net.creep3rcrafter.techconomy.register.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class MineCoinItem extends Item {

    public final int baseWorth;
    public static final int randMin = 100;
    public static final int randMax = 500;

    public MineCoinItem(Properties properties, int baseWorth) {
        super(properties);
        this.baseWorth = baseWorth;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        MineCoinItem.initWorth(itemStack, ((MineCoinItem)itemStack.getItem()).baseWorth);
        if ((itemStack.getItem() == ModItems.RARE_MINECOIN.get())){
            componentList.add( Component.translatable("Sometimes multiple coins are smashed together").withStyle(ChatFormatting.GRAY));
        }
        componentList.add( Component.translatable("Worth: " + compoundTag.getInt("worth")).withStyle(ChatFormatting.GOLD));
        super.appendHoverText(itemStack, level, componentList, tooltipFlag);
    }

    @Override
    public void fillItemCategory(CreativeModeTab creativeModeTab, NonNullList<ItemStack> itemStacks) {
        for(int i = 0; i < itemStacks.size(); i++) {
            if (itemStacks.get(i).getItem() instanceof MineCoinItem){
                MineCoinItem.initWorth(itemStacks.get(i), ((MineCoinItem)itemStacks.get(i).getItem()).baseWorth);
            }
        }
        super.fillItemCategory(creativeModeTab, itemStacks);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int value, boolean bool) {
        initWorth(itemStack, ((MineCoinItem)itemStack.getItem()).baseWorth);
        super.inventoryTick(itemStack, level, entity, value, bool);
    }

    public static void initWorth(ItemStack itemStack, int value){
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        if(compoundTag.contains("worth")){

        }else{
            if (((MineCoinItem)itemStack.getItem()).baseWorth == 0){
                randomWorth(compoundTag, randMin, randMax);
            }else{
                setWorth(compoundTag, value);
            }
        }
    }
    public static void setWorth(CompoundTag compoundTag, int value) {
        compoundTag.putInt("worth", value);
    }
    public static void randomWorth(CompoundTag compoundTag, int min, int max) {
        Random random = new Random();
        int value = random.nextInt(min, max);
        setWorth(compoundTag, value);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return (itemStack.getItem() == ModItems.RARE_MINECOIN.get());
    }
}
