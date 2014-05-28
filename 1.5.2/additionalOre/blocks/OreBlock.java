package mods.additionalOre.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.additionalOre.AdditionalOre;
import mods.japanAPI.JapanAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class OreBlock extends Block
{

	protected ItemStack dropItem;

	public OreBlock(int blockID)
    {
        super(blockID, Material.rock);
		setStepSound(soundStoneFootstep);
		setHardness(1.5F);
		setResistance(3F);
		setCreativeTab(AdditionalOre.TABS_ore);
        Register();
	}

    public void Register()
    {
        GameRegistry.registerBlock(this,ItemBlock_Ore.class,"OreBlock");
        for(Enum_ores O: Enum_ores.VALID_ARGS)
        {
            LanguageRegistry.addName(new ItemStack(this,1,O.meta),O.unlocalizedName);
            LanguageRegistry.instance().addNameForObject(new ItemStack(this,1,O.meta),"ja_JP",O.jpNames + "鉱石");
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ore" + O.unlocalizedName, new ItemStack(this,1,O.meta));
        }
    }


    public String getUnlocalizedName(ItemStack itemStack)
    {
        return Enum_ores.VALID_ARGS[itemStack.getItemDamage()].unlocalizedName;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta)
    {
        return Enum_ores.VALID_ARGS[meta].texture;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
        for(Enum_ores O : Enum_ores.VALID_ARGS)
        {
            O.loadTextures(iconRegister);
        }
    }


	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack>list = new ArrayList<ItemStack>();
        Enum_ores ore = Enum_ores.VALID_ARGS[metadata];


        int count = (fortune + 1) + JapanAPI.RANDOM.nextInt(fortune + 1);




        dropXpOnBlockBreak(world,x,y,z, MathHelper.getRandomIntegerInRange(JapanAPI.RANDOM,ore.MIN_EXP,ore.MAX_EXP));
        list.add(ore.drop == null ? ore.getItemStack() : ore.getDropStack(count));


		return list;
	}


    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockid, CreativeTabs creativeTab,List list)
    {
        for(Enum_ores O : Enum_ores.VALID_ARGS)
        {
            list.add(new ItemStack(this,1,O.meta));
        }
    }
}
