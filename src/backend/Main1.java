package backend;

import backend.feld.*;
import backend.set.*;
import backend.struktur.*;
import com.jme3.math.*;
import java.util.*;

public class Main1
{
	public static Deck deck;
	public static Deck ablage;
	public static Spielfeld spielfeld;
	public static Deck hand;
	public static List<Spieler> spieler;
	public static int amz;
	public static ZugAbschnitt abschnitt;
	public static ArrayList<KPosition> felder = new ArrayList<>();
	public static boolean item2;
	public static boolean umdreh;
	public static boolean aussuchen;
	public static boolean fallen;
	public static List<ConnectedBoden> marked1 = Collections.EMPTY_LIST;
	public static List<KPosition> marked2 = Collections.EMPTY_LIST;
	public static boolean updateMarked;
	public static TargetingType targetingType;

	public static void init(KartenSet set, Feld feld1, Spieler... spieler1)
	{
		deck = new Deck(0);
		deck.fillWithSet(set);
		ablage = new Deck(1);
		spielfeld = new Spielfeld(feld1);
		hand = new Deck(2);
		spieler = Arrays.asList(spieler1);
		amz = FastMath.rand.nextInt(spieler.size());
	}

	public static Karte xz2k(KPosition xz)
	{
		switch(xz.t)
		{
			case SPIELFELD:
				return spielfeld.getKarte(xz);
			case DECK:
				return deck.getKarte(xz);
			case ABLAGE:
				return ablage.getKarte(xz);
			case HAND:
				return hand.getKarte(xz);
		}
		return null;
	}

	public static void neuerZug()
	{
		amz = (amz + 1) % spieler.size();
		System.out.println("Spieler " + amz + " ist dran");
		Spieler ak = spieler.get(amz);
		if(ak.position == null)
		{
			abschnitt = ZugAbschnitt.SPAWN1;
			targetingType = TargetingType.BODEN;
			//marked2 = Arrays.stream(spielfeld.karten[1]).filter(Objects::nonNull).map(e -> e.xz).collect(Collectors.toList());
			//updateMarked = true;
		}
		else
		{
			abschnitt = ZugAbschnitt.ANFANG;
			targetingType = TargetingType.BODEN;
		}
		felder.clear();
		item2 = false;
		umdreh = false;
		aussuchen = false;
		fallen = false;
	}

	public static boolean process(boolean down, InputCode ic, Karte karte, ConnectedBoden cb)
	{
		if(down)
		{
			System.out.println(ic);
			if(cb != null)
				System.out.println(cb);
			else if(karte != null)
				System.out.println(karte.xz);
			if(aussuchen)
			{

			}
			else if(fallen)
			{
				if(ic == InputCode.LK && cb != null)
				{

				}
			}
			else switch(abschnitt)
			{
				case SPAWN1:
					if(ic == InputCode.LK && karte != null && karte.xz.t == KPositionTyp.SPIELFELD)
					{
						System.out.println(karte.xz);
						spieler.get(amz).position = karte.xz;
						if(karte.xz.verdeckt)
						{
							aussuchen = true;
						}
						fallen = true;
					}
					return false;
			}
		}
		return false;
	}

	public enum ZugAbschnitt
	{
		SPAWN1,
		SPAWN2,
		ANFANG,
		BEWEGT,
		FELDBEWEGT,
		AUFGEDECKT,
		ENDE
	}

	public enum TargetingType
	{
		BODEN,
		KARTE
	}
}