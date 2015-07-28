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


package backup;

import debug.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import org.jfugue.player.Player;

/**
 * <h1>The Main GUI of the Piano</h1>
 * 
 * @author  Md. Al-amin Nowshad
 * @version 1.0
 */

public class PianoGUI extends JFrame implements ActionListener, KeyListener{
    //main JFrame
    private JFrame frame;
    //key notes to repeat
    private String[] notes = {"C","D","E","F","G","A","B"};
    //dhsrp notes
    private String[] sharps = {"C#","D#","F#","G#","A#"};
    //octaves : primarily 3 octaves used
    private String[] octave = {"4","5","6"};
    
    //creating a player object to play notes
    public static Player player = new Player();
    //note to view the currently palying note
    private static JLabel currentlyPlayingLabel = new JLabel();
    public static JLabel testLabel;
    //creating menu object to access Menu class components
    public static Menu menuObj = new Menu();
    //hashmap for button reference
    public static Map<String,JButton> buttonMap = new HashMap<String,JButton>();
    
    /**
     * constructor that creates GUI
     * 
     * @throws BadLocationException 
     */
    public PianoGUI() throws BadLocationException{
        //Main JFrame
        frame = new JFrame("VirtualPiano");
        //setting jframe icon
        try{
            Image image = new ImageIcon("images/piano2.png").getImage();
            frame.setIconImage(image);
        }catch(Exception e){
            System.out.println("Appilcation icon not found");
        }
        
        // Set a background for JFrame
        frame.setContentPane(new JLabel(new ImageIcon("images/background.jpg")));
        
        /*
        for Nimbus look & feel
        
        try {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        */
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setFocusable(true);
        
        frame.addKeyListener(this);
        
        //main Conatainer MainPanel
        
        Container mainPanel = frame.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setForeground(new Color(0,0,0,0));
		mainPanel.setBackground(new Color(0,0,0,0));
        
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        
        //top panel
        
        JPanel topPanel = new JPanel();
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setLayout(new GridLayout(1,3));
        topPanel.setBackground(new Color(0,0,0,0));
        //topPanel.setPreferredSize(new Dimension(600,50));
        topPanel.setFocusable(false);
      
        //logo area/ "Virtual Piano" - title area
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0,0,0,65));
        JLabel leftLabel = new JLabel();
        leftLabel.setText("<html><h1>Virtual<br>Piano</h1></html>");
        leftLabel.setForeground(new Color(0, 102, 255,1));
        leftPanel.add(leftLabel);
        
        
        //tabbed menu starts
        JPanel tabbedMenu = menuObj.createDifferentMenu();
        tabbedMenu.setSize(200, 100);
        
        //right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0,0,0,65));
        JLabel rightLabel = new JLabel();
        rightLabel.setText("<html><h4>© 2014<br>Nowshad</h4></html>");
        
        
        //test label
        testLabel = new JLabel("Test label");
        
        
        rightPanel.add(rightLabel);
        
        //adding to topPanel
        topPanel.add(leftPanel);
        //topPanel.add(tabbedMenuPanel);
        topPanel.add(tabbedMenu);
        topPanel.add(rightPanel);
        
        
        //top panel finished
        
        mainPanel.add(topPanel);
        
        //making & adding the keyboard
        JLayeredPane pianoKeyPanel = makeKeyboard();
        
        mainPanel.add(pianoKeyPanel);
        
        frame.setVisible(true);
        //frame.setResizable(false);
        frame.pack();

        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width/2, height/2);

        // here's the part where i center the jframe on screen
        frame.setLocationRelativeTo(null);
        frame.setSize(880,450);
    }
    
    
    
    
    
    /**
     * <h1>function to generate the keyboard</h1>
     * 
     * @return keyboard the keys drawn for the piano
     */
    public JLayeredPane makeKeyboard(){
        
        String name = "";
	int x = 75;
        int y = 20;
        
        JLayeredPane keyboard = new JLayeredPane();
        keyboard.setPreferredSize(new Dimension(830,180));
        keyboard.add(Box.createRigidArea(new Dimension(x, 0)));
        keyboard.setFocusable(false);
        //adding the white keys
        for(int i=0;i<octave.length;++i){
            for(int j=0;j<notes.length;++j){
                ImageIcon img = new ImageIcon("images/"+notes[j]+".png");
                
                        name = notes[j]+octave[i];
                        //trying to show name on the button
                        //but not working
                        JButton jb = new JButton(name,img);
                        jb.setVerticalTextPosition(SwingConstants.BOTTOM);
                        jb.setHorizontalTextPosition(SwingConstants.CENTER);
        		jb.setName(name);
                        //storing the button reference
                        buttonMap.put(name, jb);
                        jb.setBorderPainted(false);
                        jb.setBorder(null);
                        jb.setMargin(new Insets(0, 0, 0, 0));
                        jb.setContentAreaFilled(false);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
        		jb.setBounds(x,y,35,207);
        		jb.setFocusable(false);
                        jb.setOpaque(false);
                        jb.setContentAreaFilled(false);
                        jb.setBorderPainted(false);
                        //set button turn black when clicked
                        jb.setPressedIcon(new ImageIcon("images/blackKeyPressed.png"));
                      
                        keyboard.add(jb,new Integer(1));
        		keyboard.add(Box.createRigidArea(new Dimension(2, 0)));
                        
        		x += 33;
            }
        }
        
        x = 0;
        
        // Add the black keys 
        for(int i=0; i< octave.length; i++){
        	
    		ImageIcon img = new ImageIcon("images/blackKey.png");
    		
    		// Make 5 "keys"
    		
    		JButton jb0 = new JButton(img);
    		name = sharps[0]+octave[i];
                buttonMap.put(name, jb0);
    		jb0.setName(name);
    		jb0.setActionCommand(name);
    		jb0.addActionListener(this);
                jb0.setPressedIcon(new ImageIcon("images/blackKeyPressed.png"));
                jb0.setBorderPainted(false);
                        jb0.setBorder(null);
                        jb0.setMargin(new Insets(0, 0, 0, 0));
                        jb0.setContentAreaFilled(false);
        		jb0.setActionCommand(name);
        		jb0.addActionListener(this);
        		jb0.setFocusable(false);
                        jb0.setOpaque(false);
                        jb0.setContentAreaFilled(false);
                        jb0.setBorderPainted(false);

    		
    		JButton jb1 = new JButton(img);
    		name = sharps[1]+octave[i];
                buttonMap.put(name, jb1);
    		jb1.setName(name);
    		jb1.setActionCommand(name);
    		jb1.addActionListener(this);
                jb1.setPressedIcon(new ImageIcon("images/blackKeyPressed.png"));
    		jb1.setBorderPainted(false);
                        jb1.setBorder(null);
                        jb1.setMargin(new Insets(0, 0, 0, 0));
                        jb1.setContentAreaFilled(false);
        		jb1.setActionCommand(name);
        		jb1.addActionListener(this);
        		jb1.setFocusable(false);
                        jb1.setOpaque(false);
                        jb1.setContentAreaFilled(false);
                        jb1.setBorderPainted(false);
    		
                JButton jb2 = new JButton(img);
    		name = sharps[2]+octave[i];
                buttonMap.put(name, jb2);
    		jb2.setName(name);
    		jb2.setActionCommand(name);
    		jb2.addActionListener(this);
                jb2.setPressedIcon(new ImageIcon("images/blackKeyPressed.png"));
    		jb2.setBorderPainted(false);
                        jb2.setBorder(null);
                        jb2.setMargin(new Insets(0, 0, 0, 0));
                        jb2.setContentAreaFilled(false);
        		jb2.setActionCommand(name);
        		jb2.addActionListener(this);
        		jb2.setFocusable(false);
                        jb2.setOpaque(false);
                        jb2.setContentAreaFilled(false);
                        jb2.setBorderPainted(false);
    		
                JButton jb3 = new JButton(img);
    		name = sharps[3]+octave[i];
                buttonMap.put(name, jb2);
    		jb3.setName(name);
    		jb3.setActionCommand(name);
    		jb3.addActionListener(this);
                jb3.setPressedIcon(new ImageIcon("images/blackKeyPressed.png"));
    		jb3.setBorderPainted(false);
                        jb3.setBorder(null);
                        jb3.setMargin(new Insets(0, 0, 0, 0));
                        jb3.setContentAreaFilled(false);
        		jb3.setActionCommand(name);
        		jb3.addActionListener(this);
        		jb3.setFocusable(false);
                        jb3.setOpaque(false);
                        jb3.setContentAreaFilled(false);
                        jb3.setBorderPainted(false);
                        
    		JButton jb4 = new JButton(img);
    		name = sharps[4]+octave[i];
                buttonMap.put(name, jb4);
    		jb4.setName(name);
    		jb4.setActionCommand(name);
    		jb4.addActionListener(this);
                jb4.setPressedIcon(new ImageIcon("images/blackKeyPressed.png"));
    		jb4.setBorderPainted(false);
                        jb4.setBorder(null);
                        jb4.setMargin(new Insets(0, 0, 0, 0));
                        jb4.setContentAreaFilled(false);
        		jb4.setActionCommand(name);
        		jb4.addActionListener(this);
        		jb4.setFocusable(false);
                        jb4.setOpaque(false);
                        jb4.setContentAreaFilled(false);
                        jb4.setBorderPainted(false);
                
    		// Place the 5 keys 
    		jb0.setBounds(93+(231*i),y-1,25,126);
    		keyboard.add(jb0,new Integer(2));
    		
    		jb1.setBounds(130+(231*i),y-1,30,126);
    		keyboard.add(jb1,new Integer(2));
    		
    		jb2.setBounds(192+(231*i),y-1,25,126);
    		keyboard.add(jb2,new Integer(2));
        	
    		jb3.setBounds(227+(231*i),y-1,28,126);
    		keyboard.add(jb3,new Integer(2));
   
    		jb4.setBounds(264+(231*i),y-1,25,126);
    		keyboard.add(jb4,new Integer(2));
        }
        
        return keyboard;
        
        
    }
      

    /**
     * Mouse event handler
     * @param e 
     */
    @Override
	public void actionPerformed(ActionEvent e) {
		
            Runnable playNotes = new Runnable(){
                         public void run() {
                            
                            String key = "";
                            Object obj = e.getSource();
                            // Cast the object to a JButton
                            final JButton jb = (JButton)obj;
                            key = jb.getName();
                            //System.out.println(key);
                            //print the currently playing note
                            setCurrentlyPlayingLabel(key);
                            try{
                                
                                //trying to play the note
                                player.play(key);
                                
                                
                                
                            }catch(Exception e){
                                System.out.println("Exception with "+ key);
                                //retrying to play
                                player.play(key);
                            }
                            
                            }
		         };
		 	(new Thread(playNotes)).start();
                    }
        
        
        
    /**
     * method that does nothing
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * method to handle key pressed events
     * 
     * gets the note to play from the PianoFunctionality
     * class. Then makes the corresponding JButton clicked.
     * 
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //find the button with the associated keyPressed
        
        Runnable playNotes = new Runnable(){
                         public void run() {
                            
                            
                            try{
                                //calling keyboard key response function
                                String keyPressed = PianoFunctionality.generateNotes(e);
                                //now click the corresponding button
                                if(keyPressed!=null){
                                    JButton pressedButton = buttonMap.get(keyPressed);
                                    pressedButton.doClick();
                                }
                            }catch(Exception e){
                               
                            }
                            
                            }
		         };
		 	(new Thread(playNotes)).start();

        
    }
    
    /**
     * function to set the text of the currently playing label
     * 
     * sets the text to the note that is playing now
     * 
     * @param labelText 
     */
    public static void setCurrentlyPlayingLabel(String labelText){
        
        //menuObj.setCurrenttlyPlayingNote(labelText);
        String playingText = "<html><h3>Currently Playing Note</h3>"
                + "<br><h4 style=\"padding-left:65;\">"+labelText+"</h4></html>";
        try{
            Menu.currentlyPlayingNote = labelText;
            Menu.menuLabel.setText(playingText);
        }catch(Exception e){
            System.out.println("label overriding failed");
        }
    }
    
    /**
     * does nothing
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
           
}
