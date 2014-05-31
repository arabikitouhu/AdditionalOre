package mods.additionalOre.items;


import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.additionalOre.AdditionalOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class AO_ItemPaxel extends AO_ItemTools
{
    int radiusCrops = 2;
    int radiusLaves = 1;
    private String name;

    public AO_ItemPaxel(int ItemID,EnumToolMaterial enumToolMaterial,String name)
    {
        super(ItemID, 3, enumToolMaterial, new Block[4096]);
        this.name = name;
        MinecraftForge.setToolClass(this,"paxel",enumToolMaterial.getHarvestLevel());
        setCreativeTab(AdditionalOre.TABS_ore);
        register();
    }

    private void register()
    {
        setUnlocalizedName("additionalOre:" + name +" Paxel");
        GameRegistry.registerItem(this,getUnlocalizedName());
        LanguageRegistry.addName(this,name + " Paxel");
    }

    @Override
    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return block.blockID != Block.bedrock.blockID ? efficiencyOnProperMaterial : 1.0F;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        if(ForgeHooks.isToolEffective(stack, block, meta))
        {
            return efficiencyOnProperMaterial;
        }

        return getStrVsBlock(stack, block);
    }

    @Override
    public boolean canHarvestBlock(Block block)
    {
        if(block == Block.obsidian)
        {
            return toolMaterial.getHarvestLevel() == 3;
        }

        if(block == Block.blockDiamond || block == Block.oreDiamond)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }

        if(block == Block.blockGold || block == Block.oreGold)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }

        if(block == Block.blockIron || block == Block.oreIron)
        {
            return toolMaterial.getHarvestLevel() >= 1;
        }

        if(block == Block.blockLapis || block == Block.oreLapis)
        {
            return toolMaterial.getHarvestLevel() >= 1;
        }

        if(block == Block.oreRedstone || block == Block.oreRedstoneGlowing)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }

        if(block.blockMaterial == Material.rock)
        {
            return true;
        }

        return block.blockMaterial == Material.iron;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Event.Result.ALLOW)
            {
                par1ItemStack.damageItem(1, par2EntityPlayer);
                return true;
            }

            int i1 = par3World.getBlockId(par4, par5, par6);
            boolean air = par3World.isAirBlock(par4, par5 + 1, par6);

            if (par7 == 0 || !air || (i1 != Block.grass.blockID && i1 != Block.dirt.blockID))
            {
                return false;
            }
            else
            {
                Block block = Block.tilledField;
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                if (par3World.isRemote)
                {
                    return true;
                }
                else
                {
                    par3World.setBlock(par4, par5, par6, block.blockID);
                    par1ItemStack.damageItem(1, par2EntityPlayer);
                    return true;
                }
            }
        }
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
