package mods.additionalOre.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.japanAPI.items.JAPI_ForgeEnumMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.List;

public abstract class AO_ItemTools extends ItemTool
{
    public AO_ItemTools(int itemID, int type, EnumToolMaterial enumToolMaterial, Block[] arrayOfBlock)
    {
        super(itemID -256, type, enumToolMaterial, arrayOfBlock);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add((getMaxDamage(itemStack) - this.getDisplayDamage(itemStack)) + "/" + this.getMaxDamage(itemStack));
    }


    public enum tools
    {
        Copper(JAPI_ForgeEnumMaterials.TOOL_COPPER,"Copper","銅"),
        Bronze(JAPI_ForgeEnumMaterials.TOOL_BRONZE,"Bronze","青銅"),
        Steel(JAPI_ForgeEnumMaterials.TOOL_STEEL,"Steel","鋼鉄"),
        Titanium(JAPI_ForgeEnumMaterials.TOOL_TUNGSTEN,"Titanium","チタニウム"),
        Chrome(JAPI_ForgeEnumMaterials.TOOL_CHROME,"Chrome","クロム"),
        Tungsten(JAPI_ForgeEnumMaterials.TOOL_TUNGSTEN,"Tungsten","タングステン"),
        Ruby(JAPI_ForgeEnumMaterials.TOOL_GEMS,"Ruby","紅玉"),
        Sapphire(JAPI_ForgeEnumMaterials.TOOL_GEMS,"Sapphire","碧玉"),
        Green_Sapphire(JAPI_ForgeEnumMaterials.TOOL_GEMS,"GreenSapphire","緑碧玉"),
        Emerald(JAPI_ForgeEnumMaterials.TOOL_EMERALD,"Emerald","翡翠")
        ;

        ;
        public EnumToolMaterial material;
        public String unlocalizedName;
        public String jpName;

        private tools(EnumToolMaterial material,String unlocalizedName,String jpName)
        {
            this.unlocalizedName = unlocalizedName;
            this.material = material;
            this.jpName = jpName;
        }

    }
}
