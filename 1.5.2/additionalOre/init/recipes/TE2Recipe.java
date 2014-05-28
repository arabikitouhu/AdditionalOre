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
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massIron"), OreUtils.getOreDic("dustIron",2),OreUtils.getOreDic("dustNickel"),10);

        //金の塊 -> 金の粉,ニッケルの粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massGold"), OreUtils.getOreDic("dustGold",2));

        //銅の塊 -> 銅の粉,金の粉(10%)
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massCopper"), OreUtils.getOreDic("dustCopper",2),OreUtils.getOreDic("dustGold"),10);

        //錫の塊 -> 錫の粉,鉄の粉(10%)
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massTin"), OreUtils.getOreDic("dustTin",2),OreUtils.getOreDic("dustIron"),10);

        //銀の塊　-> 銀の粉,鉛の粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massSilver"), OreUtils.getOreDic("dustSilver", 2),OreUtils.getOreDic("dustLead"),10);

        //鉛の塊 -> 鉛の粉,銀の粉
        CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massLead"), OreUtils.getOreDic("dustLead", 2),OreUtils.getOreDic("dustSilver"),10);

        //ニッケルの塊　-> ニッケルの粉x2、(白金が存在する場合は白金x1(10％))
        if(OreUtils.getOreDic("dustPlatinum") != null)
        {
            CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massNickel"), OreUtils.getOreDic("dustNickel", 2),OreUtils.getOreDic("dustPlatinum"),10);
        }else
        {
            CraftingManagers.pulverizerManager.addRecipe(400, OreUtils.getOreDic("massNickel"), OreUtils.getOreDic("dustNickel", 2));
        }

        //ボーキサイトの塊 -> ボーキサイトの粉ｘ１ + アルミの粉x1(10％)
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDic("massBaxuite"),OreUtils.getOreDic("dustBauxite", 2),OreUtils.getOreDic("dustAluminium"),10);

        //チタニウムの塊　-> チタニウムの粉ｘ１ + 1 （Random
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDic("massTitanium"),OreUtils.getOreDic("dustTitanium", 2),OreUtils.getOreDic("dustTitanium"),5);

        //イリヂウムの塊 -> イリジウムの粉x1　+ 白金の粉x1(10%)
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDic("massIridium"),OreUtils.getOreDic("dustIridium", 2),OreUtils.getOreDic("dustPlatinum"),10);

        //クロムの塊 -> クロムの粉 x 1
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDic("massChrome"),OreUtils.getOreDic("dustChrome", 2),OreUtils.getOreDic("dustChrome"),5);

        //タングステンの塊 -> タングステンの粉x1
        CraftingManagers.pulverizerManager.addRecipe(400,OreUtils.getOreDic("massTungsten"),OreUtils.getOreDic("dustTungsten", 2),OreUtils.getOreDic("dustTungsten"),5);
    }

    private static void Induction()
    {
        //鉄x1 + 木炭x2 = 鋼鉄
        if(ConfigurationManager.canInductionSteel)
        CraftingManagers.smelterManager.addRecipe(400, new ItemStack(Item.ingotIron,1),new ItemStack(Item.coal,2,1),OreUtils.getOreDic("ingotSteel"));

        //鋼鉄の粉x2 + 砂x1 = 鋼鉄のインゴット
        if(ConfigurationManager.canInductionSteel)
        CraftingManagers.smelterManager.addRecipe(400, OreUtils.getOreDic("dustSteel"),new ItemStack(Block.sand,1),OreUtils.getOreDic("ingotSteel"));

        if(!ConfigurationManager.isLoadedGregTech )
        {
        //アルミの粉x2 + 砂x1 = アルミのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDic("dustAluminium",2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDic("ingotAluminium",2), ItemRegistry.getItem("slag",1),10);
        //チタニウムの粉x2 + 砂x1 = チタンのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDic("dustTitanium",2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDic("ingotTitanium",2), ItemRegistry.getItem("slag",1),10);
        //クロムの粉x2 + 砂x1 = クロムのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDic("dustChrome",2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDic("ingotChrome",2), ItemRegistry.getItem("slag",1),10);
        //タングステンの粉x2 + 砂x1 = タングステンのインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDic("dustTungsten",2),new ItemStack(Block.sand,1),OreDictionaryUtil.getOreDic("ingotTungsten",2), ItemRegistry.getItem("slag",1),10);
        //タングステンの インゴットx1 + 鋼鉄のインゴット = タングステン鋼のインゴット
            CraftingManagers.smelterManager.addRecipe(400,OreDictionaryUtil.getOreDic("ingotTungsen"),OreDictionaryUtil.getOreDic("ingotSteel"),OreDictionaryUtil.getOreDic("ingotTungstenSteel",2), ItemRegistry.getItem("slag",1),10);
        }
    }



}
