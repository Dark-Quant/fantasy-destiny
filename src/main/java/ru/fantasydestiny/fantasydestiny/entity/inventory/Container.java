package ru.fantasydestiny.fantasydestiny.entity.inventory;

import ru.fantasydestiny.fantasydestiny.item.ItemStack;

public interface Container {
    void setItem(int id, ItemStack itemStack);
    ItemStack getItem(int id);
}
