package me.hsgamer.bettergui.exampleaddon;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;

public final class Main extends PluginAddon {
    @Override public void onEnable() {
        ItemModifierBuilder.INSTANCE.register(ItemReaderModifier::new, "read-player-inventory", "read-inv", "read");
    }
}
