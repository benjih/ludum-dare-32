package com.benjih.ld32.gl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.benjih.ld32.DisplayScale;

public class GameDisplay {
	
	private long lastFrame;
	private float scaleFactor;
	private int x;
	private int y;
	public GameDisplay () {
		
		DisplayMode displayModeToUse = null;
		try {
			for(DisplayMode displayMode : Display.getAvailableDisplayModes()) {
				if(displayMode.getWidth() == 1920 && displayMode.getHeight() == 1080) {
					displayModeToUse = displayMode;
					x = 1920;
					y = 1080;
					scaleFactor = 1.0f;
				} else if(displayMode.getWidth() == 1920 && displayMode.getHeight() == 1080) {
					displayModeToUse = displayMode;
					x = 1080;
					y = 720;
					scaleFactor = 1.5f;
				}
			}
			Display.setDisplayMode(displayModeToUse);
			Display.setFullscreen(true);
			Display.setDisplayMode(displayModeToUse);
			Display.setFullscreen(true);
			Display.setTitle("Unconventional Card Battles");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void init () {
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);       
		 
		GL11.glViewport(0,0,x,y);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, x, y, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		generateDelta();
	}
	
	public void blit () {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void update () {
		GL11.glDisable(GL11.GL_BLEND);
		Display.update();
		Display.sync(60);
	}
	
	public void end () {
		Display.destroy();
		System.exit(0);
	}
	
	public static long getTime () {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int generateDelta () {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}

	public void closeIfRequested () {
		if (Display.isCloseRequested()) {
			end();
		}		
	}
	
	public DisplayScale getDisplayScale () {
		return new DisplayScale(scaleFactor);
	}

}