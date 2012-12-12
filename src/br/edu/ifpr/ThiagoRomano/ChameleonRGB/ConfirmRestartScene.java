package br.edu.ifpr.ThiagoRomano.ChameleonRGB;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ConfirmRestartScene extends MenuScene implements
		IOnMenuItemClickListener {

	private static final int MENU_YES = 0;
	private static final int MENU_NO = 1;
	private GameScene mMainScene;
	private CamaleaotestActivity activity;

	public ConfirmRestartScene(GameScene mMainScene) {
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

			this.mMainScene.reset();

			/* Remove the menu and reset it. */
			this.mMainScene.clearChildScene();
			this.mMainScene.restart();
			mMainScene.toggleEscuro(false);
			return true;
		case MENU_NO:
			/* End Activity. */
			mMainScene
			.setChildScene(mMainScene.mMenuScene, false, true, true);
			return true;
		default:
			return false;
		}
	}

	@Override
	public void reset() {

	}

	public class MenuRectangleRegion extends Rectangle implements IMenuItem {
		private int ID;

		public MenuRectangleRegion(int ID, float pX, float pY, float pWidth,
				float pHeight,
				VertexBufferObjectManager vertexBufferObjectManager) {
			super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
			this.ID = ID;
			this.setAlpha(0);
		}

		@Override
		public int getID() {
			return ID;
		}

		@Override
		public void onSelected() {
			// TODO Auto-generated method stub

		}

		@Override 
		public void onUnselected() {
			// TODO Auto-generated method stub

		}

	}

}
