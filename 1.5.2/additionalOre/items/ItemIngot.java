package mods.additionalOre.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.japanAPI.JapanAPI;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemIngot extends AO_Item
{

	public ItemIngot(int id)
    {
		super(id);
        register();
	}

    private void register()
    {
        GameRegistry.registerItem(this,"Item Ingot");

        for(Ingot M : Ingot.VAILD_ARGS)
        {
            LanguageRegistry.addName(new ItemStack(this,1,M.meta),M.unlocalizedName + " Ingot");
            LanguageRegistry.instance().addNameForObject(new ItemStack(this,1,M.meta),"ja_JP",M.jpName + "のインゴット");
            OreDictionary.registerOre("ingot" + M.unlocalizedName,new ItemStack(this,1,M.meta));
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ingot" + M.meta, new ItemStack(this, 1, M.meta));
        }
    }

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int meta)
    {
		return Ingot.VAILD_ARGS[meta].texture;
	}


	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
		return "additionalOre:" + Ingot.VAILD_ARGS[itemStack.getItemDamage()] + " Ingot";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
        for (Ingot I :Ingot.VAILD_ARGS)
        {
            I.loadTexture(iconRegister);
        }

	}

    @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list)
    {
		for (Ingot I :Ingot.VAILD_ARGS)
        {
			list.add(new ItemStack(id, 1, I.meta));
		}
	}

    public enum Ingot
    {
        COPPER("Copper","銅"),
        Tin("Tin","錫"),
        URANIUM("Uranium","ウラニウム"),
        ALUMINIUM("Aluminium","アルミニウム"),
        BRONZE("Bronze","青銅"),
        STEEL("Steel","鋼鉄"),
        TITANIUM("Titanium","チタニウム"),
        LEAD("Lead","鉛"),
        NICKEL("Nickel","ニッケル"),
        WHITE_GOLD("WhiteGold","ホワイトゴールド"),
        PLATINUM("Platinum","白金"),
        SILVER("Silver","銀"),
        ELECTRUM("Electrum","琥珀金"),
        CHROME("Chrome","クロム"),
        TUNGSTEN("Tungsten","タングステン"),
        TUNGSTEN_STEEL("TungstenSteel","タングステン鋼"),
        IRIDIUM("Iridium","イリヂウム"),
        ;

        public String unlocalizedName;
        public String jpName;
        public int meta = ordinal();
        public static final Ingot[] VAILD_ARGS = values();

        @SideOnly(Side.CLIENT)
        public Icon texture;

        private Ingot(String unlocalizedName,String jpName)
        {
            this.unlocalizedName = unlocalizedName;
            this.jpName= jpName;
        }

        public void loadTexture(IconRegister iconRegister)
        {
            texture = iconRegister.registerIcon("additionalOre:" + unlocalizedName + " Ingot");
        }
    }
}
