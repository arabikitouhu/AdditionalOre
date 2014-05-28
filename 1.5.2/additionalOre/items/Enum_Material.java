package mods.additionalOre.items;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;

public enum Enum_Material
{
    //Enum名("アイテム名","日本語のアイテム名",{粉,宝石,インゴット,塊,ナゲット},"サブアイテム1","サブアイテム2","サブアイテム3","鉱石辞書名")
    IRON("Iron","鉄",new int[]{ 0, -1, -1, 0, 0},"Nickel","Tin",null,"Iron"),
    GOLD("Gold","金",new int[]{ 1, -1, -1, 0 -1},"Copper","Nickel",null,"Gold"),
    LAPIS("Lapis","瑠璃",new int[]{ -1, -1, -1, -1, 1},"Lapis","Lapis",null,"Lapis"),
    EMERALD("Emerald","蒼玉",new int[]{ 2, -1, -1, -1, 2},"Emerald",null,null,"Emerald"),
    DIAMOND("Diamond","金剛",new int[]{ 3, -1, -1, 1, 1},"Diamond","","","Diamond"),
    COAL("Carbon","炭素",new int[]{ 4, -1, -1, 1, 1},null,null,null,"Coal"),
    QUARTZ("Quartz","石英",new int[]{ 5, -1, -1, 1, 1},"Quartz",null,null,"Quartz"),
    COPPER("Copper","銅",new int[]{ 6, -1, -1, 1, 1},"Gold","Nickel","","Copper"),
    TIN("Tin","錫",new int[]{ 7, -1, -1, 1, 1},"Iron","Zinc","","Tin"),
    URANIUM("Uranium","ウラニウム",new int[]{ 8, -1, -1, 1, 1},"Gold","Nickel","","Uranium"),
    NICKEL("Nickel","ニッケル",new int[]{ 9, -1, -1, 1, 1},"Gold","Nickel","","Nickel"),
    SILVER("Silver","銀",new int[]{ 10, -1, -1, 1, 1},"Gold","Nickel","","Silver"),
    CHROME("Chrome","クロム",new int[]{ 11, -1, -1, 1, 1},"Gold","Nickel","","Chrome"),
    TUNGSTATE("Tungsten","タングステン",new int[]{ 12, -1, -1, 1, 1},"Gold","Nickel","","Tungsten"),
    IRIDIUM("Iridium","イリヂウム",new int[]{ 13, -1, -1, 1, 1},"Platinum","Nickel","","Iridium"),
    BRONZE("Bronze","青銅",new int[]{ 14, -1, 1, -1, 1},"","","","Bronze"),
    WHITE_GOLD("WhiteGold","ホワイトゴールド",new int[]{ 15, -1, 1, -1, 1},"","","","Platinum"),
    TUNGSTEN_STEEL("TungstenSteel","タングステン鋼",new int[]{ 16, -1, 1, -1, 1},"","","","TungstenSteel"),
    RUBY("Ruby","紅玉",new int[]{ 17, -1, 1, -1, 1},"","","","TungstenSteel"),
    SAPPHIRE("Sapphire","碧玉",new int[]{ 16, -1, 1, -1, 1},"","","","TungstenSteel"),

    ;


    public String unlocalizedName ;//英語用共通名称
    public String jpName; //日本語名称
    public int[] meta = new int[6]; //各種のmeta　-1は非登録用 粉、宝石、インゴット、ナゲットの順。
    public static final Enum_Material[] VAILD_ARGS = values();
    public String[] subItem = new String[3];
    public String oreName; //鉱石辞書名

    @SideOnly(Side.CLIENT)
    public Icon texture;

    @SideOnly(Side.CLIENT)
    public Icon[] textureArray;

    private Enum_Material(String unlocalizedName,String jpName,int[] meta,String subItem1,String subItem2,String subItem3,String OreName)
    {
        this.unlocalizedName = unlocalizedName;
        this.jpName = jpName;
        this.meta = meta;
        this.subItem[0] = subItem1;
        this.subItem[1] = subItem2;
        this.subItem[2] = subItem3;
        this.oreName = OreName;
    }

    public static Enum_Material getMetadata(int ordinal)
    {
        if(ordinal > VAILD_ARGS.length -1)
            return null;
        return VAILD_ARGS[ordinal];
    }

    public Enum_Material getMassMeta(int ordinal)
    {
        if(ordinal > meta[3] -1)
            return null;
        return VAILD_ARGS[meta[3]];
    }
}
