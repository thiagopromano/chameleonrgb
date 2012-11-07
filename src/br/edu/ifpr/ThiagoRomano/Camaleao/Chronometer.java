package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.sprite.batch.SpriteGroup;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Chronometer extends SpriteGroup {

	static final int X_INICIAL = 100;
	static final int Y_INICIAL = 100;

	NinjaModeScene mScene;
	CamaleaotestActivity activity;
	float time;
	Text timeText;
	TiledSprite mNumeros[] = new TiledSprite[8];

	public Chronometer(NinjaModeScene scene,
			VertexBufferObjectManager mVertexBufferObjectManager) {
		super(X_INICIAL, Y_INICIAL, null, 8, mVertexBufferObjectManager);
		activity = CamaleaotestActivity.getSharedInstance();
		this.mScene = scene;
		time = NinjaModeScene.STARTING_TIME;
		
		TiledTextureRegion regiao = new TiledTextureRegion(activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO0_ID).getTexture(),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO0_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO1_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO2_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO3_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO4_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO5_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO6_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO7_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO8_ID),
				activity.mSpritesheetTexturePackTextureRegionLibrary.get(posicoes.NUMERO9_ID));
		mNumeros[0] = new TiledSprite(0, 0, regiao, mVertexBufferObjectManager);
		mNumeros[1] = new TiledSprite(40, 0, regiao, mVertexBufferObjectManager);
		mNumeros[2] = new TiledSprite(80, 0, regiao, mVertexBufferObjectManager);
		mNumeros[3] = new TiledSprite(120, 0, regiao, mVertexBufferObjectManager);
		mNumeros[4] = new TiledSprite(160, 0, regiao, mVertexBufferObjectManager);
		mNumeros[5] = new TiledSprite(200, 0, regiao, mVertexBufferObjectManager);
		mNumeros[6] = new TiledSprite(240, 0, regiao, mVertexBufferObjectManager);
		mNumeros[7] = new TiledSprite(280, 0, regiao, mVertexBufferObjectManager);

	}

	void updateTime(float time) {
		if (time <=0)
		{
			mScene.endTime();
			time = 0;
		}
		this.time = time;
		int temp = ((int) (time * 100));
		
		for (int i = 0; i < mNumeros.length; i++) {
			mNumeros[i].setCurrentTileIndex((int) ((temp/Math.pow(10,i))%10));
		}

	}

	public void restart() {
		this.setScale(1);
		this.setPosition(X_INICIAL, Y_INICIAL);
	}
}
