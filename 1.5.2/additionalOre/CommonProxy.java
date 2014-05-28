package mods.additionalOre;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
	public void registerTextures()
    {
        ;
    }

    public EntityPlayer getPlayer()
    {
        return null;
    }

	public void registerAnimation()
    {
        ;
    }

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
		return null;
	}


}
