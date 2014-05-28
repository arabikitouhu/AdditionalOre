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

public class ItemMass extends AO_Item
{

	public ItemMass(int id)
    {
		super(id);
        Register();
	}

    public void Register()
    {
        GameRegistry.registerItem(this,"Item Mass");
        for(Mass E : Mass.values())
        {

                LanguageRegistry.addName(new ItemStack(this,1,E.ordinal()),E.unlocalizedName + " mass");
                LanguageRegistry.instance().addNameForObject(new ItemStack(this, 1, E.ordinal()), "ja_JP", E.jpName + "の塊");
                OreDictionary.registerOre("mass" + E.unlocalizedName, new ItemStack(this, 1, E.ordinal()));
                JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("mass" + E.unlocalizedName, new ItemStack(this, 1, E.ordinal()));


        }
    }

    @Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
    {
		return Mass.values()[meta].texture;
	}


	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        return Mass.values()[itemStack.getItemDamage()].unlocalizedName + " Mass";
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
    {
		for (Mass M : Mass.values())
        {
			M.loadTexture(iconRegister);
		}

	}

    @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list)
    {
		for (Mass M : Mass.values())
        {
			list.add(new ItemStack(id, 1, M.ordinal()));
		}
	}


    public enum Mass
    {
        IRON("Iron","鉄"),
        GOLD("Gold","金"),
        LAPIS("Lapis","瑠璃"),
        QUARTZ("Quartz","石英"),
        COPPER("Copper","銅"),
        TIN("Tin","錫"),
        URANIUM("Uranium","ウラニウム"),
        BAUXITE("Bauxite","ボーキサイト"),
        TITANIUM("Titanium","チタニウム"),
        LEAD("Lead","鉛"),
        NICKEL("Nickel","ニッケル"),
        SILVER("Silver","銀"),
        CHROME("Chrome","クロム"),
        TUNGSTEN("Tungsten","タングステン"),
        IRIDIUM("Iridium","イリヂウム")
        ;

        public String unlocalizedName;
        public String jpName;

        @SideOnly(Side.CLIENT)
        public Icon texture;

        private Mass(String unlocalizedName,String jpName)
        {
            this.unlocalizedName = unlocalizedName;
            this.jpName = jpName;
        }

        public void loadTexture(IconRegister iconRegister)
        {
            texture = iconRegister.registerIcon("additionalOre:" + unlocalizedName + " Mass");
        }

        public ItemStack getItemStack( )
        {
            return getItemStack(1);
        }

        public ItemStack getItemStack(int amount)
        {
            return new ItemStack(AdditionalOre.ITEM_masses,amount,ordinal());
        }
    }
}
