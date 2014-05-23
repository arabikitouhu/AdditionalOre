package mods.additionalOre.blocks;

import java.util.ArrayList;

import mods.additionalOre.AdditionalOre;
import mods.japanAPI.JapanAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OreBlock extends Block {

	public static final String[] uniNames = { "oreGold", "oreIron", "oreEmerald", "oreLapis", "oreDiamond", "additionalOre:Quartz Ore", "additionalOre:Copper Ore",
											"additionalOre:Tin Ore", "additionalOre:Uranium Ore", "additionalOre:Bauxite Ore", "additionalOre:Lead Ore", "additionalOre:Nickel Ore",
											"additionalOre:Silver Ore"};
	public static final String[] enNames = { "Gold", "Iron", "Emerald", "Lapis", "Diamond", "Quartz", "Copper", "Tin", "Uranium", "Bauxite", "Lead", "Nickel", "Silver" };
	public static final String[] jpNames = { "\u91D1\u9271\u77F3", "\u9244\u9271\u77F3", "\u30A8\u30E1\u30E9\u30EB\u30C9\u9271\u77F3", "\u30E9\u30D4\u30B9\u30E9\u30BA\u30EA\u9271\u77F3",
											"\u30C0\u30A4\u30E4\u30E2\u30F3\u30C9\u9271\u77F3", "\u30AF\u30A9\u30FC\u30C4\u9271\u77F3", "\u9285\u9271\u77F3", "\u932B\u9271\u77F3",
											"\u30A6\u30E9\u30CB\u30A6\u30E0\u9271\u77F3", "\u30DC\u30FC\u30AD\u30B5\u30A4\u30C8\u9271\u77F3", "\u925B\u9271\u77F3", "\u30CB\u30C3\u30B1\u30EB\u9271\u77F3",
											"\u9280\u9271\u77F3" };


	protected ItemStack dropItem;

	public OreBlock(int blockID) {
		super(blockID, Material.rock);
		setStepSound(soundStoneFootstep);
		setHardness(1.5f);
		setResistance(3.0f);
		setCreativeTab(AdditionalOre.TABS_ore);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2) { return super.getIcon(par1, par2); }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) { super.registerIcons(par1IconRegister); }

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ItemStack item = dropItem.copy();
		item.stackSize = (fortune + 1) + JapanAPI.RANDOM.nextInt(fortune + 1);
		ret.add(item);
		return ret;
	}

	public Block setDropItem(ItemStack item) {
		dropItem = item;
		return this;
	}
}
