package br.edu.ifpr.ThiagoRomano.Camaleao;

import java.util.Random;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.util.modifier.ease.EaseElasticOut;

import android.graphics.Color;
import android.view.KeyEvent;

public class NinjaModeScene extends GameScene {

	static final float STARTING_TIME = 5f;
	static final float TIME_CORRECT = 4f;
	public final int[] CORES = { 0, 0, 0, 255, 0, 0, 0, 255, 0, 0, 0, 255, 0,
			255, 255, 255, 255, 0, 100, 100, 100, };

	CamaleaotestActivity activity;
	Sprite mChamp;
	Sprite mBlue;
	Sprite mRed;
	Sprite mGreen;
	Sprite mLetraR;
	Sprite mLetraG;
	Sprite mLetraB;
	Sprite mSliderBlue;
	Sprite mSliderRed;
	Sprite mSliderGreen;
	Sprite mAdendo;
	Sprite mChampShadow;
	Sprite mPalco;
	Sprite mFolhasFrente;

	Chronometer mChronometer;

	Random rand;
	// Text mTextRemainingTime;
	Sprite mBackground;
	Sprite mPlaca;
	Sprite mPlacaSaindo;
	Sprite mTronco;
	
	int mPlacaColor;
	int mActualColor = 0;
	public int theColorLocation = ShaderProgramConstants.LOCATION_INVALID;
	// public boolean mOverlayed = true;
	float remainingTime = 5f;
	int score = 0;

	private Text mTextScore;

	private Sprite mBox;

	public NinjaModeScene() {

		rand = new Random();
		this.setOnAreaTouchTraversalFrontToBack();
		colors = Color.rgb(255, 255, 255);
		mPlacaColor = 0xff00ffff;
		activity = CamaleaotestActivity.getSharedInstance();

		createAssets();
		createPauseMenu();
		mConfirmExit = new ConfirmExitScene(this);
		mConfirmRestart = new ConfirmRestartScene(this);
		setTouchAreaBindingOnActionDownEnabled(true);
		registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				remainingTime -= pSecondsElapsed;
				updateTime();
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub

			}
		});
	}

	private void createAssets() {
		/*
		 * mTextRemainingTime = new Text(10, 10, activity.mFont, "", 10,
		 * activity.getVertexBufferObjectManager());
		 */
		mTextScore = new Text(300, 10, activity.mFont, "jna", 4,
				activity.getVertexBufferObjectManager());

		updateScore();
		updateTime();
		mBackground = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.FUNDO_ID),
				activity.getVertexBufferObjectManager());
		mFolhasFrente = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.FOLHAS_FRENTE_ID),
				activity.getVertexBufferObjectManager());

		mPalco = new Sprite(62, 365,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.PALCO_ID),
				activity.getVertexBufferObjectManager());

		mAdendo = new Sprite(-16, -15,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NYLON_DETALHES_ID),
				activity.getVertexBufferObjectManager());

		mPlaca = new Sprite(83, 65,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.NYLON_TROCACOR_ID),
				activity.getVertexBufferObjectManager());

		mPlaca.setColor(colorIntToFloat(Color.red(mPlacaColor)),
				colorIntToFloat(Color.green(mPlacaColor)),
				colorIntToFloat(Color.blue(mPlacaColor)));

		mTronco = new Sprite(0, 219,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.TRONCO_ID),
				activity.getVertexBufferObjectManager());

		mChampShadow = new Sprite(127, 68,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.CAMALEAO_SOMBRA_ID),
				activity.getVertexBufferObjectManager());

		mChamp = new Sprite(127, 68,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.CAMALEAO_MASK_TROCACOR_ID),
				activity.getVertexBufferObjectManager());

		mBox = new Sprite(45, 489,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BOX_ID),
				activity.getVertexBufferObjectManager());
		mRed = new BarSprite(0, this, 112, 543, activity.mTextureBar,
				activity.getVertexBufferObjectManager());

		mGreen = new BarSprite(1, this, 112, 615, activity.mTextureBar,
				activity.getVertexBufferObjectManager());

		mBlue = new BarSprite(2, this, 112, 686, activity.mTextureBar,
				activity.getVertexBufferObjectManager());

		mLetraR = new Sprite(69, 524,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.R_ID),
				activity.getVertexBufferObjectManager());
		mLetraG = new Sprite(67, 600,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.G_ID),
				activity.getVertexBufferObjectManager());
		mLetraB = new Sprite(69, 674,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.B_ID),
				activity.getVertexBufferObjectManager());

		mSliderRed = new SliderSprite(0, this, 372, 525,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MARCADOR_ID),
				activity.getVertexBufferObjectManager());

		mSliderGreen = new SliderSprite(1, this, 372, 597,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MARCADOR_ID),
				activity.getVertexBufferObjectManager());

		mSliderBlue = new SliderSprite(2, this, 372, 668,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.MARCADOR_ID),
				activity.getVertexBufferObjectManager());
		mChronometer = new Chronometer(this,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.CHRONOMETROFUNDO_ID),
				activity.getVertexBufferObjectManager());
		mBlackBehind = new Sprite(0, 0, activity.mSpritesheetTexturePackTextureRegionLibrary
				.get(posicoes.BLACK_BEHIND_ID), activity.getVertexBufferObjectManager());

		this.attachChild(mBackground);
		this.attachChild(mPalco);

		this.attachChild(mPlaca);
		mPlaca.attachChild(mAdendo);

		this.attachChild(mTronco);
		this.attachChild(mFolhasFrente);

		this.attachChild(mChamp);
		this.attachChild(mChampShadow);

		this.attachChild(mBox);
		this.attachChild(mRed);
		this.attachChild(mGreen);
		this.attachChild(mBlue);

		this.attachChild(mLetraR);
		this.attachChild(mLetraG);
		this.attachChild(mLetraB);

		this.attachChild(mSliderRed);
		registerTouchArea(mSliderRed);

		this.attachChild(mSliderGreen);
		registerTouchArea(mSliderGreen);

		this.attachChild(mSliderBlue);
		registerTouchArea(mSliderBlue);

		// this.attachChild(mTextRemainingTime);
		this.attachChild(mTextScore);

		this.attachChild(mChronometer);
		movements = new MoveModifier(2f, -mPlaca.getWidth(), mPlaca.getX(),
				mPlaca.getY(), mPlaca.getY(), EaseElasticOut.getInstance());
		mPlaca.registerEntityModifier(movements);
		movements.setAutoUnregisterWhenFinished(false);
		// setTouchAreaBindingOnActionMoveEnabled(true);

	}

	@Override
	public void restart() {
		remainingTime = STARTING_TIME;
		iniciando = true;
		mChronometer.restart();
		score = 0;
		mActualColor = 0;
		updateTime();
		updateScore();
		this.reset();

	}

	private void createPauseMenu() {
		mMenuScene = new PauseMenu(this);
	}

	@Override
	public void ChangeColors(float newColor, int index) {
		int iNewColor = (int) (newColor * 255);
		// colors = (0xff00ffff >> (2*index))&((int)Math.floor(newColor*255));
		int mask = (0xff00ffff >> (8 * index));
		iNewColor <<= 16;
		iNewColor >>= index * 8;
		// a.setColor(pColor)

		colors = (colors & mask);
		colors |= iNewColor;
		mChamp.setColor(Color.red(colors) / 256f, Color.green(colors) / 256f,
				Color.blue(colors) / 256f);

		int redStepsDiference = Math.abs(Color.red(colors) / SliderSprite.STEP
				- Color.red(mPlacaColor) / SliderSprite.STEP);

		int greenStepsDiference = Math.abs(Color.green(colors)
				/ SliderSprite.STEP - Color.green(mPlacaColor)
				/ SliderSprite.STEP);
		int blueStepsDiference = Math.abs(Color.blue(colors)
				/ SliderSprite.STEP - Color.blue(mPlacaColor)
				/ SliderSprite.STEP);
		/*
		 * mTextRemainingTime.setText(Integer.toString(redStepsDiference +
		 * greenStepsDiference + blueStepsDiference));
		 */
		if (redStepsDiference < 50
				&& greenStepsDiference < 50
				&& blueStepsDiference < 50
				&& redStepsDiference + blueStepsDiference + greenStepsDiference < 80)
			nextColor();
	}

	MoveModifier movements;

	public void nextColor() {

		remainingTime += TIME_CORRECT;

		mPlacaColor = Color.rgb(rand.nextInt(255), rand.nextInt(255),
				rand.nextInt(255));

		setPlacaColor(mPlacaColor);

		// mPlaca.setX(-mPlaca.getX());

		// animacao entrando
		movements.reset();
		score++;
		updateScore();
		mActualColor++;
	}

	private void updateScore() {
		mTextScore.setText(Integer.toString(score));
	}

	private void updateTime() {
		if (mChronometer != null) {
			mChronometer.updateTime(remainingTime);
		}
	}

	public int colorFloatToInt(float number) {
		return (int) (number * 255);
	}

	public float colorIntToFloat(int number) {
		return number / 255f;
	}

	public void setPlacaColor(int color) {
		mPlaca.setColor(colorIntToFloat(Color.red(mPlacaColor)),
				colorIntToFloat(Color.green(mPlacaColor)),
				colorIntToFloat(Color.blue(mPlacaColor)));
	}

	@Override
	public boolean handleKeyDown(int pKeyCode, KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_MENU
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			if (this.hasChildScene()) {
				/* Remove the menu and reset it. */
				this.mMenuScene.back();
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
	@Override
	public void clearChildScenes()
	{
		this.mMenuScene.back();
		this.mConfirmExit.back();
	}
	StatsScreen stats;

	public void endTime() {
		// TODO tocaFimDeJogo
		// TODO showScore
		// mChronometer.registerEntityModifier(new FadeOutModifier(3));
		if (stats == null)
			stats = new StatsScreen(this);
		stats.updateText(score);
		this.setChildScene(stats, false, true, true);
	}
}