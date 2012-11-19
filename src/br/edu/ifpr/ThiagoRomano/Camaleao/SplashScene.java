package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;

public class SplashScene extends Scene {
	CamaleaotestActivity activity;
	private boolean mReady = false;

	Sprite mSplash;

	public SplashScene() {
		setBackground(new Background(0.f, 0f, .0f));
		activity = CamaleaotestActivity.getSharedInstance();

		mSplash = new Sprite(117, 177, activity.mSplashScreen,
				activity.getVertexBufferObjectManager());
		mSplash.setAlpha(0);
		this.attachChild(mSplash);
		mSplash.registerEntityModifier(new SequenceEntityModifier(
				new FadeInModifier(2) {
					@Override
					protected void onModifierFinished(IEntity pItem) {
						activity.loadAssets();
						super.onModifierFinished(pItem);

					}
				}, new DelayModifier(5f) {
					@Override
					protected void onModifierFinished(IEntity pItem) {
						// TODO Auto-generated method stub
						super.onModifierFinished(pItem);
						mReady = true;
					}
				}));

		this.registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void reset() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUpdate(float pSecondsElapsed) {
				if (activity.loading || !mReady)
					return;
				mSplash.registerEntityModifier(new FadeOutModifier(2f) {
					@Override
					protected void onModifierFinished(IEntity pItem) {
						// TODO Auto-generated method stub
						super.onModifierFinished(pItem);
						activity.setCurrentScene(new MainMenuScene());
					}
				});
			}
		});
	}

}
