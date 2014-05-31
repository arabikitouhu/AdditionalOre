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
                        'G',OreDictionaryUtil.getOreDicItemStack("gemRuby")

                });

        //ラポジトロンクリスタル
        Recipes.advRecipes.addRecipe(new ItemStack(Items.getItem("lapotronCrystal").getItem(),1),
                new Object[]{"LCL","LSL","LCL",
                        'L',OreDictionaryUtil.getOreDicItemStack("dyeBlue"),
                        'C',Items.getItem("electronicCircuit").getItem(),
                        'S',OreDictionaryUtil.getOreDicItemStack("gemSapphire")

                });

        //ガラスファイバー
        if(OreDictionaryUtil.getOreDicItemStack("ingotElectrum") != null)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("glassFiberCableItem").getItem(), 8),
                    new Object[]{"GGG", "EDE", "GGG",
                            'G', Block.glass,
                            'E', "ingotElectrum",
                            'D', "gemDiamond"
                    }));


            //電子回路x2
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("electronicCircuit").getItem(),2),
                    new Object[]{"CCC","ERE","CCC",
                            'C',Items.getItem("insulatedCopperCableBlock"),
                            'E',"ingotElectrum",
                            'R',Items.getItem("refinedIronIngot")
                    }));

            //改良型回路x2 パターン1
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("electronicCircuit").getItem(),2),
                    new Object[]{"EGE","LCL","EGE",
                            'G',Item.lightStoneDust,
                            'E',"ingotElectrum",
                            'L',"dyeBlue",
                            'C',Items.getItem("electronicCircuit")

                    }));

            //改良型回路x2 パターン2
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("electronicCircuit").getItem(),2),
                    new Object[]{"ELE","GCG","ELE",
                            'G',Item.lightStoneDust,
                            'E',"ingotElectrum",
                            'L',"dyeBlue",
                            'C',Items.getItem("electronicCircuit")
                    }));

        }else if(OreDictionaryUtil.getOreDicItemStack("dustElectrum") != null)
        {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.getItem("glassFiberCableItem").getItem(), 8),
                new Object[]{"GGG", "EDE", "GGG",
                        'G', Block.glass,
                        'E', "dustElectrum",
                        'D', "gemDiamond"
                }));
        }
    }

    private static void Macerator()
    {
        //鉄
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 0), OreDictionaryUtil.getOreDicItemStack("dustIron", 2));
        //金
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 1), OreDictionaryUtil.getOreDicItemStack("dustGold", 2));
        //石英
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 3), OreDictionaryUtil.getOreDicItemStack("dustQuartz", 2));
        //銅
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 4), OreDictionaryUtil.getOreDicItemStack("dustCopper", 2));
        //錫
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 5), OreDictionaryUtil.getOreDicItemStack("dustTin", 2));

        //ウラニウム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 6), OreDictionaryUtil.getOreDicItemStack("dustUranium", 2));

        //ボーキサイト(アルミニウム)　※GregTech導入時はかまどによるアルミニウムへの精錬が不可能となる。
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 7),OreDictionaryUtil.getOreDicItemStack("dustBauxite", 2));
        if(ModLoader.isModLoaded("GregTech-Addon"))
        {

        }else
        {
            FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_masses, 7, OreDictionaryUtil.getOreDicItemStack("ingotAluminium", 2),1F);
            FurnaceRecipes.smelting().addSmelting(ConfigurationManager.ID_dusts, 7, OreDictionaryUtil.getOreDicItemStack("ingotAluminium", 1),1F);
        }

        //チタニウム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 8),OreDictionaryUtil.getOreDicItemStack("dustTitanium", 2));
        //鉛
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 9),OreDictionaryUtil.getOreDicItemStack("dustLead", 2));
        //ニッケル
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 10),OreDictionaryUtil.getOreDicItemStack("dustNickel", 2));
        //銀
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 11),OreDictionaryUtil.getOreDicItemStack("dustSilver", 2));
        //クロム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 12),OreDictionaryUtil.getOreDicItemStack("dustChrome", 2));
        //タングステン
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 13),OreDictionaryUtil.getOreDicItemStack("dustTungsten", 2));
        //イリジウム
        Recipes.macerator.addRecipe(new ItemStack(AdditionalOre.ITEM_masses, 1, 14),OreDictionaryUtil.getOreDicItemStack("dustIridium", 2));
    }

    private static void Compressor()
    {

    }

    private static void Extractor()
    {

    }
}
