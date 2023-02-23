package me.hsgamer.bettergui.inventoryreader.modifier;

import cc.mewcraft.mewcore.util.UtilInventory;
import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Reads the item in current viewer's inventory and sets it as button item.
 */
public class ReadViewerInventoryModifier implements ItemModifier {
    private String slot = EquipmentSlot.HAND.name().toLowerCase(Locale.ROOT);

    @Override public String getName() {
        return "read-viewer-inventory";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(slot, uuid, map.values());
        Player viewer = Bukkit.getPlayer(uuid);
        if (viewer == null)
            return itemStack;
        PlayerInventory inventory = viewer.getInventory();
        return UtilInventory.getItemInSlot(inventory, replace, itemStack);
    }

    @Override public Object toObject() {
        return slot;
    }

    @Override public void loadFromObject(final Object o) {
        this.slot = String.valueOf(o);
    }

    @Override public void loadFromItemStack(final ItemStack itemStack) {
        // EMPTY
    }

    @Override public boolean compareWithItemStack(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        // EMPTY
        return true;
    }
}
