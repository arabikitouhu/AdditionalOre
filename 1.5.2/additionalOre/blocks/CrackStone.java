package mods.additionalOre.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.additionalOre.AdditionalOre;
import mods.additionalOre.init.ConfigurationManager;
import mods.japanAPI.JapanAPI;
import mods.japanAPI.utils.OreDictionaryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CrackStone extends Block {

	public CrackStone(int blockID)
    {
		super(blockID, Material.rock);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName("additionalOre:CrackStone");
		setHardness(1.5F);
		setResistance(3.0F);
		setCreativeTab(AdditionalOre.TABS_ore);

	}

    @Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) { return super.getIcon(par1, par2); }

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) { super.registerIcons(par1IconRegister); }

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		if(fortune > 5)
            fortune = 5;

		//共通
		ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune * 2 + 1), 0));	//鉄
		ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune * 2 + 1), 1));	//金
		ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune * 2 + 1), 4));	//銅
		ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune * 2 + 1), 5));	//錫


		if(y >= 41 && y <= 99)
        {	//中間層
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 3 * fortune)
            {
                //銀(20%)
                ret.add(OreDictionaryUtil.getOreDicItemStack("massSilver", JapanAPI.RANDOM.nextInt(fortune * 2 + 1)));
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 40 + 3 * fortune)
            {
                //ニッケル(40%)
				ret.add(OreDictionaryUtil.getOreDicItemStack("massNickel", JapanAPI.RANDOM.nextInt(fortune * 2 + 1)));
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 5 + 2 * fortune)
            {
                //ダイヤモンド(5%)
				ret.add(new ItemStack(Item.diamond, JapanAPI.RANDOM.nextInt(fortune + 1)));
			}
		}

        //下層
		if(y < 41)
        {
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 2 * fortune)
            {
				ret.add(OreDictionaryUtil.getOreDicItemStack("massTitanium", JapanAPI.RANDOM.nextInt(fortune + 1)));	//チタニウム(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 2 * fortune)
            {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 1), 12));	//クロム(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 40 + 2 * fortune) {
				ret.add(new ItemStack(Item.emerald, JapanAPI.RANDOM.nextInt(fortune + 1)));		//エメラルド(40%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 40 + 2 * fortune) {
				ret.add(new ItemStack(Item.diamond, JapanAPI.RANDOM.nextInt(fortune + 1)));		//ダイヤモンド(40%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 40 + 4 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_gems, JapanAPI.RANDOM.nextInt(fortune + 1), 0));		//ルビー(40%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 40 + 4 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_gems, JapanAPI.RANDOM.nextInt(fortune + 1), 1));		//サファイア(40%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 5) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(1), 13));		//タングステン(5%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 3) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(1), 14));		//イリジウム(3%)
			}

		} else if(y >= 100 && y < 200) {	//上層
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 30 + 2 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 1), 8));	//チタニウム(30%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 30 + 2 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 1), 12));	//クロム(30%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 2 * fortune) {
				ret.add(new ItemStack(Item.emerald, JapanAPI.RANDOM.nextInt(fortune + 1)));		//エメラルド(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 2 * fortune) {
				ret.add(new ItemStack(Item.diamond, JapanAPI.RANDOM.nextInt(fortune + 1)));		//ダイヤモンド(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 50 + 4 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_gems, JapanAPI.RANDOM.nextInt(fortune + 1), 0));		//ルビー(50%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 50 + 4 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_gems, JapanAPI.RANDOM.nextInt(fortune + 1), 1));		//サファイア(50%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 5 + fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune), 13));		//タングステン(5%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 3 + fortune)
            {
				ret.add(OreDictionaryUtil.getOreDicItemStack("massIridium", JapanAPI.RANDOM.nextInt(fortune)));		//イリジウム(3%)
			}

		} else {	//最上層
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 30 + 2 * fortune)
            {
                ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 3), 8));	//チタニウム(30%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 30 + 2 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 3), 12));	//クロム(30%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 2 * fortune) {
				ret.add(new ItemStack(Item.emerald, JapanAPI.RANDOM.nextInt(fortune + 3)));		//エメラルド(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 2 * fortune) {
				ret.add(new ItemStack(Item.diamond, JapanAPI.RANDOM.nextInt(fortune + 3)));		//ダイヤモンド(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 4 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_gems, JapanAPI.RANDOM.nextInt(fortune + 3), 0));		//ルビー(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 20 + 4 * fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_gems, JapanAPI.RANDOM.nextInt(fortune + 3), 1));		//サファイア(20%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 25 + fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 1), 13));	//タングステン(25%)
			}
			if(JapanAPI.RANDOM.nextInt(101) % 100 < 15 + fortune) {
				ret.add(new ItemStack(ConfigurationManager.ID_masses, JapanAPI.RANDOM.nextInt(fortune + 1), 13));	//イリジウム(15%)
			}
		}


		return ret;
	}

	//置き換えるブロックの指定
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		int fortune = EnchantmentHelper.getFortuneModifier(player);
		int meta = world.getBlockMetadata(x, y, z);
		if(fortune > 0)
        {
            if(JapanAPI.RANDOM.nextInt(101) * 0.01F > fortune * 0.1F)
            {
                meta--;
            }
		}
        if(fortune > 5)
        {
            if(JapanAPI.RANDOM.nextInt(101) * 0.01F > 5 * 0.1F)
            {
                meta--;
            }
        }
        else
        {
			meta--;
		}
		if(meta > 0)
        {
            world.setBlock(x, y, z, blockID);
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            return true;
		}
        return world.setBlockToAir(x, y, z);
    }

	//システムによって設置された場合
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        int i;
		do
        {
            i = JapanAPI.RANDOM.nextInt(6);
		}
        while(i < 3);
        world.setBlockMetadataWithNotify(x, y, z, i, 2);
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return false;
    }

}
