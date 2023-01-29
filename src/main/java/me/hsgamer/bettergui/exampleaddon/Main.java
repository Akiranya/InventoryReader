package me.hsgamer.bettergui.exampleaddon;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import me.hsgamer.hscore.variable.VariableManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Main extends PluginAddon {
    @Override public void onEnable() {
        VariableManager.register("readinv_", (original, uuid) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                return null;
            }
            PlayerInventory inventory = player.getInventory();
            ItemStack itemInSlot = InventoryUtils.getItemInSlot(inventory, original, null);
            return itemInSlot == null ? "AIR" : itemInSlot.getType().name();
        });
        ItemModifierBuilder.INSTANCE.register(ItemReaderModifier::new, "read-player-inventory", "read-inv", "read");
    }
}
