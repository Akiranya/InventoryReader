package me.hsgamer.bettergui.exampleaddon;

import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ItemReaderModifier implements ItemModifier {
    private String slot = EquipmentSlot.HAND.name();

    @Override public String getName() {
        return "item-reader";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replace = StringReplacer.replace(slot, uuid, map.values());
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            return itemStack;
        PlayerInventory inv = player.getInventory();
        try {
            EquipmentSlot parsed = EquipmentSlot.valueOf(replace.toUpperCase(Locale.ROOT));
            return switch (parsed) {
                // held items could be AIR (not null)
                case HAND ->
                    Optional.of(inv.getItemInMainHand()).map(i -> i.getType().isAir() ? itemStack : i.clone()).orElse(itemStack);
                case OFF_HAND ->
                    Optional.of(inv.getItemInOffHand()).map(i -> i.getType().isAir() ? itemStack : i.clone()).orElse(itemStack);

                // armor items could be null
                case HEAD -> Optional.ofNullable(inv.getHelmet()).map(ItemStack::clone).orElse(itemStack);
                case CHEST -> Optional.ofNullable(inv.getChestplate()).map(ItemStack::clone).orElse(itemStack);
                case LEGS -> Optional.ofNullable(inv.getLeggings()).map(ItemStack::clone).orElse(itemStack);
                case FEET -> Optional.ofNullable(inv.getBoots()).map(ItemStack::clone).orElse(itemStack);
            };
        } catch (IllegalArgumentException e) { // not a valid equipment slot string - fallback to item slot index
            try {
                int slotIdx = Integer.parseInt(replace);
                return Optional.ofNullable(inv.getItem(slotIdx))
                    .map(ItemStack::clone)
                    .orElse(itemStack);
            } catch (NumberFormatException ignored) {
                return itemStack;
            }
        }
    }

    @Override public Object toObject() {
        return slot;
    }

    @Override public void loadFromObject(final Object o) {
        this.slot = String.valueOf(o);
    }

    @Override public void loadFromItemStack(final ItemStack itemStack) {
        // EMPTY
    }

    @Override public boolean compareWithItemStack(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        // EMPTY
        return true;
    }
}
