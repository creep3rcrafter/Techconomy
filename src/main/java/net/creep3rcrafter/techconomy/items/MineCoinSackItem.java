package net.creep3rcrafter.techconomy.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MineCoinSackItem extends BundleItem {
    public MineCoinSackItem(Properties properties, int storage) {
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
}
