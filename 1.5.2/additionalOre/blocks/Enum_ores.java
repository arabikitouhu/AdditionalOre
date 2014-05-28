package mods.additionalOre.blocks;


import mods.additionalOre.AdditionalOre;
import mods.additionalOre.items.ItemMass;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public enum Enum_ores
{
    QUARTZ("Quartz","石英", ItemMass.Mass.QUARTZ.getItemStack(),1,3,1,10),
    COPPER("Copper","銅", ItemMass.Mass.COPPER.getItemStack(),1,3,1,10),
    TIN("Tin","錫",ItemMass.Mass.TIN.getItemStack(),1,3,1,10),
    URANIUM("Uranium","ウラン",ItemMass.Mass.URANIUM.getItemStack(),1,3,1,10),
    BAUXITE("Bauxite","ボーキサイト",ItemMass.Mass.BAUXITE.getItemStack(),1,3,1,10),
    LEAD("Lead","鉛",ItemMass.Mass.LEAD.getItemStack(),1,3,1,10),
    NICKEL("Nickel","ニッケル",ItemMass.Mass.NICKEL.getItemStack(),1,3,1,10),
    SILVER("Silver","銀",ItemMass.Mass.SILVER.getItemStack(),1,3,1,10),

    ;

    public String unlocalizedName;
    public String enNames;
    public String jpNames;
    public ItemStack drop;
    protected final int MIN_DROP;
    protected final int MAX_DROP;
    protected final int MIN_EXP;
    protected final int MAX_EXP;

    public static Enum_ores[] VALID_ARGS = values();


    private Enum_ores(String unlocalizedName,String jpNames,ItemStack drop,int minDrop,int maxDrop,int minExp,int maxExp)
    {
        this.unlocalizedName = unlocalizedName;
        this.jpNames = jpNames;
        this.drop = drop;
        this.MIN_DROP = minDrop;
        this.MAX_DROP = maxDrop;
        this.MIN_EXP = minExp;
        this.MAX_EXP = maxExp;
    }

    public Icon texture;
    public final int meta = this.ordinal();

    private static Enum_ores get(int ordinal)
    {
        if(ordinal > VALID_ARGS.length -1)
        {
            return null;
        }
        return VALID_ARGS[ordinal];
    }

    public void loadTextures(IconRegister iconRegister)
    {
        texture = iconRegister.registerIcon("additionalOre:" + unlocalizedName + " Ore");
    }

    public ItemStack getItemStack( )
    {
        return getItemStack(1);
    }

    public ItemStack getItemStack(int amount)
    {
        return new ItemStack(AdditionalOre.Block_ores,amount,meta);
    }

    public ItemStack getDropStack(int amount)
    {
        return new ItemStack(drop.getItem(),amount, drop.getItemDamage());
    }
}
