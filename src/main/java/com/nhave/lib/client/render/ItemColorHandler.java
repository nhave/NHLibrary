package com.nhave.lib.client.render;

import com.nhave.lib.library.item.IColoredItem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ItemColorHandler implements IItemColor
{
	public static final ItemColorHandler INSTANCE = new ItemColorHandler();
	
	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex)
	{
		if (stack.getItem() instanceof IColoredItem) return ((IColoredItem) stack.getItem()).getItemColor(stack, tintIndex);
		return 16777215;
	}
}