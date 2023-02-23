package me.hsgamer.bettergui.inventoryreader.modifier.cosmetic;

import me.hsgamer.bettergui.inventoryreader.cosmetic.CosmeticProvider;
import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public abstract class ReadCosmeticModifier implements ItemModifier {
    private final CosmeticProvider cosmetics;
    protected String type = CosmeticProvider.Type.HAT.name();

    public ReadCosmeticModifier() {
        this.cosmetics = CosmeticProvider.get();
    }

    protected CosmeticProvider getCosmeticProvider() {
        return this.cosmetics;
    }

    @Override public Object toObject() {
        return this.type;
    }

    @Override public void loadFromObject(final Object o) {
        this.type = String.valueOf(o);
    }

    @Override public void loadFromItemStack(final ItemStack itemStack) {
        // NOT USED
    }

    @Override public boolean compareWithItemStack(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        return true; // NOT USED
    }
}
