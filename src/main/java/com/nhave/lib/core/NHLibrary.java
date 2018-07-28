package com.nhave.lib.core;

import java.io.File;

import org.apache.logging.log4j.Logger;

import com.nhave.lib.common.network.PacketHandler;
import com.nhave.lib.core.proxy.CommonProxy;
import com.nhave.lib.library.helper.WrenchHelper;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, guiFactory = Reference.GUIFACTORY)
public class NHLibrary
{
	public static boolean redstoneflux, cofhcore;
	
	public static Logger logger;
    
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
    
    @Mod.Instance(Reference.MODID)
	public static NHLibrary instance = new NHLibrary();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	
		proxy.setupConfig(new File(event.getModConfigurationDirectory(), "nhlib.cfg"));
		
		PacketHandler.init();
		proxy.registerKeybindings();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerRenders();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.registerEventHandlers();
    	
		redstoneflux = Loader.isModLoaded("redstoneflux");
		cofhcore = Loader.isModLoaded("cofhcore");
		
		WrenchHelper.init();
    }
}