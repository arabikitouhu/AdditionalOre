package mods.additionalOre.items;

import java.util.List;

import mods.additionalOre.AdditionalOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGem extends Item {

	public static final String[] uniNames = { "Ruby", "Sapphire" };
	public static final String[] jpNames = { "\u30EB\u30D3\u30FC", "\u30B5\u30D5\u30A1\u30A4\u30A2" };

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemGem(int id) {
		super(id - 256);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(AdditionalOre.TABS_ore);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int meta) {
		return icons[MathHelper.clamp_int(meta, 0, uniNames.length)];
	}


	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, uniNames.length);
		return "additionalOre:" + uniNames[meta] + " Gem";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[uniNames.length];
		for (int i = 0; i < uniNames.length; i++) {
			icons[i] = iconRegister.registerIcon("additionalOre:" + uniNames[i] + " Gem");
		}

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < uniNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

}
