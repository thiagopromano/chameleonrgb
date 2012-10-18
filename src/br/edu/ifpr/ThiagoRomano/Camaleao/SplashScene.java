package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.IModifier;

import android.view.KeyEvent;

public class SplashScene extends Scene {
	CamaleaotestActivity activity;

	public SplashScene() {
		setBackground(new Background(0.09804f, 0.6274f, 0));
		activity = CamaleaotestActivity.getSharedInstance();
		Text title1 = new Text(0, 0, activity.mFont, "splash",
				activity.getVertexBufferObjectManager());
		Text title2 = new Text(0, 0, activity.mFont, "screen",
				activity.getVertexBufferObjectManager());

		title1.setPosition(-title1.getWidth(), activity.mCamera.getHeight() / 2);
		title2.setPosition(activity.mCamera.getWidth(),
				activity.mCamera.getHeight() / 2);

		attachChild(title1);
		attachChild(title2);

		title1.registerEntityModifier(new MoveXModifier(1, title1.getX(),
				activity.mCamera.getWidth() / 2 - title1.getWidth()));
		title2.registerEntityModifier(new MoveXModifier(1, title2.getX(),
				activity.mCamera.getWidth() / 2));

		DelayModifier dMod = new DelayModifier(2,
				new IEntityModifierListener() {
					@Override
					public void onModifierStarted(IModifier arg0, IEntity arg1) {
						activity.loadAssets();
					}

					public void onModifierFinished(IModifier arg0, IEntity arg1) {
						activity.setCurrentScene(new MainMenuScene());
					}
				});
		registerEntityModifier(dMod);

	}

	@Override
	public boolean handleKeyDown(int pKeyCode, KeyEvent pEvent) {
		// TODO Auto-generated method stub
		return false;
	}

}
