package br.edu.ifpr.ThiagoRomano.ChameleonRGB;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicManager;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.modifier.ease.EaseElasticOut;

import android.view.KeyEvent;

public class MainMenuScene extends MenuScene implements
		IOnMenuItemClickListener {
	CamaleaotestActivity activity;

	Sprite mBackground;
	Sprite mChameleon;
	Sprite mTabua;
	Sprite mTitle;
	TiledSprite mVibrate;
	TiledSprite mSound;

	IMenuItem mNinjaModeButton;
	IMenuItem mArcadeModeButton;
	IMenuItem mAbout;
	IMenuItem mCredits;
	MenuRectangleRegion mSoundMenu;
	MenuRectangleRegion mVibrateMenu;

	final int MENU_ARCADE = 0;
	final int MENU_NINJA = 1;
	final int MENU_ABOUT = 2;
	final int MENU_CREDITS = 3;
	final int MENU_VIBRATE = 4;
	final int MENU_SOUND = 5;

	private ConfirmExitGame mConfirmExitGame;
	public Sprite mBlackBehind;

	public MainMenuScene() {

		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;

		mNinjaModeButton = new SpriteMenuItem(MENU_NINJA,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NINJA_ID),
				activity.getVertexBufferObjectManager());

		mArcadeModeButton = new SpriteMenuItem(MENU_ARCADE,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ARCADE_ID),
				activity.getVertexBufferObjectManager());
		mArcadeModeButton.setPosition(76, 30);

		mAbout = new SpriteMenuItem(MENU_ABOUT,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ABOUT_ID),
				activity.getVertexBufferObjectManager());
		mAbout.setPosition(101, 217);

		mCredits = new SpriteMenuItem(MENU_CREDITS,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.CREDITS_ID),
				activity.getVertexBufferObjectManager());
		mCredits.setPosition(73, 309);

		mBackground = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MENU_BG_ID),
				activity.getVertexBufferObjectManager());

		mChameleon = new Sprite(277, 256,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.CHAMELEON_ID),
				activity.getVertexBufferObjectManager());
		mChameleon.setAlpha(0f);

		mSound = new TiledSprite(0, 0, new TiledTextureRegion(
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(
						posicoes.SOM_LIGADO_ID).getTexture(),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.SOM_LIGADO_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.SOM_DESLIGADO_ID)),
				activity.getVertexBufferObjectManager());
		updateSound();
		mSoundMenu = new MenuRectangleRegion(MENU_SOUND, 300, 0, 90, 100,
				activity.getVertexBufferObjectManager());

		addMenuItem(mSoundMenu);

		mVibrate = new TiledSprite(0, 0, new TiledTextureRegion(
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(
						posicoes.VIBRA_LIGADO_ID).getTexture(),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.VIBRA_LIGADO_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.VIBRA_DESLIGADO_ID)),
				activity.getVertexBufferObjectManager());
		updateVibrate();
		mVibrateMenu = new MenuRectangleRegion(MENU_VIBRATE, 390, 0, 100, 100,
				activity.getVertexBufferObjectManager());
		addMenuItem(mVibrateMenu);

		mTabua = new Sprite(64, 350,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TABUA_ID),
				activity.getVertexBufferObjectManager()) {
			@Override
			public void setPosition(float pX, float pY) {
				super.setPosition(pX, pY);
				mNinjaModeButton.setPosition(this.mX + 32, this.mY + 122);
				mArcadeModeButton.setPosition(this.mX + 76, this.mY + 30);
				mAbout.setPosition(this.mX + 101, this.mY + 217);
				mCredits.setPosition(this.mX + 73, this.mY + 309);
			}
		};
		mTabua.registerEntityModifier(new MoveModifier(2f, 64, 64, 800, 350,
				EaseElasticOut.getInstance()) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
				mChameleon.registerEntityModifier(new SequenceEntityModifier(
						new DelayModifier(0.0f), new FadeInModifier(2f)));
			}
		});

		mTitle = new Sprite(28, 36,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TITLE_ID),
				activity.getVertexBufferObjectManager());

		attachChild(mBackground);
		attachChild(mTabua);
		addMenuItem(mCredits);
		addMenuItem(mNinjaModeButton);
		addMenuItem(mArcadeModeButton);
		addMenuItem(mAbout);
		attachChild(mTitle);
		attachChild(mChameleon);
		attachChild(mVibrate);
		attachChild(mSound);

		setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);

		mConfirmExitGame = new ConfirmExitGame(this);

		mBlackBehind = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BLACK_BEHIND_ID),
				activity.getVertexBufferObjectManager());
		attachChild(mBlackBehind);
		mBlackBehind.setVisible(false);

		Sounds.getSharedInstace().playJogoComeco();
		// Sounds.getSharedInstace().mMusic.play();

	}

	private void updateVibrate() {
		if (activity.mVibra) {
			mVibrate.setCurrentTileIndex(0);
			mVibrate.setPosition(403, 26);
			mVibrate.setWidth(60);
			mVibrate.setHeight(56);
		} else {
			mVibrate.setCurrentTileIndex(1);
			mVibrate.setPosition(420, 29);
			mVibrate.setWidth(32);
			mVibrate.setHeight(46);
		}
	}

	private void updateSound() {
		if (activity.mSom) {
			mSound.setCurrentTileIndex(0);
			mSound.setPosition(333, 26);
			mSound.setWidth(58);
			mSound.setHeight(48);
		} else {
			mSound.setCurrentTileIndex(1);
			mSound.setPosition(328, 26);
			mSound.setWidth(50);
			mSound.setHeight(51);
			Sounds.getSharedInstace().stopSounds();
		}
	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		switch (arg1.getID()) {
		case MENU_NINJA:
			activity.setCurrentScene(new NinjaModeScene());
			return true;
		case MENU_ARCADE: {
			activity.setCurrentScene(new ArcadeLevelSelect());
			return true;
		}
		case MENU_ABOUT: {
			activity.setCurrentScene(new AboutScene());
			return true;
		}
		case MENU_CREDITS: {
			activity.setCurrentScene(new CreditsScene());
			return true;
		}

		case MENU_SOUND: {
			activity.mSom = !activity.mSom;
			updateSound();
			return true;
		}

		case MENU_VIBRATE: {
			activity.mVibra = !activity.mVibra;
			updateVibrate();
			return true;
		}
		default:
			break;

		}
		return false;
	}

	@Override
	public boolean handleKeyDown(int pKeyCode, KeyEvent pEvent) {
		if ((pKeyCode == KeyEvent.KEYCODE_MENU || pKeyCode == KeyEvent.KEYCODE_BACK)
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			if (this.hasChildScene()) {
				/* Remove the menu and reset it. */
				this.mConfirmExitGame.back();
				toggleEscuro(false);
			} else {
				/* Attach the menu. */
				this.setChildScene(this.mConfirmExitGame, false, true, true);
				toggleEscuro(true);
			}
		}
		return false;
	}

	public void toggleEscuro(boolean bool) {
		if (bool) {
			mBlackBehind.setVisible(true);

		} else {
			mBlackBehind.setVisible(false);
		}
	}

}
