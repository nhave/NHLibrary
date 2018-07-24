package com.nhave.lib.library.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public abstract class RenderUtils
{
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    public static void drawStringLeft(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow)
    {
        fontRenderer.drawString(string, x, y, color, shadow);
    }
    
    public static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow)
    {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2, y, color, shadow);
    }
    
    public static void drawStringRight(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow)
    {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string), y, color, shadow);
    }
    
    public static void drawStringAtHUDPosition(String string, HUDPositions position, FontRenderer fontRenderer, int xOffset, int yOffset, double scale, int color, boolean shadow, int lineOffset) {
        ScaledResolution res = new ScaledResolution(mc);
        
        int screenWidth = res.getScaledWidth();
        screenWidth /= scale;
        int screenHeight = res.getScaledHeight();
        screenHeight /= scale;
        
        switch (position)
        {
	        case TOP_LEFT:
	            yOffset += lineOffset * 9;
	            drawStringLeft(string, fontRenderer, 2 + xOffset, 2 + yOffset, color, shadow);
	            break;
	        case TOP_CENTER:
	            yOffset += lineOffset * 9;
	            drawStringCenter(string, fontRenderer, screenWidth / 2 + xOffset, 2 + yOffset, color, shadow);
	            break;
	        case TOP_RIGHT:
	            yOffset += lineOffset * 9;
	            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, 2 + yOffset, color, shadow);
	            break;
	        case LEFT:
	            yOffset += lineOffset * 9;
	            drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight / 2 + yOffset, color, shadow);
	            break;
	        case RIGHT:
	            yOffset += lineOffset * 9;
	            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color, shadow);
	            break;
	        case BOTTOM_LEFT:
	            yOffset -= lineOffset * 9;
	            drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight - 9 + yOffset, color, shadow);
	            break;
	        case BOTTOM_RIGHT:
	            yOffset -= lineOffset * 9;
	            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color, shadow);
        }
    }
    
    public enum HUDPositions
    {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        LEFT,
        RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT;
        
        public static String[] getStringValues()
        {
        	String[] values = new String[values().length];
        	for (int i = 0; i < values.length; ++i)
        	{
        		values[i] = values()[i].name();
        	}
        	return values;
        }
        
        public static int getFromString(String value)
        {
        	for (int i = 0; i < values().length; ++i)
        	{
        		if (values()[i].name().equals(value)) return i;
        	}
        	return 0;
        }
    }
}