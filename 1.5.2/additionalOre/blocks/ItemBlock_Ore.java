package mods.additionalOre.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlock_Ore extends ItemBlock
{
    public ItemBlock_Ore(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
        setUnlocalizedName("additonalOre:BlockOre");
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return Enum_ores.VALID_ARGS[itemStack.getItemDamage()].unlocalizedName +" Ore";
    }

}
