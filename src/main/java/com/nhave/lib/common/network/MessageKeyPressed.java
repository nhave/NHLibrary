package com.nhave.lib.common.network;

import com.nhave.lib.library.item.IKeyBound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage>
{
    private byte keyPressed;

    public MessageKeyPressed()
    {
    }

    public MessageKeyPressed(Key key, boolean chat)
    {
        if (key == Key.TOGGLE_ITEM && !chat)
        {
            this.keyPressed = (byte) 0;
        }
        else if (key == Key.TOGGLE_ITEM && chat)
        {
            this.keyPressed = (byte) 1;
        }
        else if (key == Key.TOGGLE_HELMET && !chat)
        {
            this.keyPressed = (byte) 2;
        }
        else if (key == Key.TOGGLE_HELMET && chat)
        {
            this.keyPressed = (byte) 3;
        }
        else if (key == Key.TOGGLE_CHEST && !chat)
        {
            this.keyPressed = (byte) 4;
        }
        else if (key == Key.TOGGLE_CHEST && chat)
        {
            this.keyPressed = (byte) 5;
        }
        else if (key == Key.TOGGLE_LEGS && !chat)
        {
            this.keyPressed = (byte) 6;
        }
        else if (key == Key.TOGGLE_LEGS && chat)
        {
            this.keyPressed = (byte) 7;
        }
        else if (key == Key.TOGGLE_BOOTS && !chat)
        {
            this.keyPressed = (byte) 8;
        }
        else if (key == Key.TOGGLE_BOOTS && chat)
        {
            this.keyPressed = (byte) 9;
        }
        else if (key == Key.SCROLLUP && !chat)
        {
            this.keyPressed = (byte) 10;
        }
        else if (key == Key.SCROLLUP && chat)
        {
            this.keyPressed = (byte) 11;
        }
        else if (key == Key.SCROLLDN && !chat)
        {
            this.keyPressed = (byte) 12;
        }
        else if (key == Key.SCROLLDN && chat)
        {
            this.keyPressed = (byte) 13;
        }
        else if (key == Key.SCROLLUP_ALT && !chat)
        {
            this.keyPressed = (byte) 14;
        }
        else if (key == Key.SCROLLUP_ALT && chat)
        {
            this.keyPressed = (byte) 15;
        }
        else if (key == Key.SCROLLDN_ALT && !chat)
        {
            this.keyPressed = (byte) 16;
        }
        else if (key == Key.SCROLLDN_ALT && chat)
        {
            this.keyPressed = (byte) 17;
        }
        else
        {
            this.keyPressed = (byte) 18;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.keyPressed = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(keyPressed);
    }

    @Override
    public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
    {
        EntityPlayer entityPlayer = ctx.getServerHandler().player;
        
        if (entityPlayer != null && entityPlayer.getHeldItemMainhand() != null)
        {
        	if (entityPlayer.getHeldItemMainhand().getItem() instanceof IKeyBound)
        	{
	        	if (message.keyPressed == 0)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.TOGGLE_ITEM, false);
	            }
	        	else if (message.keyPressed == 1)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.TOGGLE_ITEM, true);
	            }
	        	if (message.keyPressed == 10)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLUP, false);
	            }
	        	else if (message.keyPressed == 11)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLUP, true);
	            }
	        	else if (message.keyPressed == 12)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLDN, false);
	            }
	        	else if (message.keyPressed == 13)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLDN, true);
	            }
	        	if (message.keyPressed == 14)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLUP_ALT, false);
	            }
	        	else if (message.keyPressed == 15)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLUP_ALT, true);
	            }
	        	else if (message.keyPressed == 16)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLDN_ALT, false);
	            }
	        	else if (message.keyPressed == 17)
	            {
	                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), Key.SCROLLDN_ALT, true);
	            }
        	}
        }

        return null;
    }
}