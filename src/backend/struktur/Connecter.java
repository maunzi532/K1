package backend.struktur;

import backend.*;
import java.util.*;

public class Connecter
{
	public Karte karte;
	public Struktur struktur;
	public ConnectedBoden[] connected;
	public ArrayList<ConnectedBoden> connectors;

	public Connecter(Karte karte, Struktur struktur)
	{
		this.karte = karte;
		this.struktur = struktur;
		connected = new ConnectedBoden[7];
		connectors = new ArrayList<>();
		if(this.struktur.loch)
		{
			ConnectedBoden cb = new ConnectedBoden(karte);
			for(int i = 0; i < 5; i++)
				connected[i] = cb;
			connectors.add(cb);
			return;
		}
		boolean midWand = this.struktur.wanditem[4] > 0;
		int pfadlim = this.struktur.mitte.anzBoden;
		if(pfadlim == 7)
			pfadlim = midWand ? 7 : 5;
		for(int i = 0; i < pfadlim; i++)
			if(((this.struktur.pfadCode + 112) & (1 << i)) > 0 && (i != 4 || !midWand))
			{
				ConnectedBoden cb = new ConnectedBoden(karte, i);
				connected[i] = cb;
				connectors.add(cb);
			}
		int[] connectMid = midWand ? this.struktur.mitte.connectW4 : this.struktur.mitte.connectMid;
		if(midWand)
			connect(5, 6, 4);
		for(int i = 3; i >= 0; i--)
			if(connectMid[i] >= 0)
				connect(i, connectMid[i], i);
	}

	private void connect(int a1, int a2, int wand)
	{
		ConnectedBoden l1 = connected[a1];
		ConnectedBoden l2 = connected[a2];
		if(l1 == null || l2 == null)
			return;
		boolean offen = offen(wand);
		l1.connect(l2, wand, offen);
		if(offen)
		{
			for(int i = 0; i < connected.length; i++)
				if(l2 == connected[i])
					connected[i] = l1;
			l2.gettingReplaced(l1);
			connectors.remove(l2);
		}
	}

	public boolean offen(int w1)
	{
		return struktur.wanditem[w1] <= 0; //TODO
		/*return struktur.wanditem[w1] <= 0 || (strukturNode.getChild("Wand" + w1) != null &&
				(Boolean) strukturNode.getChild("Wand" + w1).getUserData("Offen"));*/
	}

	public ConnectedBoden getConnector(int seite)
	{
		return connected[(seite + 8 - karte.xz.drehung - struktur.drehung) % 4];
	}

	public int midOrt(int lc)
	{
		if(lc == 4)
		{
			if(struktur.mitte == Mitte.BLOCKIERT)
				return 15;
			if(connected[4] == null)
				lc = 5;
		}
		return connected[lc].midOrt(struktur.mitte, offen(4));
	}

	public int midOrt(ConnectedBoden cb)
	{
		return cb.midOrt(struktur.mitte, offen(4));
	}

	public int wandOrt(int w)
	{
		if(w < 4)
			return w + 4;
		if(struktur.mitte == Mitte.KURVE)
			return 12;
		return 14;
	}

	public List<Integer> pfad(int von, int nach)
	{
		if(nach == von)
			return Collections.emptyList();
		if(von >= 12)
		{
			if(nach >= 12)
				return Collections.singletonList(nach);
			return pfadM(nach, true);
		}
		if(nach >= 12)
		{
			ArrayList<Integer> re = new ArrayList<>();
			re.addAll(pfadM(von, false));
			re.add(nach);
			return re;
		}
		if((von - nach) % 4 == 0)
			return Collections.singletonList(nach);
		ArrayList<Integer> re = new ArrayList<>();
		re.addAll(pfadM(von, false));
		re.addAll(pfadM(nach, true));
		return re;
	}

	private List<Integer> pfadM(int ort, boolean weg)
	{
		int r = ort % 4;
		if(ort / 4 == 1 && !weg)
			return Collections.emptyList();
		if(ort / 4 != 1 && weg)
			return Arrays.asList(r + 4, ort);
		return Collections.singletonList(r + 4);
	}
}