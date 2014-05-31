package mods.additionalOre.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class AO_Sickel extends AO_ItemTools
{

    int radiusCrops = 2;
    int radiusLaves = 1;
    private String name;
    protected  int RADIUS = 10;
    public AO_Sickel(int itemId,EnumToolMaterial enumToolMaterial,String name)
    {
        super(itemId, 2, enumToolMaterial, new Block[0]);
        setMaxStackSize(1);
        this.name = name;
        register();
    }

    private void register()
    {
        setUnlocalizedName("additionalOre:"+ name + " Sickel");
        GameRegistry.registerItem(this,getUnlocalizedName());
        LanguageRegistry.addName(this,name + " Sickel");
    }

    @Override
    public float getStrVsBlock(ItemStack itemStack,Block block)
    {
        if(block instanceof BlockLeaves)
            return this.efficiencyOnProperMaterial;
        return super.getStrVsBlock(itemStack,block);
    }

    public boolean recurseLeaves(ItemStack itemStack,World world,int x,int y,int z,EntityPlayer player)
    {
        boolean used = false;

        for(int i = -radiusLaves; i <= radiusLaves; i++){
            for(int j = -radiusLaves; j <= radiusLaves; j++){
                for(int k = -radiusLaves; k<= radiusLaves; k++){
                    int localX = x + i;
                    int localY = y + j;
                    int localZ = z + k;
                    int id = world.getBlockId(localX,localY,localZ);
                    int meta = world.getBlockMetadata(localX,localY,localZ);
                    Block localBlock = Block.blocksList[id];

                    if(localBlock != null && (localBlock.isLeaves(world, localX,localY,localZ)||localBlock instanceof BlockLeaves))
                    {
                        if(localBlock.canHarvestBlock(player,meta)){
                            localBlock.harvestBlock(world,player,localX,localY,localZ,meta);
                            world.setBlock(localX,localY,localZ,0);
                            used = true;
                        }
                    }
                }
            }
        }
        if(used)
            itemStack.damageItem(1,player);
        return used;
    }

    public boolean recurseCrops(ItemStack stack, World w, int x, int y, int z, EntityPlayer player)
    {
        boolean used = false;
        for (int i = -radiusCrops; i <= radiusCrops; i++)
            for (int j = -radiusCrops; j <= radiusCrops; j++)
            {
                int localX = x + i;
                int localY = y;
                int localZ = z + j;
                int id = w.getBlockId(localX, localY, localZ);
                int meta = w.getBlockMetadata(localX, localY, localZ);
                Block localBlock = Block.blocksList[id];
                if (localBlock != null && (localBlock instanceof BlockFlower))
                {
                    if (localBlock.canHarvestBlock(player, meta))
                        localBlock.harvestBlock(w, player, localX, localY, localZ, meta);
                    w.setBlock(localX, localY, localZ, 0);
                    used = true;
                }
            }
        if (used)
            stack.damageItem(1, player);
        return used;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemStack,World world,int blockID,int x,int y,int z,EntityLiving entityLiving)
    {
        EntityPlayer player;
        if(entityLiving instanceof EntityPlayer)
            player =(EntityPlayer)entityLiving;
        else
            return false;

        Block block = Block.blocksList[blockID];
        if(block != null)
        {
            if(block.isLeaves(world,x,y,z))

                return recurseLeaves(itemStack,world,x,y,z,player);

            if(block instanceof BlockFlower)
                return recurseCrops(itemStack,world,x,y,z,player);
        }
        return super.onBlockDestroyed(itemStack, world, blockID, x, y, z, player);
    }
}