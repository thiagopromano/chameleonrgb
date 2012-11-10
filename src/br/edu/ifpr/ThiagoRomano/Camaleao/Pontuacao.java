package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.sprite.batch.SpriteGroup;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Pontuacao extends SpriteGroup {

	static final int X_INICIAL = 300;
	static final int Y_INICIAL = 0;

	NinjaModeScene mScene;
	CamaleaotestActivity activity;
	float time;
	Text timeText;
	TiledSprite mNumeros[] = new TiledSprite[3];

	public Pontuacao(NinjaModeScene scene,
			VertexBufferObjectManager mVertexBufferObjectManager) {
		super(
				X_INICIAL,
				Y_INICIAL,
				CamaleaotestActivity.getSharedInstance().mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.ABOUT_ID).getTexture(), 5,
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
		mNumeros[2] = new TiledSprite(92, 0, regiao, mVertexBufferObjectManager);
		this.attachChild(mNumeros[2]);
	}
	
	public void updateScore(int score)
	{
		
		for (int i = 0; i < mNumeros.length; i++) {
			mNumeros[mNumeros.length - 1 - i]
					.setCurrentTileIndex((int) ((score / Math.pow(10, i)) % 10));
		}
	}
	
}
