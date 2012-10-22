package br.edu.ifpr.ThiagoRomano.Camaleao;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.shader.PositionTextureCoordinatesShaderProgram;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.shader.exception.ShaderProgramLinkException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

import android.graphics.Color;
import android.opengl.GLES20;

public class BarSprite extends Sprite {
	private int id;
	private NinjaModeScene mScene;
	private String fShader = null;
	ShaderProgram mShaderProgram;
	private String vShader = null;
//todo implementar overlay
	public static final String VERTEXSHADER =
			"uniform mat4 " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX + ";\n" +
			"attribute vec4 " + ShaderProgramConstants.ATTRIBUTE_POSITION + ";\n" +
			"attribute vec4 " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
			"attribute vec2 " + ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
			"varying vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
			"varying vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
//			"uniform vec4 theColor;\n"+
			"void main() {\n" +
			"	" + ShaderProgramConstants.VARYING_COLOR + " = " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
			"	" + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + " = " + ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
			"	gl_Position = " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX + " * " + ShaderProgramConstants.ATTRIBUTE_POSITION + ";\n" +
			"}";

	public static final String FRAGMENTSHADERRED =
			"precision lowp float;\n" +
			"uniform sampler2D " + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ";\n" +
			"varying lowp vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
			"varying mediump vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
			"uniform vec4 theColor;\n"+
			"vec4 cor;\n"+
			"void main() {\n" +
			"   cor = vec4(" + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ".x*2.0, theColor.y, theColor.z, 1.0);\n"+
			"	gl_FragColor = (cor) * texture2D(" + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ", " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ");\n" +
			"}";

	public static final String FRAGMENTSHADERGREEN = 
			"precision lowp float;\n" +
			"uniform sampler2D " + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ";\n" +
			"varying lowp vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
			"varying mediump vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
			"uniform vec4 theColor;\n"+
			"vec4 cor;\n"+
			"void main() {\n" +
			"   cor = vec4(theColor.x, " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ".x*2.0, theColor.z, 1.0);\n"+
			"	gl_FragColor = (cor) * texture2D(" + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ", " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ");\n" +
			"}";

	public static final String FRAGMENTSHADERBLUE = 
			"precision lowp float;\n" +
			"uniform sampler2D " + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ";\n" +
			"varying lowp vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
			"varying mediump vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
			"uniform vec4 theColor;\n"+
			"vec4 cor;\n"+
			"void main() {\n" +
			"   cor = vec4(theColor.x, theColor.y, " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ".x*2.0, 1.0);\n"+
			"	gl_FragColor = (cor) * texture2D(" + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ", " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ");\n" +
			"}";

	public BarSprite(int id, final NinjaModeScene scene, float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mScene = scene;
		this.id = id;
		
		switch (id) {
		case 0: {
			fShader = FRAGMENTSHADERRED;
			break;
		}
		case 1: {
			fShader = FRAGMENTSHADERGREEN;
			break;
		}
		case 2: {
			fShader = FRAGMENTSHADERBLUE;
			break;
		}
		default: {
			//fShader = new StringShaderSource(FRAGMENTSHADERRED);
		}

		}
		vShader = VERTEXSHADER;
		mShaderProgram = new ShaderProgram(vShader, fShader) {
			
			public int theColorLocation = ShaderProgramConstants.LOCATION_INVALID;
			
			
		        @Override
			protected void link(final GLState pGLState) throws ShaderProgramLinkException {
		        
			     GLES20.glBindAttribLocation(this.mProgramID, ShaderProgramConstants.ATTRIBUTE_POSITION_LOCATION, ShaderProgramConstants.ATTRIBUTE_POSITION);
					GLES20.glBindAttribLocation(this.mProgramID, ShaderProgramConstants.ATTRIBUTE_COLOR_LOCATION, ShaderProgramConstants.ATTRIBUTE_COLOR);
					GLES20.glBindAttribLocation(this.mProgramID, ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES_LOCATION, ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES);
		             super.link(pGLState);
		             
		             PositionTextureCoordinatesShaderProgram.sUniformModelViewPositionMatrixLocation = this.getUniformLocation(ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX);
			     theColorLocation = this.getUniformLocation("theColor");
			}  
		        NinjaModeScene cena = mScene;
		       @Override
		       public void bind(final GLState pGLState, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		           super.bind(pGLState, pVertexBufferObjectAttributes);
		           GLES20.glUniformMatrix4fv(PositionTextureCoordinatesShaderProgram.sUniformModelViewPositionMatrixLocation, 1, false, pGLState.getModelViewProjectionGLMatrix(), 0);
		           GLES20.glUniform4f(theColorLocation, Color.red(cena.colors)/256f, Color.green(cena.colors)/256f, Color.blue(cena.colors)/256f, 1f);
		       }

		};
		CamaleaotestActivity.getSharedInstance().getShaderProgramManager().loadShaderProgram(mShaderProgram);
		setShaderProgram(mShaderProgram);
		
		
	}
}