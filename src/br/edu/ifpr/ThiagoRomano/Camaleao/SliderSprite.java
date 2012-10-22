package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SliderSprite extends Sprite {

	public static final int STEP = 1;
	private static final int MIN_X = 100;
	private static final int MAX_X = 372;

	// Para aumentar a area do touch, tava dificil selecionar
	private static final int OFFSET_X = 30;
	private static final int OFFSET_Y = 30;
	NinjaModeScene mScene;
	private int id;
	int value = 255;
	Text textValue;

	public SliderSprite(int id, NinjaModeScene scene, float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mScene = scene;
		this.id = id;
		textValue = new Text(-10, -30, mScene.activity.mFont, "255",
				mScene.activity.getVertexBufferObjectManager());
		// textValue.
		/*
		 * switch (id) { case 0: { textValue.setColor(Color.RED); break; } case
		 * 1: { textValue.setColor(Color.GREEN); break; } case 2: {
		 * textValue.setColor(Color.BLUE); break; } }
		 */
		textValue.setColor(1f, 1f, 1f);

		this.attachChild(textValue);

	}

	@Override
	public boolean contains(float pX, float pY) {
		if (pX > mX - OFFSET_X && pX < mX + mWidth + OFFSET_X
				&& pY > mY - OFFSET_X && pY < mY + mHeight + OFFSET_Y)
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
		if (this.mX < MIN_X)
			this.mX = MIN_X;
		if (this.mX > MAX_X)
			this.mX = MAX_X;
		this.mX = Math.round((this.mX - MIN_X) / STEP) * STEP + MIN_X;
		float fValor = (this.mX - MIN_X) / (MAX_X - MIN_X);
		value = (int) (fValor * 255);
		textValue.setText(Integer.toString(value));
		textValue.setPosition(this.getWidth() / 2 - textValue.getWidth() / 2,
				-30);
		mScene.ChangeColors(fValor, id);
		return true;
	}

}
