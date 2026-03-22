package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/BackGround_soud.WAV");
		soundURL[1] = getClass().getResource("/sound/pick_up.WAV");
		soundURL[2] = getClass().getResource("/sound/supChest_open.WAV");
		soundURL[3] = getClass().getResource("/sound/supChest_close.WAV");
		soundURL[4] = getClass().getResource("/sound/slime_jump.WAV");
		soundURL[5] = getClass().getResource("/sound/slime_fall.WAV");
	}
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			
		}
	}
	public void play() {
		
		clip.start();
		
	}
	public void stop() {
		
		clip.stop();
		
	}
	public void loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
		
	}
}
