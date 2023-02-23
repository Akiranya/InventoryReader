package me.hsgamer.bettergui.inventoryreader.modifier.inventory;

import cc.mewcraft.mewcore.util.UtilInventory;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

/**
 * Reads the item in target player's inventory (if online).
 */
public class ReadTargetInventoryModifier extends ReadInventoryModifier {

    @Override public String getName() {
        return "read-target-inventory";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(this.slot, uuid, map.values());
        String[] parts = replace.split(";", 2);
        if (parts.length != 2)
            return itemStack; // the option format must be: {slot_name}:{player_name}
        String slotName = parts[0];
        String targetName = parts[1];
        Player targetPlayer = Bukkit.getPlayer(targetName);
        if (targetPlayer == null)
            return itemStack; // target player is not online - don't modify the item
        return UtilInventory.getItemInSlot(targetPlayer.getInventory(), slotName, itemStack);
    }

}
