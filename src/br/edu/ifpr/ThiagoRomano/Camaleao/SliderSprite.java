package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Color;

public class SliderSprite extends Sprite {

	public int mStep = 1;
	public static final int MIN_X = 100;
	public static final int MAX_X = 372;

	// Para aumentar a area do touch, tava dificil selecionar
	private static final int OFFSET_X = 400;
	private static final int OFFSET_Y = 30;
	GameScene mScene;
	private int id;
	int value = 255;
	Text textValue;
	CamaleaotestActivity activity;

	public SliderSprite(int iStep, int id, GameScene scene, float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		activity = CamaleaotestActivity.getSharedInstance();
		this.mScene = scene;
		this.id = id;
		this.mStep = iStep;
		textValue = new Text(-10, -30, activity.mFont, "255",
				activity.getVertexBufferObjectManager());
		// textValue.
		/*
		 * switch (id) { case 0: { textValue.setColor(Color.RED); break; } case
		 * 1: { textValue.setColor(Color.GREEN); break; } case 2: {
		 * textValue.setColor(Color.BLUE); break; } }
		 */
		textValue.setColor(1f, 1f, 1f);

		this.attachChild(textValue);

	}

	public SliderSprite(int id, GameScene scene, float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		this(1, id, scene, pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}

	@Override
	public boolean contains(float pX, float pY) {
		if (pX > mX - OFFSET_X && pX < mX + mWidth + OFFSET_X
				&& pY > mY - OFFSET_Y && pY < mY + mHeight + OFFSET_Y)
			return true;
		return false;
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (mScene.iniciando)
			return true;
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
				this.getY());
		updateValue(true);
		return true;
	}

	void updateValue(boolean pVerify) {
		if (this.mX < MIN_X)
			this.mX = MIN_X;
		if (this.mX > MAX_X)
			this.mX = MAX_X;
		this.mX = Math.round((this.mX - MIN_X) / mStep) * mStep + MIN_X;
		float fValor = (this.mX - MIN_X) / (MAX_X - MIN_X);
		value = (int) (fValor * 255);
		textValue.setText(Integer.toString(value));
		textValue.setPosition(this.getWidth() / 2 - textValue.getWidth() / 2,
				-30);
		if (pVerify)
			mScene.ChangeColors(fValor, id);
		else {
			int iNewColor = value;
			int mask = (0xff00ffff >> (8 * id));
			iNewColor <<= 16;
			iNewColor >>= id * 8;
		
			mScene.colors = (mScene.colors & mask);
			mScene.colors |= iNewColor;
			mScene.mChamp.setColor(Color.red(mScene.colors) / 256f,
					Color.green(mScene.colors) / 256f, Color.blue(mScene.colors) / 256f);
		}
	}

}
