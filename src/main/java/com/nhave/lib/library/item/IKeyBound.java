package com.nhave.lib.library.item;

import com.nhave.lib.common.network.Key;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound
{
	public boolean isKeyInUse(ItemStack itemStack, Key key);
	
	public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key, boolean chat);
}