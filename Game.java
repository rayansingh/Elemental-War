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

public class Game implements Serializable{
	HashMap<Location,Block> grid;

	Stack<Integer> p1h;
	Stack<Integer> p2h;

	int p1x = 0;
    int p1y = 0;

   	int p2x = 0;
    int p2y = 0;
    
    int c1 = 0;
    int c2 = 0;

    int winner = 0;

    private int cTotal = 0;

    private Boolean gameOver = false;

	public Game(){
		p1h = new Stack<Integer>();
		p2h = new Stack<Integer>();

		for(int i = 0; i < 4; i++){
			p1h.push(i);
			p2h.push(i);
		}
		

		grid = new HashMap<Location,Block>();

        for(int r = 0; r < 36; r++){
            for(int c = 0; c < 36; c++){

            	int rand = (int)(Math.random()*100);

            	if(r == 35 && c == 35){
            		rand = 1;
            	}

            	if(rand <= 70){
            		grid.put(new Location(r,c), new Block(0));
            	} else if(rand <= 75){
            		grid.put(new Location(r,c), new Block(5));
            	} else if(rand <= 80){
            		grid.put(new Location(r,c), new Block(6));
            	} else if(rand <= 91){
            		grid.put(new Location(r,c), new Block(1));
            	} else if(rand <= 93){
            		grid.put(new Location(r,c), new Block(2));
            	} else if(rand <= 95){
            		//wateerrrrr
            		grid.put(new Location(r,c), new Block(3));
            		fill(r,c,3);
            	} else if(rand <= 99){
            		grid.put(new Location(r,c), new Block(4));
            		fill(r,c,4);
            	}
              
            }
        }

        Location lee = new Location(-1,-1);

        cTotal = 0;
        for(Location l : grid.keySet()){
        	if(grid.get(l).getType() == 2){
        		cTotal++;
        	}
        	lee = l;
        }
        
        if(cTotal%2 == 0){
        	for(Location l : grid.keySet()){
        		if(grid.get(l).getType() != 2){
        			grid.put(l,new Block(2));
        			cTotal++;
        			break;
        		}
        	}
        }



        System.out.println(cTotal);
	}
	public void fill(int row, int col, int type){
        fillRow(row,col, type);
        fillCol(row,col, type);

        fillClearSquare();
    }

    public void changeValue(int row, int col, int type){
    	Location key = new Location(row,col);
        for(Location l : grid.keySet()){
          if(l.getX() == col && l.getY() == row){
            key = l;
          }
        }
        grid.put(key, new Block(type));
    }

    public void fillClearSquare(){

    	for(int i = 0; i < 4; i++){
    		for(int j = 0; j < 4; j++){
    			for(Location l : grid.keySet()){
    				if(l.getX() == i && l.getY() == j){
    					if(grid.get(l).getType() != 2){
    						grid.put(l,new Block(1));
    					}
    				}
    			}
    		}
    	}


    }

    public boolean checkEmpty(int row, int col){
    	Location key = new Location(row,col);
        for(Location l : grid.keySet()){
          if(l.getX() == col && l.getY() == row){
            key = l;
          }
        }
        Block b = grid.get(key);

        if(b.getType() != 0 && b.getType() != 1){
        	return true;
        } else {
        	return false;
        }
    }

    public void fillRow(int row, int col, int type){
        for(int i = col + 1; i < 36; i++){
        	int rand = (int)(Math.random()*12);
            if(rand > 7){
            	Location key;
		        for(Location l : grid.keySet()){
		          if(l.getX() == row && l.getY() == col){
		            key = l;
		            grid.put(key, new Block(type));
                	fillCol(row,i,type);
		            break;
		          } 
		        }
            } else {
                break;
            }
        }

        for(int i = col - 1; i >= 0; i--){
        	int rand = (int)(Math.random()*12);
            if(rand > 7){
            	Location key;
		        for(Location l : grid.keySet()){
		          if(l.getX() == row && l.getY() == col){
		            key = l;
		            grid.put(key, new Block(type));
                	fillCol(row,i,type);
		            break;
		          } 
		        }
                
            } else {
                break;
            }
        }
    }

    public void fillCol(int row,int col,int type){
        for(int i = row + 1; i < 36; i++){
        	int rand = (int)(Math.random()*12);
            if(rand > 7){
            	Location key;
            	for(Location l : grid.keySet()){
		          if(l.getX() == row && l.getY() == col){
		            key = l;
		            grid.put(key, new Block(type));
                	fillRow(col,i,type);
		            break;
		          } 
		        }
            } else {
                break;
            }
        }

        for(int i = row - 1; i >= 0; i--){
        	int rand = (int)(Math.random()*12);
            if(rand > 7){
            	Location key;
            	for(Location l : grid.keySet()){
		          if(l.getX() == row && l.getY() == col){
		            key = l;
		            grid.put(key, new Block(type));
                	fillRow(col,i,type);
		            break;
		          } 
		        }
            } else {
                break;
            }
        }

    }

	public HashMap<Location,Block> getGrid(){
		return grid;
	}

	public void m1U(){

		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p1x/10 && l.getY() == p1y/10 - 1){
            key = l;
          } 
        }


        if(grid.get(key).getType() <= 3){
        	p1y-=10;
        } else if(grid.get(key).getType() == 4){
			p1y-=10; 
			p1h.pop();
			if(p1h.size() <= 0){
				gameOver = true;
				c1 = -1;
				winner = 2;
			}
			p1x = 0;
			p1y = 0;
		}


		
	}

	public void m1D(){

		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p1x/10 && l.getY() == p1y/10 + 1){
            key = l;
          } 
        }


        if(grid.get(key).getType() <= 3){
			p1y+=10;
		} else if(grid.get(key).getType() == 4){
			p1y+=10; 
			p1h.pop();
			if(p1h.size() <= 0){
				gameOver = true;
				c1 = -1;
				winner = 2;
			}
			p1x = 0;
			p1y = 0;
		}
	}

	public void m1L(){

		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p1x/10 - 1 && l.getY() == (p1y/10)){
            key = l;
          } 
        }


        if(grid.get(key).getType() <= 3){
			p1x-=10;
		} else if(grid.get(key).getType() == 4){
			p1x-=10; 
			p1h.pop();
			if(p1h.size() <= 0){
				gameOver = true;
				c1 = -1;
				winner = 2;
			}
			p1x = 0;
			p1y = 0;
		}
	}

	public void m1R(){
		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p1x/10 + 1 && l.getY() == p1y/10){
            key = l;
          } 
        }


        if(grid.get(key).getType() <= 3){
			p1x+=10;
		} else if(grid.get(key).getType() == 4){
			p1x+=10; 
			p1h.pop();
			if(p1h.size() <= 0){
				gameOver = true;
				c1 = -1;
				winner = 2;
			}
			p1x = 0;
			p1y = 0;
		}
	}

	public void m2U(){

		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p2x/10 && l.getY() == p2y/10 - 1){
            key = l;
          } 
        }


        if(grid.get(key).getType() < 3 || grid.get(key).getType() == 4){
        	p2y-=10;
        } else if(grid.get(key).getType() == 3){
			p2y-=10; 
			p2h.pop();
			if(p2h.size() <= 0){
				gameOver = true;
				c2 = -1;
				winner = 1;
			}
			p2x = 0;
			p2y = 0;
		}


		
	}

	public void m2D(){

		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p2x/10 && l.getY() == p2y/10 + 1){
            key = l;
          } 
        }


        if(grid.get(key).getType() < 3 || grid.get(key).getType() == 4){
			p2y+=10;
		} else if(grid.get(key).getType() == 3){
			p2y+=10; 
			p2h.pop();
			if(p2h.size() <= 0){
				gameOver = true;
				c2 = -1;
				winner = 1;
			}
			p2x = 0;
			p2y = 0;
		}
	}

	public void m2L(){

		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p2x/10 - 1 && l.getY() == (p2y/10)){
            key = l;
          } 
        }


        if(grid.get(key).getType() < 3 || grid.get(key).getType() == 4){
			p2x-=10;
		} else if(grid.get(key).getType() == 3){
			p2x-=10; 
			p2h.pop();
			if(p2h.size() <= 0){
				gameOver = true;
				c2 = -1;
				winner = 1;
			}
			p2x = 0;
			p2y = 0;
		}
	}

	public void m2R(){
		Location key = new Location(-1,-1);
    	for(Location l : grid.keySet()){
          if(l.getX() == p2x/10 + 1 && l.getY() == (p2y/10)){
            key = l;
          } 
        }


        if(grid.get(key).getType() < 3 || grid.get(key).getType() == 4){
			p2x+=10;
		} else if(grid.get(key).getType() == 3){
			p2x+=10;
			p2h.pop();
			if(p2h.size() <= 0){
				gameOver = true;
				c2 = -1;
				winner = 1;
			}
			p2x = 0;
			p2y = 0;
		}
	}

	public int geth1(){

		if(p1h.size() == 0){
			gameOver = true;
			c1 = -5;
		}
		return p1h.peek();
	}

	public int geth2(){
		if(p2h.size() == 0){
			gameOver = true;
			c2 = -5;
		}
		return p2h.peek();
	}

	public int get1X(){
		return p1x;
	}

	public int get1Y(){
		return p1y;
	}

	public int get2X(){
		return p2x;
	}

	public int get2Y(){
		return p2y;
	}

	public void inc1(){
		c1++;

		if(c2 + c1 == cTotal){
			gameOver = true;
		}
	}

	public void inc2(){
		c2++;

		if(c2 + c1 == cTotal){
			gameOver = true;
		}
	}

	public boolean isGameOver(){
		return gameOver;
	}
	public int getWinner(){
		if(p1h.size()<=0){
			winner = 2;
			gameOver = true;
		} 

		if(p2h.size()<0){
			winner = 1;
			gameOver = true;
		}

		if(winner == 1 || winner == 2){
			return winner;
		}
		

		if(c1 > c2){
			winner = 1;
			return 1;
		} else if(c2 > c1){
			winner = 2;
			return 2;
		} else {
			winner = 0;
			return 0;
		}
	}

	public boolean checkCoins(){
		if(c1 + c2 < cTotal){
			return true;
		} else {
			gameOver = true;
			return false;
		}
	}

	public void clear(Location l){
		grid.remove(l);
		grid.put(l,new Block(0));
	}
}