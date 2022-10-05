package net.creep3rcrafter.techconomy.register;

import net.creep3rcrafter.techconomy.items.MineCoinItem;
import net.creep3rcrafter.techconomy.Techconomy;
import net.creep3rcrafter.techconomy.items.MineCoinBagItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Techconomy.MODID);

    public static final RegistryObject<MineCoinItem> COPPER_MINECOIN = ITEMS.register("copper_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 1));
    public static final RegistryObject<MineCoinItem> IRON_MINECOIN = ITEMS.register("iron_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 5));
    public static final RegistryObject<MineCoinItem> GOLD_MINECOIN = ITEMS.register("gold_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 10));
    public static final RegistryObject<MineCoinItem> EMERALD_MINECOIN = ITEMS.register("emerald_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 20));
    public static final RegistryObject<MineCoinItem> DIAMOND_MINECOIN = ITEMS.register("diamond_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 50));
    public static final RegistryObject<MineCoinItem> NETHERITE_MINECOIN = ITEMS.register("netherite_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS), 100));

    public static final RegistryObject<MineCoinItem> RARE_MINECOIN = ITEMS.register("rare_minecoin", () -> new MineCoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).rarity(Rarity.UNCOMMON), 0));

    public static final RegistryObject<Item> CREDIT_MINECARD = ITEMS.register("credit_minecard", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> DEBIT_MINECARD = ITEMS.register("debit_minecard", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<MineCoinBagItem> MINECOIN_BAG = ITEMS.register("minecoin_bag", () -> new MineCoinBagItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).stacksTo(1), 64));
}
