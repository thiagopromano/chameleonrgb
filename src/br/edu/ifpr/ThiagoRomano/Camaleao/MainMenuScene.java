package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;

public class MainMenuScene extends MenuScene implements
		IOnMenuItemClickListener {
	CamaleaotestActivity activity;

	Sprite mBackground;
	Sprite mChameleon;
	Sprite mTabua;
	Sprite mTitle;

	IMenuItem mNinjaModeButton;
	IMenuItem mArcadeModeButton;
	IMenuItem mAbout;
	IMenuItem mCredits;

	final int MENU_ARCADE = 0;
	final int MENU_NINJA = 1;
	final int MENU_ABOUT = 2;
	final int MENU_CREDITS = 3;

	public MainMenuScene() {

		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;

		mNinjaModeButton = new SpriteMenuItem(MENU_NINJA,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NINJA_ID),
				activity.getVertexBufferObjectManager());
		mNinjaModeButton.setPosition(96, 472);

		mArcadeModeButton = new SpriteMenuItem(MENU_ARCADE,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ARCADE_ID),
				activity.getVertexBufferObjectManager());
		mArcadeModeButton.setPosition(140, 380);
		
		mAbout = new SpriteMenuItem(MENU_ABOUT,
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.ABOUT_ID),
				activity.getVertexBufferObjectManager());
		mAbout.setPosition(165, 567);
		
		mCredits = new SpriteMenuItem(MENU_CREDITS,
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.CREDITS_ID),
				activity.getVertexBufferObjectManager());
		mCredits.setPosition(137, 659);

		mBackground = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MENU_BG_ID),
				activity.getVertexBufferObjectManager());
		
		mChameleon = new Sprite(277, 256,
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.CHAMELEON_ID),
				activity.getVertexBufferObjectManager());
		
		mTabua = new Sprite(64, 350,
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.TABUA_ID),
				activity.getVertexBufferObjectManager());
		
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

		setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);

	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		switch (arg1.getID()) {
		case MENU_NINJA:
			activity.setCurrentScene(new NinjaModeScene());
			return true;
		case MENU_ARCADE: {
			activity.setCurrentScene(new ArcadeModeScene());
			return true;
		}
		default:
			break;

		}
		return false;
	}

}
