package me.hsgamer.bettergui.inventoryreader;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import me.hsgamer.hscore.variable.VariableManager;

public final class Main extends PluginAddon {
    @Override public void onEnable() {
        VariableManager.register("readinv_", InventoryStringReplacer.READINV);
        ItemModifierBuilder.INSTANCE.register(InventoryReaderModifier::new, "read-inv", "read");
    }
}
