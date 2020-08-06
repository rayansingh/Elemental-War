import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.logging.*;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player extends JPanel implements KeyListener, ActionListener{
	public ObjectOutputStream out;
    public Game game;
    private int collected = 0;
    private Color grass = new Color(65, 156, 47);
    private Color darkGrass = new Color(60, 145, 40);
    private Color lava = new Color(235, 87, 28);

    Sounds s;

    //private JButton resetMapButton;

    public Boolean gameOver = false;

    private int h1;
    private int h2;

    private int c1;
    private int c2;
    int winner = 0;

    public Player(){
    	


		s = new Sounds();

		game = new Game();

		h1 = game.p1h.size();
		h2 = game.p2h.size();

    	

    	c1 = 0;
    	c2 = 0;

    	this.setLayout(null);
        this.setFocusable(true);
        addKeyListener(this);
    }

    public Dimension getPreferredSize() {
 
        return new Dimension(360,420);
    }
     
    public void drawMe(Graphics g, int t) {
        super.paintComponent(g);

        if(!game.isGameOver()){
	        g.setColor(grass);
	        g.fillRect(0,0,360,360);

	        if(c1 < game.c1){
	        	s.coin();
	        	c1 = game.c1;
	        }

	        if(c2 < game.c2){
	        	s.coin();
	        	c2 = game.c2;
	        }

	        if(t == 1){
	        	g.setColor(Color.blue);
	      		g.fillRect(0,360,360,60);
	        } else {
	        	g.setColor(lava);
	      		g.fillRect(0,360,360,60);
	        }

	        

	        if(h1 > game.p1h.size()){
	        	h1 = game.p1h.size();
	        	s.oof();
	        } 

	        if(h2 > game.p2h.size()){
	        	h2 = game.p2h.size();
	        	s.oof();
	        }
	        if(gameOver){
	          g.setColor(Color.black);
	          if(game.getWinner() == 1){
	            g.drawString("You Win!", 40,50);
	          } else {
	            g.drawString("You Lose", 40,50);
	          }
	          
	        } else {
				HashMap<Location,Block> grid = new HashMap<Location,Block>();
				grid = game.getGrid();

	          	for(int r = 0; r < 36; r++){
	              	for(int c = 0; c < 36; c++){
	                Location key = new Location(r,c);
	                for(Location l : grid.keySet()){
	                  if(l.getX() == r && l.getY() == c){
	                    key = l;
	                  }
	                }
	                Block b = grid.get(key);

	                if(b != null){
	                  if(b.getType() == 0){
	                    g.setColor(grass);
	                    g.fillRect(r*10,c*10,10,10);
	                  }else if(b.getType() == 1){
	                    g.setColor(darkGrass);
	                    g.fillRect(r*10,c*10,10,10);
	                  }else if(b.getType() == 2){
	                    g.setColor(Color.yellow);
	                    g.fillOval(r*10,c*10,10,10);
	                  }else if(b.getType() == 3){
	                    g.setColor(Color.blue);
	                    g.fillRect(r*10,c*10,10,10);
	                  }else if(b.getType() == 4){
	                    g.setColor(lava);
	                    g.fillRect(r*10,c*10,10,10);
	                  } else if(b.getType() == 5){
	                  	g.setColor(new Color(105, 54, 0));
	                  	g.fillRect(r*10,c*10,10,10);
	                  	g.setColor(new Color(145, 100, 51));
	                  	g.fillRect(r*10+2,c*10+2,6,6);
	                  } else if(b.getType() == 6){
	                  	g.setColor(Color.gray);
	                  	g.fillRect(r*10,c*10,10,10);
	                  }
	                }

	                
	              

	              }
	          }

	      
	          
	          g.setColor(Color.black);

	          g.drawString("Coins Collected:" + collected, 0,760);
	          g.setColor(new Color(171, 171, 171));
	          g.fillRect(game.get2X(),game.get2Y(),10,10);
	          g.setColor(new Color(201, 190, 177));

	          int[] xP = {game.get2X(), game.get2X() + 5, game.get2X() + 10};
	          int[] yP = {game.get2Y(), game.get2Y()-3, game.get2Y()};
	          g.fillPolygon(xP, yP, 3);

	          g.setColor(new Color(112, 62, 0));
	          g.fillRect(game.get1X(),game.get1Y(),10,10);

	          g.setColor(new Color(99, 237, 0));
	          int[] xP2 = {game.get1X(), game.get1X() + 5, game.get1X() + 10};
	          int[] yP2 = {game.get1Y(), game.get1Y()-3, game.get1Y()};
	          g.fillPolygon(xP2, yP2, 3);

	          int p1h = game.geth1();
	          int p2h = game.geth2();

	          g.setColor(Color.black);
	          g.fillRect(10,370,100,10);
	          g.fillRect(10,390,100,10);
	          g.drawString("Your Health:",0,370);
	          g.drawString("Opponent Health:",00,390);

	          g.drawString("Player 1 Has " + game.c1 + " Coins",120,380);
	          g.drawString("Player 2 Has " + game.c2 + " Coins",120,400);

	          g.setColor(Color.red);
	          g.fillRect(12,372,90,6);
	          g.setColor(Color.green);
	          g.fillRect(12,372,p1h*30,6);

	          g.setColor(Color.red);
	          g.fillRect(12,392,90,6);
	          g.setColor(Color.green);
	          g.fillRect(12,392,p2h*30,6);
	        }
        
	    } else {
	    	if(c1 > c2){
	    		winner = 1;
	    	} else if(c2 > c1){
	    		winner = 2;
	    	} else {
	    		winner = 0;
	    	}
	    }
       

    } 

    public void actionPerformed(ActionEvent e) {}

    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}