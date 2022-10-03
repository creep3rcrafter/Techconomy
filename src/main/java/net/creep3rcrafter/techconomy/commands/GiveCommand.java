package net.creep3rcrafter.techconomy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.creep3rcrafter.techconomy.Techconomy;
import net.creep3rcrafter.techconomy.register.ModItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GiveCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(Techconomy.MODID)
                .then(Commands.literal("give").then(Commands.argument("count", IntegerArgumentType.integer())
                .executes(Command -> {
                    return giveCoins(Command);
                }))));
    }

    public static int giveCoins(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        int count = context.getArgument("count", Integer.class);
        getChange(count, context);
        return 1;
    }

    public static Item getChange(int count, CommandContext<CommandSourceStack> context) {
        ServerPlayer serverPlayer = context.getSource().getPlayer();
        if (count >= ModItems.NETHERITE_MINECOIN.get().getWorth()) {
            ItemStack itemStack = new ItemStack(ModItems.NETHERITE_MINECOIN.get());
            boolean test = serverPlayer.getInventory().add(itemStack);
            if (!test){
                ItemEntity itementity = serverPlayer.drop(itemStack, false);
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(serverPlayer.getUUID());
                }
            }
            getChange(count - ModItems.NETHERITE_MINECOIN.get().getWorth(), context);

        } else if (count >= ModItems.DIAMOND_MINECOIN.get().getWorth()) {
            ItemStack itemStack = new ItemStack(ModItems.DIAMOND_MINECOIN.get());
            boolean test = serverPlayer.getInventory().add(itemStack);
            if (!test){
                ItemEntity itementity = serverPlayer.drop(itemStack, false);
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(serverPlayer.getUUID());
                }
            }
            getChange(count - ModItems.DIAMOND_MINECOIN.get().getWorth(), context);

        } else if (count >= ModItems.EMERALD_MINECOIN.get().getWorth()) {
            ItemStack itemStack = new ItemStack(ModItems.EMERALD_MINECOIN.get());
            boolean test = serverPlayer.getInventory().add(itemStack);
            if (!test){
                ItemEntity itementity = serverPlayer.drop(itemStack, false);
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(serverPlayer.getUUID());
                }
            }
            getChange(count - ModItems.EMERALD_MINECOIN.get().getWorth(), context);

        } else if (count >= ModItems.GOLD_MINECOIN.get().getWorth()) {
            ItemStack itemStack = new ItemStack(ModItems.GOLD_MINECOIN.get());
            boolean test = serverPlayer.getInventory().add(itemStack);
            if (!test){
                ItemEntity itementity = serverPlayer.drop(itemStack, false);
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(serverPlayer.getUUID());
                }
            }
            getChange(count - ModItems.GOLD_MINECOIN.get().getWorth(), context);

        } else if (count >= ModItems.IRON_MINECOIN.get().getWorth()) {
            ItemStack itemStack = new ItemStack(ModItems.IRON_MINECOIN.get());
            boolean test = serverPlayer.getInventory().add(itemStack);
            if (!test){
                ItemEntity itementity = serverPlayer.drop(itemStack, false);
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(serverPlayer.getUUID());
                }
            }
            getChange(count - ModItems.IRON_MINECOIN.get().getWorth(), context);

        } else if (count >= ModItems.COPPER_MINECOIN.get().getWorth()) {
            ItemStack itemStack = new ItemStack(ModItems.COPPER_MINECOIN.get());
            boolean test = serverPlayer.getInventory().add(itemStack);
            if (!test){
                ItemEntity itementity = serverPlayer.drop(itemStack, false);
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(serverPlayer.getUUID());
                }
            }
            getChange(count - ModItems.COPPER_MINECOIN.get().getWorth(), context);

        } else {
            //System.out.println("no more change");
        }
        return null;
    }
}
