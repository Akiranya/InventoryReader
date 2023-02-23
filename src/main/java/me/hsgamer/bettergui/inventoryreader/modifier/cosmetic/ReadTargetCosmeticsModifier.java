package me.hsgamer.bettergui.inventoryreader.modifier.cosmetic;

import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ReadTargetCosmeticsModifier extends ReadCosmeticModifier {

    @Override public String getName() {
        return "read-target-cosmetics";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(this.type, uuid, map.values());
        String[] parts = replace.split(";", 2);
        if (parts.length != 2)
            return itemStack; // the option format must be: {player_name}:{slot_name}
        String typeName = parts[0];
        String targetName = parts[1];
        Player targetPlayer = Bukkit.getPlayer(targetName);
        if (targetPlayer == null)
            return itemStack; // target player is not online - simply don't modify the item
        ItemStack cosItem = getCosmeticProvider().getGuiItem(targetPlayer, typeName);
        return Optional.ofNullable(cosItem).orElse(itemStack);
    }

}
