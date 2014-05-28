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

public class ItemNugget extends AO_Item
{

	public ItemNugget(int id)
    {
		super(id);
        register();
	}

    private void register()
    {
        GameRegistry.registerItem(this,"Item Nugget");
        for(Nugget M : Nugget.VAILD_ARGS)
        {
            LanguageRegistry.addName(new ItemStack(this,1,M.meta),M.unlocalizedName + " Nugget");
            LanguageRegistry.instance().addNameForObject(new ItemStack(this,1,M.meta),"ja_JP",M.jpName + "のかけら");
            OreDictionary.registerOre("nugget" + M.unlocalizedName,new ItemStack(this,1,M.meta));
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("nugget" + M.meta, new ItemStack(this, 1, M.meta));
        }
    }

    @Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
    {
		return Nugget.VAILD_ARGS[meta].texture;
	}


	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
		return "additionalOre:" + Nugget.VAILD_ARGS[itemStack.getItemDamage()] + " Nugget";
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
    {

		for (Nugget N : Nugget.VAILD_ARGS)
        {
			N.loadTexture(iconRegister);
		}

	}

    @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list)
    {
		for (Nugget N : Nugget.VAILD_ARGS)
        {
			list.add(new ItemStack(id, 1, N.meta));
		}
	}

    private enum Nugget
    {
        IRON("Iron","鉄"),
        LAPIS("Lapis","ラピス"),
        EMERALD("Emerald","翡翠"),
        DIAMOMD("Diamond","金剛"),
        QUARTZ("Quartz","石英"),
        COPPER("Copper","銅"),
        TIN("Tin","錫"),
        URANIUM("Uranium","ウラニウム"),
        ALUMINIUM("Aluminium","アルミニウム"),
        BRONZE("Bronze","青銅"),
        STEEL("Steel","鋼鉄"),
        TITANIUM("Titanium","チタニウム"),
        LEAD("Lead","鉛"),
        NICKEL("Nickel","ニッケル"),
        SILVER("Silver","銀"),
        CHROME("Chrome","クロム"),
        TUNGSTEN("Tungsten","タングステン"),
        IRIDIUM("Iridium","イリヂウム"),
        ;

        public String unlocalizedName;
        public String jpName;
        public static final Nugget[] VAILD_ARGS = values();
        public int meta = ordinal();

        @SideOnly(Side.CLIENT)
        public Icon texture;

        private Nugget(String unlocalizedName,String jpName)
        {
            this.unlocalizedName = unlocalizedName;
            this.jpName = jpName;
        }

        public void loadTexture(IconRegister iconRegister)
        {
            texture = iconRegister.registerIcon("additionalOre:" + unlocalizedName + " Nugget");
        }
    }
}
