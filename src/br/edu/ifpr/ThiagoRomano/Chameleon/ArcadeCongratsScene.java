package br.edu.ifpr.ThiagoRomano.Chameleon;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ColorModifier;
import org.andengine.entity.modifier.EntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException;
import org.andengine.util.modifier.ease.EaseQuadInOut;

public class ArcadeCongratsScene extends MenuScene implements
		IOnMenuItemClickListener {

	Sprite mBackground;
	Sprite mEscuro;
	Sprite mTexto;
	Sprite mCham;
	Sprite mChamSombra;
	Sprite mLingua;

	CamaleaotestActivity activity;
	float[] valores = new float[] { 1f, 0.345f, 0.2745f,

	82 / 255f, 0 / 255f, 7 / 255f,

	91 / 255f, 185 / 255f, 200 / 255f,

	216 / 255f, 255 / 255f, 115 / 255f,

	0 / 255f, 94 / 255f, 64 / 255f,

	118 / 255f, 64 / 255f, 255 / 255f,

	255 / 255f, 106 / 255f, 255 / 255f };
	int corAtual = 0;
	IMenuItem mBack;

	public ArcadeCongratsScene() {
		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;
		mBackground = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.FUNDO_ID),
				activity.getVertexBufferObjectManager());
		mEscuro = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BLACK_BEHIND_ID),
				activity.getVertexBufferObjectManager());

		mTexto = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.CONGRATS_TEXTO_ID),
				activity.getVertexBufferObjectManager());

		mCham = new Sprite(36, 223,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NINJA_BASE_ID),
				activity.getVertexBufferObjectManager());
		mChamSombra = new Sprite(36, 223,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NINJA_SHADOW_ID),
				activity.getVertexBufferObjectManager());
		mLingua = new Sprite(202, 309,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.NINJA_LINGUA_ID),
				activity.getVertexBufferObjectManager());

		mBack = new SpriteMenuItem(0,
				activity.mSpritesheetTexturePackTextureRegionLibrary2
						.get(posicoes2.BACK_ID),
				activity.getVertexBufferObjectManager());
		mBack.setPosition(375, 678);
		mCham.registerEntityModifier(createColorModifier());

		this.attachChild(mBackground);
		this.attachChild(mEscuro);
		this.attachChild(mTexto);
		this.attachChild(mCham);
		this.attachChild(mChamSombra);
		this.attachChild(mLingua);
		this.addMenuItem(mBack);
		mBack.setScale(0.6f);

		setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);
		Sounds.getSharedInstace().playMusic();
	}

	ColorModifier createColorModifier() {
		return new ColorModifier(2f, mCham.getColor(), new Color(valores[corAtual * 3],
				valores[3 * corAtual + 1], valores[3 * corAtual + 2]),
				EaseQuadInOut.getInstance()) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
				corAtual = (corAtual + 1) % (valores.length/3);
				pItem.registerEntityModifier(createColorModifier());
			}
		};
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		activity.setCurrentScene(new MainMenuScene());
		return true;
	}
}
