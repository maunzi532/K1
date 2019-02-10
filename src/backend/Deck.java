package backend;

import backend.set.*;
import com.jme3.math.*;
import java.util.*;
import java.util.stream.*;

public class Deck implements SendeK
{
	public int xnum;
	public ArrayList<Karte> karten = new ArrayList<>();
	public int counter;

	public Deck(int xnum)
	{
		this.xnum = xnum;
	}

	public void fillWithSet(KartenSet set)
	{
		List<Karte> set1 = set.karten.stream().map(e -> new Karte(null, e)).collect(Collectors.toList());
		Collections.shuffle(set1);
		for(int i = 0; i < set.karten.size(); i++)
			set1.get(i).xz = new KPosition(KPositionTyp.DECK, xnum, 0, i, true, FastMath.rand.nextInt(4));
		karten.addAll(0, set1);
		counter += set1.size();
	}

	public Karte getKarte(KPosition xz)
	{
		return karten.stream().filter(e -> e.xz.y == xz.y).findAny().orElse(null);
	}

	@Override
	public void sendeK(Karte karte)
	{
		karten.add(karte);
		counter++;
	}
}