package br.edu.ifpr.ThiagoRomano.Chameleon;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

import android.view.KeyEvent;

public class GameScene extends Scene {

	public int colors;
	public boolean iniciando = true;
	public Scene mConfirmExit;
	public PauseMenu mMenuScene;
	public Scene mConfirmRestart;
	Sprite mBlackBehind;
	Sprite mChamp;
	public void ChangeColors(float fValor, int id) {

	}

	public void restart() {
	}

	public void clearChildScenes() {
		// TODO Auto-generated method stub

	}

	public void toggleEscuro(boolean bool) {
		if (bool)
		{
			mBlackBehind.setVisible(true);
	 
		}else{
			mBlackBehind.setVisible(false);
		}
	}
	
	@Override
	public boolean handleKeyDown(int pKeyCode, KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_MENU
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			if (this.hasChildScene()) {
				/* Remove the menu and reset it. */
				this.mMenuScene.back();
				this.mConfirmExit.back();
				this.mConfirmRestart.back();
				toggleEscuro(false);
			} else {
				/* Attach the menu. */
				this.setChildScene(this.mMenuScene, false, true, true);
				toggleEscuro(true);
			}
			return true;
		} else if (pKeyCode == KeyEvent.KEYCODE_BACK
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			if (this.hasChildScene()) {
				/* Remove the menu and reset it. */
				clearChildScenes();
				toggleEscuro(false);
			} else {
				/* Attach the confirm. */
				this.setChildScene(this.mConfirmExit, false, true, true);
				toggleEscuro(true);
			}
			return true;
		} else {
			return false;
		}
	}
}
