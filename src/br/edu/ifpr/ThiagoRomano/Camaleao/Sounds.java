package br.edu.ifpr.ThiagoRomano.Camaleao;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.util.debug.Debug;

public class Sounds {
	public static Sounds instance;
	CamaleaotestActivity activity;
	public Sound mUhul;
	public Sound mYay;
	public Music mMusic;
	static public Sounds getSharedInstace()
	{
		return instance;
	}
	Sounds(CamaleaotestActivity activity)
	{
		instance = this;
		this.activity = activity;
		try {
			SoundFactory.setAssetBasePath("mfx/");
			MusicFactory.setAssetBasePath("mfx/");
			mUhul = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Uhul.ogg");
			mYay = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Yay.ogg");
			mMusic = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "musica1.mp3");
			mMusic.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}

}
