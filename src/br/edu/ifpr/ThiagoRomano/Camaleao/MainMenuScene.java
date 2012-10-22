package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;

public class MainMenuScene extends MenuScene implements
		IOnMenuItemClickListener {
	CamaleaotestActivity activity;
	final int MENU_ARCADE = 0;
	final int MENU_TIMEATTACK = 1;

	public MainMenuScene() {

		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		IMenuItem timeAttackButton = new SpriteMenuItem(MENU_TIMEATTACK, 
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.TIMEATTACKBUTTON_ID),
				activity.getVertexBufferObjectManager());
		timeAttackButton.setPosition(100, 320);
		
		IMenuItem ArcadeButton = new SpriteMenuItem(MENU_ARCADE, 
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.ARCADEBUTTON_ID),
				activity.getVertexBufferObjectManager());
		ArcadeButton.setPosition(300, 320);
		
		
		addMenuItem(timeAttackButton);
		addMenuItem(ArcadeButton);
		
		setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);

	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		switch (arg1.getID()) {
		case MENU_TIMEATTACK:
			activity.setCurrentScene(new NinjaModeScene());
			return true;
		default:
			break;
		
		}
		return false;
	}
	

}
