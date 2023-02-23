package me.hsgamer.bettergui.inventoryreader.modifier.cosmetic;

import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ReadViewerCosmeticsModifier extends ReadCosmeticModifier {

    @Override public String getName() {
        return "read-target-cosmetics";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(this.type, uuid, map.values());
        Player viewer = Bukkit.getPlayer(uuid);
        if (viewer == null)
            return itemStack; // player is not online - don't modify the item
        ItemStack cosItem = getCosmeticProvider().getGuiItem(viewer, replace);
        return Optional.ofNullable(cosItem).orElse(itemStack);
    }

}