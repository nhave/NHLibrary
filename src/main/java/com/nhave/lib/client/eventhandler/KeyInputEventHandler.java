package com.nhave.lib.client.eventhandler;

import com.nhave.lib.common.content.ModConfig;
import com.nhave.lib.common.network.Key;
import com.nhave.lib.common.network.MessageKeyPressed;
import com.nhave.lib.common.network.PacketHandler;
import com.nhave.lib.library.item.IKeyBound;
import com.nhave.lib.library.util.StringUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
	private static Key getPressedKeybinding()
    {
        if (com.nhave.lib.common.network.KeyBinds.toggleItem.isKeyDown())
        {
            return com.nhave.lib.common.network.Key.TOGGLE_ITEM;
        }

        return Key.UNKNOWN;
    }
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
    public void mouseHandler(MouseEvent event)
    {
		if (event.getDwheel() == 0) return;
		if (FMLClientHandler.instance().getClientPlayerEntity() != null)
        {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

            if (player.getHeldItemMainhand() != null)
            {
                if (player.isSneaking() || StringUtils.isAltKeyDown())
                {
                	ItemStack stack = player.getHeldItemMainhand();
                    
                	if (stack.getItem() instanceof IKeyBound)
	                {
                		boolean chat = ModConfig.disableHud;
                		Key key = event.getDwheel() < 0 ? (StringUtils.isAltKeyDown() ? Key.SCROLLDN_ALT : Key.SCROLLDN) : (StringUtils.isAltKeyDown() ? Key.SCROLLUP_ALT : Key.SCROLLUP);
                        if (((IKeyBound) stack.getItem()).isKeyInUse(stack, key))
                        {
	                		if (player.world.isRemote)
	                        {
	                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(key, chat));
	                        }
	                        else
	                        {
	                            ((IKeyBound) stack.getItem()).doKeyBindingAction(player, stack, key, chat);
	                        }
	                        event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
    	if (getPressedKeybinding() == Key.UNKNOWN)
        {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus)
        {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

                if (player.getHeldItemMainhand() != null)
                {
                    ItemStack stack = player.getHeldItemMainhand();
                    
                    if (stack.getItem() instanceof IKeyBound && ((IKeyBound) stack.getItem()).isKeyInUse(stack, getPressedKeybinding()))
                    {
                		boolean chat = ModConfig.disableHud;
                        if (player.world.isRemote)
                        {
                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeybinding(), chat));
                        }
                        else
                        {
                            ((IKeyBound) stack.getItem()).doKeyBindingAction(player, stack, getPressedKeybinding(), chat);
                        }
                    }
                }
            }
        }
   	}
}