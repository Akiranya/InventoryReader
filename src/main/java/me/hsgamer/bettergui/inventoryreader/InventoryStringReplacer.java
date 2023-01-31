package me.hsgamer.bettergui.inventoryreader;

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

public class InventoryStringReplacer {
    protected static final StringReplacer READINV = (original, uuid) -> {
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
    };
}
