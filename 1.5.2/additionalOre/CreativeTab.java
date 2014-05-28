package mods.additionalOre;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {

	String type;

	public CreativeTab(String type)
    {
		super(type);
		this.type = type;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
		if(this.type.matches("AdditonalOre : Block"))
			return new ItemStack(Block.cobblestone);
		else if(this.type.matches("AdditonalOre : Tools"))
			return new ItemStack(Item.pickaxeWood);
		else
			return new ItemStack(Item.redstone);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() { return type; }
}