package com.game.FX;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.game.Exceptions.AssetLoaderException;

public class Assets 
{
  public static final int ASHHEIGHT = 30;
  public static final int ASHWIDTH  = 20;
  
  public static BufferedImage 
  imgGameFreakLogo        = null,
  imgPkmnIntLogo          = null,
  imgPkmnMenuBg           = null,
  imgMenuPlayBtn          = null,
  imgMenuSelectedPlayBtn  = null,
  imgMenuClickedPlayBtn   = null,
  imgMenuExitBtn          = null,
  imgMenuSelectedExitBtn  = null,
  imgMenuClickedExitBtn   = null,
  
  ashSpriteSheet          = null,
  spriteAshStandForward   = null,
  spriteGrassTile         = null;
  
  public static AudioInputStream
  soundMSIntro              = null,
  soundMainMenuBtnSelect    = null,
  soundMainMenuBGMusic      = null,
  soundMainMenuPlayBtnEnter = null,
  soundWorldTheme           = null;
  
  
  public static void load() throws AssetLoaderException
  {
    try
    {
      imgGameFreakLogo        = ImageIO.read(new File("Resources/Images/GameFreakLogo.png"));
      imgPkmnIntLogo          = ImageIO.read(new File("Resources/Images/PkmnIntLogo.png"));
      imgPkmnMenuBg           = ImageIO.read(new File("Resources/Images/PkmnMenuBackground.png"));
      imgMenuPlayBtn          = ImageIO.read(new File("Resources/Images/MenuUnselectedPlayBtn.png"));
      imgMenuSelectedPlayBtn  = ImageIO.read(new File("Resources/Images/MenuMousedOverPlayBtn.png"));
      imgMenuClickedPlayBtn   = ImageIO.read(new File("Resources/Images/MenuClickedPlayBtn.png"));
      imgMenuExitBtn          = ImageIO.read(new File("Resources/Images/MenuUnselectedExitBtn.png"));
      imgMenuSelectedExitBtn  = ImageIO.read(new File("Resources/Images/MenuMousedOverExitBtn.png"));
      imgMenuClickedExitBtn   = ImageIO.read(new File("Resources/Images/MenuClickedExitBtn.png"));
      
      ashSpriteSheet          = ImageIO.read(new File("Resources/Images/AshSpriteSheet.png"));
      spriteAshStandForward   = crop(ashSpriteSheet, 1, 1, ASHWIDTH, ASHHEIGHT);
      
      soundMSIntro              = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MSIntroWAV.wav"));
      soundMainMenuBtnSelect    = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MainMenuButtonSelect.wav"));
      soundMainMenuBGMusic      = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MainMenuBGMusic.wav"));
      soundMainMenuPlayBtnEnter = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MainMenuEnterButtonChimes.wav"));
      soundWorldTheme           = AudioSystem.getAudioInputStream(new File("Resources/Sounds/WorldMainTheme.wav"));
    }
    catch(IOException pException)
    { 
      throw new AssetLoaderException("Assets.load - " + pException.getMessage(), 
                                     pException);
    }
    catch(UnsupportedAudioFileException pException)
    {
      throw new AssetLoaderException("Assets.load - " + pException.getMessage(), 
                                      pException);
    }
  }
  
  
  public static int getHeight(BufferedImage pImage)
  {
    return pImage.getHeight();
  }
  
  
  public static int getWidth(BufferedImage pImage)
  {
    return pImage.getWidth();
  }
  
  public static BufferedImage crop(BufferedImage pImage, int pX, int pY, int pWidth, int pHeight)
  {
    return pImage.getSubimage(pX, pY, pWidth, pHeight);
  }
}
