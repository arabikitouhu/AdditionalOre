package mods.additionalOre.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.japanAPI.JapanAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class AO_ItemPickaxe extends AO_ItemTools
{
    private tools pickaxe;

    /** an array of the blocks this pickaxe is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, Block.railActivator};

    public AO_ItemPickaxe(int itemID,tools pickaxe)
    {
        super(itemID, 1, pickaxe.material, blocksEffectiveAgainst);
        this.pickaxe = pickaxe;
        MinecraftForge.setToolClass(this,"pickaxe",toolMaterial.getHarvestLevel());
        register();
    }

    public void register()
    {
            setUnlocalizedName("additionalOre:" + pickaxe.unlocalizedName +" Pickaxe");
            LanguageRegistry.addName(this, pickaxe.unlocalizedName + " Pickaxe");
            LanguageRegistry.instance().addNameForObject(this, "ja_JP", pickaxe.jpName + "のつるはし");
            OreDictionary.registerOre("pickaxe" + pickaxe.unlocalizedName, this);
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("pickaxe" + pickaxe.unlocalizedName, new ItemStack(this,1,32767));

    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block block)
    {
        return block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (block != Block.blockDiamond && block != Block.oreDiamond ? (block != Block.oreEmerald && block != Block.blockEmerald ? (block != Block.blockGold && block != Block.oreGold ? (block != Block.blockIron && block != Block.oreIron ? (block != Block.blockLapis && block != Block.oreLapis ? (block != Block.oreRedstone && block != Block.oreRedstoneGlowing ? (block.blockMaterial == Material.rock ? true : (block.blockMaterial == Material.iron ? true : block.blockMaterial == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack itemStack, Block block)
    {
        return block != null && (block.blockMaterial == Material.iron || block.blockMaterial == Material.anvil || block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(itemStack, block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add((getMaxDamage(itemStack) - this.getDisplayDamage(itemStack)) + "/" + this.getMaxDamage(itemStack));
    }
}
