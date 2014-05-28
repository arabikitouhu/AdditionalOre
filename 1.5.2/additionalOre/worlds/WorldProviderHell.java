package mods.additionalOre.worlds;

import mods.additionalOre.worlds.gens.ChunkProviderHell;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderHell extends WorldProvider
{

    @Override
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {
        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }

    @Override
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2)
    {
        return true;
    }

    @Override
    public String getDimensionName()
    {
        return "Nether";
    }
}
