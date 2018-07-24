package com.nhave.lib.library.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;

public interface IItemBlockRegister
{
	public void registerItemBlock(Register<Item> event);
}