package com.benjih.ld32.core;

import org.lwjgl.input.Mouse;

public class MouseUtils {

	public static boolean isClick(int i, int j, int k, int l) {
		
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		System.out.println(mouseX + " " + mouseY);
		return mouseX >= i; //&& mouseX<= k && mouseY >= k && mouseY <= l;
//		return Mouse.isButtonDown(0);
	}

}
