package mods.additionalOre.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;


public class AO_ItemShovel extends AO_ItemTools
{

    private tools tools;

    /** an array of the blocks this spade is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

    public AO_ItemShovel(int itemID, tools tools)
    {
        super(itemID, 2, tools.material, blocksEffectiveAgainst);
        this.tools = tools;
        register();
    }

    private void register()
    {
        setUnlocalizedName("additionalOre:" + tools.unlocalizedName + " Shovel");
        LanguageRegistry.addName(this,tools.unlocalizedName + " Shovel");
    }

    @Override
    public boolean canHarvestBlock(Block block)
    {
        return block == Block.snow ? true : block == Block.blockSnow;
    }
}
