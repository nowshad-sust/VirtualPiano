/*
 * Copyright 2015 nowshad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.text.BadLocationException;

/**
 *
 * @author nowshad
 */

//brain of PianoGUI class
public class PianoFunctionality {
    
    PianoGUI PianoGUIObject;
    
    public PianoFunctionality(){
        try {
            PianoGUIObject = new PianoGUI();
        } catch (BadLocationException ex) {
            Logger.getLogger(PianoFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void goWebsite(JLabel website, final String url, String text) {
        website.setText("<html> : <a href=\"\">"+text+"</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                            //It looks like there's a problem
                    }
            }
        });
    }
    
    //function to call for keyboard events
        public static void playSound(String key){
            Runnable playNotes = new Runnable(){
                         public void run() {
                            try{
                                //print the currently playing note
                                //pianoGUIRef.setCurrentlyPlayingLabel.setText("<html><h1 style=\"margin-left:200;\">"+key+"</h1></html>");
                                PianoGUI.setCurrentlyPlayingLabel("<html><h1 style=\"margin-left:200;\">"+key+"</h1></html>");
                                //trying to play note
                                PianoGUI.player.play(key);
                            }catch(Exception e){
                                System.out.println("Exception with "+ key);
                                //retrying to play
                                PianoGUI.player.play(key);
                            }
                             
                            
                            }
		         };
		 	(new Thread(playNotes)).start();
		            
        }
    
        
    //method to determine which note to play
    //with corresponding key event
    //incomplete: black keys still left
    public static void generateNotes(KeyEvent e){
        char keyPressed = e.getKeyChar();
        System.out.println("Pressed: "+keyPressed);
        
        switch(keyPressed){
            case 'q':
                playSound("C4");
                break;
            case 'w':
                playSound("D4");
                break;
            case 'e':
                playSound("E4");
                break;
            case 'r':
                playSound("F4");
                break;
            case 't':
                playSound("G4");
                break;
            case 'y':
                playSound("A4");
                break;
            case 'u':
                playSound("B4");
                break;
            case 'a':
                playSound("C5");
                break;
            case 's':
                playSound("D5");
                break;
            case 'd':
                playSound("E5");
                break;
            case 'f':
                playSound("F5");
                break;
            case 'g':
                playSound("G5");
                break;
            case 'h':
                playSound("A5");
                break;
            case 'j':
                playSound("B5");
                break;
            case 'z':
                playSound("C6");
                break;
            case 'x':
                playSound("D6");
                break;
            case 'c':
                playSound("E6");
                break;
            case 'v':
                playSound("F6");
                break;
            case 'b':
                playSound("G6");
                break;
            case 'n':
                playSound("A6");
                break;
            case 'm':
                playSound("B6");
                break;
            
        }
        
    }
     public static void main(String args[]) {
         new PianoFunctionality();
    }
    
}
