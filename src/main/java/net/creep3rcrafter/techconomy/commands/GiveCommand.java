package net.creep3rcrafter.techconomy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.creep3rcrafter.techconomy.Techconomy;
import net.creep3rcrafter.techconomy.items.MineCoinItem;
import net.creep3rcrafter.techconomy.register.ModItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

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

    public static void addOrDrop(ItemStack itemStack, ServerPlayer serverPlayer){
        boolean itemAdded = serverPlayer.getInventory().add(itemStack);
        if(!itemAdded){
            ItemEntity itementity = serverPlayer.drop(itemStack, false);
            if (itementity != null) {
                itementity.setNoPickUpDelay();
                itementity.setOwner(serverPlayer.getUUID());
            }
        }
    }

    public static MineCoinItem getLargestCoin(int value){
        List<MineCoinItem> mineCoins = new ArrayList<>();
        mineCoins.add(ModItems.COPPER_MINECOIN.get());
        mineCoins.add(ModItems.IRON_MINECOIN.get());
        mineCoins.add(ModItems.GOLD_MINECOIN.get());
        mineCoins.add(ModItems.EMERALD_MINECOIN.get());
        mineCoins.add(ModItems.DIAMOND_MINECOIN.get());
        mineCoins.add(ModItems.NETHERITE_MINECOIN.get());
        MineCoinItem largestCoin = ModItems.COPPER_MINECOIN.get();
        if (value > 0){
            for(MineCoinItem mineCoinItem : mineCoins){
                if (mineCoinItem.baseWorth <= value && mineCoinItem.baseWorth > largestCoin.baseWorth){
                    largestCoin = mineCoinItem;
                    System.out.println(mineCoinItem.baseWorth + " testing");
                }
            }
        }
        System.out.println(largestCoin.baseWorth);
        return largestCoin;
    }

    public static void getChange(int value, CommandContext<CommandSourceStack> context) {
        ServerPlayer serverPlayer = context.getSource().getPlayer();
        if (value > 0){
            MineCoinItem mineCoinItem = getLargestCoin(value);
            ItemStack itemStack = new ItemStack(mineCoinItem);
            MineCoinItem.initWorth(itemStack, ((MineCoinItem)itemStack.getItem()).baseWorth);
            addOrDrop(itemStack, serverPlayer);
            getChange(value - itemStack.getTag().getInt("worth"), context);
        }
    }
}
