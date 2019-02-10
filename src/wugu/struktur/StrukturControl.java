package wugu.struktur;

import backend.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;
import com.jme3.scene.shape.*;
import wugu.*;

public class StrukturControl extends AbstractControl
{
	public Karte karte;
	public Node strukturNode;

	public StrukturControl(Karte karte)
	{
		this.karte = karte;
		enabled = false;
	}

	@Override
	public void setSpatial(Spatial spatial)
	{
		super.setSpatial(spatial);
		strukturNode = new Node("StrukturNode");
		strukturNode.rotate(0, FastMath.PI * karte.struktur.drehung / 2, 0);
		if(karte.struktur.loch)
		{
			Geometry g2 = new Geometry("Boden4", new StrukturMesh(Kartenteil.LOCH, karte.struktur.mitte, 4));
			g2.setMaterial(MaterialLager.boden1);
			g2.setUserData("Typ", "Boden");
			g2.setUserData("Num", 4);
			strukturNode.attachChild(g2);
		}
		else
		{
			Geometry g1 = new Geometry("Struktur", new StrukturMesh(Kartenteil.STRUKTUR, karte.struktur.mitte, karte.struktur.pfadCode));
			g1.setMaterial(MaterialLager.struktur1);
			g1.setUserData("Typ", "Struktur");
			strukturNode.attachChild(g1);
			for(int i = 0; i < 7; i++)
				if(karte.connecter.connected[i] != null)
				{
					Geometry g2 = new Geometry("Boden" + i, new StrukturMesh(Kartenteil.BODEN, karte.struktur.mitte, i));
					g2.setMaterial(MaterialLager.boden1);
					g2.setUserData("Typ", "Boden");
					g2.setUserData("Num", i);
					strukturNode.attachChild(g2);
				}
		}
		for(int i = 0; i < 5; i++)
			if(karte.struktur.wanditem[i] > 0)
			{
				Geometry g2 = new Geometry("Wand" + i, new StrukturMesh(Kartenteil.WAND, karte.struktur.mitte, i));
				g2.setMaterial(MaterialLager.wand1);
				g2.setUserData("Num", i);
				g2.setUserData("Typ", "Wand");
				g2.setUserData("WTyp", karte.struktur.wanditem[i]);
				g2.setUserData("Offen", false);
				strukturNode.attachChild(g2);
			}
		for(int i = 0; i < 5; i++)
		{
			if(karte.struktur.wanditem[i + 5] > 0)
			{
				Box b = new Box(1, 1, 1);
				Geometry g2 = new Geometry("Box", b);
				g2.setLocalTranslation(StrukturMesh.ort(karte.connecter.midOrt(i)).addLocal(0, 2, 0));
				g2.setMaterial(MaterialLager.item1);
				g2.setUserData("Typ", "Item");
				g2.setUserData("Num", i);
				strukturNode.attachChild(g2);
			}
		}
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		if(enabled)
			((Node) spatial).attachChild(strukturNode);
		else
			((Node) spatial).detachChild(strukturNode);
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