package com.benjih.ld32.core;

import org.lwjgl.input.Mouse;

public class MouseUtils {

	public static boolean isClick(int x, int y, int widthX, int widthY) {
		
		int mouseX = Mouse.getX();
		int mouseY = 1080 - Mouse.getY();
		System.out.println(mouseX + " " + mouseY);
		System.out.println("X:" + x + " " + x + widthX);
		System.out.println("Y:" + y + " " + y + widthY);
		return mouseX >= x && mouseX<= x + widthX && mouseY >= y && mouseY <= y + widthY;
	}

}
