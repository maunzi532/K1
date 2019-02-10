package backend.struktur;

import backend.*;
import java.util.*;

public class ConnectedBoden
{
	public Karte karte;
	public int pfad;
	public int pfadCode;
	public ArrayList<Integer> mid;
	public HashMap<ConnectedBoden, Integer> connections;

	public ConnectedBoden(Karte karte)
	{
		this.karte = karte;
		connections = new HashMap<>();
		mid = new ArrayList<>();
		pfad = -1;
		pfadCode = 15;
		mid.add(4);
	}

	public ConnectedBoden(Karte karte, int num)
	{
		this.karte = karte;
		connections = new HashMap<>();
		mid = new ArrayList<>();
		if(num < 4)
		{
			pfad = num;
			pfadCode = 1 << num;
		}
		else
		{
			pfad = 15;
			mid.add(num);
		}
	}

	public void connect(ConnectedBoden c2, int wand, boolean offen)
	{
		if(offen)
		{
			connections.remove(c2);
			c2.connections.remove(this);
			if(c2.pfad != 15)
				if(pfad == 15)
					pfad = c2.pfad;
				else
					pfad = 15;
			pfadCode |= c2.pfadCode;
			mid.addAll(c2.mid);
			connections.putAll(c2.connections);
		}
		else
		{
			connections.put(c2, wand);
			c2.connections.put(this, wand);
		}
	}

	public void connect(ConnectedBoden c2, int wand)
	{
		connections.put(c2, wand + 8);
		c2.connections.put(this, (wand + 2) % 4 + 8);
	}

	public void disconnect(ConnectedBoden c2)
	{
		connections.remove(c2);
		c2.connections.remove(this);
	}

	public void gettingReplaced(ConnectedBoden von)
	{
		for(ConnectedBoden cb : connections.keySet())
		{
			Integer w = cb.connections.remove(this);
			cb.connections.put(von, w);
		}
	}

	public int midOrt(Mitte mitte, boolean w4Offen)
	{
		if(mid.size() == 0)
			return pfad;
		if(mid.size() > 1)
			if(mitte == Mitte.KURVE)
				return 12;
			else
				return 14;
		switch(mitte)
		{
			case LEER:
				if(w4Offen)
					return 14;
			case DOPPELKURVE:
				if(mid.get(0) == 6)
					return 13;
			case KURVE:
				if(w4Offen)
					return 12;
				return pfad;
			case GERADE:
				if(!w4Offen)
					return pfad;
		}
		return 14;
	}

	public ArrayList<Integer> glow()
	{
		ArrayList<Integer> re = new ArrayList<>();
		if(pfad != -1)
			for(int i = 0; i < 4; i++)
				if((pfadCode & (1 << i)) > 0)
					re.add(i);
		re.addAll(mid);
		return re;
	}

	@Override
	public String toString()
	{
		return "ConnectedBoden{" +
				"karte=" + karte.xz +
				", pfadCode=" +
				((pfadCode & 1) > 0 ? '0' : 'x') +
				((pfadCode & 2) > 0 ? '1' : 'x') +
				((pfadCode & 4) > 0 ? '2' : 'x') +
				((pfadCode & 8) > 0 ? '3' : 'x') +
				", nummer=" + karte.connecter.connectors.indexOf(this) +
				'}';
	}
}