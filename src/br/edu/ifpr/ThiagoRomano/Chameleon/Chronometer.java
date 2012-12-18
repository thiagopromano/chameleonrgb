package br.edu.ifpr.ThiagoRomano.Chameleon;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.sprite.batch.SpriteGroup;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Chronometer extends SpriteGroup {

	static final int X_INICIAL = 83;
	static final int Y_INICIAL = 221;

	NinjaModeScene mScene;
	CamaleaotestActivity activity;
	float time;
	Text timeText;
	TiledSprite mNumeros[] = new TiledSprite[6];

	public Chronometer(NinjaModeScene scene,
			VertexBufferObjectManager mVertexBufferObjectManager) {
		super(
				X_INICIAL,
				Y_INICIAL,
				CamaleaotestActivity.getSharedInstance().mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ABOUT_ID).getTexture(), 8,
				mVertexBufferObjectManager);
		activity = CamaleaotestActivity.getSharedInstance();
		this.mScene = scene;
		time = NinjaModeScene.STARTING_TIME;

		TiledTextureRegion regiao = new TiledTextureRegion(
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(
						posicoes.NUMERO0_ID).getTexture(),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO0_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO1_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO2_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO3_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO4_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO5_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO6_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO7_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO8_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NUMERO9_ID));
		mNumeros[0] = new TiledSprite(0, 0, regiao, mVertexBufferObjectManager);
		this.attachChild(mNumeros[0]);
		mNumeros[1] = new TiledSprite(46, 0, regiao, mVertexBufferObjectManager);
		this.attachChild(mNumeros[1]);
		mNumeros[2] = new TiledSprite(114, 0, regiao,
				mVertexBufferObjectManager);
		this.attachChild(mNumeros[2]);
		mNumeros[3] = new TiledSprite(160, 0, regiao,
				mVertexBufferObjectManager);
		this.attachChild(mNumeros[3]);
		mNumeros[4] = new TiledSprite(228, 0, regiao,
				mVertexBufferObjectManager);
		this.attachChild(mNumeros[4]);
		mNumeros[5] = new TiledSprite(274, 0, regiao,
				mVertexBufferObjectManager);
		this.attachChild(mNumeros[5]);

		this.attachChild(new Sprite(92, 19,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.DOISPONTOS_ID), activity
						.getVertexBufferObjectManager()));
		this.attachChild(new Sprite(206, 19,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.DOISPONTOS_ID), activity
						.getVertexBufferObjectManager()));

	}

	void updateTime(float time) {
		if (time <= 0) {
			mScene.endTime();
			time = 0;
		}
		this.time = time;
		int temp = ((int) (time * 100));

		for (int i = 0; i < mNumeros.length; i++) {
			mNumeros[mNumeros.length - 1 - i]
					.setCurrentTileIndex((int) ((temp / Math.pow(10, i)) % 10));
		}

	}
	ParallelEntityModifier mAnimacao =  new ParallelEntityModifier(new MoveModifier(2, X_INICIAL, 0, Y_INICIAL, 0), new ScaleModifier(2, 1f, 0.8f));
	
	public void restart() {
		this.setScale(1);
		this.setPosition(X_INICIAL, Y_INICIAL);
		this.unregisterEntityModifier(mAnimacao);
	}
	
	public void anima() {
		mAnimacao.reset();
		this.registerEntityModifier(mAnimacao);
	}
}
