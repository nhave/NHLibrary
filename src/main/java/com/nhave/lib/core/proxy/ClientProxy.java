package com.nhave.lib.core.proxy;

import java.io.File;

import com.nhave.lib.client.eventhandler.HudTickHandler;
import com.nhave.lib.client.eventhandler.KeyInputEventHandler;
import com.nhave.lib.client.render.ItemColorHandler;
import com.nhave.lib.common.content.ModConfig;
import com.nhave.lib.common.network.KeyBinds;
import com.nhave.lib.core.NHLibrary;
import com.nhave.lib.library.item.IColoredItem;
import com.nhave.lib.library.tile.ITileRegister;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void setupConfig(File configFile)
	{
		MinecraftForge.EVENT_BUS.register(new ModConfig(true));
		ModConfig.init(configFile);
	}
	
	@Override
    public void registerKeybindings()
	{
		ClientRegistry.registerKeyBinding(KeyBinds.toggleItem);
	}
	
	@Override
	public void registerRenders()
	{
		MinecraftForge.EVENT_BUS.register(new HudTickHandler());
		
		for (Item item : Item.REGISTRY)
        {
			if (item instanceof IColoredItem)
			{
				FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(ItemColorHandler.INSTANCE, item);
			}
			if (item instanceof ItemBlock)
			{
				Block block = ((ItemBlock) item).getBlock();
				if (block instanceof ITileRegister)
				{
					if (((ITileRegister) block).registerTileEntityRender())
					{
						NHLibrary.logger.info("Adding TileEntitySpecialRenderer to Block: " + block.getRegistryName());
					}
				}
			}
        }
	}
	
	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
	}
}