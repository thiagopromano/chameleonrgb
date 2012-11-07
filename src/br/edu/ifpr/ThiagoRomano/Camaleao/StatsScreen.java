package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.ease.EaseElasticOut;

import android.opengl.GLES20;

public class StatsScreen extends MenuScene implements IOnMenuItemClickListener {

	private static final int MENU_RESET = 0;
	private static final int MENU_QUIT = 1;
	public Text mText2 = null;

	CamaleaotestActivity activity;
	public Text mText;
	private GameScene mMainScene;

	public StatsScreen(GameScene mMainScene) {
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
						.get(posicoes.GO_ID),
				activity.getVertexBufferObjectManager());
		resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
		resetMenuItem.setPosition(100, 320);
		addMenuItem(resetMenuItem);


		mText = new Text(0, 230, activity.mFont, " ", 60,
				activity.getVertexBufferObjectManager());
		mText2 = new Text(10, 260, activity.mFont, " ", 30,
				activity.getVertexBufferObjectManager());
		this.attachChild(mText);
		this.attachChild(mText2);
		mText.setScale(0.65f);
		mText2.setScale(0.65f);
		this.setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);

		this.registerEntityModifier(new MoveXModifier(3, -500, 0,
				EaseElasticOut.getInstance()));
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_RESET:
			/* Restart the animation. */
			this.mMainScene.reset();

			/* Remove the menu and reset it. */
			this.mMainScene.clearChildScene();
			this.mMainScene.restart();
			return true;
		case MENU_QUIT:
			/* End Activity. */
			mMainScene
					.setChildScene(mMainScene.mConfirmExit, false, true, true);
			return true;
		default:
			return false;
		}
	}

	@Override
	public void reset() {

	}

	public void updateText(int score) {
		mText.setText("You have correctly guessed " + Integer.toString(score)
				+ " colors");
		mText2.setText("And survived "
				+ (int) (score * NinjaModeScene.TIME_CORRECT + NinjaModeScene.STARTING_TIME)
				+ " seconds");

	}
}
