package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import android.opengl.GLES20;

public class PlacaNinjaScreen extends MenuScene implements
		IOnMenuItemClickListener {

	private static final int MENU_GO = 0;
	public Text mText2 = null;

	CamaleaotestActivity activity;
	public Text mText;
	private GameScene mMainScene;

	public PlacaNinjaScreen(GameScene mMainScene) {
		super(CamaleaotestActivity.getSharedInstance().mCamera);
		this.mMainScene = mMainScene;
		activity = CamaleaotestActivity.getSharedInstance();

		final Sprite mBox = new Sprite(21, 177,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TABUAPAUSA_ID),
				activity.getVertexBufferObjectManager());
		attachChild(mBox);
		mBox.setScale(.85f);
		final SpriteMenuItem goMenuItem = new SpriteMenuItem(MENU_GO,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.GO_ID),
				activity.getVertexBufferObjectManager());
		goMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
		goMenuItem.setPosition(150, 276);
		addMenuItem(goMenuItem);

		this.setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_GO:
			this.mMainScene.clearChildScene();
			return true;
		}
		return false;
	}

	@Override
	public void reset() {

	}
}
