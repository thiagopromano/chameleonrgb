package br.edu.ifpr.ThiagoRomano.Camaleao;

import java.util.Random;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.entity.text.Text;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.modifier.ease.EaseElasticOut;
import org.andengine.util.system.CPUUsage;

import android.graphics.Color;
import android.view.KeyEvent;

public class ArcadeModeScene extends GameScene {

	public final float STARTING_TIME = 60f;
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
	
	static final int X_PONTUACAO_INICIAL = 330;
	static final int Y_PONTUACAO_INICIAL = 0;
	
	TiledSprite mWisps[] = new TiledSprite[7];

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
	int score = 0;

	private Sprite mBox;
	private Pontuacao mPontuacao;

	public ArcadeModeScene() {

		rand = new Random();
		this.setOnAreaTouchTraversalFrontToBack();
		colors = Color.rgb(255, 255, 255);
		mPlacaColor = 0xff00ffff;
		activity = CamaleaotestActivity.getSharedInstance();

		createAssets();
		createPauseMenu();
		iniciando = false;
		mConfirmExit = new ConfirmExitScene(this);
		mConfirmRestart = new ConfirmRestartScene(this);
		setTouchAreaBindingOnActionDownEnabled(true);
		registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
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
		

		updateScore();
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
		mBlackBehind = new Sprite(0, 0,
				activity.mSpritesheetTexturePackTextureRegionLibrary
						.get(posicoes.BLACK_BEHIND_ID),
				activity.getVertexBufferObjectManager());
		
		mPontuacao = new Pontuacao(X_PONTUACAO_INICIAL, Y_PONTUACAO_INICIAL, 2,
				this, activity.getVertexBufferObjectManager());
		
		for (int i = 0; i < mWisps.length; i++) {
			mWisps[i] = new TiledSprite(-100, -100, new TiledTextureRegion(
					activity.mSpritesheetTexturePackTextureRegionLibrary.get(
							posicoes.POEIRA1_ID).getTexture(),
						activity.mSpritesheetTexturePackTextureRegionLibrary
							.get(posicoes.POEIRA1_ID),
					activity.mSpritesheetTexturePackTextureRegionLibrary
							.get(posicoes.POEIRA2_ID),
					activity.mSpritesheetTexturePackTextureRegionLibrary
							.get(posicoes.POEIRA3_ID),
					activity.mSpritesheetTexturePackTextureRegionLibrary
							.get(posicoes.POEIRA4_ID)),
					activity.getVertexBufferObjectManager());
			RestartWisp(mWisps[i]);
		}

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
		this.attachChild(mPontuacao);

		this.attachChild(mBlackBehind);
		mBlackBehind.setVisible(false);

		for (int i = 0; i < mWisps.length; i++) {
			this.attachChild(mWisps[i]);
		}

		movements = new MoveModifier(2f, -mPlaca.getWidth(), mPlaca.getX(),
				mPlaca.getY(), mPlaca.getY(), EaseElasticOut.getInstance());
		mPlaca.registerEntityModifier(movements);
		movements.setAutoUnregisterWhenFinished(false);
		// setTouchAreaBindingOnActionMoveEnabled(true);

	}

	private void RestartWisp(TiledSprite pWisp) {
		pWisp.setCurrentTileIndex(rand.nextInt(4));
		if (rand.nextBoolean()) {
			pWisp.registerEntityModifier(new MoveModifier(rand.nextInt(10) + 5,
					-60, 660, rand.nextInt(600), rand.nextInt(600)) {
				@Override
				protected void onModifierFinished(IEntity pItem) {
					super.onModifierFinished(pItem);
					RestartWisp((TiledSprite) pItem);
				}

				@Override
				protected void onSetValues(final IEntity pEntity,
						final float pPercentageDone, final float pX,
						final float pY) {
					super.onSetValues(pEntity, pPercentageDone, pX, pY);

					pEntity.setPosition(
							pEntity.getX()
									+ (float) Math.cos(pPercentageDone * 20)
									* 20,
							pEntity.getY()
									+ (float) Math.sin(pPercentageDone * 20)
									* 20);

					// pEntity.setPosition(pEntity.getX() + rand.nextInt(20) -
					// 10,
					// pEntity.getY() + rand.nextInt(10) - 5);
				}
			});
		} else {
			pWisp.registerEntityModifier(new MoveModifier(rand.nextInt(10) + 5,
					660, -60, rand.nextInt(600), rand.nextInt(600)) {
				@Override
				protected void onModifierFinished(IEntity pItem) {
					super.onModifierFinished(pItem);
					RestartWisp((TiledSprite) pItem);
				}

				@Override
				protected void onSetValues(final IEntity pEntity,
						final float pPercentageDone, final float pX,
						final float pY) {
					super.onSetValues(pEntity, pPercentageDone, pX, pY);

					pEntity.setPosition(
							pEntity.getX()
									+ (float) Math.cos(pPercentageDone * 20)
									* 20,
							pEntity.getY()
									+ (float) Math.sin(pPercentageDone * 20)
									* 20);
					// pEntity.setPosition(pEntity.getX() + rand.nextInt(20) -
					// 10,
					// pEntity.getY() + rand.nextInt(10) - 5);
				}
			});
		}
	}

	@Override
	public void restart() {
		score = 0;
		mActualColor = 0;
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

		int redStepsDiference = Math.abs(Color.red(colors)
				- Color.red(mPlacaColor));
		int greenStepsDiference = Math.abs(Color.green(colors)
				- Color.green(mPlacaColor));
		int blueStepsDiference = Math.abs(Color.blue(colors)
				- Color.blue(mPlacaColor));

		/*
		 * mTextRemainingTime.setText(Integer.toString(redStepsDiference +
		 * greenStepsDiference + blueStepsDiference));
		 */
		// mChampShadow
		// .setAlpha(Math
		// .min(((redStepsDiference + greenStepsDiference + blueStepsDiference)
		// / 765f) * 7f + 0.1f,
		// 1f));
		if (redStepsDiference < 25
				&& greenStepsDiference < 25
				&& blueStepsDiference < 25
				&& redStepsDiference + blueStepsDiference + greenStepsDiference < 50) {
			if (mChamp.getEntityModifierCount() == 0) {
				mChampShadow.registerEntityModifier(new FadeOutModifier(2f) {

					@Override
					protected void onModifierStarted(IEntity pItem) {
						iniciando = true;
					};

					@Override
					protected void onManagedUpdate(float pSecondsElapsed,
							IEntity pItem) {
						super.onManagedUpdate(pSecondsElapsed, pItem);

					}

					@Override
					protected void onSetValue(IEntity pEntity,
							float pPercentageDone, float pAlpha) {
						// TODO Auto-generated method stub
						super.onSetValue(pEntity, pPercentageDone, pAlpha);
						if (pAlpha < 0.24f) {

							pEntity.setAlpha(0.24f);
						}
					}

					@Override
					protected void onModifierFinished(IEntity pItem) {
						// TODO Auto-generated method stub
						super.onModifierFinished(pItem);
						nextColor();
					}
				});
			}
		}
	}

	MoveModifier movements;

	public void nextColor() {

		movements.reset();
		score++;
		updateScore();
		mActualColor++;

		// mChamp.setAlpha(1f);
		mChampShadow.setAlpha(1f);
		iniciando = false;
		if (mActualColor * 3 + 2 < CORES.length)
			mPlacaColor = Color.rgb(CORES[mActualColor * 3],
					CORES[mActualColor * 3 + 1], CORES[mActualColor * 3 + 2]);
		else {
			mPlacaColor = Color.rgb(rand.nextInt(255), rand.nextInt(255),
					rand.nextInt(255));
		}

		setPlacaColor(mPlacaColor);
		/*
		 * if (rand.nextBoolean()) { Sounds.getSharedInstace().mYay.play();
		 * }else{ Sounds.getSharedInstace().mUhul.play(); }
		 */

		// mPlaca.setX(-mPlaca.getX());

		// animacao entrando

	}

	private void updateScore() {
		mPontuacao.updateScore(score);
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
	public void clearChildScenes() {
		this.mMenuScene.back();
		this.mConfirmExit.back();
	}

}
