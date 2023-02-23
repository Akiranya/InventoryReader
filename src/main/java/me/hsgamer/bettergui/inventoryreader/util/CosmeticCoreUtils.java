package me.hsgamer.bettergui.inventoryreader.util;

import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor;
import me.hsgamer.bettergui.inventoryreader.cosmetic.CosmeticProvider;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * This class contains awful reflection code to handle the unfinished API of CosmeticsCore.
 * <p>
 * It should not be used as soon as the API of CosmeticsCore is improved.
 */
public class CosmeticCoreUtils {

    private CosmeticCoreUtils() {
        throw new UnsupportedOperationException();
    }

    public static CosmeticProvider.@NonNull Type mapType(String typeName) {
        if (typeName.contains("HAT")) {
            return CosmeticProvider.Type.HAT;
        } else if (typeName.contains("BODY")) {
            return CosmeticProvider.Type.BODY;
        } else if (typeName.contains("BALLOON")) {
            return CosmeticProvider.Type.BALLOON;
        }
        throw new IllegalStateException("cannot map \"" + typeName + "\" to " + CosmeticProvider.Type.class.getName());
    }

    public static @Nullable String getTypeName(CosmeticAccessor accessor) {
        try {
            // getter of InternalSettings
            Method settings = accessor.getClass().getDeclaredMethod("getInternalSettings");
            settings.setAccessible(true);

            // InternalSettings is abstract, inherited by 4 cosmetic classes (hat, body item, body entity, balloon)
            Object cosmeticObject = settings.invoke(accessor);

            // the field stores cosmetic enum type, such as "HAT", "BODY_ITEM", etc.
            Field cosmeticTypeField = cosmeticObject.getClass().getFields()[0];

            // get the value of the field
            Object cosmeticTypeFieldValue = cosmeticTypeField.get(cosmeticObject);

            // it's an enum, so toString() is its enum name
            return cosmeticTypeFieldValue.toString();
        } catch (Throwable e) {
            return null;
        }
    }

}
