package com.nhave.lib.common.network;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBinds
{
    public static KeyBinding toggleItem = new KeyBinding("key.nhlib.toggleitem", Keyboard.KEY_G, "key.nhlib.category");
}