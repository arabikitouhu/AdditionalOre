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

public class ItemNugget extends Item {

	public static final String[] uniNames = {
		"Iron", "Lapis", "Emerald", "Diamond",
		"Quartz", "Copper", "Tin", "Uranium",
		"Aluminium", "Bronze", "Steel", "Titanium",
		"Lead", "Nickel", "Silver", "Chrome",
		"Tungsten", "Iridium" };
	public static final String[] jpNames = {
		"\u9244\u306E\u304B\u3051\u3089", "\u30E9\u30D4\u30B9\u30E9\u30BA\u30EA\u306E\u304B\u3051\u3089", "\u30A8\u30E1\u30E9\u30EB\u30C9\u306E\u304B\u3051\u3089", "\u30C0\u30A4\u30E4\u30E2\u30F3\u30C9\u306E\u304B\u3051\u3089",
		"\u77F3\u82F1\u306E\u304B\u3051\u3089", "\u9285\u306E\u304B\u3051\u3089", "\u932B\u306E\u304B\u3051\u3089", "\u30A6\u30E9\u30CB\u30A6\u30E0\u306E\u304B\u3051\u3089",
		"\u30A2\u30EB\u30DF\u30CB\u30A6\u30E0\u306E\u304B\u3051\u3089", "\u9752\u9285\u306E\u304B\u3051\u3089", "\u92FC\u306E\u304B\u3051\u3089", "\u30C1\u30BF\u30CB\u30A6\u30E0\u306E\u304B\u3051\u3089",
		"\u925B\u306E\u304B\u3051\u3089", "\u30CB\u30C3\u30B1\u30EB\u306E\u304B\u3051\u3089", "\u9280\u306E\u304B\u3051\u3089", "\u30AF\u30ED\u30E0\u306E\u304B\u3051\u3089",
		"\u30BF\u30F3\u30B0\u30B9\u30C6\u30F3\u306E\u304B\u3051\u3089", "\u30A4\u30EA\u30B8\u30A6\u30E0\u306E\u304B\u3051\u3089"};

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemNugget(int id) {
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
		return "additionalOre:" + uniNames[meta] + " Nugget";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[uniNames.length];
		for (int i = 0; i < uniNames.length; i++) {
			icons[i] = iconRegister.registerIcon("additionalOre:" + uniNames[i] + " Nugget");
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
