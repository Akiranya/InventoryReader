package me.hsgamer.bettergui.inventoryreader.cosmetic;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EmptyCosmeticImpl implements CosmeticProvider {
    private final ItemStack empty = new ItemStack(Material.BARRIER);

    @Override public @NonNull ItemStack getGuiItem(final @NotNull UUID player, final @NotNull Type type) {
        return this.empty;
    }
}
