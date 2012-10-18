package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import android.opengl.GLES20;

public class ConfirmExitScene extends MenuScene implements
IOnMenuItemClickListener{

	private static final int MENU_YES = 1;
	private static final int MENU_NO = 2;
	private Scene mMainScene;
	private CamaleaotestActivity activity;
	public ConfirmExitScene(Scene mMainScene) {
		super(CamaleaotestActivity.getSharedInstance().mCamera);
		this.mMainScene = mMainScene;
		activity = CamaleaotestActivity.getSharedInstance();
		
		final Text textSprite = new Text(100, 240, activity.mFont,
				"R U SURE U WANT TO EXIT????", activity.getVertexBufferObjectManager());
		attachChild(textSprite);
		final Sprite mBox = new Sprite(45, 200,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BOX_ID),
						activity.getVertexBufferObjectManager());
		attachChild(mBox);
		final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_YES,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ARCADEBUTTON_ID),
				activity.getVertexBufferObjectManager());
		resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
		resetMenuItem.setPosition(100,320);
		addMenuItem(resetMenuItem);

		final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_NO,
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
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case MENU_YES:
			/* Restart the animation. */
			activity.setCurrentScene(new MainMenuScene());
			return true;
		case MENU_NO:
			/* End Activity. */
			this.mMainScene.clearChildScene();
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
