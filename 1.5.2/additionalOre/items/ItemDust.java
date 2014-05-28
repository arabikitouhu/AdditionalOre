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

public class ItemDust extends AO_Item
{
	public ItemDust(int id)
    {
		super(id);
        Register();
	}

    private void Register()
    {
        GameRegistry.registerItem(this,"Item Dust");

        for(Dusts M : Dusts.VAILD_ARGS)
        {
            LanguageRegistry.addName(new ItemStack(this,1,M.meta),M.unlocalizedName + " Dust");
            LanguageRegistry.instance().addNameForObject(new ItemStack(this,1,M.meta),"ja_JP",M.jpName + "の粉");
            OreDictionary.registerOre("dust" + M.oreDicName, new ItemStack(this, 1, M.meta));
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("dust" + M.oreDicName, new ItemStack(this, 1, M.meta));

        }
    }

    @Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
    {
		return Dusts.VAILD_ARGS[meta].texture;
	}


	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
		return "additionalOre:" + Dusts.VAILD_ARGS[itemStack.getItemDamage()] + " Dust";
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
    {
		for (Dusts D: Dusts.VAILD_ARGS)
        {
			D.loadTexture(iconRegister);
		}
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list)
    {
		for (Dusts D: Dusts.VAILD_ARGS)
        {
			list.add(new ItemStack(id, 1, D.meta));
		}
	}

    public enum Dusts
    {
        //TEMPLATE("","",""),
        COAL("Carbon","炭素","Coal"),
        IRON("Iron","鉄","Iron"),
        GOLD("Gold","金","Gold"),
        EMERALD("Emerald","翡翠","Emerald"),
        DIAMOND("Diamond","金剛","Diamond"),
        QUARTZ("Quartz","石英","Quartz"),
        COPPER("Copper","銅","Copper"),
        TIN("Tin","錫","Tin"),
        URANIUM("Uranium","ウラニウム","Uranium"),
        ALUMINUM("Aluminium","アルミニウム","Aluminum"),
        BRONZE("Bronze","青銅","Bronze"),
        STEEL("Steel","鋼鉄","Steel"),
        TITANIUM("Titanium","チタニウム","Titanium"),
        LEAD("Lead","鉛","Lead"),
        NICKEL("Nickel","ニッケル","Nickel"),
        INVAR("FerNickel","フェルニッケル","Invar"),
        WHITE_GOLD("WhiteGold","ホワイトゴールド","Platinum"),
        PLATINUM("Platinum","白金","Platinum"),
        SILVER("Silver","銀","Silver"),
        ELECTRUM("Electrum","琥珀金","Electrum"),
        CHORME("Chrome","クロム","Chrome"),
        TUNGSTEN("Tungsten","タングステン","Tungsten"),
        TUNGSTEN_STEEL("TungstenSteel","タングステン鋼","TungstenSteel"),
        IRIDIUM("Iridium","イリヂウム","Iridium")

        ;

        public String unlocalizedName;
        public String jpName;
        public String oreDicName;

        public int meta = ordinal();

        public static final Dusts[] VAILD_ARGS = values();

        @SideOnly(Side.CLIENT)
        public Icon texture;

        private Dusts(String unlocalizedName,String name,String oreDicName)
        {
            this.unlocalizedName = unlocalizedName;
            this.jpName = name;
            this.oreDicName = oreDicName;

        }

        public void loadTexture(IconRegister iconRegister)
        {
            texture = iconRegister.registerIcon("additionalOre:" + unlocalizedName +" Dust");
        }
    }

}
