package mods.additionalOre.worlds.gens;

import cpw.mods.fml.common.IWorldGenerator;
import mods.japanAPI.JapanAPI;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenerator implements IWorldGenerator
{

	private static final ArrayList<WorldGenMinable> genMinable_Hell = new ArrayList<WorldGenMinable>();
	private static final ArrayList<WorldGenMinable> genMinable_Overworld = new ArrayList<WorldGenMinable>();

	private static final ArrayList<Integer> minHeight_Hell = new ArrayList<Integer>();
	private static final ArrayList<Integer> maxHeight_Hell = new ArrayList<Integer>();

	private static final ArrayList<Integer> minHeight_Overworld = new ArrayList<Integer>();
	private static final ArrayList<Integer> maxHeight_Overworld = new ArrayList<Integer>();

	private static final ArrayList<Integer> count_Hell = new ArrayList<Integer>();
	private static final ArrayList<Integer> count_Overworld = new ArrayList<Integer>();

	public static void addGenMinable_Hell(WorldGenMinable genMinable, int minHeight, int maxHeight, int count)
    {
		genMinable_Hell.add(genMinable);
		minHeight_Hell.add(minHeight);
		maxHeight_Hell.add(maxHeight);
		count_Hell.add(count);
	}

	public static void addGenMinable_Overworld(WorldGenMinable genMinable, int minHeight, int maxHeight, int count)
    {
		genMinable_Overworld.add(genMinable);
		minHeight_Overworld.add(minHeight);
		maxHeight_Overworld.add(maxHeight);
		count_Overworld.add(count);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
    {

		switch(world.provider.dimensionId)
        {
			case 0:		//通常ワールド
				generate_Overworld(JapanAPI.RANDOM, chunkX, chunkZ, world, chunkProvider, chunkProvider);
				break;
			case -1:	//ネザー
				generate_Hell(JapanAPI.RANDOM, chunkX, chunkZ, world, chunkProvider, chunkProvider);
				break;
			case 1:		//エンド？
				break;
			default:
				generate_Overworld(JapanAPI.RANDOM, chunkX, chunkZ, world, chunkProvider, chunkProvider);
				break;
		}

	}

	private void generate_Overworld(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
    {
		for(int i = 0; i < genMinable_Overworld.size(); i++)
        {
			int min = minHeight_Overworld.get(i);
			int max = maxHeight_Overworld.get(i);
			for(int cnt = random.nextInt(count_Overworld.get(i) + 1); cnt >= 0; cnt--)
            {
				int x = chunkX * 16 + random.nextInt(16);
				int z = chunkZ * 16 + random.nextInt(16);

				int y = min + random.nextInt(max - min + 1);
				genMinable_Overworld.get(i).generate(world, random, x, y, z);
			}
		}

	}

	private void generate_Hell(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		for(int i = 0; i < genMinable_Hell.size(); i++)
        {
			int min = minHeight_Hell.get(i);
			int max = maxHeight_Hell.get(i);
			for(int cnt = random.nextInt(count_Hell.get(i) + 1); cnt >= 0; cnt--)
            {
				int x = chunkX * 16 + random.nextInt(16);
				int z = chunkZ * 16 + random.nextInt(16);

				int y = min + ((max - min) * (random.nextInt() + 1) % 100) / 100;
				genMinable_Hell.get(i).generate(world, random, x, y, z);
			}
		}

	}
}