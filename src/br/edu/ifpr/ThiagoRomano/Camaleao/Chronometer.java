package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Chronometer extends Sprite {

	static final int X_INICIAL = 100;
	static final int Y_INICIAL = 100;

	NinjaModeScene mScene;
	CamaleaotestActivity activity;
	float time;
	Text timeText;

	public Chronometer(NinjaModeScene scene, ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(X_INICIAL, Y_INICIAL, pTextureRegion, vertexBufferObjectManager);
		activity = CamaleaotestActivity.getSharedInstance();
		this.mScene = scene;
		time = 5f;
		timeText = new Text(0, -10, activity.mChronometerFont,
				Integer.toString((int) (time * 100)), 7,
				activity.getVertexBufferObjectManager());
		timeText.setAutoWrapWidth(200f);
		this.attachChild(timeText);
	}

	void updateTime(float time) {
		if (time <= 0) {
			if (mScene.iniciando == true) {
				time = mScene.STARTING_TIME;
				mScene.remainingTime = mScene.STARTING_TIME;
				mScene.iniciando = false;
				this.registerEntityModifier(new ParallelEntityModifier(
						new ScaleModifier(1.5f, 1f, 0.5f), new MoveModifier(
								1.5f, this.getX(), 0, this.getY(), 0)));
			} else {
				mScene.endTime();
				time = 0;
			}
		}
		this.time = time;
		String temp = Integer.toString((int) (time * 100));
		int qtdASeremAdd = 5 - temp.length();
		for (int i = 0; i < qtdASeremAdd; i++) {
			temp = "0" + temp;
		}
		timeText.setText(temp);
	}

	public void restart() {
		this.setScale(1);
		this.setPosition(X_INICIAL, Y_INICIAL);
	}
}
