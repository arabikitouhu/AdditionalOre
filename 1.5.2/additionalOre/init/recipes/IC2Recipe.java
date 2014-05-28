package mods.additionalOre.init.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.Items;
import ic2.api.recipe.Recipes;
import mods.additionalOre.AdditionalOre;
import mods.additionalOre.init.ConfigurationManager;
import mods.japanAPI.utils.OreDictionaryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class IC2Recipe
{
    public static void initalized()
    {
        Crafting();
        Macerator();
        Compressor();
        Extractor();
    }

    private static void Crafting()
    {
        //エネルギークリスタル
        Recipes.advRecipes.addRecipe(new ItemStack(Items.getItem("energyCrystal").getItem(),1),
                new Object[]{"RRR","RGR","RRR",
                        'R',Item.redstone,
                        'S',OreDictionaryUtil.getOreDic("gemRuby")

                });

        //ラポジトロンクリスタル
        Recipes.advRecipes.addRecipe(new ItemStack(Items.getItem("lapotronCrystal").getItem(),1),
                new Object[]{"LCL","LSL","LCL",
                        'L',OreDictionaryUtil.getOreDic("dyeBlue"),
                        'C',Items.getItem("electronicCircuit").getItem(),
                        'S',OreDictionaryUtil.getOreDic("gemSapphire")

                });

        //ガラスファイバー
        if(OreDictionaryUtil.getOreDic("ingotElectram") != null)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("glassFiberCableItem").getItem(), 8),
                    new Object[]{"GGG", "EDE", "GGG",
                            'G', Block.glass,
                            'E', "ingotElectram",
                            'D', "gemDiamond"
                    }));
        }else if(OreDictionaryUtil.getOreDic("dustElectram") != null){
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("glassFiberCableItem").getItem(), 8),
                new Object[]{"GGG", "EDE", "GGG",
                        'G', Block.glass,
                        'E', "dustElectram",
                        'D', "gemDiamond"
                }));
        }

        //電子回路x2
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("electronicCircuit").getItem(),2),
                new Object[]{"CCC","ERE","CCC",
                        'C',Items.getItem("insulatedCopperCableBlock"),
                        'E',"ingotElctram",
                        'R',Items.getItem("reinforcedIron")
                }));

        //改良型回路x2 パターン1
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("electronicCircuit").getItem(),2),
                new Object[]{"EGE","LCL","EGE",
                        'G',Item.lightStoneDust,
                        'E',"ingotElctram",
                        'R',"dyeBlue"
                }));

        //改良型回路x2 パターン2
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("electronicCircuit").getItem(),2),
                new Object[]{"ELE","GCG","ELE",
                        'G',Item.lightStoneDust,
                        'E',"ingotElctram",
                        'R',"dyeBlue"
                }));
    }

    private static void Macerator()
    {
        //鉄
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 0), OreDictionaryUtil.getOreDic("dustIron",2));
        //金
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 1), OreDictionaryUtil.getOreDic("dustGold",2));
        //石英
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 3), OreDictionaryUtil.getOreDic("dustQuartz",2));
        //銅
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 4), OreDictionaryUtil.getOreDic("dustCopper",2));
        //錫
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 5), OreDictionaryUtil.getOreDic("dustTin",2));

        //ウラニウム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 6), OreDictionaryUtil.getOreDic("dustUranium",2));

        //ボーキサイト(アルミニウム)　※GregTech導入時はかまどによるアルミニウムへの精錬が不可能となる。
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 7),OreDictionaryUtil.getOreDic("dustBauxite",2));
        if(ModLoader.isModLoaded("GregTech-Addon"))
        {

        }else
        {
            FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, 7, OreDictionaryUtil.getOreDic("ingotAluminium", 2),1F);
            FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, 7, OreDictionaryUtil.getOreDic("ingotAluminium", 1),1F);
        }

        //チタニウム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 8),OreDictionaryUtil.getOreDic("dustTitanium",2));
        //鉛
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 9),OreDictionaryUtil.getOreDic("dustLead",2));
        //ニッケル
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 10),OreDictionaryUtil.getOreDic("dustNickel",2));
        //銀
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 11),OreDictionaryUtil.getOreDic("dustSilver",2));
        //クロム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 12),OreDictionaryUtil.getOreDic("dustChrome",2));
        //タングステン
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 13),OreDictionaryUtil.getOreDic("dustTungsten",2));
        //イリジウム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 14),OreDictionaryUtil.getOreDic("dustIridium",2));
    }

    private static void Compressor()
    {

    }

    private static void Extractor()
    {

    }
}
