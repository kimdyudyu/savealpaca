package com.save_alpaca.view;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class Sound implements Runnable{
    public static Thread bgmThread;
   private URL fileURL;
   private int musicTime;
   private Clip clip = null;

   public Sound() {}
   
   public Sound(URL fileURL, int musicTime) {
	   this.fileURL = fileURL;
	   this.musicTime = musicTime;
   }
   @Override
   public void run() {
      File bgm;
      AudioInputStream stream;
      AudioFormat format;
      DataLine.Info info;
      try {
         while(true) {
            stream = AudioSystem.getAudioInputStream(fileURL);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            System.out.println("--- BGM 시작 ---");
            clip.start();
            Thread.sleep(musicTime);
         }
      } catch (InterruptedException ee) {
         System.out.println("--- BGM 종료 ---");
           
      } catch (Exception e) {
         System.out.println("err : " + e);
      }
      clip.close();
      
   }
   
   public void closeClip() {
      this.clip.close();
   }
   

   
   public static void soundShutdown() {
	   
   }

   
   
}
