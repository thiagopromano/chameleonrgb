package br.edu.ifpr.ThiagoRomano.Chameleon;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuRectangleRegion extends Rectangle implements IMenuItem {
	private int ID;

	public MenuRectangleRegion(int ID, float pX, float pY, float pWidth,
			float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
		this.ID = ID;
		this.setAlpha(0);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void onSelected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnselected() {
		// TODO Auto-generated method stub

	}

}
