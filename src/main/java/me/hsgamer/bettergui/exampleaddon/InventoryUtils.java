package me.hsgamer.bettergui.exampleaddon;

import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Optional;

public class InventoryUtils {
    /**
     * Gets the item in given slot.
     * <p>
     * The param slot accepts both {@link EquipmentSlot} name and slot index.
     *
     * @param inv      the player inventory
     * @param slot     the slot to get the item
     * @param fallback fallback item if given slot has no item
     *
     * @return the item stack in the specific slot, or the fallback if there is no item in the specific slot
     */
    public static @Nullable ItemStack getItemInSlot(@NotNull PlayerInventory inv, @NotNull String slot, @Nullable ItemStack fallback) {
        try {
            EquipmentSlot parsed = EquipmentSlot.valueOf(slot.toUpperCase(Locale.ROOT));
            return switch (parsed) {
                // held items could be AIR (not null)
                case HAND -> Optional.of(inv.getItemInMainHand())
                    .map(i -> i.getType().isAir() ? fallback : i.clone()).orElse(fallback);
                case OFF_HAND -> Optional.of(inv.getItemInOffHand())
                    .map(i -> i.getType().isAir() ? fallback : i.clone()).orElse(fallback);
                // armor items could be null
                case HEAD -> Optional.ofNullable(inv.getHelmet())
                    .map(ItemStack::clone).orElse(fallback);
                case CHEST -> Optional.ofNullable(inv.getChestplate())
                    .map(ItemStack::clone).orElse(fallback);
                case LEGS -> Optional.ofNullable(inv.getLeggings())
                    .map(ItemStack::clone).orElse(fallback);
                case FEET -> Optional.ofNullable(inv.getBoots())
                    .map(ItemStack::clone).orElse(fallback);
            };
        } catch (IllegalArgumentException e) { // not a valid equipment slot string - fallback to item slot index
            try {
                int slotIdx = Integer.parseInt(slot);
                return Optional.ofNullable(inv.getItem(slotIdx))
                    .map(ItemStack::clone).orElse(fallback);
            } catch (NumberFormatException ignored) {
                return fallback;
            }
        }
    }
}
