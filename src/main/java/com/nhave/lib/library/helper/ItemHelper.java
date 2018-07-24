package com.nhave.lib.library.helper;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHelper
{
	public static void addItemToPlayer(EntityPlayer player, ItemStack stack)
	{
		if (!player.world.isRemote && !player.inventory.addItemStackToInventory(stack.copy()))
		{
			player.entityDropItem(stack.copy(), 1F);
		}
	}
	
	/**
	 * Gets the players current item or armor
	 * 0. Mainhand Item
	 * 1. Offhand Item
	 * 2. Boots
	 * 3. Leggings
	 * 4. Chest
	 * 5. Helmet
	 * 
	 * @param player
	 * @param slot
	 */
	public static ItemStack getCurrentItemOrArmor(EntityPlayer player, int slot)
	{
		if (slot == 0) return player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
		else if (slot == 1) return player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
		else if (slot == 2) return player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		else if (slot == 3) return player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		else if (slot == 4) return player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		else if (slot == 5) return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		else return null;
	}
	
	public static void dismantleBlock(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player)
	{
		Block block = blockState.getBlock();
		TileEntity tile = world.getTileEntity(blockPos);
		
		block.harvestBlock(world, player, blockPos, blockState, tile, player.getHeldItemMainhand());
    	block.onBlockHarvested(world, blockPos, blockState, player);
	    world.setBlockToAir(blockPos);
	}
	
	/**
	 * Drops the {@link Item} in the {@link World}.
	 * 
	 * @param world
	 * @param blockPos
	 * @param stack
	 */
	public static void dropItemInWorld(World world, BlockPos blockPos, ItemStack stack)
	{
		float f = 0.3F;
    	double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
    	double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
    	double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
    	EntityItem theItem = new EntityItem(world, blockPos.getX() + x2, blockPos.getY() + y2, blockPos.getZ() + z2, stack);
    	theItem.setDefaultPickupDelay();
    	world.spawnEntity(theItem);
	}
}