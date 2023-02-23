package me.hsgamer.bettergui.inventoryreader;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.bettergui.inventoryreader.modifier.ReadTargetInventoryModifier;
import me.hsgamer.bettergui.inventoryreader.modifier.ReadViewerInventoryModifier;
import me.hsgamer.bettergui.inventoryreader.replacer.ReadInventoryReplacer;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import me.hsgamer.hscore.variable.VariableManager;

public final class Main extends PluginAddon {
    @Override public void onEnable() {
        VariableManager.register("read_", new ReadInventoryReplacer());
        ItemModifierBuilder.INSTANCE.register(ReadViewerInventoryModifier::new, "read-viewer-inv", "read-viewer");
        ItemModifierBuilder.INSTANCE.register(ReadTargetInventoryModifier::new, "read-target-inv", "read-target");
    }
}
