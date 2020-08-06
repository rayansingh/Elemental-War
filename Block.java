import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.logging.*;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.*;


public class Block implements Serializable{
	int x;
	int y;
	int type;

	public Block(int t){
		type = t;
	}

	public int getType(){
		if(x == -1 && y == -1){
			return 0;
		}
		return type;
	}
	public String toString(){
		return type + "";
	}
}