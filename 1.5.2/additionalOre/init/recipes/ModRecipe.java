package mods.additionalOre.init.recipes;


import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.additionalOre.AdditionalOre;
import mods.additionalOre.init.ConfigurationManager;
import mods.additionalOre.items.ItemDust;
import mods.additionalOre.items.ItemDyeNew;
import mods.additionalOre.items.ItemIngot;
import mods.additionalOre.items.ItemMass;
import mods.japanAPI.JapanAPI;
import mods.japanAPI.utils.OreDictionaryUtil;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipe
{
    public static void initRecipe()
    {
        System.out.println("--- AdditonalOre initRecipe START ---");

        JapanAPI.DeleteCraftingRecipe(new ItemStack(Block.blockLapis),new ItemStack(Item.dyePowder, 9 ,4));
        System.out.println("クラフト：青染料x9　→　ラピスラズリブロック を削除しました。");
        System.out.println("クラフト：ラピスラズリブロック　→　青染料x9 を削除しました。");

        JapanAPI.DeleteSmeltingRecipe(new ItemStack(Block.oreDiamond), new ItemStack(Block.oreEmerald), new ItemStack(Block.oreCoal),
                new ItemStack(Block.oreRedstone), new ItemStack(Block.oreLapis));
        System.out.println("精錬：ダイヤ鉱石→ダイヤモンド を削除しました。");
        System.out.println("精錬：エメラルド鉱石→エメラルド を削除しました。");
        System.out.println("精錬：石炭鉱石→石炭 を削除しました。");
        System.out.println("精錬：レッドストーン鉱石→レッドストーン を削除しました。");
        System.out.println("精錬：ラピスラズリ鉱石→ラピスラズリ を削除しました。");

        JapanAPI.DeleteCraftingRecipe(
                new ItemStack(Item.pickaxeGold), new ItemStack(Item.pickaxeDiamond));
        System.out.println("クラフト：金ピッケル を削除しました。");
        System.out.println("クラフト：ダイヤピッケル を削除しました。");

        ItemStack resipeItem = new ItemStack(Item.pickaxeGold);
        resipeItem.addEnchantment(Enchantment.fortune, 3);
        GameRegistry.addShapedRecipe(resipeItem.copy(),
                "XXX", " Y ", " Y ",
                Character.valueOf('X'), Item.ingotGold,
                Character.valueOf('Y'), Item.stick);
        resipeItem = new ItemStack(Item.pickaxeDiamond);
        resipeItem.addEnchantment(Enchantment.fortune, 1);
        GameRegistry.addShapedRecipe(resipeItem.copy(),
                "XXX", " Y ", " Y ",
                Character.valueOf('X'), Item.diamond,
                Character.valueOf('Y'), Item.stick);
        System.out.println("クラフト：金ピッケル を追加しました。");
        System.out.println("クラフト：ダイヤピッケル を追加しました。");

        System.out.println("----  AdditionalOre initRecipe  END   ----");
    }

    public static void PickaxeRecipe()
    {
        //銅のつるはし
        ItemStack resipeItem = new ItemStack(AdditionalOre.ITEM_pickaxes[0]);
        //耐久
        resipeItem.addEnchantment(Enchantment.unbreaking, 1);
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", " Y ", " Y ",
                'X', "ingotCopper",
                'Y', Item.stick));

        //青銅のつるはし
        resipeItem = new ItemStack(AdditionalOre.ITEM_pickaxes[1]);
        resipeItem.addEnchantment(Enchantment.unbreaking, 2);	//耐久
        resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", " Y ", " Y ",
                'X', "ingotBronze",
                'Y', Item.stick));

        //鋼のつるはし
        resipeItem = new ItemStack(AdditionalOre.ITEM_pickaxes[2]);
        resipeItem.addEnchantment(Enchantment.unbreaking, 2);	//耐久
        resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", " Y ", " Y ",
                'X', "ingotSteel",
                'Y', Item.stick));

        //チタンのつるはし
        resipeItem = new ItemStack(AdditionalOre.ITEM_pickaxes[3]);
        resipeItem.addEnchantment(Enchantment.unbreaking, 1);	//耐久
        resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
        resipeItem.addEnchantment(Enchantment.fortune, 1);	//幸運
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", " Y ", " Y ",
                'X', "ingotTitanium",
                'Y', Item.stick));

        //クロムのつるはし
        resipeItem = new ItemStack(AdditionalOre.ITEM_pickaxes[4]);
        resipeItem.addEnchantment(Enchantment.unbreaking, 1);	//耐久
        resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
        resipeItem.addEnchantment(Enchantment.fortune, 1);	//幸運
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", " Y ", " Y ",
                'X', "ingotChrome",
                'Y', Item.stick));

        //タングステンのつるはし
        resipeItem = new ItemStack(AdditionalOre.ITEM_pickaxes[5]);
        resipeItem.addEnchantment(Enchantment.unbreaking, 3);	//耐久
        resipeItem.addEnchantment(Enchantment.efficiency, 3);	//効率強化
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", " Y ", " Y ",
                'X', "ingotTungsten",
                'Y', Item.stick));

        //ルビーのつるはし
        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(AdditionalOre.ITEM_pickaxes[6]),
                "XXX", " Y ", " Y ",
                'X', "gemRuby",
                'Y', Item.stick));

        //サファイアのつるはし
        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(AdditionalOre.ITEM_pickaxes[7]),
                "XXX", " Y ", " Y ",
                'X', "gemSapphire",
                'Y', Item.stick));

        //エメラルドのつるはし
        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(AdditionalOre.ITEM_pickaxes[8]),
                "XXX", " Y ", " Y ",
                'X', Item.emerald,
                'Y', Item.stick));
    }

    public static void HammerRecipe()
    {
        //木のハンマー
        ItemStack resipeItem = new ItemStack(AdditionalOre.ITEM_hammer[0]);
        GameRegistry.addRecipe(new ShapedOreRecipe(
                resipeItem.copy(),
                "XXX", "XXX", " Y ",
                'X', "logWood",
                'Y', Item.stick));

    }


    public static void dustRecipe()
    {

        for(ItemMass.Mass mass :ItemMass.Mass.values())
        {
            if(OreDictionaryUtil.getOreDic("dust" + mass.unlocalizedName) != null)
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(
                        OreDictionaryUtil.getOreDic("dust" + mass.unlocalizedName),
                        "H","O",
                        'H',"craftingToolHardHammer",
                        'O',"mass" + mass.unlocalizedName
                ));
            }
        }

        for(ItemIngot.Ingot ingot : ItemIngot.Ingot.VAILD_ARGS)
        {
            if(OreDictionaryUtil.getOreDic("dust" + ingot.unlocalizedName) != null)
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(
                        OreDictionaryUtil.getOreDic("dust" + ingot.unlocalizedName),
                        "H","O",
                        'H',"craftingToolHardHammer",
                        'O',"ingot" + ingot.unlocalizedName
                ));
            }
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(
                OreDictionaryUtil.getOreDic("dustIron"),
                "H","O",
                'H',"craftingToolHardHammer",
                'O',"ingotIron"
        ));

        //青銅の粉
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                OreDictionaryUtil.getOreDic("dustBronze", 4),
                "dustTin", "dustCopper","dustCopper","dustCopper"));

            //フェルニッケル
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                OreDictionaryUtil.getOreDic("dustInvar",3),
                "dustIron","dustIron","dustIron","dustNickel"
                ));


        //琥珀金
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                OreDictionaryUtil.getOreDic("dustElectrum",4),
                "dustGold", "dustSilver"));

        //ホワイトゴールド
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                new ItemStack(AdditionalOre.ITEM_dusts,3, ItemDust.Dusts.WHITE_GOLD.meta),
                "dustGold", "dustNickel", "dustNickel", "dustNickel"));

        //鋼の粉
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                OreDictionaryUtil.getOreDic("dustSteel"),
                "dustIron", "dustCoal"));
    }

    public static void smeltingRecipe()
    {

        //鉄インゴット
        OreDictionary.registerOre("ingotIron", new ItemStack(Item.ingotIron));
        JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ingotIron", new ItemStack(Item.ingotIron));

        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.IRON.ordinal(), new ItemStack(Item.ingotIron), 0.35F);	//鉄の塊　→　鉄インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.IRON.ordinal(), new ItemStack(Item.ingotIron), 0.35F);	//鉄の粉　→　鉄インゴット

        //金インゴット
        OreDictionary.registerOre("ingotGold", new ItemStack(Item.ingotGold));
        JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ingotGold", new ItemStack(Item.ingotGold));

        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.GOLD.ordinal(), new ItemStack(Item.ingotGold), 0.7F);	//金の塊→金インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.GOLD.ordinal(), new ItemStack(Item.ingotGold), 0.7F);	//金の粉→金インゴット

        //銅インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.COPPER.ordinal(), OreDictionaryUtil.getOreDic("ingotCopper"), 0.2F);//銅の塊→銅インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.COPPER.ordinal(), OreDictionaryUtil.getOreDic("ingotCopper"), 0.2F);	//銅の粉→銅インゴット

        //錫インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.TIN.ordinal(), OreDictionaryUtil.getOreDic("ingotTin"), 0.2F);//錫の塊→錫インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.TIN.ordinal(), OreDictionaryUtil.getOreDic("ingotTin"), 0.2F);	//錫の粉→錫インゴット

        //ウラニウムインゴット
        //ウラニウムの塊→ウラニウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.URANIUM.ordinal(), OreDictionaryUtil.getOreDic("ingotUranium"), 1F);
        //ウラニウムの粉→ウラニウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.URANIUM.ordinal(), OreDictionaryUtil.getOreDic("ingotUranium"), 1F);

        //アルミニウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.ALUMINUM.ordinal(), OreDictionaryUtil.getOreDic("ingotAluminium"), 1F);	//アルミニウムの粉→アルミニウムインゴット

        //青銅インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.BRONZE.ordinal(), OreDictionaryUtil.getOreDic("ingotBronze"), 1F);	//青銅の粉→青銅インゴット

        //鋼インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.STEEL.ordinal(), OreDictionaryUtil.getOreDic("ingotSteel"), 1F);	//鋼の粉→鋼インゴット

        //チタニウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.TITANIUM.ordinal(), OreDictionaryUtil.getOreDic("ingotTitanium"), 1F);	//チタニウムの塊→チタニウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.TITANIUM.ordinal(), OreDictionaryUtil.getOreDic("ingotTitanium"), 1F);	//チタニウムの粉→チタニウムインゴット

        //鉛インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.LEAD.ordinal(), OreDictionaryUtil.getOreDic("ingotLead"), 1F);	//鉛の塊→鉛インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.LEAD.ordinal(), OreDictionaryUtil.getOreDic("ingotLead"), 1F);	//鉛の粉→鉛インゴット

        //ニッケルインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.NICKEL.ordinal(), OreDictionaryUtil.getOreDic("ingotNickel"), 1F);	//ニッケルの塊→ニッケルインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.NICKEL.ordinal(), OreDictionaryUtil.getOreDic("ingotNickel"), 1F);	//ニッケルの粉→ニッケルインゴット

        //銀インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.SILVER.ordinal(), OreDictionaryUtil.getOreDic("ingotSilver"), 1F);	//銀の塊→銀インゴット
        //銀の粉→銀インゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.SILVER.ordinal(), OreDictionaryUtil.getOreDic("ingotSilver"), 1F);

        //クロムインゴット
        //クロムの塊→クロムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.CHROME.ordinal(), OreDictionaryUtil.getOreDic("ingotChrome"), 1F);
        //クロムの粉→クロムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.CHORME.ordinal(), OreDictionaryUtil.getOreDic("ingotChrome"), 1F);

        //タングステンインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.TUNGSTEN.ordinal(), OreDictionaryUtil.getOreDic("ingotTungsten"), 1F);//タングステンの塊→タングステンインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.TUNGSTEN.ordinal(), OreDictionaryUtil.getOreDic("ingotTungsten"), 1F);	//タングステンの粉→タングステンインゴット

        //イリジウムインゴット
        //イリジウムの塊→イリジウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, ItemMass.Mass.IRIDIUM.ordinal(), OreDictionaryUtil.getOreDic("ingotIridium"), 1F);
        //イリジウムの粉→イリジウムインゴット
        FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, ItemDust.Dusts.IRIDIUM.ordinal(), OreDictionaryUtil.getOreDic("ingotIridium"), 1F);
    }

    public static void ingotRecipe()
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionaryUtil.getOreDic("ingotBronze",2),
                "ingotCopper","ingotCopper","ingotCopper","ingotTin"
                ));

        GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionaryUtil.getOreDic("ingotBronze",2),
                "ingotCopper","ingotCopper","ingotCopper","ingotTin"
        ));
    }



    public static void initDyePowder()
    {
        System.out.println("----  AdditionalOre initDyePowder START  ----");
        Item.itemsList[95] = null;
        Item.itemsList[95] = new ItemDyeNew();

        GameRegistry.registerItem(Item.itemsList[95], "DayPowder");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 0), "Black Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 0), "ja_JP", "黒の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 1), "Red Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 1), "ja_JP", "赤の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 2), "Green Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 2), "ja_JP", "緑の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 3), "Brown Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 3), "ja_JP", "茶色の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 4), "Blue Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 4), "ja_JP", "青の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 5), "Purple Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 5), "ja_JP", "紫の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 6), "Cyan Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 6), "ja_JP", "シアンの染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 7), "Silver Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 7), "ja_JP", "銀の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 8), "Gray Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 8), "ja_JP", "灰色の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 9), "Pink Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 9), "ja_JP", "桃色の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 10), "Lime Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 10), "ja_JP", "ライムの染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 11), "Yellow Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 11), "ja_JP", "黄色の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 12), "LightBlue Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 12), "ja_JP", "空色の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 13), "Magenta Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 13), "ja_JP", "マゼンダの染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 14), "Orange Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 14), "ja_JP", "橙色の染料");

        LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 15), "White Dye");
        LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 15), "ja_JP", "白の染料");

        String[] dyes = {
                "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown",
                "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray",
                "dyeGray", "dyePink", "dyeLime", "dyeYellow",
                "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"
        };

        for(int i = 0; i < 16; i++) {
            ItemStack dye = new ItemStack(Item.itemsList[95], 1, i);
            OreDictionary.registerOre(dyes[i], dye);
        }

        System.out.println("染料（青）を変更しました。");
        System.out.println("----  AdditionalOre initDyePowder  END   ----");
    }
}
