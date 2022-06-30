package com.ed.testmod.utils;

import net.minecraftforge.api.distmarker.OnlyIn;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
public class KeyboardHelper {
	
		private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
		
		@OnlyIn(Dist.CLIENT)
		public static boolean isHoldingCapsLook() 
		{
			return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_CAPS_LOCK);
		}
		@OnlyIn(Dist.CLIENT)
		public static boolean isHoldingR() 
		{
			return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_R);
		}
		
		@OnlyIn(Dist.CLIENT)
		public static boolean isHoldingCtrl() 
		{
			return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
		}

}
