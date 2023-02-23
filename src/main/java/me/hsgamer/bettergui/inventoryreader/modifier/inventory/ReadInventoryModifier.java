package me.hsgamer.bettergui.inventoryreader.modifier.inventory;

import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

/**
 * Reads the item in player's inventory and sets it as menu button item.
 */
public abstract class ReadInventoryModifier implements ItemModifier {
    protected String slot = EquipmentSlot.HAND.name();

    @Override public Object toObject() {
        return this.slot;
    }

    @Override public void loadFromObject(final Object o) {
        this.slot = String.valueOf(o);
    }

    @Override public void loadFromItemStack(final ItemStack itemStack) {
        // EMPTY
    }

    @Override public boolean compareWithItemStack(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        return true; // EMPTY
    }
}