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
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

/**
 *
 * @author nowshad
 */

//brain of PianoGUI class
public class PianoFunctionality {
    
    public static PianoGUI PianoGUIObject;
    
    public PianoFunctionality(){
        
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
    public static String generateNotes(KeyEvent e){
        char keyPressed = e.getKeyChar();
        System.out.println("Pressed: "+keyPressed);
        String noteToPlay = null;
        switch(keyPressed){
            case 'q':
                noteToPlay="C4";
                
                break;
            case 'w':
                noteToPlay=("D4");
                break;
            case 'e':
                noteToPlay=("E4");
                break;
            case 'r':
                noteToPlay=("F4");
                break;
            case 't':
                noteToPlay=("G4");
                break;
            case 'y':
                noteToPlay=("A4");
                break;
            case 'u':
                noteToPlay=("B4");
                break;
            case 'a':
                noteToPlay=("C5");
                break;
            case 's':
                noteToPlay=("D5");
                break;
            case 'd':
                noteToPlay=("E5");
                break;
            case 'f':
                noteToPlay=("F5");
                break;
            case 'g':
                noteToPlay=("G5");
                break;
            case 'h':
                noteToPlay=("A5");
                break;
            case 'j':
                noteToPlay=("B5");
                break;
            case 'z':
                noteToPlay=("C6");
                break;
            case 'x':
                noteToPlay=("D6");
                break;
            case 'c':
                noteToPlay=("E6");
                break;
            case 'v':
                noteToPlay=("F6");
                break;
            case 'b':
                noteToPlay=("G6");
                break;
            case 'n':
                noteToPlay=("A6");
                break;
            case 'm':
                noteToPlay=("B6");
                break;
            
        }
        if(noteToPlay!=null){
            playSound(noteToPlay);
            return noteToPlay;
        }
        return null;
        
        
    }
     public static void main(String args[]) {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PianoGUIObject = new PianoGUI();
                } catch (BadLocationException ex) {
                    Logger.getLogger(PianoFunctionality.class.getName()).log(Level.SEVERE, null, ex);
                }
                PianoFunctionality pianoFunctionality = new PianoFunctionality();
            }
        });
        
    }
    
}
