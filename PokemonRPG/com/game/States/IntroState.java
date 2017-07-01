package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import com.game.FX.Assets;

public class IntroState implements State 
{
  private int displayWidth;
  private int displayHeight;
  
  private int alphaValue;
  private int deltaAlpha;
  
  private long startTime;
  
//Fields for displaying correct image
  private enum  IntroStateImage{GAME_FREAK_LOGO, PKMN_INT_LOGO}
  
  private IntroStateImage currentImageState;
  private Image           currentImage;
  
//IntroState image offsets
  private int gameFreakLogoOffSetX;
  private int gameFreakLogoOffSetY;
  
  private int pkmnIntLogoOffSetX;
  private int pkmnIntLogoOffSetY;
  
  private int currentXOffset;
  private int currentYOffset;
  
  
  public IntroState(int pDisplayWidth, int pDisplayHeight)
  {
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    
    gameFreakLogoOffSetX = Assets.getWidth(Assets.imgGameFreakLogo)  / 2;
    gameFreakLogoOffSetY = Assets.getHeight(Assets.imgGameFreakLogo) / 2;
    
    pkmnIntLogoOffSetX = Assets.getWidth(Assets.imgPkmnIntLogo) / 2;
    pkmnIntLogoOffSetY = Assets.getHeight(Assets.imgPkmnIntLogo) / 2;
    
    alphaValue = 255;
    deltaAlpha = -1;
    
    startTime = System.currentTimeMillis();
    
    currentImage   = Assets.imgGameFreakLogo;
    currentXOffset = gameFreakLogoOffSetX;
    currentYOffset = gameFreakLogoOffSetY;
    
    currentImageState = IntroStateImage.GAME_FREAK_LOGO;
  }
  
  
  public void update()
  {
    if(currentImageState == IntroStateImage.GAME_FREAK_LOGO)
    {
      updateLogoScreen1();
    }
    if(currentImageState == IntroStateImage.PKMN_INT_LOGO)
    {
      updateLogoScreen2();
    }
    
  }
  
  
  public void updateLogoScreen1()
  {
    long vNow     = System.currentTimeMillis();
    long vElapsed = vNow - startTime;
    
    if(vElapsed < 2000)
    {System.out.println("returning hasn't been 2 seconds");
      return;
    }
    else if(vElapsed < 4000)
    {
      System.out.println("its been more than 2 seconds");
      deltaAlpha = -2;
    }
    else if(vElapsed < 6000)
    {System.out.println("its been more than 4 seconds");
      deltaAlpha = -3;
    }
    
    if(alphaValue + deltaAlpha >= 0)
    {
      alphaValue += deltaAlpha;
    }
    else
    {
      alphaValue = 0;
    }
    System.out.println("Alphavalue= " + alphaValue);
  }
  
  
  public void updateLogoScreen2()
  {
    
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.drawImage(
        currentImage,
        displayWidth/2 - currentXOffset, 
        displayHeight/2 - currentYOffset, 
        null);
    
    pGraphics.setColor(new Color(255,255, 255, alphaValue));
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
    
    
  }

}
