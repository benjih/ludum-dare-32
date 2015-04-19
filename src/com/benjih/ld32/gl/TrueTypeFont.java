package com.benjih.ld32.gl;



import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Font;
import org.newdawn.slick.opengl.GLUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.BufferedImageUtil;

/**
 * A TrueType font implementation for Slick
 * 
 * @author James Chambers (Jimmy)
 * @author Jeremy Adams (elias4444)
 * @author Kevin Glass (kevglass)
 * @author Peter Korzuszek (genail)
 * @author David Muhar (bobjob)
 */
public class TrueTypeFont implements org.newdawn.slick.Font {
   public final static int
   ALIGN_LEFT = 0,
   ALIGN_RIGHT = 1,
   ALIGN_CENTER = 2;
   
   /** The renderer to use for all GL operations */
   private static final SGL GL = Renderer.get();

   /** Array that holds necessary information about the font characters */
   private IntObject[] charArray = new IntObject[256];
   
   /** Map of user defined font characters (Character <-> IntObject) */
   private Map customChars = new HashMap();

   /** Boolean flag on whether AntiAliasing is enabled or not */
   private boolean antiAlias;

   /** Font's size */
   private int fontSize = 0;

   /** Font's height */
   private int fontHeight = 0;

   /** Texture used to cache the font 0-255 characters */
   private Texture fontTexture;
   
   /** Default font texture width */
   private int textureWidth = 512;

   /** Default font texture height */
   private int textureHeight = 512;

   /** A reference to Java's AWT Font that we create our font texture from */
   private java.awt.Font font;

   /** The font metrics for our Java AWT font */
   private FontMetrics fontMetrics;

   /**
    * This is a special internal class that holds our necessary information for
    * the font characters. This includes width, height, and where the character
    * is stored on the font texture.
    */
   private class IntObject {
      /** Character's width */
      public int width;

      /** Character's height */
      public int height;

      /** Character's stored x position */
      public int storedX;

      /** Character's stored y position */
      public int storedY;
   }

   /**
    * Constructor for the TrueTypeFont class Pass in the preloaded standard
    * Java TrueType font, and whether you want it to be cached with
    * AntiAliasing applied.
    * 
    * @param font
    *            Standard Java AWT font
    * @param antiAlias
    *            Whether or not to apply AntiAliasing to the cached font
    * @param additionalChars
    *            Characters of font that will be used in addition of first 256 (by unicode).
    */
   public TrueTypeFont(java.awt.Font font, boolean antiAlias, char[] additionalChars) {
      GLUtils.checkGLContext();
      
      this.font = font;
      this.fontSize = font.getSize();
      this.antiAlias = antiAlias;

      createSet( additionalChars );
   }
   
   /**
    * Constructor for the TrueTypeFont class Pass in the preloaded standard
    * Java TrueType font, and whether you want it to be cached with
    * AntiAliasing applied.
    * 
    * @param font
    *            Standard Java AWT font
    * @param antiAlias
    *            Whether or not to apply AntiAliasing to the cached font
    */
   public TrueTypeFont(java.awt.Font font, boolean antiAlias) {
      this( font, antiAlias, null );
   }

   /**
    * Create a standard Java2D BufferedImage of the given character
    * 
    * @param ch
    *            The character to create a BufferedImage for
    * 
    * @return A BufferedImage containing the character
    */
   private BufferedImage getFontImage(char ch) {
      // Create a temporary image to extract the character's size
      BufferedImage tempfontImage = new BufferedImage(1, 1,
            BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = (Graphics2D) tempfontImage.getGraphics();
      if (antiAlias == true) {
         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
      }
      g.setFont(font);
      fontMetrics = g.getFontMetrics();
      int charwidth = fontMetrics.charWidth(ch);

      if (charwidth <= 0) {
         charwidth = 1;
      }
      int charheight = fontMetrics.getHeight();
      if (charheight <= 0) {
         charheight = fontSize;
      }

      // Create another image holding the character we are creating
      BufferedImage fontImage;
      fontImage = new BufferedImage(charwidth, charheight,
            BufferedImage.TYPE_INT_ARGB);
      Graphics2D gt = (Graphics2D) fontImage.getGraphics();
      if (antiAlias == true) {
         gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
      }
      gt.setFont(font);

      gt.setColor(Color.WHITE);
      int charx = 0;
      int chary = 0;
      gt.drawString(String.valueOf(ch), (charx), (chary)
            + fontMetrics.getAscent());

      return fontImage;

   }

   /**
    * Create and store the font
    * 
    * @param customCharsArray Characters that should be also added to the cache.
    */
   private void createSet( char[] customCharsArray ) {
      // If there are custom chars then I expand the font texture twice      
      if   (customCharsArray != null && customCharsArray.length > 0) {
         textureWidth *= 2;
      }
      
      // In any case this should be done in other way. Texture with size 512x512
      // can maintain only 256 characters with resolution of 32x32. The texture
      // size should be calculated dynamicaly by looking at character sizes. 
      
      try {
         
         BufferedImage imgTemp = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g = (Graphics2D) imgTemp.getGraphics();

         g.setColor(new Color(255,255,255,1));
         g.fillRect(0,0,textureWidth,textureHeight);
         
         int rowHeight = 0;
         int positionX = 0;
         int positionY = 0;
         
         int customCharsLength = ( customCharsArray != null ) ? customCharsArray.length : 0; 

         for (int i = 0; i < 256 + customCharsLength; i++) {
            
            // get 0-255 characters and then custom characters
            char ch = ( i < 256 ) ? (char) i : customCharsArray[i-256];
            
            BufferedImage fontImage = getFontImage(ch);

            IntObject newIntObject = new IntObject();

            newIntObject.width = fontImage.getWidth();
            newIntObject.height = fontImage.getHeight();

            if (positionX + newIntObject.width >= textureWidth) {
               positionX = 0;
               positionY += rowHeight;
               rowHeight = 0;
            }

            newIntObject.storedX = positionX;
            newIntObject.storedY = positionY;

            if (newIntObject.height > fontHeight) {
               fontHeight = newIntObject.height;
            }

            if (newIntObject.height > rowHeight) {
               rowHeight = newIntObject.height;
            }

            // Draw it here
            g.drawImage(fontImage, positionX, positionY, null);

            positionX += newIntObject.width;

            if( i < 256 ) { // standard characters
               charArray[i] = newIntObject;
            } else { // custom characters
               customChars.put( new Character( ch ), newIntObject );
            }

            fontImage = null;
         }

         fontTexture = BufferedImageUtil
               .getTexture(font.toString(), imgTemp);

      } catch (IOException e) {
         System.err.println("Failed to create font.");
         e.printStackTrace();
      }
   }
   
   
   /**
    * Draw a textured quad
    * 
    * @param drawX
    *            The left x position to draw to
    * @param drawY
    *            The top y position to draw to
    * @param drawX2
    *            The right x position to draw to
    * @param drawY2
    *            The bottom y position to draw to
    * @param srcX
    *            The left source x position to draw from
    * @param srcY
    *            The top source y position to draw from
    * @param srcX2
    *            The right source x position to draw from
    * @param srcY2
    *            The bottom source y position to draw from
    */
   private void drawQuad(float drawX, float drawY, float drawX2, float drawY2,
         float srcX, float srcY, float srcX2, float srcY2) {
      float DrawWidth = drawX2 - drawX;
      float DrawHeight = drawY2 - drawY;
      float TextureSrcX = srcX / textureWidth;
      float TextureSrcY = srcY / textureHeight;
      float SrcWidth = srcX2 - srcX;
      float SrcHeight = srcY2 - srcY;
      float RenderWidth = (SrcWidth / textureWidth);
      float RenderHeight = (SrcHeight / textureHeight);

      GL.glTexCoord2f(TextureSrcX, TextureSrcY);
      GL.glVertex2f(drawX, drawY);
      GL.glTexCoord2f(TextureSrcX, TextureSrcY + RenderHeight);
      GL.glVertex2f(drawX, drawY + DrawHeight);
      GL.glTexCoord2f(TextureSrcX + RenderWidth, TextureSrcY + RenderHeight);
      GL.glVertex2f(drawX + DrawWidth, drawY + DrawHeight);
      GL.glTexCoord2f(TextureSrcX + RenderWidth, TextureSrcY);
      GL.glVertex2f(drawX + DrawWidth, drawY);
   }

   /**
    * Get the width of a given String
    * 
    * @param whatchars
    *            The characters to get the width of
    * 
    * @return The width of the characters
    */
   public int getWidth(String whatchars) {
      int totalwidth = 0;
      IntObject intObject = null;
      int currentChar = 0;
      for (int i = 0; i < whatchars.length(); i++) {
         currentChar = whatchars.charAt(i);
         if (currentChar < 256) {
            intObject = charArray[currentChar];
         } else {
            intObject = (IntObject)customChars.get( new Character( (char) currentChar ) );
         }
         
         if( intObject != null )
            totalwidth += intObject.width;
      }
      return totalwidth;
   }

   /**
    * Get the font's height
    * 
    * @return The height of the font
    */
   public int getHeight() {
      return fontHeight;
   }

   /**
    * Get the height of a String
    * 
    * @return The height of a given string
    */
   public int getHeight(String HeightString) {
      return fontHeight;
   }

   /**
    * Get the font's line height
    * 
    * @return The line height of the font
    */
   public int getLineHeight() {
      return fontHeight;
   }

   /**
    * Draw a string
    * 
    * @param x
    *            The x position to draw the string
    * @param y
    *            The y position to draw the string
    * @param whatchars
    *            The string to draw
    * @param color
    *            The color to draw the text
    */
   public void drawString(float x, float y, String whatchars,
         org.newdawn.slick.Color color) {
      GL.glPushMatrix();
      GL.glTranslatef(x, y, 0);
      drawString(whatchars,color,0,whatchars.length()-1, ALIGN_LEFT);
      GL.glPopMatrix();
   }

   /**
    
    */
   public void drawString(float x, float y, String whatchars,
         org.newdawn.slick.Color color, int align) {
      GL.glPushMatrix();
      GL.glTranslatef(x, y, 0);
      drawString(whatchars,color,0,whatchars.length()-1, align);
      GL.glPopMatrix();
   }
   /**
    * @see Font#drawString(float, float, String, org.newdawn.slick.Color, int, int)
    */
   public void drawString(float x, float y, String whatchars,
         org.newdawn.slick.Color color, int startIndex, int endIndex) {
      GL.glPushMatrix();
      GL.glTranslatef(x, y, 0);
      drawString(whatchars, color, startIndex, endIndex, ALIGN_LEFT);
      GL.glPopMatrix();
   }
   /**

    */
   public void drawString(float x, float y, String whatchars,
         org.newdawn.slick.Color color, int startIndex, int endIndex, int align) {
      GL.glPushMatrix();
      GL.glTranslatef(x, y, 0);
      drawString(whatchars, color, startIndex, endIndex, align);
      GL.glPopMatrix();
   }

   /**

    */
   public void drawString(String whatchars,
         org.newdawn.slick.Color color, int startIndex, int endIndex, int align) {
      color.bind();
      fontTexture.bind();

      IntObject intObject = null;
      int charCurrent, totalwidth = 0, i = startIndex, d;
      float startY = 0;
      
      GL.glBegin(SGL.GL_QUADS);

      switch (align) {
      case ALIGN_RIGHT: {
         d = -1;
         while (i < endIndex) {
            if (whatchars.charAt(i) == '\n') startY += fontHeight;
            i++;
         }
         break;
      }
      case ALIGN_CENTER: {
         for (int l = startIndex; l <= endIndex; l++) {
            charCurrent = whatchars.charAt(l);
            if (charCurrent == '\n') break;
            if (charCurrent < 256) {
               intObject = charArray[charCurrent];
            } else {
               intObject = (IntObject)customChars.get( new Character( (char) charCurrent ) );
            }
            totalwidth += intObject.width;
         }
         totalwidth /= -2;
      }
      case ALIGN_LEFT:
      default: {
         d = 1;
         break;
      }
      }
      //int totalwidth = 0;
      //for (int i = 0; i < whatchars.length(); i++) {
      while (i >= startIndex && i <= endIndex) {
         
         charCurrent = whatchars.charAt(i);
         if (charCurrent < 256) {
            intObject = charArray[charCurrent];
         } else {
            intObject = (IntObject)customChars.get( new Character( (char) charCurrent ) );
         } 
         
         if( intObject != null ) {
            if (d < 0) totalwidth += intObject.width * d;
               if (charCurrent == '\n') {
                  startY += fontHeight * d;
                  totalwidth = 0;
                  if (align == ALIGN_CENTER) {
                     for (int l = i+1; l <= endIndex; l++) {
                        charCurrent = whatchars.charAt(l);
                        if (charCurrent == '\n') break;
                        if (charCurrent < 256) {
                           intObject = charArray[charCurrent];
                        } else {
                           intObject = (IntObject)customChars.get( new Character( (char) charCurrent ) );
                        }
                        totalwidth += intObject.width;
                     }
                     totalwidth /= -2;
                  }
                  //if center get next lines total width/2;
               }
               else {
                  drawQuad(totalwidth, startY,
                        totalwidth + intObject.width,
                        startY + intObject.height, intObject.storedX,
                        intObject.storedY, intObject.storedX + intObject.width,
                        intObject.storedY + intObject.height);
                  if (d > 0) totalwidth += intObject.width * d ;
               }
            
            i += d;
            
         }
      }

      GL.glEnd();
   }

   /**
    * Draw a string
    * 
    * @param x
    *            The x position to draw the string
    * @param y
    *            The y position to draw the string
    * @param whatchars
    *            The string to draw
    */
   public void drawString(float x, float y, String whatchars) {
      drawString(x, y, whatchars, org.newdawn.slick.Color.white);
   }
}