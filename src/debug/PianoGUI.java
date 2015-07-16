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

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.text.BadLocationException;
import org.jfugue.player.Player;

/**
 *
 * @author nowshad
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
    Menu menuObj = new Menu();
    
    public static Map<String,JButton> buttonMap = new HashMap<String,JButton>();
    
    //constructor
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
        look & feel doesn't work properly
        makes piano playing jammed
        & all static labels vanished
        
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
        leftPanel.add(leftLabel);
        
        
        
        
        //massive GUI bug that gets created when any button is pressed
        //or mouse clicked
        
        
        //tabbed menu starts
        JPanel tabbedMenu = menuObj.createDifferentMenu();
        tabbedMenu.setSize(200, 100);
        /*
        //1st tab -> CurrentlyPlayingPanel
        
        JPanel CurrentlyPlayingPanel = new JPanel();
        
        CurrentlyPlayingPanel.setLayout(new BoxLayout(CurrentlyPlayingPanel, BoxLayout.Y_AXIS));
        
        CurrentlyPlayingPanel.setBackground(new Color(0,0,0,0));
        
        JLabel label1 = new JLabel();
        currentlyPlayingLabel = new JLabel("<html><h2 style=\"margin-left:200;\">Nothing</h2></html>"); 
        label1.setText("<html><h2 style=\"margin-left:100;\">Currently Playing Note</h2></html>");
        
        CurrentlyPlayingPanel.add(label1);
        CurrentlyPlayingPanel.add(currentlyPlayingLabel);
        
        //2nd tab -> CreditsPanel
        JPanel creditsPanel = new JPanel();
        creditsPanel.setBackground(new Color(0,0,0,0));
        JLabel creditsLabel = new JLabel();
        //credits text
        creditsLabel.setText("<html>Md. Al-amin Nowshad"
                + "<br>Batch: 2012,"
                + "<br>Dept. of Computer Science & Engineering,"
                + "<br>ShahJalal University of Science & Technology"
                + "<br>email: iamnowshad@gmail.com</html>");
        
        creditsPanel.add(creditsLabel);
        
        //3rd tab -> helpPanel
        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(new Color(0,0,0,0));
        JLabel helpLabel = new JLabel("<html><p>Watch the keymap to play</p></html>");
        JLabel linkLabel = new JLabel();
        PianoFunctionality.goWebsite(linkLabel,"http://nowshad.scdnlab.com","Click here");
        helpPanel.add(helpLabel);
        helpPanel.add(linkLabel);
        
        //adding tabs to main JTabbedPane
        tabbedMenuPanel.addTab("Playing", CurrentlyPlayingPanel);
        tabbedMenuPanel.addTab("Credits", creditsPanel);
        tabbedMenuPanel.addTab("Help", helpPanel);
        
        
        
        //tabbed menu ends
     */
        
        
        
        
        //right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0,0,0,65));
        JLabel rightLabel = new JLabel();
        rightLabel.setText("<html><h1>Right<br>Panel</h1></html>");
        
        
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
        frame.setResizable(false);
        frame.setSize(900,400);
    }
    
    
    
    
    
    //function to generate the keyboard
    public JLayeredPane makeKeyboard(){
        
        String name = "";
	int x = 55;
        int y = 20;
        
        JLayeredPane keyboard = new JLayeredPane();
        keyboard.setPreferredSize(new Dimension(900,162));
        keyboard.add(Box.createRigidArea(new Dimension(x, 0)));
        keyboard.setFocusable(false);
        //adding the white keys
        for(int i=0;i<octave.length;++i){
            for(int j=0;j<notes.length;++j){
                ImageIcon img = new ImageIcon("images/"+notes[j]+".png");
                JButton jb = new JButton(img);

                        name = notes[j]+octave[i];
        		jb.setName(name);
                        buttonMap.put(name, jb);
                        jb.setBorderPainted(false);
                        jb.setBorder(null);
                        jb.setMargin(new Insets(0, 0, 0, 0));
                        jb.setContentAreaFilled(false);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
                        //test keyboard input
                        //jb.setMnemonic(KeyEvent.VK_Q);
        		jb.setBounds(x,y,35,207);
        		jb.setFocusable(false);
                        jb.setOpaque(false);
                        jb.setContentAreaFilled(false);
                        jb.setBorderPainted(false);
                        //set button turn black when clicked
                        jb.setPressedIcon(new ImageIcon("images/blackKey.png"));
                        /* used for pressed & released effect
                        button.setIcon(myIcon1);
                        button.setRolloverIcon(myIcon2);
                        button.setPressedIcon(myIcon3);
                        button.setDisabledIcon(myIcon4);
                        */
                        keyboard.add(jb,new Integer(1));
        		keyboard.add(Box.createRigidArea(new Dimension(2, 0)));
                        
        		x += 37;
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
                jb0.setBorderPainted(false);
                        jb0.setBorder(null);
                        jb0.setMargin(new Insets(0, 0, 0, 0));
                        jb0.setContentAreaFilled(false);
        		jb0.setActionCommand(name);
        		jb0.addActionListener(this);
        		jb0.setBounds(x,y,35,207);
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
    		jb1.setBorderPainted(false);
                        jb1.setBorder(null);
                        jb1.setMargin(new Insets(0, 0, 0, 0));
                        jb1.setContentAreaFilled(false);
        		jb1.setActionCommand(name);
        		jb1.addActionListener(this);
        		jb1.setBounds(x,y,35,207);
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
    		jb2.setBorderPainted(false);
                        jb2.setBorder(null);
                        jb2.setMargin(new Insets(0, 0, 0, 0));
                        jb2.setContentAreaFilled(false);
        		jb2.setActionCommand(name);
        		jb2.addActionListener(this);
        		jb2.setBounds(x,y,35,207);
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
    		jb3.setBorderPainted(false);
                        jb3.setBorder(null);
                        jb3.setMargin(new Insets(0, 0, 0, 0));
                        jb3.setContentAreaFilled(false);
        		jb3.setActionCommand(name);
        		jb3.addActionListener(this);
        		jb3.setBounds(x,y,35,207);
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
    		jb4.setBorderPainted(false);
                        jb4.setBorder(null);
                        jb4.setMargin(new Insets(0, 0, 0, 0));
                        jb4.setContentAreaFilled(false);
        		jb4.setActionCommand(name);
        		jb4.addActionListener(this);
        		jb4.setBounds(x,y,35,207);
        		jb4.setFocusable(false);
                        jb4.setOpaque(false);
                        jb4.setContentAreaFilled(false);
                        jb4.setBorderPainted(false);
                        
    		// Place the 5 keys 
    		jb0.setBounds(77+(260*i),y-1,25,115);
    		keyboard.add(jb0,new Integer(2));
    		
    		jb1.setBounds(115+(260*i),y-1,25,115);
    		keyboard.add(jb1,new Integer(2));
    		
    		jb2.setBounds(188+(260*i),y-1,25,115);
    		keyboard.add(jb2,new Integer(2));
        	
    		jb3.setBounds(226+(260*i),y-1,25,115);
    		keyboard.add(jb3,new Integer(2));
   
    		jb4.setBounds(264+(260*i),y-1,25,115);
    		keyboard.add(jb4,new Integer(2));
        }
        
        return keyboard;
        
        
    }
      

    //mouse event handler
    @Override
	public void actionPerformed(ActionEvent e) {
		
            Runnable playNotes = new Runnable(){
                         public void run() {
                            
                            String key = "";
                            Object obj = e.getSource();
                            // Cast the object to a JButton
                            final JButton jb = (JButton)obj;
                            key = jb.getName();
                            try{
                                System.out.println(key);
                                //print the currently playing note
                                setCurrentlyPlayingLabel(key);
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
        
        
        

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

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
    
    public static void setCurrentlyPlayingLabel(String labelText){
        Menu menuObj2 = new Menu();
        menuObj2.setCurrentlyPlayingLabel(labelText);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
           
}
