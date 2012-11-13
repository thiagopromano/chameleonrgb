package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;
import org.andengine.util.texturepack.TexturePack;
import org.andengine.util.texturepack.TexturePackLoader;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;
import org.andengine.util.texturepack.exception.TexturePackParseException;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.KeyEvent;

public class CamaleaotestActivity extends SimpleBaseGameActivity {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 800;

	// ===========================================================
	// Fields
	// ===========================================================

	public Camera mCamera;
	private BitmapTextureAtlas mBitmapTextureAtlas;

	public Scene mCurrentScene;
	public static CamaleaotestActivity instance;
	public Font mFont;

	private BitmapTextureAtlas mFontTexture;
	public TexturePackTextureRegionLibrary mSpritesheetTexturePackTextureRegionLibrary;
	public TexturePackTextureRegionLibrary mSpritesheetTexturePackTextureRegionLibrary2;
	public TextureRegion mTextureBar;
	public boolean loading;
	public TextureRegion mSplashScreen;
	public BitmapTextureAtlas mBitmapTextureAtlasSplash;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		final EngineOptions mEngineOptions = new EngineOptions(true,
				ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
		mEngineOptions.getRenderOptions().setDithering(true);
		mEngineOptions.getTouchOptions().setNeedsMultiTouch(true);
		mEngineOptions.getAudioOptions().setNeedsSound(true);
		mEngineOptions.getAudioOptions().setNeedsMusic(true);
		return mEngineOptions;
	}

	@Override
	protected void onCreateResources() {
		this.mFontTexture = new BitmapTextureAtlas(this.getTextureManager(),
				256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFont = new Font(this.getFontManager(), this.mFontTexture,
				Typeface.create("Franklin Gothic Book", Typeface.NORMAL), 32,
				true, Color.WHITE);

		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
		this.getFontManager().loadFont(this.mFont);

		this.mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(),
				512, 16);

		this.mBitmapTextureAtlasSplash = new BitmapTextureAtlas(
				getTextureManager(), 512, 512);

		mSplashScreen = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlasSplash,
				CamaleaotestActivity.getSharedInstance(), "gfx/splash.png", 0,
				0);
		mEngine.getTextureManager().loadTexture(mBitmapTextureAtlasSplash);

		// WindowManager.initialize(this);
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		mCurrentScene = new SplashScene();

		return mCurrentScene;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static CamaleaotestActivity getSharedInstance() {
		return instance;
	}

	public void loadAssets() {
		Looper.prepare();
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				loading = true;
				try {
					final TexturePack spritesheetTexturePack = new TexturePackLoader(
							getAssets(), getTextureManager()).loadFromAsset(
							"gfx/positions.xml", "gfx/");
					spritesheetTexturePack.loadTexture();
					mSpritesheetTexturePackTextureRegionLibrary = spritesheetTexturePack
							.getTexturePackTextureRegionLibrary();

					final TexturePack spritesheetTexturePack2 = new TexturePackLoader(
							getAssets(), getTextureManager()).loadFromAsset(
							"gfx/positions2.xml", "gfx/");
					spritesheetTexturePack2.loadTexture();

					mSpritesheetTexturePackTextureRegionLibrary2 = spritesheetTexturePack2
							.getTexturePackTextureRegionLibrary();

				} catch (final TexturePackParseException e) {
					Debug.e(e);
				}
				mTextureBar = BitmapTextureAtlasTextureRegionFactory
						.createFromAsset(mBitmapTextureAtlas,
								CamaleaotestActivity.getSharedInstance(),
								"gfx/color_bar.png", 0, 0);

				mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
				new Sounds(CamaleaotestActivity.getSharedInstance());
				loading = false;
				return null;
			}

		}.execute((Void[]) null);

	}

	public void setCurrentScene(Scene scene) {
		mCurrentScene = scene;
		// WindowManager.getInstance().setScene(mCurrentScene, new
		// RightPushInTransition(1));
		getEngine().setScene(mCurrentScene);
	}

	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		return mCurrentScene.handleKeyDown(pKeyCode, pEvent);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
