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
		time = NinjaModeScene.STARTING_TIME;

	}

	void updateTime(float time) {
		{
			mScene.endTime();
			time = 0;
		}
		this.time = time;
		String temp = Integer.toString((int) (time * 100));
		int qtdASeremAdd = 5 - temp.length();
		for (int i = 0; i < qtdASeremAdd; i++) {
			temp = "0" + temp;
		}
		
	}

	public void restart() {
		this.setScale(1);
		this.setPosition(X_INICIAL, Y_INICIAL);
	}
}
