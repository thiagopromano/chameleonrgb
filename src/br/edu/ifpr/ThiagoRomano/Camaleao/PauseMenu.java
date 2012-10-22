package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import android.opengl.GLES20;

public class PauseMenu extends MenuScene implements
IOnMenuItemClickListener {

	private static final int MENU_RESET = 0;
	private static final int MENU_QUIT = 1;

	CamaleaotestActivity activity;
	private Text mText;
	private NinjaModeScene mMainScene;

	public PauseMenu(NinjaModeScene mMainScene) {
		super(CamaleaotestActivity.getSharedInstance().mCamera);
		this.mMainScene = mMainScene;
		activity = CamaleaotestActivity.getSharedInstance();
		
		
		final Sprite mBox = new Sprite(45, 200,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BOX_ID),
						activity.getVertexBufferObjectManager());
		attachChild(mBox);
		final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESET,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ARCADEBUTTON_ID),
				activity.getVertexBufferObjectManager());
		resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
		resetMenuItem.setPosition(100,320);
		addMenuItem(resetMenuItem);

		final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT,
				activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.TIMEATTACKBUTTON_ID),
				activity.getVertexBufferObjectManager());
		quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
		quitMenuItem.setPosition(300, 320);
		addMenuItem(quitMenuItem);


		

		this.setBackgroundEnabled(false);

		setOnMenuItemClickListener(this);

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case MENU_RESET:
			/* Restart the animation. */
			this.mMainScene.reset();

			/* Remove the menu and reset it. */
			this.mMainScene.clearChildScene();
			this.mMainScene.restart();
			return true;
		case MENU_QUIT:
			/* End Activity. */
			mMainScene.setChildScene(mMainScene.mConfirmExit, false, true, true); 
			return true;
		default:
			return false;
	}
	}
	@Override
	public void reset()
	{
		
	}
}
