package PianoPlain;

//package com.tutorialspoint;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FileDemo {
   public static void main(String[] args) {
      
      File f = null;
      String[] paths;
      FileDemo fd = new FileDemo();
      
            
      try{      
         // create new file
          Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString()+"\\src\\wav";
            System.out.println("Current relative path is: " + s);
         //String url = System.getProperty("user.dir");
         //System.out.println(url);
         f = new File(s);
                                 
         // array of files and directory
         paths = f.list();
         System.out.println(paths.length);
        
         /*audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         */// for each name in the path array
         fd.LoadSounds(paths);
         for(String path:paths)
         { 
         
            System.out.println(path);
         }
      }catch(Exception e){
         // if any error occurs
         e.printStackTrace();
      }
   }
   
   public void LoadSounds(String[] paths){
       
       AudioInputStream audioIn = null;
       URL url;
       Clip clip = null;
       for(String path:paths)
         { 
         
           try {
               url = this.getClass().getClassLoader().getResource("\\wav\\"+path);
               audioIn = AudioSystem.getAudioInputStream(url);
               // Get a sound clip resource.
               clip = AudioSystem.getClip();
               // Open audio clip and load samples from the audio input stream.
               clip.open(audioIn);
               clip.start();
               // prints filename and directory name
               System.out.println(path);
           } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
               Logger.getLogger(FileDemo.class.getName()).log(Level.SEVERE, null, ex);
           }
         }
   }
}