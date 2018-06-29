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
  public static final int ASH_HEIGHT  = 30;
  public static final int ASH_WIDTH   = 20;
  public static final int GRID_OFFSET = 1;
  
  public static BufferedImage 
  imgGameFreakLogo            = null,
  imgPkmnIntLogo              = null,
  imgPkmnMenuBg               = null,
  imgMenuPlayBtn              = null,
  imgMenuSelectedPlayBtn      = null,
  imgMenuClickedPlayBtn       = null,
  imgMenuExitBtn              = null,
  imgMenuSelectedExitBtn      = null,
  imgMenuClickedExitBtn       = null,
  imgMenuUnselectedOptionsBtn = null,
  imgMenuFocusOptionsBtn      = null,
  imgMenuSelectedOptionsBtn   = null,
  
  
  ashSpriteSheet          = null,
  spriteAshStandForward   = null,
  spriteAshStandLeft      = null,
  spriteAshStandRight     = null,
  spriteAshStandBackwards = null,
  
  spriteAshRunForward1   = null,
  spriteAshRunForward2   = null,
  spriteAshRunForward3   = null,
  spriteAshRunForward4   = null,
  
  spriteAshRunBackwards1 = null,
  spriteAshRunBackwards2 = null,
  spriteAshRunBackwards3 = null,
  spriteAshRunBackwards4 = null,
  
  spriteAshRunLeft1      = null,
  spriteAshRunLeft2      = null,
  spriteAshRunLeft3      = null,
  spriteAshRunLeft4      = null,
  
  spriteAshRunRight1     = null,
  spriteAshRunRight2     = null,
  spriteAshRunRight3     = null,
  spriteAshRunRight4     = null,
  
  terrainSpriteSheet        = null,
  spriteTileGrass           = null,
  spriteTileWater           = null,
  spriteTileDirt            = null,
  spriteTileWeed            = null,
  spriteTileTreeTopLeft     = null,
  spriteTileTreeTopRight    = null,
  spriteTileTreeBottomLeft  = null,
  spriteTileTreeBottomRight = null;
  
  
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
      imgGameFreakLogo            = ImageIO.read(new File("Resources/Images/GameFreakLogo.png"));
      imgPkmnIntLogo              = ImageIO.read(new File("Resources/Images/PkmnIntLogo.png"));
      imgPkmnMenuBg               = ImageIO.read(new File("Resources/Images/PkmnMenuBackground.png"));
      imgMenuPlayBtn              = ImageIO.read(new File("Resources/Images/MenuUnselectedPlayBtn.png"));
      imgMenuSelectedPlayBtn      = ImageIO.read(new File("Resources/Images/MenuMousedOverPlayBtn.png"));
      imgMenuClickedPlayBtn       = ImageIO.read(new File("Resources/Images/MenuClickedPlayBtn.png"));
      imgMenuExitBtn              = ImageIO.read(new File("Resources/Images/MenuUnselectedExitBtn.png"));
      imgMenuSelectedExitBtn      = ImageIO.read(new File("Resources/Images/MenuMousedOverExitBtn.png"));
      imgMenuClickedExitBtn       = ImageIO.read(new File("Resources/Images/MenuClickedExitBtn.png"));
      imgMenuUnselectedOptionsBtn = ImageIO.read(new File("Resources/Images/MenuUnselectedOptionsBtn.png"));
      imgMenuFocusOptionsBtn      = ImageIO.read(new File("Resources/Images/MenuMouseOverOptionsBtn.png"));
      imgMenuSelectedOptionsBtn   = ImageIO.read(new File("Resources/Images/MenuClickedOptionsBtn.png"));
      
      //PLAYER
      ashSpriteSheet          = ImageIO.read(new File("Resources/Images/AshSpriteSheet.png"));
      spriteAshStandForward   = crop(ashSpriteSheet, GRID_OFFSET, GRID_OFFSET, ASH_WIDTH, ASH_HEIGHT);
      spriteAshStandLeft      = crop(ashSpriteSheet, GRID_OFFSET, GRID_OFFSET + 1 + ASH_HEIGHT, ASH_WIDTH, ASH_HEIGHT);
      spriteAshStandBackwards = crop(ashSpriteSheet, GRID_OFFSET, GRID_OFFSET + 2 + (2 *ASH_HEIGHT), ASH_WIDTH, ASH_HEIGHT);
      spriteAshStandRight     = crop(ashSpriteSheet, GRID_OFFSET, GRID_OFFSET + 3 + (3 * ASH_HEIGHT), ASH_WIDTH, ASH_HEIGHT);
      
      spriteAshRunForward1 = crop(ashSpriteSheet, GRID_OFFSET + 1 + ASH_WIDTH, GRID_OFFSET, ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunForward2 = crop(ashSpriteSheet, GRID_OFFSET + 2 + ASH_WIDTH * 2, GRID_OFFSET, ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunForward3 = crop(ashSpriteSheet, GRID_OFFSET + 3 + ASH_WIDTH * 3, GRID_OFFSET, ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunForward4 = crop(ashSpriteSheet, GRID_OFFSET + 4 + ASH_WIDTH * 4, GRID_OFFSET, ASH_WIDTH, ASH_HEIGHT);
      
      spriteAshRunBackwards1 = crop(ashSpriteSheet, GRID_OFFSET + 1 + ASH_WIDTH, GRID_OFFSET + 2 + (ASH_HEIGHT * 2), ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunBackwards2 = crop(ashSpriteSheet, GRID_OFFSET + 2 + (ASH_WIDTH * 2), GRID_OFFSET + 2 + (ASH_HEIGHT * 2), ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunBackwards3 = crop(ashSpriteSheet, GRID_OFFSET + 3 + (ASH_WIDTH * 3), GRID_OFFSET + 2 + (ASH_HEIGHT * 2), ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunBackwards4 = crop(ashSpriteSheet, GRID_OFFSET + 4 + (ASH_WIDTH * 4), GRID_OFFSET + 2 + (ASH_HEIGHT * 2), ASH_WIDTH, ASH_HEIGHT);
      
      spriteAshRunLeft1 = crop(ashSpriteSheet, GRID_OFFSET + 1 + ASH_WIDTH,  GRID_OFFSET + 1 + ASH_HEIGHT, ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunLeft2 = crop(ashSpriteSheet, GRID_OFFSET + 2 + (ASH_WIDTH * 2),  GRID_OFFSET + 1 + ASH_HEIGHT, ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunLeft3 = crop(ashSpriteSheet, GRID_OFFSET + 3 + (ASH_WIDTH * 3),  GRID_OFFSET + 1 + ASH_HEIGHT, ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunLeft4 = crop(ashSpriteSheet, GRID_OFFSET + 4 + (ASH_WIDTH * 4),  GRID_OFFSET + 1 + ASH_HEIGHT, ASH_WIDTH, ASH_HEIGHT);
      
      spriteAshRunRight1 = crop(ashSpriteSheet, GRID_OFFSET + 1 + ASH_WIDTH, GRID_OFFSET + 3 + (ASH_HEIGHT * 3), ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunRight2 = crop(ashSpriteSheet, GRID_OFFSET + 2 + (ASH_WIDTH * 2), GRID_OFFSET + 3 + (ASH_HEIGHT * 3), ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunRight3 = crop(ashSpriteSheet, GRID_OFFSET + 3 + (ASH_WIDTH * 3), GRID_OFFSET + 3 + (ASH_HEIGHT * 3), ASH_WIDTH, ASH_HEIGHT);
      spriteAshRunRight4 = crop(ashSpriteSheet, GRID_OFFSET + 4 + (ASH_WIDTH * 4), GRID_OFFSET + 3 + (ASH_HEIGHT * 3), ASH_WIDTH, ASH_HEIGHT);
      
      //TERRAIN
      terrainSpriteSheet        = ImageIO.read(new File("Resources/Images/TerrainSpriteSheet.png"));
      spriteTileGrass           = crop(terrainSpriteSheet, 0, 0, 16, 16);
      spriteTileWater           = crop(terrainSpriteSheet, 17, 0, 16, 16);
      spriteTileDirt            = crop(terrainSpriteSheet, 34, 0, 16, 16);
      spriteTileWeed            = crop(terrainSpriteSheet, 51, 0, 16, 16);
      spriteTileTreeTopLeft     = crop(terrainSpriteSheet, 0, 17, 17, 22);
      spriteTileTreeTopRight    = crop(terrainSpriteSheet, 16, 17, 15, 22);
      spriteTileTreeBottomLeft  = crop(terrainSpriteSheet, 32, 17, 15, 22);
      spriteTileTreeBottomRight = crop(terrainSpriteSheet, 48, 17, 15, 22);
      
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
