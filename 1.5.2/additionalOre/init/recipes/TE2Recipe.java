package mods.additionalOre.init.recipes;

import mods.additionalOre.init.ConfigurationManager;
import mods.japanAPI.utils.OreDictionaryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thermalexpansion.api.crafting.CraftingManagers;
import thermalexpansion.api.item.ItemRegistry;

public class TE2Recipe
{
    protected static OreDictionaryUtil OreUtils;

    public static void Initilization()
    {
        //粉砕
        Pulvalizer();
        //溶剤
        Induction();
        //
    }

    private static void Pulvalizer()
    {
        //鉄の塊 -> 鉄の粉,ニッケルの粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massIron"), OreUtils.getOreDicItemStack("dustIron", 2),OreUtils.getOreDicItemStack("dustNickel"),10);

        //金の塊 -> 金の粉,ニッケルの粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massGold"), OreUtils.getOreDicItemStack("dustGold", 2));

        //銅の塊 -> 銅の粉,金の粉(10%)
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massCopper"), OreUtils.getOreDicItemStack("dustCopper", 2),OreUtils.getOreDicItemStack("dustGold"),10);

        //錫の塊 -> 錫の粉,鉄の粉(10%)
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massTin"), OreUtils.getOreDicItemStack("dustTin", 2),OreUtils.getOreDicItemStack("dustIron"),10);

        //銀の塊　-> 銀の粉,鉛の粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massSilver"), OreUtils.getOreDicItemStack("dustSilver", 2),OreUtils.getOreDicItemStack("dustLead"),10);

        //鉛の塊 -> 鉛の粉,銀の粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massLead"), OreUtils.getOreDicItemStack("dustLead", 2),OreUtils.getOreDicItemStack("dustSilver"),10);

        //ニッケルの塊　-> ニッケルの粉x2、(白金が存在する場合は白金x1(10％))
        if(OreUtils.getOreDicItemStack("dustPlatinum") != null)
        {
            CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massNickel"), OreUtils.getOreDicItemStack("dustNickel", 2),OreUtils.getOreDicItemStack("dustPlatinum"),10);
        }else
        {
            CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDicItemStack("massNickel"), OreUtils.getOreDicItemStack("dustNickel", 2));
        }

        //ボーキサイトの塊 -> ボーキサイトの粉ｘ１ + アルミの粉x1(10％)
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDicItemStack("massBaxuite"),OreUtils.getOreDicItemStack("dustBauxite", 2),OreUtils.getOreDicItemStack("dustAluminium"),10);

        //チタニウムの塊　-> チタニウムの粉ｘ１ + 1 （Random
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDicItemStack("massTitanium"),OreUtils.getOreDicItemStack("dustTitanium", 2),OreUtils.getOreDicItemStack("dustTitanium"),5);

        //イリヂウムの塊 -> イリジウムの粉x1　+ 白金の粉x1(10%)
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDicItemStack("massIridium"),OreUtils.getOreDicItemStack("dustIridium", 2),OreUtils.getOreDicItemStack("dustPlatinum"),10);

        //クロムの塊 -> クロムの粉 x 1
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDicItemStack("massChrome"),OreUtils.getOreDicItemStack("dustChrome", 2),OreUtils.getOreDicItemStack("dustChrome"),5);

        //タングステンの塊 -> タングステンの粉x1
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDicItemStack("massTungsten"),OreUtils.getOreDicItemStack("dustTungsten", 2),OreUtils.getOreDicItemStack("dustTungsten"),5);
    }

    private static void Induction()
    {
        //鉄x1 + 木炭x2 = 鋼鉄
        if(ConfigurationManager.canInductionSteel)
        CraftingManagers.smelterManager.addRecipe(400, new ItemStack(Item.ingotIron,1),new ItemStack(Item.coal,2,1),OreUtils.getOreDicItemStack("ingotSteel"));

        //鋼鉄の粉x2 + 砂x1 = 鋼鉄のインゴット
        if(ConfigurationManager.canInductionSteel)
        CraftingManagers.smelterManager.addRecipe(400, OreUtils.getOreDicItemStack("dustSteel"),new ItemStack(Block.sand,1),OreUtils.getOreDicItemStack("ingotSteel"));

        if(!ConfigurationManager.isLoadedGregTech )
        {
        //アルミの粉x2 + 砂x1 = アルミのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDicItemStack("dustAluminium", 2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDicItemStack("ingotAluminium", 2), ItemRegistry.getItem("slag",1),10);
        //チタニウムの粉x2 + 砂x1 = チタンのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDicItemStack("dustTitanium", 2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDicItemStack("ingotTitanium", 2), ItemRegistry.getItem("slag",1),10);
        //クロムの粉x2 + 砂x1 = クロムのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDicItemStack("dustChrome", 2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDicItemStack("ingotChrome", 2), ItemRegistry.getItem("slag",1),10);
        //タングステンの粉x2 + 砂x1 = タングステンのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDicItemStack("dustTungsten", 2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDicItemStack("ingotTungsten", 2), ItemRegistry.getItem("slag",1),10);
        //タングステンの インゴットx1 + 鋼鉄のインゴット = タングステン鋼のインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDicItemStack("ingotTungsen"),OreDictionaryUtil.getOreDicItemStack("ingotSteel"),OreDictionaryUtil.getOreDicItemStack("ingotTungstenSteel", 2), ItemRegistry.getItem("slag",1),10);
        }
    }



}
