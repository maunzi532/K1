package backend.set;

import backend.struktur.*;
import java.util.*;

public abstract class KartenSet
{
	public ArrayList<Struktur> karten;

	public String stats()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Karten: ").append(karten.size());
		return sb.toString();
	}
}