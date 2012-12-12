package br.edu.ifpr.ThiagoRomano.ChameleonRGB;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;

public class AboutScene extends MenuScene implements IOnMenuItemClickListener {

	Sprite mBackground;
	Sprite mAdendo;
	Sprite mNylon;
	Sprite mTexto;

	CamaleaotestActivity activity;

	IMenuItem mBack;

	public AboutScene() {
		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;
		mBackground = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.FUNDO_ID),
				activity.getVertexBufferObjectManager());

		mNylon = new Sprite(28, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.NYLON_ID),
				activity.getVertexBufferObjectManager());
		mNylon.setColor(0.94f, 0.82f, 0.627f);
		mAdendo = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.ADD_ID),
				activity.getVertexBufferObjectManager());
		
		mTexto = new Sprite(68, 47,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
				.get(posicoes2.ABOUT_TEXT_ID),
				activity.getVertexBufferObjectManager());
		
		mBack = new SpriteMenuItem(0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
				.get(posicoes2.BACK_ID),
		activity.getVertexBufferObjectManager());
		mBack.setPosition(375, 678);
		
		this.attachChild(mBackground);
		this.attachChild(mNylon);
		this.attachChild(mAdendo);
		this.attachChild(mTexto);
		this.addMenuItem(mBack);
		
		setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		activity.setCurrentScene(new MainMenuScene());
		return true;
	}
}
