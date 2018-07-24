package com.nhave.lib.core.proxy;

import java.io.File;

import com.nhave.lib.common.content.ModConfig;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
{
	public void setupConfig(File configFile)
	{
		MinecraftForge.EVENT_BUS.register(new ModConfig(false));
		ModConfig.init(configFile);
	}
	
    public void registerKeybindings() {}
	
	public void registerRenders() {}
	
	public void registerEventHandlers() {}
}