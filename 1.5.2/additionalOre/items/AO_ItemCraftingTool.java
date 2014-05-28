package mods.additionalOre.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.additionalOre.AdditionalOre;
import mods.japanAPI.JapanAPI;
import mods.japanAPI.items.JAPI_ItemCrafingTool;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


public class AO_ItemCraftingTool extends JAPI_ItemCrafingTool
{
    public AO_ItemCraftingTool(int par1, Types types, int type)
    {
        super(par1, types, type);
        register();
    }

    private void register()
    {

        GameRegistry.registerCraftingHandler(this);
        switch(toolType)
        {
            case 0:
                setUnlocalizedName("additionalOre:"  + name + " Hammer");
                GameRegistry.registerItem(this, getUnlocalizedName());
                LanguageRegistry.addName(new ItemStack(this, 1, 32767), name + "Hammer");
                OreDictionary.registerOre("craftingToolHardHammer", new ItemStack(this, 1, 32767));
                break;
            case 1:
                setUnlocalizedName("additionalOre:" + name + " Saw");
                GameRegistry.registerItem(this,getUnlocalizedName());
                LanguageRegistry.addName(new ItemStack(this,1, 32767), name + "Saw");
                OreDictionary.registerOre("craftingToolSaw", new ItemStack(this, 1, 32767));
                break;
            case 2:
                setUnlocalizedName("additionalOre:" + name + " Pickaxe");
                GameRegistry.registerItem(this,getUnlocalizedName());
                LanguageRegistry.addName(new ItemStack(this,1, 32767), name + "File");
                OreDictionary.registerOre("craftingToolFile", new ItemStack(this, 1, 32767));
                break;
            default:
        }

    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack)
    {
        EntityPlayer  player = AdditionalOre.proxy.getPlayer();
        if (itemStack != null && itemStack.itemID == this.itemID)
        {
            if(itemStack.getItemDamage() > itemStack.getMaxDamage())
            {
                player.playSound("random.break",1,0.9F + JapanAPI.RANDOM.nextFloat() * 0.2F);
            }else
            itemStack.setItemDamage(itemStack.getItemDamage() +1);
            player.playSound("random.anvil_use",0.5F,0.9F + JapanAPI.RANDOM.nextFloat() * 0.2F);
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(getUnlocalizedName().replace("item.",""));
    }
}
