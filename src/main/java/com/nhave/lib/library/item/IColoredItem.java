package com.nhave.lib.library.item;

import net.minecraft.item.ItemStack;

public interface IColoredItem
{
	public int getItemColor(ItemStack stack, int index);
}