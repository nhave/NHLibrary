package com.nhave.lib.library.tile;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ITileRegister
{
	public void registerTileEntity();
	
	@SideOnly(Side.CLIENT)
	public default boolean registerTileEntityRender()
	{
		return false;
	}
}