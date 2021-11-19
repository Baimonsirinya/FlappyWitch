/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappywitch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.Timer;

public class FlappyWitch implements ActionListener,KeyListener
{
    public static FlappyWitch flappywitch;
    public final int WIDTH = 828, HEIGHT = 1050;
    public Renderer renderer;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public int ticks,score;
    public int mt;
    public boolean gameOver, started;
    public Random rand;
    
    

    FlappyWitch()
    {
        JFrame jframe = new JFrame();
	Timer timer = new Timer(20, this);
	renderer = new Renderer();
	rand = new Random();
	jframe.add(renderer);
	jframe.setTitle("Flappy Witch");
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(WIDTH, HEIGHT);
	jframe.addKeyListener(this);
	jframe.setResizable(false);
	jframe.setVisible(true);
   
	bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
 
	columns = new ArrayList<>();
		addColumn(true);
		addColumn(true);
		addColumn(true);
		timer.start();
    }

    

	public void addColumn(boolean start)
	{
		int space = 300;
		int width = 100;
		int height = 90 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 900, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.pink.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	public void jump()
	{
		if (gameOver)
		{
                    bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
                    columns.clear();
                    mt = 0;
                    score = 0;

                    addColumn(true);
                    addColumn(true);
                    addColumn(true);

                    gameOver = false;
		}
		if (!started)
		{
                    started = true;
		}
		else if (!gameOver)
		{
		if (mt > 0)
		{
                    mt = 0;
                }
                    mt -= 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
            int speed = 10;
            ticks++;
            if (started)
            {
		for (int i = 0; i < columns.size(); i++)
		{
                    Rectangle column = columns.get(i);
			column.x -= speed;
		}

		if (ticks % 2 == 0 && mt < 15)
		{
                    mt += 2;
		}
		for (int i = 0; i < columns.size(); i++)
		{
                    Rectangle column = columns.get(i);
                    if (column.x + column.width < 0)
                    {
			columns.remove(column);
                        if (column.y == 0)
                    {
			addColumn(false);
                    }
                    }
		}

		bird.y += mt;

		for (Rectangle column : columns)
		{
                    if(column.y == 0 && bird.x + bird.width / 5  > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
                    {
			score++;
                    }
                    if (column.intersects(bird))
                    {
			gameOver = true;
                    if (bird.x <= column.x)
                    {
			bird.x = column.x - bird.width;
                    }
                    else
                    {
                        if (column.y != 0)
                        {
                            bird.y = column.y - bird.height;
			}
                        else if (bird.y < column.height)
			{
                            bird.y = column.height;
			}
                    }
                    }
               }
		if (bird.y > HEIGHT - 120 || bird.y < 0)
		{
                    gameOver = true;
                }
		if (bird.y + mt >= HEIGHT - 120)
		{
                    bird.y = HEIGHT - 120 - bird.height;
                    gameOver = true;
		}
            }       
            renderer.repaint();
	}
        
	public void repaint(Graphics g)
        {
            g.setColor(Color.red.darker());
            g.fillRect(bird.x, bird.y, bird.width, bird.height);

            for(Rectangle column : columns)
            {
               paintColumn(g,column);
            }
            g.setColor(Color.white);
            g.setFont(new Font("Ariel", 1, 100));
            if (!started)
            {
                g.drawString("Press to start!", 75, HEIGHT / 2 - 50);
            }
            if (gameOver)
            {
                g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
            }
            if (!gameOver && started)
            {
                g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
            }
	}

	public static void main(String[] args)
	{
            JFrame jf = new setbuttom();
            jf.setSize(828,1050);
            jf.setTitle("Flappy Witch");
            jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                jump();
            }
	}
	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}
 
}