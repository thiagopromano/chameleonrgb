package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;

public class ArcadeCongratsScene extends MenuScene implements IOnMenuItemClickListener {

	Sprite mBackground;
	Sprite mEscuro;
	Sprite mTexto;
	Sprite mCham;
	Sprite mChamSombra;
	Sprite mLingua;

	CamaleaotestActivity activity;

	IMenuItem mBack;

	public ArcadeCongratsScene() {
		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;
		mBackground = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.FUNDO_ID),
				activity.getVertexBufferObjectManager());
		mEscuro = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.BLACK_BEHIND_ID),
				activity.getVertexBufferObjectManager());

		mTexto = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.CONGRATS_TEXTO_ID),
				activity.getVertexBufferObjectManager());
		
		mCham = new Sprite(36, 223,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
				.get(posicoes2.NINJA_BASE_ID),
				activity.getVertexBufferObjectManager());
		mChamSombra = new Sprite(36, 223,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
				.get(posicoes2.NINJA_SHADOW_ID),
				activity.getVertexBufferObjectManager());
		mLingua = new Sprite(202, 309,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
				.get(posicoes2.NINJA_LINGUA_ID),
				activity.getVertexBufferObjectManager());

		mBack = new SpriteMenuItem(0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.BACK_ID),
				activity.getVertexBufferObjectManager());
		mBack.setPosition(375, 678);

		this.attachChild(mBackground);
		this.attachChild(mEscuro);
		this.attachChild(mTexto);
		this.attachChild(mCham);
		this.attachChild(mChamSombra);
		this.attachChild(mLingua);
		this.addMenuItem(mBack);
		mBack.setScale(0.6f);

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
