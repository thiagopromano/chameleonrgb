package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.ease.EaseQuadInOut;

public class TutorialScene extends MenuScene implements
		IOnMenuItemClickListener {

	CamaleaotestActivity activity;

	IMenuItem mNext;
	int tela;
	boolean glowingLow;
	public TutorialScene(int number) {
		activity = CamaleaotestActivity.getSharedInstance();
		this.mCamera = activity.mCamera;
		tela = number;
		switch (number) {
		case 0:
			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.FUNDO_TELA_1_2_3_ID),
					activity.getVertexBufferObjectManager()));
				
			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary
							.get(posicoes.BLACK_BEHIND_ID),
					activity.getVertexBufferObjectManager()));
			
			attachChild(new Sprite(32, 346,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.TELA_1_ID),
					activity.getVertexBufferObjectManager()));

			mNext = new SpriteMenuItem(0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.SETINHA_ID),
					activity.getVertexBufferObjectManager());
			mNext.setPosition(389, 725);
			this.addMenuItem(mNext);
			break;
		case 1:
			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.FUNDO_TELA_1_2_3_ID),
					activity.getVertexBufferObjectManager()));
			attachChild(new Sprite(38, 215,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.TELA_2_ID),
					activity.getVertexBufferObjectManager()));

			mNext = new SpriteMenuItem(0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.SETINHA_ID),
					activity.getVertexBufferObjectManager());
			mNext.setPosition(389, 725);
			this.addMenuItem(mNext);
			break;
		case 2:
			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.FUNDO_TELA_1_2_3_ID),
					activity.getVertexBufferObjectManager()));
			attachChild(new Sprite(102, 629,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.TELA_3_ID),
					activity.getVertexBufferObjectManager()));

			mNext = new SpriteMenuItem(0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.SETINHA_ID),
					activity.getVertexBufferObjectManager());
			mNext.setPosition(389, 725);
			this.addMenuItem(mNext);
			break;
		case 3:

			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.TELA_4_ID),
					activity.getVertexBufferObjectManager()));

			mNext = new SpriteMenuItem(0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.SETINHA_ID),
					activity.getVertexBufferObjectManager());
			mNext.setPosition(389, 725);
			this.addMenuItem(mNext);
			break;
		case 4:
			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.FUNDO_TELA_5_6_ID),
					activity.getVertexBufferObjectManager()));
			attachChild(new Sprite(44, 155,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.TELA_5_ID),
					activity.getVertexBufferObjectManager()));

			mNext = new SpriteMenuItem(0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.SETINHA_ID),
					activity.getVertexBufferObjectManager());
			mNext.setPosition(389, 725);
			this.addMenuItem(mNext);
			break;
		case 5:
			attachChild(new Sprite(0, 0,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.FUNDO_TELA_5_6_ID),
					activity.getVertexBufferObjectManager()));
					
			attachChild(new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BLACK_BEHIND_ID),
				activity.getVertexBufferObjectManager()));
				
			attachChild(new Sprite(31, 347,
					activity.mSpritesheetTexturePackTextureRegionLibrary2
							.get(posicoes2.TELA_6_ID),
					activity.getVertexBufferObjectManager()));
					
			mNext = new SpriteMenuItem(1,
					activity.mSpritesheetTexturePackTextureRegionLibrary
							.get(posicoes.GO_ID),
					activity.getVertexBufferObjectManager());
			mNext.setPosition(164, 440);
			this.addMenuItem(mNext);
			break;
		default:
			break;
		}
		if (mNext != null)
		{
			mNext.registerEntityModifier(createGlowModifier());
		}
		setBackgroundEnabled(false);
		setOnMenuItemClickListener(this);
	}
	
	ScaleModifier createGlowModifier() {
		return new ScaleModifier(0.7f, (glowingLow) ? 1f : 0.5f,
				(!glowingLow) ? 1f : 0.5f, EaseQuadInOut.getInstance()) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
				glowingLow = !glowingLow;
				pItem.registerEntityModifier(createGlowModifier());
			}
		};
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		if (pMenuItem.getID() == 0)
			activity.setCurrentScene(new TutorialScene(tela+1));
		else{
			activity.setCurrentScene(new ArcadeModeScene(1, new int[] {

					255, 255, 0,

					0, 255, 0,

					255, 0, 0,

					255, 0, 255,

					0, 0, 255,

					255, 255, 255, }));
		}
		return true;
	}
}
