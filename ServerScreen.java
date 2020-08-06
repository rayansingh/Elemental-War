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

public class ServerScreen extends Player {

    private BufferedImage img;

    
    public void paintComponent(Graphics g) {

      

      super.drawMe(g,1);

      if(game.isGameOver()){

        winner = game.getWinner();
        if(winner == 1){
          try {
            img = ImageIO.read(new File("1W.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          s.winGame();
        } else if(winner == 2){
          try {
            img = ImageIO.read(new File("1L.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          s.womp();
        } else if(winner == 0){
          try {
            img = ImageIO.read(new File("0.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          s.oof();
        }
        
        g.drawImage(img,0,0,360,420,null);
      }
    }

    public void poll() throws IOException {
        int port = 5;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        out = new ObjectOutputStream(clientSocket.getOutputStream());
        
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

        out.reset();
        out.writeObject((Game)game);

        while(true){
          repaint();
          try{
              game = (Game)in.readObject();
          } catch(ClassNotFoundException e){
              
          }
          gameOver = game.isGameOver();
        }
        
    } 

    public void keyPressed(KeyEvent e) {
      System.out.println(e.getKeyCode());

      if(e.getKeyCode() == 87){
        game.m1U();
      }

      if(e.getKeyCode() == 65){
        game.m1L();
      }

      if(e.getKeyCode() == 83){
        game.m1D();
      }

      if(e.getKeyCode() == 68){
        game.m1R();
      }

      try{
          out.reset(); 
          out.writeObject((Game)game);
       } catch(IOException eededed){
        
       }

      HashMap<Location,Block> grid = game.getGrid();
      int x = game.get1X();
      int y = game.get1Y();
      Location key = new Location(x/10,y/10);
      for(Location l : grid.keySet()){
        if(l.getX() == (x/10) && l.getY() == (y/10)){
          key = l;
        }
      }
      Block b = grid.get(key);

      if(b.getType() == 2){
        game.inc1();
        grid.put(key,new Block(0));
        try{
          out.reset(); 
          out.writeObject((Game)game);
        } catch(IOException eedede){

        }
      }
      repaint();
    }
}