/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappywitch;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

public class Renderer extends JPanel
{
	private static final long serialVersionUID = 1L;
        private ImageIcon bghome = new ImageIcon(this.getClass().getResource("image/blackgound.jpg"));
        private ImageIcon gound = new ImageIcon(this.getClass().getResource("image/gound.jpg"));
       
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
                g.drawImage(bghome.getImage(),0,0,828,1050,this);
                g.drawImage(gound.getImage(), 0,925, 828, 1250, this);
		FlappyWitch.flappywitch.repaint(g);
	}
	
}