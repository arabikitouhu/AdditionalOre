package mods.additionalOre.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.additionalOre.AdditionalOre;
import mods.japanAPI.JapanAPI;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemGem extends AO_Item
{

	public ItemGem(int id)
    {
		super(id);
        register();
	}

    private void register()
    {
        GameRegistry.registerItem(this, "Item Gem");
        for(Gems G : Gems.VAILD_ARGS)
        {
                LanguageRegistry.addName(new ItemStack(this, 1, G.meta), G.unlocalizedName);
                LanguageRegistry.instance().addNameForObject(new ItemStack(this, 1, G.meta), "ja_JP", G.jpName);
                OreDictionary.registerOre("gem" + G.unlocalizedName, new ItemStack(this, 1, G.meta));
                JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("gem" + G.meta, new ItemStack(this, 1, G.meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        for(Gems G :Gems.VAILD_ARGS)
        {
            G.loadTexture(iconRegister);
        }
    }

    @Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
    {
		return Gems.VAILD_ARGS[meta].texture;
	}


	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
		return "additionalOre:" + Gems.VAILD_ARGS[itemStack.getItemDamage()] + " Gem";
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list)
    {
		for (Gems G:Gems.VAILD_ARGS)
        {
			list.add(new ItemStack(id, 1, G.ordinal()));
		}
	}

    public enum Gems
    {
        RUBY("Ruby","紅玉"),
        SAPPHIRE("Sapphire","碧玉"),
        GREEN_SAPPHIRE("GreenSapphire","緑碧玉"),

        ;
        public String unlocalizedName;
        public String jpName;
        public int meta = ordinal();
        public static Gems[] VAILD_ARGS = values();

        @SideOnly(Side.CLIENT)
        public Icon texture;

        private Gems(String unlocalizedName,String jpName)
        {
            this.unlocalizedName = unlocalizedName;
            this.jpName = jpName;
        }

        public ItemStack getItemStack( )
        {
            return getItemStack(1);
        }

        public ItemStack getItemStack(int amount)
        {
            return new ItemStack(AdditionalOre.ITEM_gems,amount,meta);
        }


        public void loadTexture(IconRegister iconRegister)
        {
            texture = iconRegister.registerIcon("additionalOre:" + unlocalizedName + " Gem");
        }
    }
}
