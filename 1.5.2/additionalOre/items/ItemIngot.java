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

public class ItemIngot extends Item {

	public static final String[] uniNames = {
		"Copper", "Tin", "Uranium", "Aluminium",
		"Bronze", "Steel", "Titanium", "Lead",
		"Nickel", "Silver", "Chrome", "Tungsten",
		"Iridium" };
	public static final String[] jpNames = {
		"\u9285\u30A4\u30F3\u30B4\u30C3\u30C8", "\u932B\u30A4\u30F3\u30B4\u30C3\u30C8", "\u30A6\u30E9\u30F3\u30A4\u30F3\u30B4\u30C3\u30C8", "\u30A2\u30EB\u30DF\u30CB\u30A6\u30E0\u30A4\u30F3\u30B4\u30C3\u30C8",
		"\u9752\u9285\u30A4\u30F3\u30B4\u30C3\u30C8", "\u92FC\u30A4\u30F3\u30B4\u30C3\u30C8", "\u30C1\u30BF\u30CB\u30A6\u30E0\u30A4\u30F3\u30B4\u30C3\u30C8", "\u925B\u306E\u30A4\u30F3\u30B4\u30C3\u30C8",
		"\u30CB\u30C3\u30B1\u30EB\u30A4\u30F3\u30B4\u30C3\u30C8", "\u9280\u30A4\u30F3\u30B4\u30C3\u30C8", "\u30AF\u30ED\u30E0\u30A4\u30F3\u30B4\u30C3\u30C8", "\u30BF\u30F3\u30B0\u30B9\u30C6\u30F3\u30A4\u30F3\u30B4\u30C3\u30C8",
		"\u30A4\u30EA\u30B8\u30A6\u30E0\u30A4\u30F3\u30B4\u30C3\u30C8"};

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemIngot(int id) {
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
		return "additionalOre:" + uniNames[meta] + " Ingot";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[uniNames.length];
		for (int i = 0; i < uniNames.length; i++) {
			icons[i] = iconRegister.registerIcon("additionalOre:" + uniNames[i] + " Ingot");
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
