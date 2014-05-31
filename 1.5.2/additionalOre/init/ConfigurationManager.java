package mods.additionalOre.init;


import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;

public class ConfigurationManager
{
    public static Configuration config;
    public static int BlockID = 4000;

    public static int ID_blockOre;
    public static int ID_CrackBlock = 4030;

    public static int ItemID = 12500;

    public static int ID_masses;
    public static int ID_dusts;

    public static int ID_ingots = 12502;
    public static int ID_nuggets = 12503;
    public static int ID_gems = 12504;
    public static int ID_pickaxes[] = new int[9];
    public static int ID_Hammer[] = new int[11];
    public static int ID_Sickel[] = new int[12];
    public static int ID_Axe[] = new int[9];
    public static int ID_Shovel[] = new int[9];
    public static int ID_Paxel[] = new int[12];

    public static boolean GenCopper;
    public static boolean GenTin;
    public static boolean GenNickel;
    public static boolean GenLead;
    public static boolean GenSilver;
    public static boolean GenQuartz;
    public static boolean GenUranium;
    public static boolean GenBauxite;
    public static boolean GenRuby;
    public static boolean GenSapphire;
    public static boolean GenGreenSapphire;
    public static boolean GenCrackStone;


    //溶剤かまどを使った鋼鉄の精錬
    public static boolean canInductionSteel;
    //溶剤かまどを使ったアルミの精錬
    public static boolean canInductionAluminium;
    //溶剤かまどを使ったチタンの精錬
    public static boolean canInductionTitanium;
    //溶剤かまどを使ったクロムの精錬
    public static boolean canInductionChrome;
    //溶剤かまどを使ったタングステンの精錬
    public static boolean canInductionTusgsten;
    //溶剤かまどを使ったタングステン鋼の精錬
    public static boolean canInductionTungstenSteel;

    public static boolean isLoadedInductrialCraft2 = false;
    public static boolean isLoadedThermalExpantion2 = false;

    public static boolean isLoadedGregTech = false;

    public ConfigurationManager(Configuration cfg)
    {
        this.config = cfg;
    }

    public int getItem(String key,int defaultID)
    {
        return config.get("ITEM",key,defaultID).getInt();
    }

    public static int getItem(String key)
    {
        return config.get("ITEM",key,ItemID++).getInt();
    }

    public int getBlock(String key,int defaultID)
    {
        return config.get("BLOCK",key,defaultID).getInt();
    }

    public static int getBlock(String key)
    {
        return config.get("BLOCK",key,BlockID++).getInt();
    }

    public static boolean getGEN(String key, boolean value)
    {
        return config.get("ORE GEN",key,value).getBoolean(value);
    }

    public static void save()
    {
        config.save();
    }

    public static void loadConfig(FMLPreInitializationEvent event)
    {
        ConfigurationManager cfg = new ConfigurationManager(new Configuration(event.getSuggestedConfigurationFile()));

        ID_blockOre = getBlock("Block Ore");
        ID_CrackBlock = getBlock("Block CrackStone");

        ID_masses = getItem("Mass Item");				//塊
        ID_dusts = getItem("Dust Item");				//粉
        ID_ingots = getItem("Ingot Item");				//インゴット
        ID_nuggets = getItem("Nugget Item");			//ナゲット
        ID_gems = getItem("Gem Item");					//宝石

        ID_pickaxes[0] = getItem("Copper Pickaxe");		//銅のつるはし
        ID_pickaxes[1] = getItem("Bronze Pickaxe");     //青銅のつるはし
        ID_pickaxes[2] = getItem("Steel Pickaxe");     //鋼鉄のつるはし
        ID_pickaxes[3] = getItem("Titanium Pickaxe");
        ID_pickaxes[4] = getItem("Chrome Pickaxe");
        ID_pickaxes[5] = getItem("Tungsten Pickaxe");
        ID_pickaxes[6] = getItem("Ruby Pickaxe");
        ID_pickaxes[7] = getItem("Sapphire Pickaxe");
        ID_pickaxes[8] = getItem("Emerald Pickaxe");

        ID_Hammer[0] = getItem("Wooden Hammer");
        ID_Hammer[1] = getItem("Stone Hammer");
        ID_Hammer[2] = getItem("Iron Hammer");
        ID_Hammer[3] = getItem("Bronze Hammer");
        ID_Hammer[4] = getItem("Steel Hammer");
        ID_Hammer[5] = getItem("TungstenSteel Hammer");
        ID_Hammer[6] = getItem("Titanium Hammer");

        ID_Sickel[0] = getItem("Wooden Sickel");
        ID_Sickel[1] = getItem("Stone Sickel");
        ID_Sickel[2] = getItem("Iron Sickel");

        ID_Shovel[0] = getItem("Copper Shovel");
        ID_Shovel[1] = getItem("Bronze Shovel");
        ID_Shovel[2] = getItem("Steel Shovel");

        ID_Axe[0] = getItem("Cooper Axe");
        ID_Axe[1] = getItem("Bronze Axe");
        ID_Axe[2] = getItem("Steel Axe");

        ID_Paxel[0] = getItem("Wood Paxel");
        ID_Paxel[1] = getItem("Stone Paxel");
        ID_Paxel[2] = getItem("Copper Paxel");
        ID_Paxel[3] = getItem("Iron Paxel");
        ID_Paxel[4] = getItem("Bronze Paxel");
        ID_Paxel[5] = getItem("Steel Paxel");

        GenCopper = getGEN("can Generation CopperOre?",true);
        GenTin = getGEN("can Generation Tin Ore?",true);
        GenCrackStone = getGEN("can Generation Quartz Ore?",true);
        GenBauxite = getGEN("can Generation Bauxite Ore?",true);
        GenSilver = getGEN("can Generation Silver Ore?",true);
        GenNickel = getGEN("can Generation Nickel Ore?",true);
        GenLead = getGEN("can Generation Lead Ore?",true);
        GenUranium = getGEN("can Generation Uranium Ore?",true);





        save();

    }

    public static void loadedMods()
    {
        if(Loader.isModLoaded("IC2"))
        {
            isLoadedInductrialCraft2 = true;
            if(Loader.isModLoaded("GregTech-Addon"))
            {
                isLoadedGregTech = true;
            }
        }
        if(Loader.isModLoaded("ThermalExpantion"))
        {
            isLoadedThermalExpantion2 = true;
        }

    }
}
