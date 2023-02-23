package me.hsgamer.bettergui.inventoryreader.cosmetic;

import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor;
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi;
import me.hsgamer.bettergui.inventoryreader.util.CosmeticCoreUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CosmeticCoreImpl implements CosmeticProvider {
    private final CosmeticsCoreApi api;

    public CosmeticCoreImpl() {
        this.api = new CosmeticsCoreApi();
    }

    @SuppressWarnings("unchecked")
    @Override public @Nullable ItemStack getGuiItem(final @NotNull UUID uuid, final @NotNull Type type) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            return null;
        List<CosmeticAccessor> accessors = this.api.getEquippedCosmeticsAccessors(player);
        for (final CosmeticAccessor accessor : accessors) {
            Type equippedType = Optional
                .ofNullable(CosmeticCoreUtils.getTypeName(accessor))
                .map(CosmeticCoreUtils::mapType)
                .orElse(null);
            if (type == equippedType)
                return accessor.getGuiModelItem();
        }
        return null;
    }

}
