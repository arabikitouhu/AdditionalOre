package mods.additionalOre;

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
import mods.additionalOre.blocks.Block_ReplacebleVanillaBlock;
import mods.additionalOre.blocks.CrackStone;
import mods.additionalOre.blocks.Enum_ores;
import mods.additionalOre.blocks.OreBlock;
import mods.additionalOre.init.ConfigurationManager;
import mods.additionalOre.init.recipes.IC2Recipe;
import mods.additionalOre.init.recipes.ModRecipe;
import mods.additionalOre.init.recipes.TE2Recipe;
import mods.additionalOre.items.*;
import mods.additionalOre.worlds.gens.WorldGenerator;
import mods.japanAPI.JapanAPI;
import mods.japanAPI.items.JAPI_ForgeEnumMaterials;
import mods.japanAPI.items.JAPI_ItemCrafingTool;
import mods.japanAPI.utils.OreDictionaryUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@Mod(modid = "AdditionalOre",name = "AdditionalOre 1.5.2",version = "0.1.2",dependencies = "required-after:JapanAPI")
@NetworkMod(clientSideRequired = true,serverSideRequired = true)
public class AdditionalOre
{

	@Instance("AdditionalOre")
	public static AdditionalOre instance;

	public static int Model_ID = -1;

	@SidedProxy(clientSide = "mods.additionalOre.ClientProxy", serverSide = "mods.additionalOre.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs TABS_ore = new CreativeTab(StatCollector.translateToLocal("AdditonalOre : Block"));
	public static final CreativeTabs TABS_tool = new CreativeTab(StatCollector.translateToLocal("AdditonalOre : Tools"));

	public static final WorldGenerator GENERATOR_world = new WorldGenerator();

	public static int[] ID_ores = {14, 15, 129, 21, 56};
	public static Block_ReplacebleVanillaBlock[] BLOCK_ores = new Block_ReplacebleVanillaBlock[ID_ores.length];
    public static Block Block_ores;

	public static Block BLOCK_CrackBlock;

	public static Item ITEM_masses;
	public static Item ITEM_dusts;
	public static Item ITEM_ingots;
	public static Item ITEM_nuggets;
	public static Item ITEM_gems;

    public static Item[] ITEM_hammer = new Item[ConfigurationManager.ID_Hammer.length];
	public static Item[] ITEM_pickaxes = new Item[ConfigurationManager.ID_pickaxes.length];
    public static Item[] ITEM_sickel = new Item[ConfigurationManager.ID_Sickel.length];

	public static EnumToolMaterial ENUM_gemTool;
	public static EnumToolMaterial ENUM_emeraldTool;

	static {
		ENUM_gemTool = EnumHelper.addToolMaterial("汎用宝石ツール", 2, 1561, 6F, 5, 16);
		ENUM_emeraldTool = EnumHelper.addToolMaterial("エメラルドツール", 3, 3122, 16F, 3, 20);
	}

	@Mod.PreInit
 	public void preInit(FMLPreInitializationEvent event)
    {
        //既存レシピの変更（不一致の削除）
		ModRecipe.initRecipe();

        //染料（青）の変更処理
		ModRecipe.initDyePowder();

		ConfigurationManager.loadConfig(event);	//コンフィグのロード

		initNewItemAndBlockAndTool();	//追加アイテム&ブロック&ツール

		JapanAPI.EVENT_entityItemPickupEventHook.addExcludedKeyWordList("oreQuartz");
	}

	private void initNewItemAndBlockAndTool()
    {
        //追加アイテム（塊）
		initItem_Mass();
        //追加アイテム（粉）
		initItem_Dust();
        //追加アイテム（インゴット）
		initItem_Ingot();
        //追加アイテム（ナゲット）
		initItem_Nugget();

		initVanillaBlock();		//バニラ鉱石ブロックの初期化＆レジスタ登録処理
		initBlock_Ore();		//追加ブロック（鉱石）

		initCrackBlock();	//岩の裂け目

        //石英鉱石
        if(ConfigurationManager.GenQuartz)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, Enum_ores.QUARTZ.meta, 6), 13, 31, 60);
        }

        //銅鉱石
        if(ConfigurationManager.GenCopper)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, Enum_ores.COPPER.meta, 6), 10, 70, 50);
        }

        //錫鉱石
        if(ConfigurationManager.GenTin)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, Enum_ores.TIN.meta, 6), 1, 40, 50);
        }

        //ウラニウム鉱石
        if(ConfigurationManager.GenUranium)
		GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre,Enum_ores.URANIUM.meta, 3), 1, 63, 40);

        //ボーキサイト鉱石
        if(ConfigurationManager.GenBauxite)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre,Enum_ores.BAUXITE.meta, 8), 20, 75, 70);
        }

        //鉛鉱石
        if(ConfigurationManager.GenLead)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre,Enum_ores.LEAD.meta, 4), 10, 35, 50);
        }
        //ニッケル鉱石
        if(ConfigurationManager.GenNickel)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre,Enum_ores.NICKEL.meta, 4), 10, 32, 50);
        }
        //銀鉱石
        if(ConfigurationManager.GenSilver)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre,Enum_ores.SILVER.meta, 4), 5, 30, 40);
        }


        //岩の裂け目
        if(ConfigurationManager.GenQuartz)
        {
		    GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_CrackBlock, 3), 1, 50, 30);
        }


		GameRegistry.registerWorldGenerator(new WorldGenerator());	//鉱石生成の設定

		initItem_Gem();		//追加アイテム（宝石）

		JAPI_ForgeEnumMaterials.ENUM_gemTool.customCraftingMaterial = ITEM_gems;
        JAPI_ForgeEnumMaterials.ENUM_emeraldTool.customCraftingMaterial = Item.emerald;

		initItem_Tools();

	}

	private void initCrackBlock()
    {
		//岩の裂け目
		BLOCK_CrackBlock = new CrackStone(ConfigurationManager.ID_CrackBlock);

		GameRegistry.registerBlock(BLOCK_CrackBlock, BLOCK_CrackBlock.getUnlocalizedName());
		LanguageRegistry.addName(BLOCK_CrackBlock, "Crack Stone");
		LanguageRegistry.instance().addNameForObject(BLOCK_CrackBlock, "ja_JP", "岩の裂け目");
	}

	private void initVanillaBlock()
    {
		System.out.println("----  AdditionalOre Init-initVanillaBlock START  ----");

		ItemStack[] items =
                {
			        new ItemStack(ITEM_masses, 1, 1),	//Gold
			        new ItemStack(ITEM_masses, 1, 0),	//Iron
			        new ItemStack(Item.emerald, 1),		//Emerald
			        new ItemStack(ITEM_masses, 1, 2),	//Lapis
			        new ItemStack(Item.diamond, 1),		//Diamond
		        };

		for(int i = 0; i < 5; i++)
        {
            Block.blocksList[ID_ores[i]] = null;
            BLOCK_ores[i] = (Block_ReplacebleVanillaBlock) (new Block_ReplacebleVanillaBlock(ID_ores[i])).setDropItem(items[i]).setUnlocalizedName(Block_ReplacebleVanillaBlock.uniNames[i]);

            GameRegistry.registerBlock(BLOCK_ores[i], Block_ReplacebleVanillaBlock.enNames[i] + " Ore");

            LanguageRegistry.addName(BLOCK_ores[i], Block_ReplacebleVanillaBlock.enNames[i] + " Ore");
            LanguageRegistry.instance().addNameForObject(BLOCK_ores[i] , "ja_JP", Block_ReplacebleVanillaBlock.jpNames[i] + "鉱石");

            OreDictionary.registerOre("ore" + Block_ReplacebleVanillaBlock.enNames[i], BLOCK_ores[i]);
            JapanAPI.EVENT_entityItemPickupEventHook.addCoercedList("ore" + Block_ReplacebleVanillaBlock.enNames[i], new ItemStack(BLOCK_ores[i]));
		}

		System.out.println("----  AdditionalOre Init-initVanillaBlock  END   ----");
	}

    //******************************************************************************************************************
    // 鉱石
    //******************************************************************************************************************
	private void initBlock_Ore()
    {
        System.out.println("----  AdditionalOre Init-initBlock_Ore START  ----");

        Block_ores = new OreBlock(ConfigurationManager.ID_blockOre);

        System.out.println("----  AdditionalOre Init-initBlock_Ore  END   ----");
	}

    //******************************************************************************************************************
    // 塊
    //******************************************************************************************************************
    private void initItem_Mass()
    {
        System.out.println("----  AdditionalOre Init-initItem_Mass START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("mass");

		ITEM_masses = new ItemMass(ConfigurationManager.ID_masses);

        //ラピスラズリの塊 → 青染料x4
		FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, 2, new ItemStack(95, 4, 4), 2F);

		System.out.println("----  AdditionalOre Init-initItem_Mass  END   ----");
	}

    //******************************************************************************************************************
    // 粉
    //******************************************************************************************************************
	private void initItem_Dust()
    {
		System.out.println("----  AdditionalOre Init-initItem_Dust START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("dust");

		ITEM_dusts = new ItemDust(ConfigurationManager.ID_dusts);

        ModRecipe.dustRecipe();

		System.out.println("----  AdditionalOre Init-initItem_Dust  END   ----");
	}

    //******************************************************************************************************************
    // インゴット
    //******************************************************************************************************************
	private void initItem_Ingot()
    {
        System.out.println("----  AdditionalOre Init-initItem_Ingot START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("ingot");

		ITEM_ingots = new ItemIngot(ConfigurationManager.ID_ingots);

		ModRecipe.smeltingRecipe();
        ModRecipe.ingotRecipe();
		System.out.println("----  AdditionalOre Init-initItem_Ingot  END   ----");
	}

    //******************************************************************************************************************
    // ナゲット
    //******************************************************************************************************************
	private void initItem_Nugget()
    {
		System.out.println("----  AdditionalOre Init-initItem_Nugget START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("nugget");

		ITEM_nuggets = new ItemNugget(ConfigurationManager.ID_nuggets);

        String[] Items = new String[]{"Iron","Emerald","Diamond","Copper","Tin","Bronze","Uranium","Bronze","Steel","Titanium","Lead","Silver","Nickel","Chrome","Tungsten","Iridium"};


        for(int i = 0; i < Items.length; i++)
        {


            if(Items[i] == "Emerald")
            {
                GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionaryUtil.getOreDic("nugget" + Items[i],9),Item.emerald));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.emerald,1),
                        "XXX","XXX","XXX",
                        'X',"nugget" + Items[i]));


            }else if(Items[i] == "Diamond")
            {
                GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionaryUtil.getOreDic("nugget"+ Items[i],9),Item.diamond));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.diamond,1),
                        "XXX","XXX","XXX",
                        'X',"nugget" + Items[i]));

            }else
            {
                GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionaryUtil.getOreDic("nugget" + Items[i],9),"ingot" + Items[i]));
                GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionaryUtil.getOreDic("ingot" + Items[i]),
                        "XXX", "XXX", "XXX",
                        'X', "nugget" + Items[i]));
            }


        }

		System.out.println("----  AdditionalOre Init-initItem_Nugget  END   ----");
	}

    //******************************************************************************************************************
    // 宝石
    //******************************************************************************************************************
    private void initItem_Gem()
    {
		System.out.println("----  AdditionalOre Init-initItem_Jewel START  ----");

		JapanAPI.EVENT_entityItemPickupEventHook.addConversionKeyWordList("gem");

		ITEM_gems = new ItemGem(ConfigurationManager.ID_gems);

		System.out.println("----  AdditionalOre Init-initItem_Jewel  END   ----");
	}

    //******************************************************************************************************************
    // ツール群
    //******************************************************************************************************************
	private void initItem_Tools()
    {
		ITEM_pickaxes[0] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[0], AO_ItemPickaxe.Pickaxes.Copper).setCreativeTab(TABS_tool);
		ITEM_pickaxes[1] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[1], AO_ItemPickaxe.Pickaxes.Bronze).setCreativeTab(TABS_tool);
		ITEM_pickaxes[2] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[2], AO_ItemPickaxe.Pickaxes.Steel).setCreativeTab(TABS_tool);
		ITEM_pickaxes[3] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[3], AO_ItemPickaxe.Pickaxes.Titanium).setCreativeTab(TABS_tool);
		ITEM_pickaxes[4] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[4], AO_ItemPickaxe.Pickaxes.Chrome).setCreativeTab(TABS_tool);
		ITEM_pickaxes[5] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[5], AO_ItemPickaxe.Pickaxes.Tungsten).setCreativeTab(TABS_tool);
		ITEM_pickaxes[6] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[6], AO_ItemPickaxe.Pickaxes.Ruby).setCreativeTab(TABS_tool);
		ITEM_pickaxes[7] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[7], AO_ItemPickaxe.Pickaxes.Sapphire).setCreativeTab(TABS_tool);
		ITEM_pickaxes[8] = new AO_ItemPickaxe(ConfigurationManager.ID_pickaxes[8], AO_ItemPickaxe.Pickaxes.Emerald).setCreativeTab(TABS_tool);

        ITEM_hammer[0] = new AO_ItemCraftingTool(ConfigurationManager.ID_Hammer[0], JAPI_ItemCrafingTool.Types.Wood,0).setCreativeTab(TABS_tool);
        ITEM_hammer[1] = new AO_ItemCraftingTool(ConfigurationManager.ID_Hammer[1], JAPI_ItemCrafingTool.Types.Stone,0).setCreativeTab(TABS_tool);

        ITEM_sickel[0] = new AO_Sickel(ConfigurationManager.ID_Sickel[0],EnumToolMaterial.WOOD,"Wood").setCreativeTab(TABS_tool);

        ModRecipe.PickaxeRecipe();
        ModRecipe.HammerRecipe();

	}

	@Mod.Init
	public void Init(FMLInitializationEvent event)
    {

	}

	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent event)
    {
        ConfigurationManager.loadedMods();

        if (ConfigurationManager.isLoadedInductrialCraft2)
        {	//IC2導入時
            IC2Recipe.initalized();
            if(ConfigurationManager.isLoadedGregTech)
            {
                //GregTech導入時
            }
		}

        if(ConfigurationManager.isLoadedThermalExpantion2)
        {
            //ThermalExpantion導入時
            TE2Recipe.Initilization();
        }



		if(Loader.isModLoaded("kegare.caveworld"))
        {
            GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 5, 6), 97, 105, 30);	//クォーツ鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 5, 6), 150, 159, 30);	//クォーツ鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 5, 8), 224, 242, 60);	//クォーツ鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 6, 6), 90, 109, 25);	//銅鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 6, 6), 130, 160, 25);	//銅鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 6, 8), 185, 245, 50);	//銅鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 7, 6), 88, 127, 25);	//錫鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 7, 6), 145, 165, 25);	//錫鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 7, 8), 215, 254, 50);	//錫鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 8, 3), 89, 120, 20);	//ウラニウム鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 8, 3), 133, 165, 20);	//ウラニウム鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 8, 5), 191, 254, 40);	//ウラニウム鉱石

			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 9, 8), 99, 155, 70);	 //ボーキサイト鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 9, 10), 179, 234, 70); //ボーキサイト鉱石

            //鉛鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 10, 4), 94, 106, 25);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 10, 4), 147, 160, 25);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 10, 6), 220, 245, 50);

            //ニッケル鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 11, 4), 94, 105, 25);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 11, 4), 149, 161, 25);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 11, 6), 223, 245, 50);

            //銀鉱石
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 12, 4), 92, 104, 20);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 12, 4), 150, 163, 20);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_blockOre, 12, 6), 225, 250, 40);

            //岩の裂け目
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_CrackBlock, 3), 90, 114, 30);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_CrackBlock, 3), 140, 165, 30);
			GENERATOR_world.addGenMinable_Overworld(new WorldGenMinable(ConfigurationManager.ID_CrackBlock, 4), 205, 254, 50);

		}
	}
}
