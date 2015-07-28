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

package debug;

import backup.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

/**
 * <h1>Main functional class</h1>
 * this class makes the piano GUI in PianoGUi class
 * functional. Running the whole project, determining
 * which sound to play - are done by this class 
 * 
 * @author  Md. Al-amin Nowshad
 * @version 1.0
 */

public class PianoFunctionality {
    
    /**
     * The default constructor for this class
     * 
     * Does Nothing
     */
    public PianoFunctionality(){
        
    } 
    /**
     * <h1>method to determine which note to play</h1>
     * just returns a name string of the note to play
     * with corresponding key event.
     * 
     * @param e Key Event
     * @return noteToPlay a string that contains the name of
     *          of the note to play
     */
    
    public static String generateNotes(KeyEvent e){
        char keyPressed = e.getKeyChar();
        //System.out.println("Pressed: "+keyPressed);
        String noteToPlay = null;
        switch(keyPressed){
            //white keys
            case 'z':
                noteToPlay="C4";
                
                break;
            case 'x':
                noteToPlay=("D4");
                break;
            case 'c':
                noteToPlay=("E4");
                break;
            case 'v':
                noteToPlay=("F4");
                break;
            case 'b':
                noteToPlay=("G4");
                break;
            case 'n':
                noteToPlay=("A4");
                break;
            case 'm':
                noteToPlay=("B4");
                break;
            case ',':
                noteToPlay=("C5");
                break;
            case '.':
                noteToPlay=("D5");
                break;
            case '/':
                noteToPlay=("E5");
                break;
            case 'q':
                noteToPlay=("F5");
                break;
            case 'w':
                noteToPlay=("G5");
                break;
            case 'e':
                noteToPlay=("A5");
                break;
            case 'r':
                noteToPlay=("B5");
                break;
            case 't':
                noteToPlay=("C6");
                break;
            case 'y':
                noteToPlay=("D6");
                break;
            case 'u':
                noteToPlay=("E6");
                break;
            case 'i':
                noteToPlay=("F6");
                break;
            case 'o':
                noteToPlay=("G6");
                break;
            case 'p':
                noteToPlay=("A6");
                break;
            case '[':
                noteToPlay=("B6");
                break;
            //black keys
            case 's':
                noteToPlay=("C#4");
                break;
            case 'd':
                noteToPlay=("D#4");
                break;
            case 'g':
                noteToPlay=("F#4");
                break;
            case 'h':
                noteToPlay=("G#4");
                break;
            case 'j':
                noteToPlay=("A#4");
                break;
            case 'l':
                noteToPlay=("C#5");
                break;
            case ';':
                noteToPlay=("D#5");
                break;
            case '2':
                noteToPlay=("F#5");
                break;
            case '3':
                noteToPlay=("G#5");
                break;
            case '4':
                noteToPlay=("A#5");
                break;
            case '6':
                noteToPlay=("C#6");
                break;
            case '7':
                noteToPlay=("D#6");
                break;
            case '9':
                noteToPlay=("F#6");
                break;
            case '0':
                noteToPlay=("G#6");
                break;
            case '-':
                noteToPlay=("A#6");
                break;
        }
        if(noteToPlay!=null){
            return noteToPlay;
        }
        return null;
        
        
    }
    /**
     * <h1>Main Function</h1>
     * creates the GUI object & makes it alive.
     * 
     * @param args 
     */
     public static void main(String args[]) {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PianoGUI PianoGUIObject = new PianoGUI();
                } catch (BadLocationException ex) {
                    Logger.getLogger(PianoFunctionality.class.getName()).log(Level.SEVERE, null, ex);
                }
                PianoFunctionality pianoFunctionality = new PianoFunctionality();
            }
        });
        
    }
    
}
