package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Chronometer extends Sprite {

	NinjaModeScene mScene;
	CamaleaotestActivity activity;
	float time;
	Text timeText;
  
	public Chronometer(NinjaModeScene scene, float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, vertexBufferObjectManager);
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
			}
			else
			{
				mScene.endTime();
				time = 0;
			}
		}
		this.time = time;
		String temp = Integer.toString((int) (time * 100));
		int qtdASeremAdd =  5-temp.length();
		for (int i = 0; i < qtdASeremAdd; i++)
		{ 
			temp = "0" + temp;
		}
		timeText.setText(temp);
	}
}
