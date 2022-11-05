package ru.fantasydestiny.fantasydestiny.item;

public class ItemStack {
    public static final ItemStack EMPTY = new ItemStack(null);
    private final Item item;
    private short count;

    public ItemStack(Item item) {
        this.item = item;
    }

    public ItemStack(Item item, short count) {
        this.item = item;
        this.count = count;
    }
}
