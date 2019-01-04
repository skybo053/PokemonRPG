package com.game.Main;

import com.game.Exceptions.AssetLoaderException;
import com.game.FX.Assets;

public class AssetLoader extends Thread
{
  
  public void run()
  {
    try
    {
      Assets.load();
      System.out.println("AssetLoader.run - AssetsLoaded, Thread ending");
    }
    catch(AssetLoaderException pAssetLoaderException)
    {
      System.out.println("AssetLoader.run - " + pAssetLoaderException.getMessage());
      pAssetLoaderException.printStackTrace();
    }
  }
}
