package com.benjih.ld32.core;

import org.lwjgl.input.Mouse;

import com.benjih.ld32.DisplayScale;

public class MouseUtils {

	public static boolean isClick (int x, int y, int sizeX, int sizeY) {
		
		int mouseX = Mouse.getX();
		int mouseY = 1080 - Mouse.getY();
		return mouseX >= x && mouseX<= x + sizeX && mouseY >= y && mouseY <= y + sizeY;
	}

	public static boolean isClick (int x, int y, int sizeX, int sizeY, DisplayScale displayScale) {
		
		int mouseX = Mouse.getX();
		int mouseY = displayScale.scaleY(1080) - Mouse.getY();
		return mouseX >= displayScale.scaleX(x) && mouseX<= displayScale.scaleX(x + sizeX) && mouseY >= displayScale.scaleY(y) && mouseY <= displayScale.scaleY(y + sizeY);
	}
}
