package me.hsgamer.bettergui.inventoryreader.modifier.inventory;

import cc.mewcraft.mewcore.util.UtilInventory;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;
import java.util.UUID;

/**
 * Reads the item in current viewer's inventory.
 */
public class ReadViewerInventoryModifier extends ReadInventoryModifier {

    @Override public String getName() {
        return "read-viewer-inventory";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(this.slot, uuid, map.values());
        Player viewer = Bukkit.getPlayer(uuid);
        if (viewer == null)
            return itemStack; // player is not online - don't modify the item
        PlayerInventory inventory = viewer.getInventory();
        return UtilInventory.getItemInSlot(inventory, replace, itemStack);
    }

}
