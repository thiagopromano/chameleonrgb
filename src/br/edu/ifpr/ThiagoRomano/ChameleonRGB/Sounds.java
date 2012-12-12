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
	private Sound mArcadeAcerto;
	private Sound mNinjaAcerto;
	private Sound mNinjaComeco;
	private Sound mJogoComeco;
	private Music mMusic;

	static public Sounds getSharedInstace() {
		return instance;
	}

	Sounds(CamaleaotestActivity activity) {
		instance = this;
		this.activity = activity;
		try {
			SoundFactory.setAssetBasePath("mfx/");
			MusicFactory.setAssetBasePath("mfx/");
			mArcadeAcerto = SoundFactory.createSoundFromAsset(
					activity.getSoundManager(), activity, "marimba.mp3");
			mNinjaAcerto = SoundFactory.createSoundFromAsset(
					activity.getSoundManager(), activity, "grito.mp3");
			mNinjaComeco = SoundFactory.createSoundFromAsset(
					activity.getSoundManager(), activity, "sino.mp3");
			mJogoComeco = SoundFactory.createSoundFromAsset(
					activity.getSoundManager(), activity, "bate.mp3");
			mMusic = MusicFactory.createMusicFromAsset(
					activity.getMusicManager(), activity, "musica1.mp3");
			mMusic.setLooping(false);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}

	public void stopSounds() {
		mMusic.stop();
		mJogoComeco.stop();
	}

	public void playArcadeAcerto() {
		if (activity.mSom)
			mArcadeAcerto.play();
	}

	public void playNinjaAcerto() {
		if (activity.mSom)
			mNinjaAcerto.play();
	}

	public void playNinjaComeco() {
		if (activity.mSom)
			mNinjaComeco.play();
	}

	public void playJogoComeco() {
		if (activity.mSom)
			mJogoComeco.play();
	}

	public void playMusic() {
		if (activity.mSom)
			mMusic.play();
	}

}
