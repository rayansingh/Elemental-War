import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music{
	private Clip clip1;
	private Clip clip2;
	private Clip clip3;
	private Clip clip4;
	private Clip clip5;
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

	public void die(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/death.wav");
	        clip2 = AudioSystem.getClip();
	        clip2.open(AudioSystem.getAudioInputStream(url));
	        clip2.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}

	public void tada(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/tada.wav");
	        clip3 = AudioSystem.getClip();
	        clip3.open(AudioSystem.getAudioInputStream(url));
	        clip3.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}



	public void stopMusic(){
		System.out.print("Stopped");

		if(clip1 != null){
			clip1.stop();
		}
		if(clip2 != null){
			clip2.stop();
		}
		if(clip3 != null){
			clip3.stop();
		}

		if(clip4 != null){
			clip4.stop();
		}

		if(clip5 != null){
			clip5.stop();
		}

	}
}
