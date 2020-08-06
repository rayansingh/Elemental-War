import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.Serializable;

public class Sounds implements Serializable{
	private Clip clip1;
	private Clip clip2;
	private Clip clip3;
	private Clip clip4;

	public void slap(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/slap.wav");
	        clip1 = AudioSystem.getClip();
	        clip1.open(AudioSystem.getAudioInputStream(url));
	        clip1.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}

	public void winGame(){
		try {
			URL url = this.getClass().getClassLoader().getResource("./sounds/winGame.wav");
			clip2 = AudioSystem.getClip();
			clip2.open(AudioSystem.getAudioInputStream(url));
			clip2.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public void womp(){
		try {
			URL url = this.getClass().getClassLoader().getResource("./sounds/womp.wav");
			clip3 = AudioSystem.getClip();
			clip3.open(AudioSystem.getAudioInputStream(url));
			clip3.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public void oof(){
		int i = (int)(Math.random()*2);
		if(i == 0){
			try {
				URL url = this.getClass().getClassLoader().getResource("./sounds/oof.wav");
				clip3 = AudioSystem.getClip();
				clip3.open(AudioSystem.getAudioInputStream(url));
				clip3.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		} else {
			try {
				URL url = this.getClass().getClassLoader().getResource("./sounds/aiee.wav");
				clip3 = AudioSystem.getClip();
				clip3.open(AudioSystem.getAudioInputStream(url));
				clip3.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
		
	}

	public void coin(){
		try {
			URL url = this.getClass().getClassLoader().getResource("./sounds/coin.wav");
			clip3 = AudioSystem.getClip();
			clip3.open(AudioSystem.getAudioInputStream(url));
			clip3.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
}
