package com.game.FX;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.game.Entities.Tile;
import com.game.Exceptions.AssetLoaderException;
import com.game.States.PlayState;
import com.game.TileEvents.TileEvent;

public class Assets 
{
  private static final int ASH_HEIGHT  = 30;
  private static final int ASH_WIDTH   = 20;
  private static final int GRID_OFFSET = 1;
  
  private static final String CONFIG_MAP_FILE = "Resources/Maps/json-map.json";
  private static final String IMAGE_MAP_FILE  = "Resources/Maps/image_map.txt";
  
  public static boolean IsLoaded = false;
  
  public static Tile[][] Map     = null;
  
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
  
  terrainSpriteSheet     = null,
  spriteTileGrass        = null,
  spriteTileWater        = null,
  spriteTileDirt         = null,
  spriteTileWeed         = null,

  spriteHouse1_houseTop_Left        = null,
  spriteHouse1_houseTop_MidLeft     = null,
  spriteHouse1_houseTop_MidRight    = null,
  spriteHouse1_houseTop_Right       = null,
  spriteHouse1_houseTopMid_Left     = null,
  spriteHouse1_houseTopMid_MidLeft  = null,
  spriteHouse1_houseTopMid_MidRight = null,
  spriteHouse1_houseTopMid_Right    = null,
  spriteHouse1_houseBotMid_Left     = null,
  spriteHouse1_houseBotMid_MidLeft  = null,
  spriteHouse1_houseBotMid_MidRight = null,
  spriteHouse1_houseBotMid_Right    = null,
  spriteHouse1_houseBot_Left        = null,
  spriteHouse1_houseBot_MidLeft     = null,
  spriteHouse1_houseBot_MidRight    = null,
  spriteHouse1_houseBot_Right       = null;

  
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
      
      spriteHouse1_houseTop_Left = crop(terrainSpriteSheet, 0, 17, 16, 16);
      spriteHouse1_houseTop_MidLeft  = crop(terrainSpriteSheet, 17, 17, 16, 16);
      spriteHouse1_houseTop_MidRight  = crop(terrainSpriteSheet, 34, 17, 16, 16);
      spriteHouse1_houseTop_Right  = crop(terrainSpriteSheet, 51, 17, 16, 16);
      
      spriteHouse1_houseTopMid_Left  = crop(terrainSpriteSheet, 0, 34, 16, 16);
      spriteHouse1_houseTopMid_MidLeft  = crop(terrainSpriteSheet, 17, 34, 16, 16);
      spriteHouse1_houseTopMid_MidRight  = crop(terrainSpriteSheet, 34, 34, 16, 16);
      spriteHouse1_houseTopMid_Right  = crop(terrainSpriteSheet, 51, 34, 16, 16);
      
      spriteHouse1_houseBotMid_Left   = crop(terrainSpriteSheet, 0, 51, 16, 16);
      spriteHouse1_houseBotMid_MidLeft  = crop(terrainSpriteSheet, 17, 51, 16, 16);
      spriteHouse1_houseBotMid_MidRight  = crop(terrainSpriteSheet, 34, 51, 16, 16);
      spriteHouse1_houseBotMid_Right  = crop(terrainSpriteSheet, 51, 51, 16, 16);
      
      spriteHouse1_houseBot_Left  = crop(terrainSpriteSheet, 0, 68, 16, 16);
      spriteHouse1_houseBot_MidLeft  = crop(terrainSpriteSheet, 17, 68, 16, 16);
      spriteHouse1_houseBot_MidRight  = crop(terrainSpriteSheet, 34, 68, 16, 16);
      spriteHouse1_houseBot_Right  = crop(terrainSpriteSheet, 51, 68, 16, 16);
     
      soundMainMenuBtnSelect    = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MainMenuButtonSelect.wav"));
      soundMainMenuBGMusic      = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MainMenuBGMusic.wav"));
      soundMainMenuPlayBtnEnter = AudioSystem.getAudioInputStream(new File("Resources/Sounds/MainMenuEnterButtonChimes.wav"));
      soundWorldTheme           = AudioSystem.getAudioInputStream(new File("Resources/Sounds/WorldMainTheme.wav"));
      
      loadMap();
      
      IsLoaded = true;
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
  
  
  private static void loadMap() throws AssetLoaderException
  {
    BufferedWriter vImageIDWriter     = null;
    JSONParser     vParser            = null; 
    JSONObject     vMapObject         = null;
    JSONObject     vDimensions        = null;
    JSONObject     vCurrTileObject    = null;
    JSONObject     vCurrTileData      = null;
    JSONArray      vTileDataArray     = null;
    JSONArray      vTileEventsArray   = null;
    
    String      vTileImage  = null;
    String      vImageID    = null;
    Boolean     vIsSolid    = null;
    
    int vMapRows = 0;
    int vMapCols = 0;
    
    int vRow        = 0;
    int vCol        = 0;
    int vTileXPos   = 0;
    int vTileYPos   = 0;
    int vRowCounter = 0;
    int vColCounter = 0;
    
    int IDWriter = 0;

    try
    {
      vParser        = new JSONParser();
      vMapObject     = (JSONObject)vParser.parse(new InputStreamReader(new FileInputStream(CONFIG_MAP_FILE)));
      vImageIDWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(IMAGE_MAP_FILE)));

      vDimensions = (JSONObject)vMapObject.get("map");
      
      vMapRows = Integer.parseInt(vDimensions.get("rows").toString());
      vMapCols = Integer.parseInt(vDimensions.get("cols").toString());
      
      Map = new Tile[vMapRows][vMapCols];
      
      vTileDataArray = (JSONArray)vMapObject.get("tiles");
      
      for(int vIndex = 0; vIndex < vTileDataArray.size(); ++vIndex)
      {
        ArrayList<TileEvent> vTileEvents = null;
        
        vCurrTileObject = (JSONObject)vTileDataArray.get(vIndex);
        vCurrTileData   = (JSONObject)vCurrTileObject.get("tile");
        
        vRow       = Integer.parseInt(vCurrTileData.get("row").toString());
        vCol       = Integer.parseInt(vCurrTileData.get("col").toString());
        vTileImage = vCurrTileData.get("image").toString();
        vImageID   = vCurrTileData.get("imageID").toString();
        vIsSolid   = Boolean.valueOf(vCurrTileData.get("solid").toString());
        
        vTileEventsArray = (JSONArray)vCurrTileData.get("events");

        if(vTileEventsArray.size() > 0)
        {
          vTileEvents = parseEventsArray(vTileEventsArray);
        }
        
        Map[vRow][vCol] = new Tile(
            vRow,
            vCol,
            vTileXPos,
            vTileYPos,
            PlayState.PLAYER_WIDTH,
            PlayState.PLAYER_WIDTH,
            vTileImage,
            vIsSolid,
            vTileEvents
            );
        
        if(vColCounter + 1 < vMapCols)
        {
          ++vColCounter;
          
          vTileXPos += PlayState.PLAYER_WIDTH;
          
          vImageIDWriter.write(vImageID + "  ");
          ++IDWriter;
        }
        else
        {
          vColCounter = 0;
          ++vRowCounter;
          vTileXPos = 0;
          vTileYPos += PlayState.PLAYER_WIDTH;
          vImageIDWriter.write(vImageID + '\n');
          ++IDWriter;
        }
      }
    }
    catch(NumberFormatException pNumberFormatException)
    {
      throw new AssetLoaderException("Assets.loadMap - " +
                                      pNumberFormatException.getMessage(),
                                      pNumberFormatException);
    }
    catch(ParseException pParseException)
    {
      throw new AssetLoaderException("Assets.loadMap - " +
                                      pParseException.getMessage(),
                                      pParseException);
    }
    catch(IOException pIOException)
    {
      throw new AssetLoaderException("Assets.loadMap - " +
                                      pIOException.getMessage(),
                                      pIOException);
    }
    catch(ArrayIndexOutOfBoundsException pArrayIndexOutOfBoundsException)
    {
      throw new AssetLoaderException("Assets.loadMap - Given map size does not "   + 
                                     "match tile array size. "                     +
                                      pArrayIndexOutOfBoundsException.getMessage(),
                                      pArrayIndexOutOfBoundsException);
    }
    catch(NullPointerException pNullPointerException)
    {
      throw new AssetLoaderException("Assets.loadMap - " +
                                      pNullPointerException.getMessage(),
                                      pNullPointerException);
    }
    finally
    {
      if(vImageIDWriter != null)
      {
        try
        {
          vImageIDWriter.close();
        }
        catch(IOException pIOException)
        {
          return;
        }
      }
    }
  }
  
  
  private static ArrayList<TileEvent> parseEventsArray(
      JSONArray pEventsArray) throws AssetLoaderException
  {
    ArrayList<TileEvent> vTileEvents = null;
    
    Class<?>[]  vArgsClass      = null;
    Object[]    vArgsObject     = null;
    
    JSONObject  vCurrEventObject  = null;
    JSONObject  vCurrEventData    = null;
    JSONArray   vEventArgs        = null;
    
    String      vEventClassName   = null;
    
    try
    {
      vTileEvents = new ArrayList<TileEvent>();
      
      for(int vIndex = 0; vIndex < pEventsArray.size(); ++vIndex)
      {
        vCurrEventObject = (JSONObject)pEventsArray.get(vIndex);
        vCurrEventData   = (JSONObject)vCurrEventObject.get("event");
        vEventClassName  = vCurrEventData.get("name").toString();
        vEventArgs       = (JSONArray)vCurrEventData.get("args");
        
        vArgsClass  = new Class<?>[vEventArgs.size()];
        vArgsObject = new Object[vEventArgs.size()];
        
        for(int vArgsIndex = 0; vArgsIndex < vEventArgs.size(); ++vArgsIndex)
        {
          String[] vArgTokens = vEventArgs.get(vArgsIndex).toString().split("\\|");
          
          vArgsClass[vArgsIndex]  = Class.forName(vArgTokens[0]);
          vArgsObject[vArgsIndex] = vArgsClass[vArgsIndex]
                                    .getConstructor(new Class<?>[]{vArgTokens[1].getClass()})
                                    .newInstance(vArgTokens[1]);
        }
        
        Class<?>       vEventClassObject = Class.forName(vEventClassName);
        Constructor<?> vEventConstructor = vEventClassObject.getConstructor(vArgsClass);
        
        vTileEvents.add( (TileEvent)vEventConstructor.newInstance(vArgsObject) );
      }
      return vTileEvents;
    }
    catch(IllegalAccessException pIllegalAccessException)
    {
      throw new AssetLoaderException("Assets.parseEventsArray - " +
          pIllegalAccessException.getMessage(),
          pIllegalAccessException);
    }
    catch(NoSuchMethodException pNoSuchMethodException)
    {
      throw new AssetLoaderException("Assets.parseEventsArray - " +
          pNoSuchMethodException.getMessage(),
          pNoSuchMethodException);
    }
    catch(ClassNotFoundException pClassNotFoundException)
    {
      throw new AssetLoaderException("Assets.parseEventsArray - " +
          pClassNotFoundException.getMessage(),
          pClassNotFoundException);
    }
    catch(InvocationTargetException pInvocationTargetException)
    {
      throw new AssetLoaderException("Assets.parseEventsArray - " +
          pInvocationTargetException.getMessage(),
          pInvocationTargetException);
    }
    catch(InstantiationException pInstantiationException)
    {
      throw new AssetLoaderException("Assets.parseEventsArray - " +
          pInstantiationException.getMessage(),
          pInstantiationException);
    }
  }
  
}//end class
