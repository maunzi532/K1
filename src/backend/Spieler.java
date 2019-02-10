package backend;

import backend.struktur.*;
import java.util.*;

public class Spieler
{
	public int farbe;
	public KPosition position;
	public ConnectedBoden boden;
	public int gold;
	public int schluessel;
	public ArrayList<Integer> items;

	public Spieler(int farbe)
	{
		this.farbe = farbe;
	}
}