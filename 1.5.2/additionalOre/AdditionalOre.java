package mods.additionalOre;

import ic2.api.recipe.Recipes;

import java.util.logging.Level;

import mods.additionalOre.blocks.CrackStone;
import mods.additionalOre.blocks.OreBlock;
import mods.additionalOre.items.ItemDust;
import mods.additionalOre.items.ItemDyeNew;
import mods.additionalOre.items.ItemGem;
import mods.additionalOre.items.ItemIngot;
import mods.additionalOre.items.ItemMass;
import mods.additionalOre.items.ItemNugget;
import mods.additionalOre.worlds.gens.WorldGenerator;
import mods.japanAPI.JapanAPI;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
		modid = "AdditionalOre",
		name = "AdditionalOre 1.5.2",
		version = "0.1.1",
		dependencies = "required-after:JapanAPI")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = true)
public class AdditionalOre {

	@Instance("AdditionalOre")
	public static AdditionalOre instance;

	public static int Model_ID = -1;

	@SidedProxy(clientSide = "mods.additionalOre.ClientProxy", serverSide = "mods.additionalOre.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs TABS_ore = new CreativeTab("\u9271\u77F3\u8FFD\u52A0");
	public static final CreativeTabs TABS_tool = new CreativeTab("\u30C4\u30FC\u30EB\u8FFD\u52A0");

	public static final WorldGenerator GENERATOR_world = new WorldGenerator();

	public static int[] ID_ores = {14, 15, 129, 21, 56, 4007, 4000, 4001, 4002, 4003, 4004, 4005, 4006};
	public static OreBlock[] BLOCK_ores = new OreBlock[ID_ores.length];

	public static int ID_CrackBlock = 4030;
	public static Block BLOCK_CrackBlock;

	public static int ID_masses = 12500;
	public static Item ITEM_masses;

	public static int ID_dusts = 12501;
	public static Item ITEM_dusts;

	public static int ID_ingots = 12502;
	public static Item ITEM_ingots;

	public static int ID_nuggets = 12503;
	public static Item ITEM_nuggets;

	public static int ID_gems = 12504;
	public static Item ITEM_gems;

	public static int[] ID_pickaxes = {12510, 12511, 12512, 12513, 12514, 12515, 12516, 12517, 12518};
	public static Item[] ITEM_pickaxes = new Item[ID_pickaxes.length];

	public static EnumToolMaterial ENUM_gemTool;
	public static EnumToolMaterial ENUM_emeraldTool;

	static {
		ENUM_gemTool = EnumHelper.addToolMaterial("汎用宝石ツール", 2, 1561, 12.0F, 5, 16);
		ENUM_emeraldTool = EnumHelper.addToolMaterial("エメラルドツール", 3, 3122, 16.0F, 3, 20);
	}

	@Mod.PreInit
 	public void preInit(FMLPreInitializationEvent event) {
		initRecipe();				//既存レシピの変更（不一致の削除）

		initDyePowder();		//染料（青）の変更処理

		loadConfig(new Configuration(event.getSuggestedConfigurationFile()));	//コンフィグのロード

		initNewItemAndBlockAndTool();	//追加アイテム&ブロック&ツール

		JapanAPI.EVENT_entityItemPickupEventHook.addExcludedKeyWordList("oreQuartz");
	}

	private void initRecipe() {
		System.out.println("----  AdditionalOre initRecipe START  ----");

		JapanAPI.DeleteCraftingRecipe(
				new ItemStack(Block.blockLapis), new ItemStack(Item.dyePowder, 9, 4));
		System.out.println("クラフト：青染料x9→ラピスラズリブロック を削除しました。");
		System.out.println("クラフト：ラピスラズリブロック→青染料x9 を削除しました。");

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

	private void initDyePowder() {
		System.out.println("----  AdditionalOre initDyePowder START  ----");
		Item.itemsList[95] = null;
		Item.itemsList[95] = new ItemDyeNew();

		GameRegistry.registerItem(Item.itemsList[95], "DayPowder");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 0), "Black Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 0), "ja_JP", "\u9ED2\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 1), "Red Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 1), "ja_JP", "\u8D64\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 2), "Green Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 2), "ja_JP", "\u7DD1\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 3), "Brown Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 3), "ja_JP", "\u8336\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 4), "Blue Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 4), "ja_JP", "\u9752\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 5), "Purple Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 5), "ja_JP", "\u7D2B\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 6), "Cyan Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 6), "ja_JP", "\u9752\u7DD1\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 7), "Silver Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 7), "ja_JP", "\u9280\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 8), "Gray Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 8), "ja_JP", "\u7070\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 9), "Pink Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 9), "ja_JP", "\u6843\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 10), "Lime Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 10), "ja_JP", "\u9EC4\u7DD1\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 11), "Yellow Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 11), "ja_JP", "\u9EC4\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 12), "LightBlue Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 12), "ja_JP", "\u660E\u9752\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 13), "Magenta Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 13), "ja_JP", "\u8D64\u7D2B\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 14), "Orange Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 14), "ja_JP", "\u6731\u67D3\u6599");

		LanguageRegistry.instance().addName(new ItemStack(Item.itemsList[95], 1, 15), "White Dye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Item.itemsList[95], 1, 15), "ja_JP", "\u767D\u67D3\u6599");

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

	private void loadConfig(Configuration cfg) {
		try
		{
			cfg.load();

			ID_ores[6] = cfg.getBlock("Copper Ore", 4000).getInt();				//銅鉱石
			ID_ores[7] = cfg.getBlock("Tin Ore", 4001).getInt();				//錫鉱石
			ID_ores[8] = cfg.getBlock("Uranium Ore", 4002).getInt();			//ウラニウム鉱石
			ID_ores[9] = cfg.getBlock("Bauxite Ore", 4003).getInt();			//ボーキサイト鉱石
			ID_ores[10] = cfg.getBlock("Lead Ore", 4004).getInt();				//鉛鉱石
			ID_ores[11] = cfg.getBlock("Nickel Ore", 4005).getInt();			//ニッケル鉱石
			ID_ores[12] = cfg.getBlock("Silver Ore", 4006).getInt();			//銀鉱石
			ID_ores[5] = cfg.getBlock("Quartz Ore", 4007).getInt();				//クォーツ鉱石

			ID_CrackBlock = cfg.getBlock("Crack Stone", 4030).getInt();			//岩の裂け目

			ID_masses = cfg.getItem("Mass Item", 12500).getInt();				//塊
			ID_dusts = cfg.getItem("Dust Item", 12501).getInt();				//粉
			ID_ingots = cfg.getItem("Ingot Item", 12502).getInt();				//インゴット
			ID_nuggets = cfg.getItem("Nugget Item", 12503).getInt();			//ナゲット
			ID_gems = cfg.getItem("Gem Item", 12504).getInt();					//宝石

			ID_pickaxes[0] = cfg.getItem("Copper Pickaxe", 12510).getInt();		//銅ピッケル
			ID_pickaxes[1] = cfg.getItem("Bronze Pickaxe", 12511).getInt();		//青銅ピッケル
			ID_pickaxes[2] = cfg.getItem("Steel Pickaxe", 12512).getInt();		//鋼ピッケル
			ID_pickaxes[3] = cfg.getItem("Titanium Pickaxe", 12513).getInt();	//チタニウムピッケル
			ID_pickaxes[4] = cfg.getItem("Chrome Pickaxe", 12514).getInt();		//クロムピッケル
			ID_pickaxes[5] = cfg.getItem("Tungstan Pickaxe", 12515).getInt();	//タングステンピッケル
			ID_pickaxes[6] = cfg.getItem("Ruby Pickaxe", 12516).getInt();		//ルビーピッケル
			ID_pickaxes[7] = cfg.getItem("Sapphire Pickaxe", 12517).getInt();	//サファイアピッケル
			ID_pickaxes[8] = cfg.getItem("Emerald Pickaxe", 12518).getInt();		//エメラルドピッケル

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Error Message");

		} finally {
			cfg.save();
		}
	}

	private void initNewItemAndBlockAndTool() {
		initItem_Mass();		//追加アイテム（塊）
		initItem_Dust();		//追加アイテム（粉）
		initItem_Ingot();		//追加アイテム（インゴット）
		initItem_Nugget();		//追加アイテム（ナゲット）

		initVanillaBlock();		//バニラ鉱石ブロックの初期化＆レジスタ登録処理
		initBlock_Ore();		//追加ブロック（鉱石）

		initCrackBlock();	//岩の裂け目

		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[5], 6), 13, 31, 60);		//クォーツ鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[6], 6), 10, 70, 50);		//銅鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[7], 6), 1, 40, 50);			//錫鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[8], 3), 1, 63, 40);			//ウラニウム鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[9], 8), 20, 75, 70);		//ボーキサイト鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[10], 4), 10, 35, 50);		//鉛鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[11], 4), 10, 32, 50);		//ニッケル鉱石
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[12], 4), 5, 30, 40);		//銀鉱石

		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_CrackBlock, 3), 1, 50, 30);		//岩の裂け目




		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[5], 9, Block.netherBrick.blockID), 1, 254, 90);			//クォーツ鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[6], 9, Block.netherBrick.blockID), 1, 254, 75);			//銅鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[7], 9, Block.netherBrick.blockID), 1, 254, 75);			//錫鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[8], 4, Block.netherBrick.blockID), 1, 254, 60);			//ウラニウム鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[9], 12, Block.netherBrick.blockID), 1, 254, 105);		//ボーキサイト鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[10], 6, Block.netherBrick.blockID), 1, 254, 75);			//鉛鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[11], 6, Block.netherBrick.blockID), 1, 254, 75);			//ニッケル鉱石
		GENERATOR_world.addGenMinable_Hell(new WorldGenMinable(ID_ores[12], 6, Block.netherBrick.blockID), 1, 254, 60);			//銀鉱石

		GameRegistry.registerWorldGenerator(new WorldGenerator());	//鉱石生成の設定

		initItem_Gem();		//追加アイテム（宝石）

		ENUM_gemTool.customCraftingMaterial = ITEM_gems;
		ENUM_emeraldTool.customCraftingMaterial = Item.emerald;

		initItem_Pickaxe();

	}

	private void initCrackBlock() {
		//岩の裂け目
		BLOCK_CrackBlock = new CrackStone(ID_CrackBlock);

		GameRegistry.registerBlock(BLOCK_CrackBlock, BLOCK_CrackBlock.getUnlocalizedName());
		LanguageRegistry.addName(BLOCK_CrackBlock, "Crack Stone");
		LanguageRegistry.instance().addNameForObject(BLOCK_CrackBlock, "ja_JP", "\u5CA9\u306E\u88C2\u3051\u76EE");
	}

	private void initVanillaBlock() {
		System.out.println("----  AdditionalOre Init-initVanillaBlock START  ----");

		ItemStack[] items = {
			new ItemStack(ITEM_masses, 1, 1),	//Gold
			new ItemStack(ITEM_masses, 1, 0),	//Iron
			new ItemStack(Item.emerald, 1),		//Emerald
			new ItemStack(ITEM_masses, 1, 2),	//Lapis
			new ItemStack(Item.diamond, 1),		//Diamond
			new ItemStack(ITEM_masses, 1, 3)	//Quartz
		};

		for(int i = 0; i < 6; i++) {
			Block.blocksList[ID_ores[i]] = null;
			BLOCK_ores[i] = (OreBlock) (new OreBlock(ID_ores[i])).setDropItem(items[i]).setUnlocalizedName(OreBlock.uniNames[i]);

			GameRegistry.registerBlock(BLOCK_ores[i], OreBlock.enNames[i] + " Ore");

			LanguageRegistry.addName(BLOCK_ores[i], OreBlock.enNames[i] + " Ore");
			LanguageRegistry.instance().addNameForObject(BLOCK_ores[i] , "ja_JP", OreBlock.jpNames[i]);

			OreDictionary.registerOre("ore" + OreBlock.enNames[i], BLOCK_ores[i]);
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ore" + OreBlock.enNames[i], new ItemStack(BLOCK_ores[i]));
		}

		System.out.println("----  AdditionalOre Init-initVanillaBlock  END   ----");
	}

//******************************************************************************************************************
// 鉱石
//******************************************************************************************************************
	private void initBlock_Ore() {
		System.out.println("----  AdditionalOre Init-initBlock_Ore START  ----");

		ItemStack[] items = {
				new ItemStack(ITEM_masses, 1, 4),	//Copper
				new ItemStack(ITEM_masses, 1, 5),	//Tin
				new ItemStack(ITEM_masses, 1, 6),	//Uranium
				new ItemStack(ITEM_masses, 1, 7),	//Bauxite
				new ItemStack(ITEM_masses, 1, 9),	//Lead
				new ItemStack(ITEM_masses, 1, 10),	//Nickel
				new ItemStack(ITEM_masses, 1, 11)	//Silver
			};

		for(int i = 6; i < ID_ores.length; i++) {
			BLOCK_ores[i] = (OreBlock) (new OreBlock(ID_ores[i])).setDropItem(items[i - 6]).setUnlocalizedName(OreBlock.uniNames[i]);

			GameRegistry.registerBlock(BLOCK_ores[i], OreBlock.enNames[i] + " Ore");

			LanguageRegistry.addName(BLOCK_ores[i], OreBlock.enNames[i] + " Ore");
			LanguageRegistry.instance().addNameForObject(BLOCK_ores[i] , "ja_JP", OreBlock.jpNames[i]);

			OreDictionary.registerOre("ore" + OreBlock.enNames[i], BLOCK_ores[i]);
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ore" + OreBlock.enNames[i], new ItemStack(BLOCK_ores[i]));
		}

		System.out.println("----  AdditionalOre Init-initBlock_Ore  END   ----");
	}

//******************************************************************************************************************
// 塊
//******************************************************************************************************************
	private void initItem_Mass() {
		System.out.println("----  AdditionalOre Init-initItem_Mass START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("mass");

		ITEM_masses = new ItemMass(ID_masses);
		GameRegistry.registerItem(ITEM_masses, "Item Mass");

		for(int i = 0; i < ItemMass.uniNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(ITEM_masses, 1, i), ItemMass.uniNames[i] +" Mass");
			LanguageRegistry.instance().addNameForObject(new ItemStack(ITEM_masses, 1, i), "ja_JP", ItemMass.jpNames[i]);
			OreDictionary.registerOre("mass" + ItemMass.uniNames[i], new ItemStack(ITEM_masses, 1, i));
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("mass" + ItemMass.uniNames[i], new ItemStack(ITEM_masses, 1, i));
		}

		FurnaceRecipes.smelting().addSmelting(ID_masses, 2, new ItemStack(95, 4, 4), 2f);	//ラピスラズリ→青染料x4

		System.out.println("----  AdditionalOre Init-initItem_Mass  END   ----");
	}

//******************************************************************************************************************
// 粉
//******************************************************************************************************************
	private void initItem_Dust() {
		System.out.println("----  AdditionalOre Init-initItem_Dust START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("dust");

		ITEM_dusts = new ItemDust(ID_dusts);
		GameRegistry.registerItem(ITEM_dusts, "Item Dust");

		for(int i = 0; i < ItemDust.uniNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(ITEM_dusts, 1, i), ItemDust.uniNames[i] +" Dust");
			LanguageRegistry.instance().addNameForObject(new ItemStack(ITEM_dusts, 1, i), "ja_JP", ItemDust.jpNames[i]);
			OreDictionary.registerOre("dust" + ItemDust.uniNames[i], new ItemStack(ITEM_dusts, 1, i));
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("dust" + ItemDust.uniNames[i], new ItemStack(ITEM_dusts, 1, i));
		}

		//ブロンズの粉
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(ITEM_dusts, 3, 10),
				"dustTin", "dustCopper", "dustCopper"));

		//鋼の粉
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(ITEM_dusts, 1, 11),
				"dustIron", "dustCoal"));

		System.out.println("----  AdditionalOre Init-initItem_Dust  END   ----");
	}

//******************************************************************************************************************
// インゴット
//******************************************************************************************************************
	private void initItem_Ingot() {
		System.out.println("----  AdditionalOre Init-initItem_Ingot START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("ingot");

		ITEM_ingots = new ItemIngot(ID_ingots);
		GameRegistry.registerItem(ITEM_ingots, "Item Ingot");

		for(int i = 0; i < ItemIngot.uniNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(ITEM_ingots, 1, i), ItemIngot.uniNames[i] +" Ingot");
			LanguageRegistry.instance().addNameForObject(new ItemStack(ITEM_ingots, 1, i), "ja_JP", ItemIngot.jpNames[i]);
			OreDictionary.registerOre("ingot" + ItemIngot.uniNames[i], new ItemStack(ITEM_ingots, 1, i));
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ingot" + ItemIngot.uniNames[i], new ItemStack(ITEM_ingots, 1, i));
		}

		//鉄インゴット
		OreDictionary.registerOre("ingotIron", new ItemStack(Item.ingotIron));
		JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ingotIron", new ItemStack(Item.ingotIron));

		FurnaceRecipes.smelting().addSmelting(ID_masses, 0, new ItemStack(Item.ingotIron), 0.35f);	//鉄の塊→鉄インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 1, new ItemStack(Item.ingotIron), 0.35f);	//鉄の粉→鉄インゴット

		//金インゴット
		OreDictionary.registerOre("ingotGold", new ItemStack(Item.ingotGold));
		JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ingotGold", new ItemStack(Item.ingotGold));

		FurnaceRecipes.smelting().addSmelting(ID_masses, 1, new ItemStack(Item.ingotGold), 0.7f);	//金の塊→金インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 2, new ItemStack(Item.ingotGold), 0.7f);	//金の粉→金インゴット

		//銅インゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 4, new ItemStack(ITEM_ingots, 1, 0), 0.2f);//銅の塊→銅インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 6, new ItemStack(ITEM_ingots, 1, 0), 0.2f);	//銅の粉→銅インゴット

		//錫インゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 5, new ItemStack(ITEM_ingots, 1, 1), 0.2f);//錫の塊→錫インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 7, new ItemStack(ITEM_ingots, 1, 1), 0.2f);	//錫の粉→錫インゴット

		//ウラニウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 6, new ItemStack(ITEM_ingots, 1, 2), 1f);	//ウラニウムの塊→ウラニウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 8, new ItemStack(ITEM_ingots, 1, 2), 1f);	//ウラニウムの粉→ウラニウムインゴット

		//アルミニウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 9, new ItemStack(ITEM_ingots, 1, 3), 1f);	//アルミニウムの粉→アルミニウムインゴット

		//青銅インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 10, new ItemStack(ITEM_ingots, 1, 4), 1f);	//青銅の粉→青銅インゴット

		//鋼インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 11, new ItemStack(ITEM_ingots, 1, 5), 1f);	//鋼の粉→鋼インゴット

		//チタニウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 8, new ItemStack(ITEM_ingots, 1, 6), 1f);	//チタニウムの塊→チタニウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 12, new ItemStack(ITEM_ingots, 1, 6), 1f);	//チタニウムの粉→チタニウムインゴット

		//鉛インゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 9, new ItemStack(ITEM_ingots, 1, 7), 1f);	//鉛の塊→鉛インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 13, new ItemStack(ITEM_ingots, 1, 7), 1f);	//鉛の粉→鉛インゴット

		//ニッケルインゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 10, new ItemStack(ITEM_ingots, 1, 8), 1f);	//ニッケルの塊→ニッケルインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 14, new ItemStack(ITEM_ingots, 1, 8), 1f);	//ニッケルの粉→ニッケルインゴット

		//銀インゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 11, new ItemStack(ITEM_ingots, 1, 9), 1f);	//銀の塊→銀インゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 15, new ItemStack(ITEM_ingots, 1, 9), 1f);	//銀の粉→銀インゴット

		//クロムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 12, new ItemStack(ITEM_ingots, 1, 10), 1f);//クロムの塊→クロムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 16, new ItemStack(ITEM_ingots, 1, 10), 1f);	//クロムの粉→クロムインゴット

		//タングステンインゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 13, new ItemStack(ITEM_ingots, 1, 11), 1f);//タングステンの塊→タングステンインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 17, new ItemStack(ITEM_ingots, 1, 11), 1f);	//タングステンの粉→タングステンインゴット

		//イリジウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_masses, 14, new ItemStack(ITEM_ingots, 1, 12), 1f);//イリジウムの塊→イリジウムインゴット
		FurnaceRecipes.smelting().addSmelting(ID_dusts, 18, new ItemStack(ITEM_ingots, 1, 12), 1f);	//イリジウムの粉→イリジウムインゴット

		System.out.println("----  AdditionalOre Init-initItem_Ingot  END   ----");
	}

//******************************************************************************************************************
// ナゲット
//******************************************************************************************************************
	private void initItem_Nugget() {
		System.out.println("----  AdditionalOre Init-initItem_Nugget START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("nugget");

		ITEM_nuggets = new ItemNugget(ID_nuggets);
		GameRegistry.registerItem(ITEM_nuggets, "Item Nugget");

		for(int i = 0; i < ItemNugget.uniNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(ITEM_nuggets, 1, i), ItemNugget.uniNames[i] +" Nugget");
			LanguageRegistry.instance().addNameForObject(new ItemStack(ITEM_nuggets, 1, i), "ja_JP", ItemNugget.jpNames[i]);
			OreDictionary.registerOre("nugget" + ItemNugget.uniNames[i], new ItemStack(ITEM_nuggets, 1, i));
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("nugget" + ItemNugget.uniNames[i], new ItemStack(ITEM_nuggets, 1, i));
		}

		//鉄のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 0), "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.ingotIron), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetIron"));

		//ラピスラズリのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 1), "massLapis"));

		//エメラルドのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 2), Item.emerald));

		//ダイヤモンドのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 3), Item.diamond));

		//石英のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 4), "massQuartz"));

		//銅のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 5), "ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 0), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetCopper"));

		//錫のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 6), "ingotTin"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 1), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetTin"));

		//ウラニウムのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 7), "ingotUranium"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 2), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetUranium"));

		//アルミニウムのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 8), "ingotAluminium"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 3), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetAluminium"));

		//青銅のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 9), "ingotBronze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 4), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetBronze"));

		//鋼のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 10), "ingotSteel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 5), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetSteel"));

		//チタニウムのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 11), "ingotTitanium"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 6), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetTitanium"));

		//鉛のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 12), "ingotLead"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 7), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetLead"));

		//ニッケルのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 13), "ingotNickel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 8), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetNickel"));

		//銀のかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 14), "ingotSilver"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 9), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetSilver"));

		//クロムのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 15), "ingotChrome"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 10), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetChrome"));

		//タングステンのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 16), "ingotTungsten"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 11), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetTungsten"));

		//イリジウムのかけら
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_nuggets, 9, 17), "ingotIridium"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ingots, 1, 12), "XXX", "XXX", "XXX", Character.valueOf('X'), "nuggetIridium"));

		System.out.println("----  AdditionalOre Init-initItem_Nugget  END   ----");
	}

//******************************************************************************************************************
// 宝石
//******************************************************************************************************************
	private void initItem_Gem() {
		System.out.println("----  AdditionalOre Init-initItem_Jewel START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("gem");

		ITEM_gems = new ItemGem(ID_gems);
		GameRegistry.registerItem(ITEM_gems, "Item Gem");

		for(int i = 0; i < ItemGem.uniNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(ITEM_gems, 1, i), ItemGem.uniNames[i] +" Gem");
			LanguageRegistry.instance().addNameForObject(new ItemStack(ITEM_gems, 1, i), "ja_JP", ItemGem.jpNames[i]);
			OreDictionary.registerOre("gem" + ItemGem.uniNames[i], new ItemStack(ITEM_gems, 1, i));
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("gem" + ItemGem.uniNames[i], new ItemStack(ITEM_gems, 1, i));
		}

		System.out.println("----  AdditionalOre Init-initItem_Jewel  END   ----");
	}

//******************************************************************************************************************
// ピッケル
//******************************************************************************************************************
	private void initItem_Pickaxe() {
		ITEM_pickaxes[0] = new ItemPickaxe(ID_pickaxes[0], EnumToolMaterial.STONE).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Copper Pickaxe");
		ITEM_pickaxes[1] = new ItemPickaxe(ID_pickaxes[1], EnumToolMaterial.STONE).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Bronze Pickaxe");
		ITEM_pickaxes[2] = new ItemPickaxe(ID_pickaxes[2], EnumToolMaterial.IRON).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Steel Pickaxe");
		ITEM_pickaxes[3] = new ItemPickaxe(ID_pickaxes[3], EnumToolMaterial.IRON).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Titanium Pickaxe");
		ITEM_pickaxes[4] = new ItemPickaxe(ID_pickaxes[4], EnumToolMaterial.IRON).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Chrome Pickaxe");
		ITEM_pickaxes[5] = new ItemPickaxe(ID_pickaxes[5], EnumToolMaterial.EMERALD).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Tungsten Pickaxe");
		ITEM_pickaxes[6] = new ItemPickaxe(ID_pickaxes[6], ENUM_gemTool).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Ruby Pickaxe");
		ITEM_pickaxes[7] = new ItemPickaxe(ID_pickaxes[7], ENUM_gemTool).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Sapphire Pickaxe");
		ITEM_pickaxes[8] = new ItemPickaxe(ID_pickaxes[8], ENUM_emeraldTool).setCreativeTab(TABS_tool).setUnlocalizedName("additionalOre:Emerald Pickaxe");

		String[] uniNames = new String[] { "Copper", "Bronze", "Steel", "Titanium", "Chrome", "Tungsten", "Ruby", "Sapphire", "Emerald" };
		String[] jpNames = new String[] { "\u9285", "\u9752\u9285", "\u92FC", "\u30C1\u30BF\u30CB\u30A6\u30E0", "\u30AF\u30ED\u30E0", "\u30BF\u30F3\u30B0\u30B9\u30C6\u30F3", "\u30EB\u30D3\u30FC", "\u30B5\u30D5\u30A1\u30A4\u30A2", "\u30A8\u30E1\u30E9\u30EB\u30C9" };
		for(int i = 0; i < uniNames.length; i++) {
			GameRegistry.registerItem(ITEM_pickaxes[i], uniNames[i] +" Pickaxe");
			LanguageRegistry.addName(ITEM_pickaxes[i], uniNames[i] +" Pickaxe");
			LanguageRegistry.instance().addNameForObject(ITEM_pickaxes[i], "ja_JP", jpNames[i] + "\u306E\u3064\u308B\u306F\u3057");
			OreDictionary.registerOre("pickaxe" + uniNames[i], ITEM_pickaxes[i]);
			JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("pickaxe" +uniNames[i], new ItemStack(ITEM_pickaxes[i]));
		}

		//銅のつるはし
		ItemStack resipeItem = new ItemStack(ITEM_pickaxes[0]);
		resipeItem.addEnchantment(Enchantment.unbreaking, 1);	//耐久
		GameRegistry.addRecipe(new ShapedOreRecipe(
				resipeItem.copy(),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "ingotCopper",
				Character.valueOf('Y'), Item.stick));

		//青銅のつるはし
		resipeItem = new ItemStack(ITEM_pickaxes[1]);
		resipeItem.addEnchantment(Enchantment.unbreaking, 2);	//耐久
		resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
		GameRegistry.addRecipe(new ShapedOreRecipe(
				resipeItem.copy(),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "ingotBronze",
				Character.valueOf('Y'), Item.stick));

		//鋼のつるはし
		resipeItem = new ItemStack(ITEM_pickaxes[2]);
		resipeItem.addEnchantment(Enchantment.unbreaking, 2);	//耐久
		resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
		GameRegistry.addRecipe(new ShapedOreRecipe(
				resipeItem.copy(),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "ingotSteel",
				Character.valueOf('Y'), Item.stick));

		//チタンのつるはし
		resipeItem = new ItemStack(ITEM_pickaxes[3]);
		resipeItem.addEnchantment(Enchantment.unbreaking, 1);	//耐久
		resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
		resipeItem.addEnchantment(Enchantment.fortune, 1);	//幸運
		GameRegistry.addRecipe(new ShapedOreRecipe(
				resipeItem.copy(),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "ingotTitanium",
				Character.valueOf('Y'), Item.stick));

		//クロムのつるはし
		resipeItem = new ItemStack(ITEM_pickaxes[4]);
		resipeItem.addEnchantment(Enchantment.unbreaking, 1);	//耐久
		resipeItem.addEnchantment(Enchantment.efficiency, 1);	//効率強化
		resipeItem.addEnchantment(Enchantment.fortune, 1);	//幸運
		GameRegistry.addRecipe(new ShapedOreRecipe(
				resipeItem.copy(),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "ingotChrome",
				Character.valueOf('Y'), Item.stick));

		//タングステンのつるはし
		resipeItem = new ItemStack(ITEM_pickaxes[5]);
		resipeItem.addEnchantment(Enchantment.unbreaking, 3);	//耐久
		resipeItem.addEnchantment(Enchantment.efficiency, 3);	//効率強化
		GameRegistry.addRecipe(new ShapedOreRecipe(
				resipeItem.copy(),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "ingotTungsten",
				Character.valueOf('Y'), Item.stick));

		//ルビーのつるはし
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ITEM_pickaxes[6]),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "gemRuby",
				Character.valueOf('Y'), Item.stick));

		//サファイアのつるはし
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ITEM_pickaxes[7]),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), "gemSapphire",
				Character.valueOf('Y'), Item.stick));

		//エメラルドのつるはし
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ITEM_pickaxes[8]),
				"XXX", " Y ", " Y ",
				Character.valueOf('X'), Item.emerald,
				Character.valueOf('Y'), Item.stick));

	}

	@Mod.Init
	public void Init(FMLInitializationEvent event) {
	}

	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent event) {

		if (Loader.isModLoaded("IC2")) {	//IC2導入時

			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 0), new ItemStack(ITEM_dusts, 2, 1));		//鉄
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 1), new ItemStack(ITEM_dusts, 2, 2));		//金
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 3), new ItemStack(ITEM_dusts, 2, 5));		//クォーツ
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 4), new ItemStack(ITEM_dusts, 2, 6));		//銅
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 5), new ItemStack(ITEM_dusts, 2, 7));		//錫
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 6), new ItemStack(ITEM_dusts, 2, 8));		//ウラニウム
			FurnaceRecipes.smelting().addSmelting(ID_masses, 7, new ItemStack(ITEM_ingots, 2, 3), 0.1f);		//アルミニウム（ボーキサイト）
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 8), new ItemStack(ITEM_dusts, 2, 12));	//チタニウム
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 9), new ItemStack(ITEM_dusts, 2, 13));	//鉛
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 10), new ItemStack(ITEM_dusts, 2, 14));	//ニッケル
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 11), new ItemStack(ITEM_dusts, 2, 15));	//銀
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 12), new ItemStack(ITEM_dusts, 2, 16));	//クロム
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 13), new ItemStack(ITEM_dusts, 2, 17));	//タングステン
			Recipes.macerator.addRecipe(new ItemStack(ITEM_masses, 1, 14), new ItemStack(ITEM_dusts, 2, 18));	//イリジウム
		}

		if(Loader.isModLoaded("kegare.caveworld")) {
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[5], 6), 97, 105, 30);	//クォーツ鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[5], 6), 150, 159, 30);	//クォーツ鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[5], 8), 224, 242, 60);	//クォーツ鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[6], 6), 90, 109, 25);	//銅鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[6], 6), 130, 160, 25);	//銅鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[6], 8), 185, 245, 50);	//銅鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[7], 6), 88, 127, 25);	//錫鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[7], 6), 145, 165, 25);	//錫鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[7], 8), 215, 254, 50);	//錫鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[8], 3), 89, 120, 20);	//ウラニウム鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[8], 3), 133, 165, 20);	//ウラニウム鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[8], 5), 191, 254, 40);	//ウラニウム鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[9], 8), 99, 155, 70);	//ボーキサイト鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[9], 10), 179, 234, 70);	//ボーキサイト鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[10], 4), 94, 106, 25);	//鉛鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[10], 4), 147, 160, 25);	//鉛鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[10], 6), 220, 245, 50);	//鉛鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[11], 4), 94, 105, 25);	//ニッケル鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[11], 4), 149, 161, 25);	//ニッケル鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[11], 6), 223, 245, 50);	//ニッケル鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[12], 4), 92, 104, 20);	//銀鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[12], 4), 150, 163, 20);	//銀鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_ores[12], 6), 225, 250, 40);	//銀鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_CrackBlock, 3), 90, 114, 30);	//岩の裂け目
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_CrackBlock, 3), 140, 165, 30);	//岩の裂け目
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ID_CrackBlock, 4), 205, 254, 50);	//岩の裂け目

		}
	}
}
