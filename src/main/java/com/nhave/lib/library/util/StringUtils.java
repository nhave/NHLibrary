package com.nhave.lib.library.util;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.text.translation.I18n;

public class StringUtils
{
	public static final String BLACK = "§0";
	public static final String BLUE = "§1";
	public static final String GREEN = "§2";
	public static final String TEAL = "§3";
	public static final String RED = "§4";
	public static final String PURPLE = "§5";
	public static final String ORANGE = "§6";
	public static final String LIGHT_GRAY = "§7";
	public static final String GRAY = "§8";
	public static final String LIGHT_BLUE = "§9";
	public static final String BRIGHT_GREEN = "§a";
	public static final String BRIGHT_BLUE = "§b";
	public static final String LIGHT_RED = "§c";
	public static final String PINK = "§d";
	public static final String YELLOW = "§e";
	public static final String WHITE = "§f";
	public static final String OBFUSCATED = "§k";
	public static final String BOLD = "§l";
	public static final String STRIKETHROUGH = "§m";
	public static final String UNDERLINE = "§n";
	public static final String ITALIC = "§o";
	public static final String END = "§r";
	
	public static String shiftForInfo()
	{
		return "§7" + localize("tooltip.nhlib.details.shift1") + " " + "§e" + "§o" + localize("tooltip.nhlib.details.shift2") + " " + "§r" + "§7" + localize("tooltip.nhlib.details.shift3") + "§r";
	}
	
	public static boolean isAltKeyDown()
	{
		return (Keyboard.isKeyDown(56)) || (Keyboard.isKeyDown(184));
	}
	
	public static boolean isControlKeyDown()
	{
		return (Keyboard.isKeyDown(29)) || (Keyboard.isKeyDown(157));
	}
	
	public static boolean isShiftKeyDown()
	{
		return (Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54));
	}
	
	public static String localize(String key)
	{
		return I18n.translateToLocal(key);
	}
	
	public static String localizeFormated(String key, Object... format)
	{
		return I18n.translateToLocalFormatted(key, format);
	}
	
	public static String limitString(String string, int max)
	{
		int maxLength = (string.length() < max) ? string.length() : max;
		String suffix = (string.length() < max) ? "" : "...";
		string = string.substring(0, maxLength);
		return string + suffix;
	}
	
	public static String format(String string, String... format)
	{
		String formatString = "";
		for (int i = 0; i < format.length; ++i)
		{
			formatString += format[i];
		}
		return formatString + string + END + LIGHT_GRAY;
	}
	
	public static String formatLocal(String string, String... format)
	{
		String formatString = "";
		for (int i = 0; i < format.length; ++i)
		{
			formatString += format[i];
		}
		return formatString + localize(string) + END + LIGHT_GRAY;
	}
}