package mods.additionalOre.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.japanAPI.JapanAPI;
import mods.japanAPI.items.JAPI_ForgeEnumMaterials;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class AO_ItemPickaxe extends ItemPickaxe
{
    private Pickaxes pickaxe;

    public AO_ItemPickaxe(int itemID,Pickaxes pickaxe)
    {
        super(itemID, pickaxe.material);
        this.pickaxe = pickaxe;
        register();
    }

    public void register()
    {
            setUnlocalizedName("additionalOre:" + pickaxe.unlocalizedName +" Pickaxe");
            LanguageRegistry.addName(this, pickaxe.unlocalizedName + " Pickaxe");
            LanguageRegistry.instance().addNameForObject(this, "ja_JP", pickaxe.jpName + "のつるはし");
            OreDictionary.registerOre("pickaxe" + pickaxe.unlocalizedName, this);
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("pickaxe" + pickaxe.unlocalizedName, new ItemStack(this,1,32767));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add((getMaxDamage(itemStack) - this.getDisplayDamage(itemStack)) + "/" + this.getMaxDamage(itemStack));
    }

    public enum Pickaxes
    {
        Copper(JAPI_ForgeEnumMaterials.TOOL_COPPER,"Cooper","銅"),
        Bronze(JAPI_ForgeEnumMaterials.TOOL_BRONZE,"Bronze","青銅"),
        Steel(JAPI_ForgeEnumMaterials.TOOL_STEEL,"Steel","鋼鉄"),
        Titanium(JAPI_ForgeEnumMaterials.TOOL_TUNGSTEN,"Titanium","チタニウム"),
        Chrome(JAPI_ForgeEnumMaterials.TOOL_CHROME,"Chrome","クロム"),
        Tungsten(JAPI_ForgeEnumMaterials.TOOL_TUNGSTEN,"Tungsten","タングステン"),
        Ruby(JAPI_ForgeEnumMaterials.ENUM_gemTool,"Ruby","紅玉"),
        Sapphire(JAPI_ForgeEnumMaterials.ENUM_gemTool,"Sapphire","碧玉"),
        Green_Sapphire(JAPI_ForgeEnumMaterials.ENUM_gemTool,"GreenSapphire","緑碧玉"),
        Emerald(JAPI_ForgeEnumMaterials.ENUM_emeraldTool,"Emerald","翡翠")
        ;

        ;
        public EnumToolMaterial material;
        public String unlocalizedName;
        public String jpName;

        private Pickaxes(EnumToolMaterial material,String unlocalizedName,String jpName)
        {
            this.unlocalizedName = unlocalizedName;
            this.material = material;
            this.jpName = jpName;
        }

    }

}
