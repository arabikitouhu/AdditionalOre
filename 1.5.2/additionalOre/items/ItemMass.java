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

public class ItemMass extends Item {

	public static final String[] uniNames = {
		"Iron", "Gold", "Lapis", "Quartz",
		"Copper", "Tin", "Uranium", "Bauxite",
		"Titanium", "Lead", "Nickel", "Silver",
		"Chrome", "Tungsten", "Iridium" };
	public static final String[] jpNames = {
		"\u9244\u306E\u584A", "\u91D1\u306E\u584A", "\u30E9\u30D4\u30B9\u30E9\u30BA\u30EA", "\u77F3\u82F1",
		"\u9285\u306E\u584A", "\u932B\u306E\u584A", "\u30A6\u30E9\u30CB\u30A6\u30E0\u306E\u584A", "\u30DC\u30FC\u30AD\u30B5\u30A4\u30C8\u306E\u584A",
		"\u30C1\u30BF\u30CB\u30A6\u30E0\u306E\u584A", "\u925B\u306E\u584A", "\u30CB\u30C3\u30B1\u30EB\u306E\u584A", "\u9280\u306E\u584A",
		"\u30AF\u30ED\u30E0\u306E\u584A", "\u30BF\u30F3\u30B0\u30B9\u30C6\u30F3\u306E\u584A", "\u30A4\u30EA\u30B8\u30A6\u30E0\u306E\u584A" };

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemMass(int id) {
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
		return "additionalOre:" + uniNames[meta] + " Mass";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[uniNames.length];
		for (int i = 0; i < uniNames.length; i++) {
			icons[i] = iconRegister.registerIcon("additionalOre:" + uniNames[i] + " Mass");
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
