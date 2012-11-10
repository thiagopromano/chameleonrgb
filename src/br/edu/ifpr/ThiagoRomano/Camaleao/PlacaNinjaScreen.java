package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.batch.SpriteGroup;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;

public class PlacaNinjaScreen extends SpriteGroup {

	public Text mText2 = null;

	CamaleaotestActivity activity;
	public Text mText;
	public NinjaModeScene mMainScene;
	private Sprite mBox;
	public Sprite goMenuItem;

	public PlacaNinjaScreen(ITexture texture, NinjaModeScene mMainCena) {
		super(texture, 5, CamaleaotestActivity.getSharedInstance()
				.getVertexBufferObjectManager());
		this.mMainScene = mMainCena;
		activity = CamaleaotestActivity.getSharedInstance();
		mBox = new Sprite(21, 177,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TABUAPAUSA_ID),
				activity.getVertexBufferObjectManager());
		mBox.setScale(0.85f);
		attachChild(mBox);
		goMenuItem = new Sprite(150, 276,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.GO_ID),
				activity.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					mMainScene.mBlackBehind.setVisible(false);
					ficaInvisivel();
					mMainScene.iniciando = false;
					mMainScene.mChronometer.anima();
				}
				return true;
			}
		};

		attachChild(goMenuItem);
		mMainCena.iniciando = true;

	}

	void ficaInvisivel() {
		this.setVisible(false);
		mMainScene.unregisterTouchArea(goMenuItem);
	}

}
