package br.edu.ifpr.ThiagoRomano.Chameleon;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class PauseMenu extends MenuScene implements IOnMenuItemClickListener {

	private static final int MENU_RESET = 0;
	private static final int MENU_QUIT = 1;
	private static final int MENU_RESUME = 2;

	CamaleaotestActivity activity;
	private GameScene mMainScene;

	public PauseMenu(GameScene mMainScene) {
		super(CamaleaotestActivity.getSharedInstance().mCamera);
		this.mMainScene = mMainScene;
		activity = CamaleaotestActivity.getSharedInstance();

		final Sprite mBox = new Sprite(21, 201,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TABUAPAUSA_ID),
				activity.getVertexBufferObjectManager());
		attachChild(mBox);

		final Sprite mOpcoes = new Sprite(32, 219,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MENU1_ID),
				activity.getVertexBufferObjectManager());
		this.attachChild(mOpcoes);

		final MenuRectangleRegion mRestartRegion = new MenuRectangleRegion(
				MENU_RESET, 0, 60, 140, 156,
				activity.getVertexBufferObjectManager());
		mRestartRegion.setPosition(mRestartRegion.getX() + mOpcoes.getX(),
				mRestartRegion.getY() + mOpcoes.getY());
		this.addMenuItem(mRestartRegion);

		final MenuRectangleRegion mMenuRegion = new MenuRectangleRegion(
				MENU_QUIT, 140, 60, 150, 156,
				activity.getVertexBufferObjectManager());
		mMenuRegion.setPosition(mMenuRegion.getX() + mOpcoes.getX(),
				mMenuRegion.getY() + mOpcoes.getY());
		this.addMenuItem(mMenuRegion);

		final MenuRectangleRegion mResumeRegion = new MenuRectangleRegion(
				MENU_RESUME, 290, 60, 110, 156,
				activity.getVertexBufferObjectManager());
		mResumeRegion.setPosition(mResumeRegion.getX() + mOpcoes.getX(),
				mResumeRegion.getY() + mOpcoes.getY());
		this.addMenuItem(mResumeRegion);

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
		case MENU_RESET:
			mMainScene.setChildScene(mMainScene.mConfirmRestart, false, true,
					true);

			return true;
		case MENU_QUIT:
			/* End Activity. */
			Quit();
			return true;
		case MENU_RESUME: {
			mMainScene.clearChildScenes();
			mMainScene.toggleEscuro(false);
			return true;
		}
		default:
			return false;
		}
	}

	public void Quit() {
		mMainScene.setChildScene(mMainScene.mConfirmExit, false, true, true);
	}

	@Override
	public void reset() {

	}
}
