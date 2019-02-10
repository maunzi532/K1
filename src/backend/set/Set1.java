package backend.set;

import backend.struktur.*;
import java.util.*;

public class Set1 extends KartenSet
{
	public Set1()
	{
		karten = new ArrayList<>();
		for(int i = 0; i < 3; i++)
		{
			r1(false, true, 4, i + 2);
			karten.add(new Struktur(Mitte.T, 11, 0, 1, i + 2));
			karten.add(new Struktur(Mitte.T, 11, 0, 0, i + 2));
			karten.add(new Struktur(Mitte.LEER, 1, 0, 0, i + 2, 5, 3, 9, 3));
		}
		r1(true, true, 9, 1);
		r1(false, false, 9, 3);
		karten.add(new Struktur(Mitte.GERADE, 15, 0, 5, 2, 7, 2));
		karten.add(new Struktur(Mitte.KURVE, 7, 0, 7, 2));
		karten.add(new Struktur(Mitte.DOPPELKURVE, 15, 0));
		karten.add(new Struktur(Mitte.LEER, 15, 0, 0, 1, 1, 1));
		karten.add(new Struktur(Mitte.LEER, 15, 0, 0, 1, 2, 1));
		karten.add(new Struktur(Mitte.T, 15, 0, 7, 2, 3, 1, 8, 3));
		karten.add(new Struktur(Mitte.T, 15, 0, 7, 3, 1, 1, 6, 2));
		karten.add(new Struktur(Mitte.LEER, 1, 0, 9, 4));
		karten.add(new Struktur(Mitte.GERADE, 10, 0, 4, 1, 6, 3, 8, 3));
		karten.add(new Struktur(Mitte.KURVE, 3, 0, 4, 1, 5, 3, 6, 3));
		karten.add(new Struktur(Mitte.BLOCKIERT, 5, 0, 5, 2, 7, 2));
		//Collections.shuffle(karten);
	}

	private void r1(boolean t, boolean l, int... d)
	{
		karten.add(new Struktur(Mitte.GERADE, 10, 0, d));
		karten.add(new Struktur(Mitte.KURVE, 3, 0, d));
		if(l)
			karten.add(new Struktur(Mitte.LEER, 15, 0, d));
		if(t)
			karten.add(new Struktur(Mitte.T, 11, 0, d));
	}
}