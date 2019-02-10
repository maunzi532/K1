package backend.feld;

import backend.*;
import backend.struktur.*;
import java.util.*;

public class Spielfeld implements SendeK
{
	public Feld feld;
	public Karte[][] karten;
	public ArrayList<ConnectedBoden> connectors;
	public KPosition ziel;

	public Spielfeld(Feld feld)
	{
		this.feld = feld;
		karten = new Karte[feld.xw][feld.zw];
		connectors = new ArrayList<>();
	}

	private void eintrag(Karte eintrag)
	{
		karten[eintrag.xz.x][eintrag.xz.z] = eintrag;
		connectors.addAll(eintrag.connecter.connectors);
		for(int i = 0; i < 4; i++)
			connect(eintrag.xz, i, false);
	}

	private void connect(KPosition xz, int d, boolean inv)
	{
		int x1 = d % 2 == 0 ? xz.x + 1 - d : xz.x;
		int z1 = d % 2 == 1 ? xz.z - 2 + d : xz.z;
		KPosition xz1 = new KPosition(x1, z1);
		if(getKarte(xz1) == null || getKarte(xz1).xz.verdeckt)
			return;
		ConnectedBoden c1 = getKarte(xz).connecter.getConnector(d);
		ConnectedBoden c2 = getKarte(xz1).connecter.getConnector((d + 2) % 4);
		if(c1 == null || c2 == null)
			return;
		if(inv)
			c1.disconnect(c2);
		else
			c1.connect(c2, d);
	}

	public Karte getKarte(KPosition xz)
	{
		if(xz.x < 0 || xz.z < 0 || xz.x >= feld.xw || xz.z >= feld.zw)
			return null;
		return karten[xz.x][xz.z];
	}

	@Override
	public void sendeK(Karte karte)
	{
		ziel.drehung = karte.xz.drehung;
		karte.xz = ziel;
		eintrag(karte);
	}

	public static void argh(Spielfeld sf)
	{
		for(int i = 0; i < sf.feld.zw; i++)
		{
			char[] arr = new char[sf.feld.xw * 9];
			for(int j = 0; j < sf.feld.xw; j++)
			{
				char[] sk = Karte.drawK(sf.getKarte(new KPosition(j, i)));
				for(int k = 0; k < 3; k++)
					System.arraycopy(sk, k * 3, arr, j * 3 + k * sf.feld.xw * 3, 3);
			}
			for(int k = 0; k < 3; k++)
				System.out.println(new String(arr).substring(sf.feld.xw * 3 * k, sf.feld.xw * 3 * (k + 1)));
		}
	}
}