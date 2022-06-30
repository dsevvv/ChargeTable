package ca.rpgcraft.chargetable.item;

import ca.rpgcraft.chargetable.gui.creative.CreativeTabMod;
import net.minecraft.item.Item;

public enum ModItems {

    BLUE_METAL("blue_metal", 1),
    CHARGE_ITEM("charge_item"),
    GREEN_METAL("green_metal", 5),
    PURPLE_METAL("purple_metal", 100),
    RED_METAL("red_metal", 30);

    private final String name;
    private final Item item;

    private int baseCharge;

    ModItems(String name){
        this.name = name;

        if ("charge_item".equalsIgnoreCase(name)) {

            this.item = new ItemChargeItem(name);
        }
        else {

            this.item = new Item()
                    .setTranslationKey(name)
                    .setRegistryName(name)
                    .setCreativeTab(CreativeTabMod.getInstance());
        }
    }

    ModItems(String name, int baseCharge){
        this.name = name;
        this.baseCharge = baseCharge;

        switch(name){
            case "blue_metal":
                this.item = new ItemBlueMetal(name);
                break;

            case "green_metal":
                this.item = new ItemGreenMetal(name);
                break;

            case "purple_metal":
                this.item = new ItemPurpleMetal(name);
                break;

            case "red_metal":
                this.item = new ItemRedMetal(name);
                break;

            default:
                this.item = new Item()
                        .setTranslationKey(name)
                        .setRegistryName(name)
                        .setCreativeTab(CreativeTabMod.getInstance());
                break;
        }
    }

    public String getName(){
        return name;
    }

    public Item getItem(){
        return item;
    }

    public int getBaseCharge(){
        return baseCharge;
    }
}
