package me.hsgamer.bettergui.inventoryreader.modifier;

import cc.mewcraft.mewcore.util.UtilInventory;
import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * The same as {@link ReadViewerInventoryModifier} but it reads the target player's inventory (if it's online).
 */
public class ReadTargetInventoryModifier implements ItemModifier {
    private String slot = EquipmentSlot.HAND.name().toLowerCase(Locale.ROOT);

    @Override public String getName() {
        return "read-target-inventory";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(slot, uuid, map.values());
        String[] parts = replace.split(";", 2);
        if (parts.length != 2)
            return itemStack; // the option format must be: {player_name}:{slot_name}
        String slotName = parts[0];
        String targetName = parts[1];
        Player targetPlayer = Bukkit.getPlayer(targetName);
        if (targetPlayer == null)
            return itemStack; // target player is not online - simply don't modify the item
        return UtilInventory.getItemInSlot(targetPlayer.getInventory(), slotName, itemStack);
    }

    @Override public Object toObject() {
        return slot;
    }

    @Override public void loadFromObject(final Object o) {
        this.slot = String.valueOf(o);
    }

    @Override public void loadFromItemStack(final ItemStack itemStack) {}

    @Override public boolean compareWithItemStack(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {return true;}
}
