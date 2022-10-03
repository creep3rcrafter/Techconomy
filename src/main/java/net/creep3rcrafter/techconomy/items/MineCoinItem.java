package net.creep3rcrafter.techconomy.items;

import net.minecraft.world.item.Item;

public class MineCoinItem extends Item {

    public int worth;

    public MineCoinItem(Properties properties, int worth) {
        super(properties);
        this.worth = worth;
    }

    public void setWorth(int worth){
        this.worth = worth;
    }
    public int getWorth() {
        return this.worth;
    }
}
