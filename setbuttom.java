/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappywitch;

import static flappywitch.FlappyWitch.flappywitch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class setbuttom extends JFrame implements ActionListener {
    Paint home = new Paint();
    
    public setbuttom(){
        this.setSize(828,1050);
	this.add(home);
        home.btStart.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== home.btStart){
            this.setLocationRelativeTo(null);
            this.dispose();
            flappywitch = new FlappyWitch();
        }
    }
}


