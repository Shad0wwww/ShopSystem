package dk.shadow.shopsystem.utils;

import io.papermc.lib.PaperLib;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 @author https://gitlab.com/uhccore/uhccore/-/blob/main/src/main/java/com/gmail/val59000mc/utils/UniversalMaterial.java
 */
public enum UniversalMaterial{
    WHITE_WOOL("WOOL", "WHITE_WOOL", (short) 0),
    ORANGE_WOOL("WOOL", "ORANGE_WOOL", (short) 1),
    MAGENTA_WOOL("WOOL", "MAGENTA_WOOL", (short) 2),
    LIGHT_BLUE_WOOL("WOOL", "LIGHT_BLUE_WOOL", (short) 3),
    YELLOW_WOOL("WOOL", "YELLOW_WOOL", (short) 4),
    LIME_WOOL("WOOL", "LIME_WOOL", (short) 5),
    PINK_WOOL("WOOL", "PINK_WOOL", (short) 6),
    GRAY_WOOL("WOOL", "GRAY_WOOL", (short) 7),
    LIGHT_GRAY_WOOL("WOOL", "LIGHT_GRAY_WOOL", (short) 8),
    CYAN_WOOL("WOOL", "CYAN_WOOL", (short) 9),
    PURPLE_WOOL("WOOL", "PURPLE_WOOL", (short) 10),
    BLUE_WOOL("WOOL", "BLUE_WOOL", (short) 11),
    BROWN_WOOL("WOOL", "BROWN_WOOL", (short) 12),
    GREEN_WOOL("WOOL", "GREEN_WOOL", (short) 13),
    RED_WOOL("WOOL", "RED_WOOL", (short) 14),
    BLACK_WOOL("WOOL", "BLACK_WOOL", (short) 15);

    private final String name8, name13;
    private final short id8;

    private Material material;

    UniversalMaterial(String name8, String name13, short id8){
        this.name8 = name8;
        this.name13 = name13;
        this.id8 = id8;
    }

    UniversalMaterial(String name8, String name13){
        this.name8 = name8;
        this.name13 = name13;
        id8 = 0;
    }

    UniversalMaterial(){
        this.name8 = name();
        this.name13 = name();
        id8 = 0;
    }

    public Material getType() {
        if (material == null) {
            try {
                material = Material.valueOf(name13);
            } catch (IllegalArgumentException ignored1) {
                try {
                    material = Material.valueOf(name8);
                } catch (IllegalArgumentException ignored2) {
                    // 1.9+ item on 1.8 server.
                }
            }
        }
        return material;
    }

    public short getData(){
        return PaperLib.getMinecraftVersion() < 13 ? id8 : 0;
    }


    @SuppressWarnings("deprecation")
    public ItemStack getStack(int amount) {
        return new ItemStack(getType(), amount, getData());
    }

    public ItemStack getStack(){
        return getStack(1);
    }

    public static UniversalMaterial ofType(Material material){
        for (UniversalMaterial universalMaterial : values()){
            if (universalMaterial.getType() == material){
                return universalMaterial;
            }
        }
        return null;
    }
}
