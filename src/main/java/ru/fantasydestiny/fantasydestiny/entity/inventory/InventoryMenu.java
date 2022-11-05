package ru.fantasydestiny.fantasydestiny.entity.inventory;

import com.google.common.collect.ImmutableList;
import ru.fantasydestiny.fantasydestiny.entity.player.Player;
import ru.fantasydestiny.fantasydestiny.entity.player.PlayerEntity;
import ru.fantasydestiny.fantasydestiny.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryMenu implements Container {
    private final List<Slot> items = new ArrayList<>(30);
    private final List<Slot> armor = new ArrayList<>(3);
    private final List<Slot> weapon = new ArrayList<>(2);
    private final List<List<Slot>> compartments = ImmutableList.of(this.items, this.armor, this.weapon);
    //    private Inventory inventory;
    private PlayerEntity player;

    {
        int i = 0;
        for (List<Slot> compartment : compartments) {
            for (int j = 0; j < compartment.size(); j++) {
                compartment.add(new Slot(i, ItemStack.EMPTY));
                i++;
            }
        }
    }

    public InventoryMenu(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void setItem(int id, ItemStack itemStack) {
        List<Slot> list = null;
        int i = 0;
        for (List<Slot> compartment : compartments) {
            if (id >= compartment.size()) {
                i += compartment.size();
            } else {
                list = compartment;
            }
        }
        list.get(id).setItemStack(itemStack);
    }

    @Override
    public ItemStack getItem(int id) {
        List<Slot> list = null;
        int i = 0;
        for (List<Slot> compartment : compartments) {
            if (id >= compartment.size()) {
                i += compartment.size();
            } else {
                list = compartment;
            }
        }
        return list.get(id).getItemStack();
    }
}
