package me.hsgamer.bettergui.itemreader;

import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;
import java.util.UUID;

public class ItemReaderModifier implements ItemModifier {
    private String slot = EquipmentSlot.HAND.name();

    @Override public String getName() {
        return "item-reader";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(slot, uuid, map.values());
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            return itemStack;
        PlayerInventory inventory = player.getInventory();
        return InventoryUtils.getItemInSlot(inventory, replace, itemStack);
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
