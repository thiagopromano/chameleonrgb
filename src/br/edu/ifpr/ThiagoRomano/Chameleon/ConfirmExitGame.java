package br.edu.ifpr.ThiagoRomano.Chameleon;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;

public class ConfirmExitGame extends MenuScene implements
		IOnMenuItemClickListener {

	private static final int MENU_YES = 0;
	private static final int MENU_NO = 1;
	private MainMenuScene mMainScene;
	private CamaleaotestActivity activity;

	public ConfirmExitGame(MainMenuScene mMainScene) {
		super(CamaleaotestActivity.getSharedInstance().mCamera);
		this.mMainScene = mMainScene;
		activity = CamaleaotestActivity.getSharedInstance();

		final Sprite mBox = new Sprite(21, 201,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TABUAPAUSA_ID),
				activity.getVertexBufferObjectManager());
		attachChild(mBox);
		final Sprite mOpcoes = new Sprite(101, 221,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MENU2_ID),
				activity.getVertexBufferObjectManager());
		attachChild(mOpcoes);

		final MenuRectangleRegion ConfirmMenuRegion = new MenuRectangleRegion(
				MENU_YES, 0, 47, 148, 166,
				activity.getVertexBufferObjectManager());
		ConfirmMenuRegion.setPosition(
				ConfirmMenuRegion.getX() + mOpcoes.getX(),
				ConfirmMenuRegion.getY() + mOpcoes.getY());
		addMenuItem(ConfirmMenuRegion);

		final MenuRectangleRegion DeclineMenuRegion = new MenuRectangleRegion(
				MENU_NO, 148, 47, 148, 166,
				activity.getVertexBufferObjectManager());
		DeclineMenuRegion.setPosition(
				DeclineMenuRegion.getX() + mOpcoes.getX(),
				DeclineMenuRegion.getY() + mOpcoes.getY());
		addMenuItem(DeclineMenuRegion);

		this.setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_YES:
			/* Restart the animation. */

			activity.finish();
			return true;
		case MENU_NO:
			/* End Activity. */
			this.mMainScene.clearChildScene();
			mMainScene.toggleEscuro(false);
			return true;
		default:
			return false;
		}
	}

	@Override
	public void reset() {

	}
}
