package com.nhave.lib.client.eventhandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.nhave.lib.common.content.ModConfig;
import com.nhave.lib.library.client.util.RenderUtils;
import com.nhave.lib.library.client.util.RenderUtils.HUDPositions;
import com.nhave.lib.library.helper.ItemHelper;
import com.nhave.lib.library.item.IHudItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class HudTickHandler
{
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private static void tickEnd()
    {
        
        if ((mc.currentScreen == null || ModConfig.showHudWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo)
        {
            List<String> info = new ArrayList<String>();
            
            for (int j = 0; j < 6; j++)
            {
            	int slot = j;
            	if (ModConfig.hudPosition < 5) slot = 4-j;
                
            	ItemStack hudItem = ItemHelper.getCurrentItemOrArmor(mc.player, slot);

                if (hudItem != null && hudItem.getItem() instanceof IHudItem)
                {
                	IHudItem provider = (IHudItem) hudItem.getItem();
                    
                    provider.addHudInfo(hudItem, mc.player, info, (slot == 0 ? false : true));
                }
            }
            
            if (ModConfig.hudPosition >= 5) Collections.reverse(info);
            
            if (info.isEmpty())
            {
            	return;
            }
            
            GL11.glPushMatrix();
            mc.entityRenderer.setupOverlayRendering();
            GL11.glScaled(ModConfig.hudScale, ModConfig.hudScale, 1.0D);
            int i = 0;
            for (String s : info)
            {
                RenderUtils.drawStringAtHUDPosition(s, HUDPositions.values()[ModConfig.hudPosition], mc.fontRenderer, ModConfig.hudOffsetX, ModConfig.hudOffsetY, ModConfig.hudScale, 0xeeeeee, true, i);
                i++;
            }
            
            GL11.glPopMatrix();
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt)
    {
    	if (mc.player != null)
        {
	    	if (evt.phase == Phase.END && (!ModConfig.disableHud))
	        {
	            tickEnd();
	        }
        }
    }
}