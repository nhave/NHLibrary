package com.nhave.lib.library.helper;

import java.util.ArrayList;
import java.util.List;

import com.nhave.lib.common.compat.CofhCoreCompat;
import com.nhave.lib.core.NHLibrary;
import com.nhave.lib.library.item.INTechWrench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class WrenchHelper
{
	private static List<Class<?>> wrenchClasses=new ArrayList<>();
	
    public static void init()
    {
        for (String className : new String[]
			{
                "buildcraft.api.tools.IToolWrench",
                "resonant.core.content.ItemScrewdriver",
                "ic2.core.item.tool.ItemToolWrench",
                "ic2.core.item.tool.ItemToolWrenchElectric",
                "mrtjp.projectred.api.IScrewdriver",
                "mods.railcraft.api.core.items.IToolCrowbar",
                "com.bluepowermod.items.ItemScrewdriver",
                "cofh.api.item.IToolHammer",
                "thermalexpansion.item.tool.ItemWrench",
                "appeng.items.tools.quartz.ToolQuartzWrench",
                "crazypants.enderio.api.tool.ITool",
                "mekanism.api.IMekWrench",
                "mcjty.rftools.items.smartwrench",
                "pneumaticCraft.common.item.ItemPneumaticWrench",
                "powercrystals.minefactoryreloaded.api.IToolHammer",
                "com.nhave.lib.library.item.INTechWrench"
    		})
        {
	        try
	        {
	            wrenchClasses.add(Class.forName(className));
	        }
	        catch (ClassNotFoundException e)  {}
        }
    }
    
    public static boolean isWrench(Item item)
    {
        for (Class<?> c : wrenchClasses)
        {
            if (c.isAssignableFrom(item.getClass()))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean canUseWrench(ItemStack stack, EntityPlayer player, EnumHand hand, BlockPos pos)
    {
    	Item item = stack.getItem();
        
        if (item instanceof INTechWrench)
        {
        	INTechWrench wrench = (INTechWrench) item;
        	return wrench.canUseWrench(stack, player, hand, pos);
        }
        else if (NHLibrary.cofhcore && CofhCoreCompat.isToolHammer(item))
        {
        	return CofhCoreCompat.canUseWrench(stack, player, pos);
        }
        else if (isWrench(item))
        {
        	return true;
        }
    	return false;
    }
    
    public static void useWrench(ItemStack stack, EntityPlayer player, EnumHand hand, BlockPos pos)
    {
    	Item item = stack.getItem();
        
        if (item instanceof INTechWrench)
        {
        	INTechWrench wrench = (INTechWrench) item;wrench.onWrenchUsed(stack, player, hand, pos);
        }
        else if (NHLibrary.cofhcore && CofhCoreCompat.isToolHammer(item))
        {
        	CofhCoreCompat.useWrench(stack, player, pos);
        }
    }
    
    /*public static boolean getWrenchUsage(ItemStack stack, EntityPlayer player, EnumHand hand, BlockPos pos)
    {
        Item item = stack.getItem();
        
        if (item instanceof INTechWrench)
        {
        	INTechWrench wrench = (INTechWrench) item;
        	boolean result = wrench.canUseWrench(stack, player, hand, pos);
        	wrench.onWrenchUsed(stack, player, hand, pos);
        	return result;
        }
        else if (NHLibrary.cofhcore && CofhCoreCompat.isToolHammer(item))
        {
        	return CofhCoreCompat.getWrenchUsage(item, stack, player, pos);
        }
        else if (isWrench(item))
        {
        	return true;
        }
    	return false;
    }*/
}