package com.nhave.lib.library.helper;

import java.util.List;

import com.nhave.lib.library.util.StringUtils;

public class TooltipHelper
{
	public static void addLocalString(List list, String tooltip)
	{
		list.add(StringUtils.localize(tooltip));
	}
	
	public static void addSplitString(List list, String tooltip, String splitAt)
	{
		String[] info = tooltip.split(splitAt);
		for (int i = 0; i < info.length; ++i)
		{
			list.add(info[i]);
		}
	}
	
	public static void addSplitString(List list, String tooltip, String splitAt, String format)
	{
		String[] info = tooltip.split(splitAt);
		for (int i = 0; i < info.length; ++i)
		{
			list.add(format + info[i]);
		}
	}
	
	public static void addHiddenTooltip(List list, String tooltip, String splitAt)
	{
		String tooltip1 = StringUtils.localize(tooltip);
		if (!tooltip1.equals(tooltip))
		{
			addSplitString(list, tooltip1, splitAt);
		}
	}
	
	public static void addHiddenTooltip(List list, String tooltip, String splitAt, String format)
	{
		String tooltip1 = StringUtils.localize(tooltip);
		if (!tooltip1.equals(tooltip))
		{
			addSplitString(list, tooltip1, splitAt, format);
		}
	}
	
	public static void addHiddenTooltip(List list, String tooltip)
	{
		String tooltip1 = StringUtils.localize(tooltip);
		if (!tooltip1.equals(tooltip))
		{
			addLocalString(list, tooltip1);
		}
	}
	
	public static String translate(String local)
	{
		return StringUtils.localize(local);
	}
}