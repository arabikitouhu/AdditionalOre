package mods.additionalOre.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class AO_ItemAxe extends AO_ItemTools
{

    private tools tools;

    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern};

    public AO_ItemAxe(int itemID, tools tools)
    {
        super(itemID, 3, tools.material, blocksEffectiveAgainst);
        this.tools = tools;
        register();
    }

    private void register()
    {
        setUnlocalizedName("additionalOre:" + tools.unlocalizedName + " Axe");
        LanguageRegistry.addName(this,tools.unlocalizedName + " Axe");
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
    }
}
