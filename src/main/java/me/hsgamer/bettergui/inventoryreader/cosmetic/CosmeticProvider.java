package me.hsgamer.bettergui.inventoryreader.cosmetic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Locale;
import java.util.UUID;

/**
 *
 */
public interface CosmeticProvider {

    static @NonNull CosmeticProvider get() {
        if (Bukkit.getServer().getPluginManager().getPlugin("CosmeticsCore") != null)
            return new CosmeticCoreImpl();
        else
            return new EmptyCosmeticImpl();
    }

    /**
     * Gets the GUI item of the cosmetics the player is currently wearing.
     *
     * @param player the player who is equipping a cosmetic (or not)
     * @param type   the type of the cosmetic to get
     *
     * @return the GUI item of the cosmetics if there is any; otherwise AIR
     */
    @Nullable ItemStack getGuiItem(@NonNull UUID player, CosmeticProvider.@NonNull Type type);

    default @Nullable ItemStack getGuiItem(@NonNull Player player, @NonNull String type) {
        try {
            return getGuiItem(player.getUniqueId(), Type.valueOf(type.toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    enum Type {
        HAT,
        BODY,
        OFF_HAND,
        BALLOON
    }
}
