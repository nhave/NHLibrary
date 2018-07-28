package com.nhave.lib.library.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public interface INTechWrench
{
	public boolean canUseWrench(ItemStack item, EntityPlayer player, EnumHand hand, BlockPos pos);
	
	public void onWrenchUsed(ItemStack item, EntityPlayer player, EnumHand hand, BlockPos pos);
}