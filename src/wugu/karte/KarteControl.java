package wugu.karte;

import backend.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;
import wugu.*;
import wugu.struktur.*;

public class KarteControl extends AbstractControl
{
	public Node node;
	public Karte karte;

	public KarteControl(Karte karte)
	{
		this.karte = karte;
	}

	@Override
	public void setSpatial(Spatial spatial)
	{
		super.setSpatial(spatial);
		if(spatial == null)
			return;
		node = (Node) spatial;
		node.setName("Karte");
		Geometry kM = new Geometry("Seite");
		kM.setMaterial(MaterialLager.seite1);
		node.attachChild(kM);
		node.addControl(new StrukturControl(karte));
		updateV(0);
	}

	public void updateV(float zeit)
	{
		KPosition xz = node.getUserData("XZ");
		if(zeit > 0)
			node.addControl(new KarteFlipControl(zeit));
		else
			node.setLocalRotation(KarteFlipControl.drehFlip(xz));
		node.getControl(StrukturControl.class).setEnabled(!xz.verdeckt);
		((Geometry) node.getChild("Seite")).setMesh(new KarteMesh(xz.verdeckt));
	}

	@Override
	protected void controlUpdate(float tpf)
	{

	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{

	}
}