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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nowshad
 */
public class ManualClass {
    public ManualClass(){
        //createManual();
    }
    public JFrame createManual(){
        JFrame manualframe = new JFrame();
        manualframe.setTitle("Manual");
        
        JPanel mainPanel = new JPanel();
        JLabel testLabel = new JLabel("Maual Label");
        
        mainPanel.add(testLabel);
        mainPanel.setBackground(new Color(0,0,0,0));
                
        manualframe.setSize(500, 100);
        manualframe.add(mainPanel);
        manualframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        return manualframe;
    }
    
}
