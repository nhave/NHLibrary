package com.nhave.lib.library.client.mesh;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CustomMeshDefinitionMetaItem implements ItemMeshDefinition
{
	private boolean useSingleTexture;
	private String[] names;
	
	public CustomMeshDefinitionMetaItem()
	{
		this(false);
	}
	
	public CustomMeshDefinitionMetaItem(String[] names)
	{
		this(false);
		this.names = names;
	}
	
	public CustomMeshDefinitionMetaItem(boolean useSingleTexture)
	{
		this.useSingleTexture = useSingleTexture;
	}
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack)
	{
		String modID = stack.getItem().getRegistryName().getResourceDomain();
		String itemName = stack.getItem().getRegistryName().getResourcePath();
		
		if (this.useSingleTexture) return new ModelResourceLocation(new ResourceLocation(modID + ":" + itemName), "inventory");
		else if (names != null && names.length > 0)
		{
			int meta = Math.min(stack.getItemDamage(), names.length-1);
			return new ModelResourceLocation(new ResourceLocation(modID + ":" + itemName + "_" + names[meta].toLowerCase()), "inventory");
		}
		else
		{
			String meta = "";
			if (stack.getItemDamage() > 0) meta = "_" + stack.getItemDamage();
			return new ModelResourceLocation(new ResourceLocation(modID + ":" + itemName + meta), "inventory");
		}
	}
}
