package ru.fantasydestiny.fantasydestiny.entity.inventory;

import ru.fantasydestiny.fantasydestiny.item.Item;
import ru.fantasydestiny.fantasydestiny.item.ItemStack;

public class Slot {
    private final int id;
    private ItemStack itemStack;
    public Slot(int id, ItemStack itemStack) {
        this.id = id;
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
