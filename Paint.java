package flappywitch;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Paint extends JPanel {
    int x = 0;
    int y = -80;
    private ImageIcon bghome = new ImageIcon(this.getClass().getResource("image/blackgound.jpg"));
    private ImageIcon actor = new ImageIcon(this.getClass().getResource("image/actor.png"));  
    private ImageIcon start = new ImageIcon(this.getClass().getResource("image/Start.jpg"));  
    public JButton btStart = new JButton(start);


    public Paint() {
        setLayout(null);
        add(btStart);
        btStart.setBounds(330,650,150,90);
        add(btStart);
    }
    
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(bghome.getImage(),0,0,828,1050,this);
            g.drawImage(actor.getImage(), x, y, 800, 1000, this);
	}
}

