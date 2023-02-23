package me.hsgamer.bettergui.inventoryreader;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.bettergui.inventoryreader.modifier.cosmetic.ReadTargetCosmeticsModifier;
import me.hsgamer.bettergui.inventoryreader.modifier.cosmetic.ReadViewerCosmeticsModifier;
import me.hsgamer.bettergui.inventoryreader.modifier.inventory.ReadTargetInventoryModifier;
import me.hsgamer.bettergui.inventoryreader.modifier.inventory.ReadViewerInventoryModifier;
import me.hsgamer.bettergui.inventoryreader.replacer.ReadInventoryReplacer;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import me.hsgamer.hscore.variable.VariableManager;

public final class Main extends PluginAddon {
    @Override public void onEnable() {
        VariableManager.register("read_", new ReadInventoryReplacer());
        ItemModifierBuilder.INSTANCE.register(ReadViewerInventoryModifier::new, "read-viewer");
        ItemModifierBuilder.INSTANCE.register(ReadTargetInventoryModifier::new, "read-target");
        ItemModifierBuilder.INSTANCE.register(ReadViewerCosmeticsModifier::new, "read-viewer-cos");
        ItemModifierBuilder.INSTANCE.register(ReadTargetCosmeticsModifier::new, "read-target-cos");
    }
}
