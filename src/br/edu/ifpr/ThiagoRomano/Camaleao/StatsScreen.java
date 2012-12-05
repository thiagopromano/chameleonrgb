package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.ease.EaseElasticOut;

import android.opengl.GLES20;

public class StatsScreen extends MenuScene implements IOnMenuItemClickListener {

	private static final int MENU_RESET = 0;
	private static final int MENU_QUIT = 1;
	CamaleaotestActivity activity;
	private NinjaModeScene mMainScene;
	private Sprite mTextoGeral;
	private Sprite mS;
	private Pontuacao mPontuacao;
	private Pontuacao mTempo;

	public StatsScreen(NinjaModeScene mMainScene) {
		super(CamaleaotestActivity.getSharedInstance().mCamera);
		this.mMainScene = mMainScene;
		activity = CamaleaotestActivity.getSharedInstance();

		final Sprite mBox = new Sprite(21, 201,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TABUAPAUSA_ID),
				activity.getVertexBufferObjectManager());
		attachChild(mBox);
		final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESET,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.OK_ID),
				activity.getVertexBufferObjectManager());
		resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
		resetMenuItem.setPosition(353, 390);

		this.mTextoGeral = new Sprite(46, 223,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TEXTOGERAL_ID),
				activity.getVertexBufferObjectManager());
		this.mS = new Sprite(353, 309,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.S_ID),
				activity.getVertexBufferObjectManager());
		mPontuacao = new Pontuacao(186, 300, 3, mMainScene,
				activity.getVertexBufferObjectManager());
		mTempo = new Pontuacao(95, 351, 3, mMainScene,
				activity.getVertexBufferObjectManager());

		addMenuItem(resetMenuItem);
		this.attachChild(mTextoGeral);
		this.attachChild(mS);
		this.attachChild(mPontuacao);
		this.attachChild(mTempo);
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
		mPontuacao.updateScore(score);
		mTempo.updateScore((int)(score * NinjaModeScene.TIME_CORRECT + NinjaModeScene.STARTING_TIME));
	}
}
