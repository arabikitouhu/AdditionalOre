package mods.additionalOre.items;

import mods.additionalOre.AdditionalOre;
import net.minecraft.item.Item;


public class AO_Item extends Item
{
    public AO_Item(int par1)
    {
        super(par1 -256);
        setCreativeTab(AdditionalOre.TABS_ore);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
}
