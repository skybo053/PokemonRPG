package com.game.FX;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class JukeBox 
{
  Clip audio = null;
  
  public JukeBox()
  {
    try
    {
      audio = AudioSystem.getClip();
    }
    catch(LineUnavailableException pException)
    {
      return;
    }
  }
  
  
  public void play(AudioInputStream pAudioStream) 
  {
    try
    {
      audio.open(pAudioStream);
      audio.start();
    }
    catch(IOException pException)
    {
      
    }
    catch(LineUnavailableException pException)
    {
      
    }
  }
  
  
  public void stop()
  {
    audio.stop();
  }
  
  
  public void close()
  {
    audio.close();
  }
  
  
  public boolean isPlaying()
  {
    return audio.isActive();
  }
  
  
  public long getCurrentFrame()
  {
    return audio.getLongFramePosition();
  }
  
  
  public int getTotalFrames()
  {
    return audio.getFrameLength();
  }
}
