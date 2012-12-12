package br.edu.ifpr.ThiagoRomano.ChameleonRGB;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.util.debug.Debug;

public class Sounds {
	public static Sounds instance;
	CamaleaotestActivity activity;
	public Sound mArcadeAcerto;
	public Sound mNinjaAcerto;
	public Sound mNinjaComeco;
	public Sound mJogoComeco;
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
			mArcadeAcerto = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "marimba.mp3");
			mNinjaAcerto = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "grito.mp3");
			mNinjaComeco = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sino.mp3");
			mJogoComeco = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "bate.mp3");
			mMusic = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "musica1.mp3");
			mMusic.setLooping(false);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}

}
