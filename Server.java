import javax.swing.JFrame;
import java.io.*;
 
public class Server {
 
    public static void main(String args[]) throws IOException {
 
        JFrame frame = new JFrame("Player 1 (Immune to Water)");
 
        ServerScreen sc = new ServerScreen();
        frame.add(sc);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
 
        sc.poll();
    }
}