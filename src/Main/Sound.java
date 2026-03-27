package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip[] clip = new Clip[30]; 
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BackGround_soud.WAV");
		soundURL[1] = getClass().getResource("/sound/pick_up.WAV");
		soundURL[2] = getClass().getResource("/sound/supChest_open.WAV");
		soundURL[3] = getClass().getResource("/sound/supChest_close.WAV");
		soundURL[4] = getClass().getResource("/sound/slime_jump.WAV");
		soundURL[5] = getClass().getResource("/sound/slime_fall.WAV");
		
		for(int i = 0; i < 6; i++) {
			setFile(i);
		}
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip[i] = AudioSystem.getClip();
			clip[i].open(ais);
			
			clip[i].start();
			clip[i].stop();
			clip[i].setFramePosition(0);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void play(int i) {
		if (clip[i] != null) {
			clip[i].setFramePosition(0);
			clip[i].start();
		}
	}
	
	public void stop(int i) {
		if (clip[i] != null) {
			clip[i].stop();
		}
	}
	
	public void loop(int i) {
		if (clip[i] != null) {
			clip[i].loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}