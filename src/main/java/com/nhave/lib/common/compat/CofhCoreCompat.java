package com.nhave.lib.common.compat;

import cofh.api.item.IToolHammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class CofhCoreCompat
{
	public static boolean isToolHammer(Item item)
	{
        return item instanceof IToolHammer;
    }
	
    /*public static boolean getWrenchUsage(Item item, ItemStack itemStack, EntityPlayer player, BlockPos pos)
    {
        IToolHammer hammer = (IToolHammer) item;
        if (hammer.isUsable(itemStack, player, pos))
        {
            hammer.toolUsed(itemStack, player, pos);
            return true;
        }
        else
        {
            return false;
        }
    }*/
    
    public static boolean canUseWrench(ItemStack itemStack, EntityPlayer player, BlockPos pos)
    {
    	IToolHammer hammer = (IToolHammer) itemStack.getItem();
    	return hammer.isUsable(itemStack, player, pos);
    }
    
    public static void useWrench(ItemStack itemStack, EntityPlayer player, BlockPos pos)
    {
    	 IToolHammer hammer = (IToolHammer) itemStack.getItem();
         hammer.toolUsed(itemStack, player, pos);
    }
}