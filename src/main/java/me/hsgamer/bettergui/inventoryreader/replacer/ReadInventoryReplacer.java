package me.hsgamer.bettergui.inventoryreader.replacer;

import cc.mewcraft.mewcore.util.UtilComponent;
import cc.mewcraft.mewcore.util.UtilInventory;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;
import java.util.UUID;

/**
 * Usage:
 * <ol>
 *     <li>{read_type_(slot)}</li> (returns Material enum name)
 *     <li>{read_name_(slot)}</li> (returns MiniMessage string)
 * </ol>
 * <p>
 * Examples:
 * <ol>
 *     <li>{read_type_hand}</li> (reads the type of item in hand)
 *     <li>{read_name_head}</li> (reads the name of item in head)
 * </ol>
 */
public class ReadInventoryReplacer implements StringReplacer {
    @Override public String replace(final String original, final UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            return null;
        }
        PlayerInventory inventory = player.getInventory();
        if (original.startsWith("type_")) { // Read the material type of the item
            ItemStack itemInSlot = UtilInventory.getItemInSlot(inventory, original.substring(5), null);
            return itemInSlot == null ? "AIR" : itemInSlot.getType().name();
        } else if (original.startsWith("name_")) { // Read the display name of the item
            ItemStack itemInSlot = UtilInventory.getItemInSlot(inventory, original.substring(5), null);
            return Optional
                .ofNullable(itemInSlot)
                .map(i -> {
                    ItemMeta meta;
                    if (!i.hasItemMeta() || !(meta = i.getItemMeta()).hasDisplayName())
                        return UtilComponent.asMiniMessage(Component.translatable(itemInSlot.getType().translationKey()));
                    return UtilComponent.asMiniMessage(meta.displayName());
                })
                .orElse("");
        }
        return "";
    }
}
