package mods.additionalOre;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{

    @Override
	public void registerTextures()
    {

	}

    @Override
    public EntityPlayer getPlayer()
    {
        return FMLClientHandler.instance().getClient().thePlayer;
    }

	@Override
	public void registerAnimation()
    {

	}

}
