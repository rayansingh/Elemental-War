import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.logging.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Location implements Comparable, Serializable{
	private int x;
	private int y;

	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	@Override

	public int compareTo(Object e){
		Location l = (Location)e;
		if(l.getY() > y){
			if(l.getX() > x){
				return -1;
			}
		} else if(l.getX() < x){
			if(l.getY() > y){
				return -1;
			} else {
				return 1;
			}
		} 
		return 0;
		
	}

	public boolean equals(Location l){
		if(l.getX() == x && l.getY() == y){
			return true;
		}

		return false;
	}
}