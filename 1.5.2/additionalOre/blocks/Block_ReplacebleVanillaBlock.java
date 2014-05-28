package mods.additionalOre.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.additionalOre.AdditionalOre;
import mods.japanAPI.JapanAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.ArrayList;

public class Block_ReplacebleVanillaBlock extends Block
{
    public static final String[] uniNames = { "oreGold", "oreIron", "oreEmerald", "oreLapis", "oreDiamond"};
    public static final String[] enNames = { "Gold", "Iron", "Emerald", "Lapis", "Diamond"};

    public static final String[] jpNames = { "金", "鉄", "蒼玉", "瑠璃","金剛"};

    protected ItemStack dropItem;

    public Block_ReplacebleVanillaBlock(int blockID)
    {
        super(blockID, Material.rock);
        setStepSound(soundStoneFootstep);
        setHardness(1.5F);
        setResistance(3F);
        setCreativeTab(AdditionalOre.TABS_ore);

    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return super.getIcon(par1, par2);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        super.registerIcons(iconRegister);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ItemStack item = dropItem.copy();
        item.stackSize = (fortune + 1) + JapanAPI.RANDOM.nextInt(fortune + 1);
        ret.add(item);
        return ret;
    }

    public Block setDropItem(ItemStack item)
    {
        dropItem = item;
        return this;
    }

}
