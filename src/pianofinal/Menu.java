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



package pianofinal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Class to create the menu shown at the top Panel of the main GUI
 * 
 * @author  Md. Al-amin Nowshad
 * @version 1.0
 */

public class Menu extends JFrame implements ActionListener{
    //declaring static labels & variable
    public static JLabel currentlyPlayingLabel  = new JLabel();
    public static JLabel menuLabel = new JLabel("Main Menu");;
    public static String currentlyPlayingNote = "Nothing";
    
    /**
     * Empty constructor
     */
    public Menu(){
        
    }
    
    /**
     * function to create menu with four button(like tabbed menu)
     * This function creates a menu which will be added to the
     * main Frame of the application at the top bar.
     * <p>
     * The menu is divided into two parts -
     * <dl>
     *   <dt>Menu Tabs</dt>
     *     <dd>- the tabs to navigate display contents</dd>
     *   <dt>Display Area</dt>
     *     <dd>- display the contents by the selected tab</dd>
     *  </dl>
     * 
     * @return <code>differentMenu</code> which is the new menu
     *              that is created in a <code>JPanel</code>.
     * @see JPanel
     * @see #goWebsite(javax.swing.JButton, java.lang.String)
     * @see #setButtonDesign(javax.swing.JButton) 
     */
    public JPanel createDifferentMenu(){
        
        JPanel differentMenu = new JPanel();
        differentMenu.setBackground(new Color(0,0,0,65));
        //differentMenu.setBackground(Color.black);
        
        //Menu bar with just four buttons
        JPanel menuBar = new JPanel();
        menuBar.setLayout(new GridLayout(1,4));
        
        JButton playingButton = new JButton("Playing");
        JButton manualButton = new JButton("Manual");
        JButton creditsButton = new JButton("Credits");
        JButton helpButton = new JButton("Contact");
        //making the helpButton clickable
        goWebsite(helpButton, "http://nowshad.scdnlab.com");
        
        //setting button design
        setButtonDesign(playingButton);
        setButtonDesign(manualButton);
        setButtonDesign(creditsButton);
        setButtonDesign(helpButton);
        //adding buttons to menubar
        menuBar.add(playingButton);
        menuBar.add(manualButton);
        menuBar.add(creditsButton);
        menuBar.add(helpButton);
        
        //menu contents display area
        JPanel menuPanel = new JPanel();
        //menuPanel.setBackground(new Color(0,0,0,0));
        menuPanel.setBackground(Color.black);

        menuPanel.setLayout(new FlowLayout());
        
        menuLabel.setForeground(new Color(242, 243, 244, 1));
        menuLabel.setSize(50, 50);
        menuPanel.add(menuLabel);
        
        
        differentMenu.add(menuBar);
        differentMenu.add(menuPanel);
        
        creditsButton.doClick();
        return differentMenu;
       
    }
    
    /**
     * function to handle the mouse events on the tabs
     * Checks which tabbed is clicked as well as changes 
     * the display area contents
     * <p>
     * there are four types of response based on which
     * tab is selected-
     *  <dl>
     *   <dt>Playing</dt>
     *     <dd>- just shows the currently playing note</dd>
     *   <dt>Manual</dt>
     *     <dd>- opens a new window which describes the
     *          <br>procedures of using this application
     *     </dd>
     *   <dt>Credits</dt>
     *     <dd>- shows the credits and info of the developer</dd>
     *   <dt>Help</dt>
     *     <dd>- takes to the help website</dd>
     *  </dl> 
     * 
     * 
     * @see Manual
     * @param e just the action event which was not used
     */
	public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Playing"))
            {
                String playingText = "<html><h3>Currently Playing Note</h3>"
                + "<br><h4 style=\"padding-left:50;\">"+currentlyPlayingNote+"</h4></html>";
                menuLabel.setText(playingText);
            }
            else if(e.getActionCommand().equals("Credits"))
            {
                String creditsText = "<html>Md. Al-amin Nowshad"
                + "<br>Batch: 2012,"
                + "<br>Dept. of Computer Science & Engineering,"
                + "<br>Shahjalal University of Science & Technology"
                + "</html>";
                
                menuLabel.setText(creditsText);
            }
            else if(e.getActionCommand().equals("Manual"))
            {
                //ManualClass manualObject = new ManualClass();
                JFrame manualFrame;
                manualFrame = new Manual();
                manualFrame.setVisible(true);
                
                    
            }
            else if(e.getActionCommand().equals("Help"))
            {
                String helpText = "<html><b>Help Menu<b></html>";
            }
            else{
                String playingText = "<html><h2>Currently Playing Note</h2>"
                + "<br><h3 style=\"padding-left:50;\">"+currentlyPlayingNote+"</h3></html>";
                menuLabel.setText(playingText);
            }
        }
    /**
     * function that sets the style for the tab buttons
     * 
     * makes background transparent
     * adds event handler
     * makes non-focusable
     * 
     * @param jb a JButton object
     */
    public void setButtonDesign(JButton jb){
                        String name  = jb.getText();
                        jb.setContentAreaFilled(false);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
                        jb.setFocusable(false);
                        //jb.setRolloverIcon(null);
                        //jb.setOpaque(false);
                        //set button turn black when clicked
                        //jb.setPressedIcon(new ImageIcon("images/blackKey.png"));
    }
    
    /**
     * function that makes a button clickable and sends to an website
     * 
     * adds a mouseListener to the button
     * takes to the help website
     * 
     * @param website a button
     * @param url address of the website
     */
     public static void goWebsite(JButton website, final String url) {
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
}
