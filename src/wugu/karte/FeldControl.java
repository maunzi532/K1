package wugu.karte;

import backend.*;
import backend.feld.*;
import backend.struktur.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;
import wugu.*;

public class FeldControl extends AbstractControl
{
	public Node feldNode;
	public Node[][] karten;
	public Spielfeld spielfeld;

	public FeldControl(Spielfeld spielfeld)
	{
		this.spielfeld = spielfeld;
		karten = new Node[spielfeld.feld.xw][spielfeld.feld.zw];
	}

	@Override
	public void setSpatial(Spatial spatial)
	{
		super.setSpatial(spatial);
		feldNode = (Node) spatial;
	}

	public Node getKarte(KPosition xz)
	{
		if(xz.x < 0 || xz.z < 0 || xz.x >= spielfeld.feld.xw || xz.z >= spielfeld.feld.zw)
			return null;
		return karten[xz.x][xz.z];
	}

	public void start(StapelControl deck)
	{
		for(int i1 = 0; i1 < spielfeld.feld.xw; i1++)
			for(int i2 = 0; i2 < spielfeld.feld.zw; i2++)
			{
				Vector3f platz = new Vector3f((i1 - (spielfeld.feld.xw - 1) / 2f) * 20f, 0, (i2 + 1.2f) * 20f);
				spielfeld.ziel = new KPosition(KPositionTyp.SPIELFELD, i1, i2, 0, false, 0);
				switch(spielfeld.feld.felder[i1 * spielfeld.feld.zw + i2])
				{
					case 1:
						Node k = deck.gibKarte(spielfeld, false);
						k.addControl(new EnhMotionEvent(feldNode, spielfeld.ziel.drehung, spielfeld.ziel.verdeckt, 3, platz.add(0, 10, 0), platz));
						karten[i1][i2] = k;
						k.setUserData("XZ", new KPosition(i1, i2));
						//feldNode.attachChild(k);
						break;
					case 2:
						Karte lkarte = new Karte(new KPosition(i1, i2), new Struktur());
						spielfeld.sendeK(lkarte);
						Node leer = new Node();
						leer.setUserData("XZ", new KPosition(i1, i2));
						leer.addControl(new KarteControl(lkarte));
						leer.setLocalTranslation(platz);
						karten[i1][i2] = leer;
						feldNode.attachChild(leer);
						break;
				}
			}
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