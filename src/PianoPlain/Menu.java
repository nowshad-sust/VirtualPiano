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


/**
 * Class to create the menu shown at the top Panel of the main GUI
 * createManu is a function that creates a tabbed menu (which is now commented out)
 * createDifferentMenu is the currently working menu
 * works fine with one bug
 * 
 * BUG:
 *      Location: menuLabel
 *      summary: when an string is assigned to the label
 *               it generates a mess on that label
 *      primary solution: made the whole menu background black
 *                        that hides the bug happening
 *      reason: still unknown
*/
package PianoPlain;

import debug.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author nowshad
 */

public class Menu extends JFrame implements ActionListener{
    public static JLabel currentlyPlayingLabel  = new JLabel();
    public static JLabel menuLabel = new JLabel("Main Menu");;
    public static String currentlyPlayingNote = "Nothing";
    public Menu(){
        /*
        JFrame frame = new JFrame();
        //frame.setContentPane(new JLabel(new ImageIcon("images/background.jpg")));
        
        JPanel topPanel = createMenu();  
        
        
        frame.add(topPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900,400);
       */
    }
    /*
    public static JPanel createMenu(){
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0,0,0,0));
        
        JTabbedPane tabbedMenuPanel = new JTabbedPane();
        //tabbedMenuPanel.setSize(200, 100);
        tabbedMenuPanel.setBackground(new Color(0,0,0,0));
        tabbedMenuPanel.setBorder(null);
        tabbedMenuPanel.setFocusable(false);
        
        //1st tab -> CurrentlyPlayingPanel
        
        JPanel CurrentlyPlayingPanel = new JPanel();
        
        CurrentlyPlayingPanel.setLayout(new BoxLayout(CurrentlyPlayingPanel, BoxLayout.Y_AXIS));
        
        CurrentlyPlayingPanel.setBackground(new Color(0,0,0,0));
        
        JLabel label1 = new JLabel();
        label1.setText("<html><h3>Currently Playing Note</h3></html>");
        currentlyPlayingLabel = new JLabel("<html><h3>Nothing</h3></html>");
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
        
        topPanel.add(tabbedMenuPanel);
        
        return topPanel;
        
    }
    */
    public JPanel createDifferentMenu(){
        
        JPanel differentMenu = new JPanel();
        differentMenu.setBackground(new Color(0,0,0,65));
        //differentMenu.setBackground(Color.black);
        //differentMenu
        JPanel menuBar = new JPanel();
        menuBar.setLayout(new GridLayout(1,4));
        
        JButton playingButton = new JButton("Playing");
        JButton manualButton = new JButton("Manual");
        JButton creditsButton = new JButton("Credits");
        JButton helpButton = new JButton("Help");
        goWebsite(helpButton, "http://nowshad.scdnlab.com");
        
        setButtonDesign(playingButton);
        setButtonDesign(manualButton);
        setButtonDesign(creditsButton);
        setButtonDesign(helpButton);
        
        
        
        
        menuBar.add(playingButton);
        menuBar.add(manualButton);
        menuBar.add(creditsButton);
        menuBar.add(helpButton);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.black);
        
        /*
        GUI bug:
            if the background is transparent then bug happens
            //menuPanel.setBackground(new Color(0,0,0,65));
        */
        
        menuPanel.setLayout(new FlowLayout());
        
        //menuLabel.setBackground(Color.BLACK);
        menuLabel.setForeground(new Color(242, 243, 244, 1));
        menuLabel.setSize(50, 50);
        menuPanel.add(menuLabel);
        
        
        differentMenu.add(menuBar);
        differentMenu.add(menuPanel);
        
        creditsButton.doClick();
        return differentMenu;
       
    }
    //mouse event handler
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
                String manualText = "<html>Manual Window</html>";
                //menuLabel.setText(manualText);
                ManualClass manualObject = new ManualClass();
                JFrame manualFrame = manualObject.createManual();
                manualFrame.setVisible(true);
                    
            }
            else if(e.getActionCommand().equals("Help"))
            {
                //PianoFunctionality.goWebsite(menuLabel,"http://nowshad.scdnlab.com","Click here");
                String helpText = "<html><b>Help Menu<b></html>";
                //menuLabel.setText(helpText);
            }
            else{
                String playingText = "<html><h2>Currently Playing Note</h2>"
                + "<br><h3 style=\"padding-left:50;\">"+currentlyPlayingNote+"</h3></html>";
                menuLabel.setText(playingText);
            }
        }
    
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
    

    public void setCurrenttlyPlayingNote(String note) {
        this.currentlyPlayingNote = note;
        String playingText = "<html><h2>Currently Playing Note</h2>"
                + "<br>"+note+"</html>";
        try{
            menuLabel.setText(playingText);
        }catch(Exception e){
            System.out.println("label overriding failed");
        }
        
    }
     public static void goWebsite(JButton website, final String url) {
        //website.setText("<html> : <a href=\"\">"+text+"</a></html>");
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
