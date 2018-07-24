package com.nhave.lib.common.content;

import java.io.File;

import com.nhave.lib.core.Reference;
import com.nhave.lib.library.client.util.RenderUtils.HUDPositions;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig
{
	public static boolean isClientConfig;
	
	public static Configuration config;

    public static int hudPosition = Defaults.hudPosition;
    public static int hudOffsetX = Defaults.hudOffsetX;
    public static int hudOffsetY = Defaults.hudOffsetY;
    public static double hudScale = Defaults.hudScale;
    public static boolean showHudWhileChatting = Defaults.showHudWhileChatting;
    public static boolean disableHud = Defaults.disableHud;
	
	public ModConfig(boolean isClient)
	{
		this.isClientConfig = isClient;
	}

	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		loadConfig(false);
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if(eventArgs.getModID().equalsIgnoreCase(Reference.MODID))
		{
			loadConfig(false);
		}
	}
	
	public static void loadConfig(boolean load)
	{
		loadCommonConfig();
		if (isClientConfig) loadClientConfig();
		
		if (!config.hasChanged()) return;
		config.save();
	}
	
	public static void loadCommonConfig()
	{
		//config.setCategoryComment("common", "Configuration for all Common configs");
	}
	
	public static void loadClientConfig()
	{
		config.setCategoryComment("client", "Configuration for all Client configs");
		hudPosition = config.get("client", "HUDBasePosition", Defaults.hudPosition, "The base position of the HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPositions.values().length - 1).getInt(Defaults.hudPosition);
        hudOffsetX = config.get("client", "HUDOffset-X", Defaults.hudOffsetX, "The HUD display will be shifted horizontally by this value. This value may be negative.").getInt(Defaults.hudOffsetX);
        hudOffsetY = config.get("client", "HUDOffset-Y", Defaults.hudOffsetY, "The HUD display will be shifted vertically by this value. This value may be negative.").getInt(Defaults.hudOffsetY);
        hudScale = Math.abs(config.get("client", "HUDScale", Defaults.hudScale, "How large the HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0.001D).getDouble(Defaults.hudScale));
        showHudWhileChatting = config.get("client", "ShowHUDwhilechatting", Defaults.showHudWhileChatting, "When enabled, the HUD will display even when the chat window is opened.").getBoolean(Defaults.showHudWhileChatting);
        disableHud = config.get("client", "DisableHud", Defaults.disableHud, "Set to true to disable the HUD").getBoolean(Defaults.disableHud);
	}
}