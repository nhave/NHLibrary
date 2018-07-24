package com.nhave.lib.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.nhave.lib.common.content.ModConfig;
import com.nhave.lib.core.Reference;
import com.nhave.lib.library.util.StringUtils;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ModConfigGUI extends GuiConfig
{
    public ModConfigGUI(GuiScreen parent)
    {
        super(parent, getConfigElements(), Reference.MODID, false, false, StringUtils.localize("cfg.nhlib.title.main"));
    }
    
    private static List<IConfigElement> getConfigElements()
    {
		List list = new ArrayList();
		list.add(new DummyConfigElement.DummyCategoryElement("cfg.nhlib.entry.client", "cfg.nhlib.entry.client", ClientEntry.class));
		//list.add(new DummyConfigElement.DummyCategoryElement("cfg.nhlib.entry.common", "cfg.nhlib.entry.common", CommonEntry.class));
		return list;
    }
    
    public static class ClientEntry extends GuiConfigEntries.CategoryEntry
    {
		public ClientEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		protected GuiScreen buildChildScreen()
		{
			return new GuiConfig(this.owningScreen,
					new ConfigElement(ModConfig.config.getCategory("client")).getChildElements(),
					this.owningScreen.modID, "client",
					(this.configElement.requiresWorldRestart())
							|| (this.owningScreen.allRequireWorldRestart),
					(this.configElement.requiresMcRestart())
							|| (this.owningScreen.allRequireMcRestart),
					GuiConfig.getAbridgedConfigPath(StringUtils.localize("cfg.nhlib.title.client")));
		}
	}
    
    public static class CommonEntry extends GuiConfigEntries.CategoryEntry
    {
		public CommonEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		protected GuiScreen buildChildScreen()
		{
			return new GuiConfig(this.owningScreen,
					new ConfigElement(ModConfig.config.getCategory("common")).getChildElements(),
					this.owningScreen.modID, "common",
					(this.configElement.requiresWorldRestart())
							|| (this.owningScreen.allRequireWorldRestart),
					(this.configElement.requiresMcRestart())
							|| (this.owningScreen.allRequireMcRestart),
					GuiConfig.getAbridgedConfigPath(StringUtils.localize("cfg.nhlib.title.common")));
		}
	}
}