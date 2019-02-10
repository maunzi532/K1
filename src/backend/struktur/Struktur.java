package backend.struktur;

public class Struktur
{
	public boolean loch;
	public Mitte mitte;
	public int pfadCode;
	public int drehung;
	public int[] wanditem;

	public Struktur()
	{
		loch = true;
		mitte = Mitte.LEER;
		pfadCode = 15;
		drehung = 0;
		wanditem = new int[10];
	}

	public Struktur(Mitte mitte, int pfadCode, int drehung, int... d)
	{
		loch = false;
		this.mitte = mitte;
		this.pfadCode = pfadCode;
		this.drehung = drehung;
		wanditem = new int[10];
		for(int i = 0; i < d.length; i += 2)
			wanditem[d[i]] = d[i + 1];
	}

	private int wanditemcode(boolean item)
	{
		int c = 0;
		for(int i = 0; i < 5; i++)
			if(wanditem[i + (item ? 5 : 0)] > 0)
				c += 1 << i;
		return c;
	}

	private void setcode(int c, boolean item)
	{
		for(int i = 0; i < 5; i++)
			if((c & (1 << i)) == 0)
				wanditem[i + (item ? 5 : 0)] = 0;
	}

	private void drehe2()
	{
		drehung = (drehung + 2) % 4;
		pfadCode = ((pfadCode + (pfadCode << 4)) >>> 2) & 15;
		for(int i = 0; i < 4; i++)
		{
			int k = i + (i > 1 ? 3 : 0);
			int l1a = wanditem[k];
			wanditem[k] = wanditem[k + 2];
			wanditem[k + 2] = l1a;
		}
	}

	public Struktur vereinfache(int freiCode)
	{
		int freiCodeD = ((freiCode + (freiCode << 4)) >>> drehung) & 15;
		Struktur v = new Struktur();
		v.drehung = drehung;
		if(loch)
		{
			v.pfadCode = freiCodeD;
			return v;
		}
		v.loch = false;
		v.pfadCode = pfadCode & (freiCodeD | (wanditemcode(true) & 15));
		int wegeCode = pfadCode & freiCodeD;
		if(mitte == Mitte.DOPPELKURVE)
		{
			boolean w1 = (wegeCode & 3) > 0;
			boolean w2 = (wegeCode & 12) > 0;
			v.pfadCode = ((w1 ? v.pfadCode : wegeCode) & 3) + ((w2 ? v.pfadCode : wegeCode) & 12);
		}
		else if((wegeCode & mitte.connectCode) > 0)
			v.pfadCode = (v.pfadCode & mitte.connectCode) + (wegeCode & (15 - mitte.connectCode));
		else
			v.pfadCode = wegeCode;
		System.arraycopy(wanditem, 0, v.wanditem, 0, 10);
		v.setcode(wanditemcode(false) & ((v.pfadCode & mitte.connectCode) + 16), false);
		v.setcode(wanditemcode(true) & (v.pfadCode + 16), true);
		v.mitte = mitte;
		int pfadAnz = Integer.bitCount(v.pfadCode);
		switch(pfadAnz)
		{
			case 0:
				v.mitte = Mitte.BLOCKIERT;
				wanditem[4] = 0;
				wanditem[9] = 0;
				break;
			case 1:
				v.mitte = Mitte.LEER;
				wanditem[4] = 0;
				int i = 0;
				for(int i1 = pfadCode; i1 > 1; i1 >>>= 1)
					i++;
				if((v.pfadCode & mitte.connectCode) == 0)
					wanditem[9] = 0;
				if(wanditem[9] == 0)
				{
					wanditem[i] = 0;
					wanditem[9] = wanditem[i + 5];
					wanditem[i + 5] = 0;
				}
				break;
			case 2:
			case 3:
				int midCode = v.pfadCode & mitte.connectCode;
				if(midCode == mitte.connectCode)
					break;
				switch(mitte)
				{
					case GERADE:
						v.mitte = Mitte.BLOCKIERT;
						wanditem[4] = 0;
						if(midCode != 0)
							wanditem[midCode == 2 ? 1 : 3] = wanditem[9];
						wanditem[9] = 0;
						break;
					case KURVE:
						v.mitte = Mitte.BLOCKIERT;
						wanditem[4] = 0;
						if(midCode != 0)
							wanditem[midCode == 1 ? 0 : 1] = wanditem[9];
						wanditem[9] = 0;
						break;
					case DOPPELKURVE:
						if((midCode & 12) != 12 && (midCode & 3) != 3)
							v.mitte = Mitte.BLOCKIERT;
						else if((midCode & 12) != 12)
							v.mitte = Mitte.KURVE;
						else if((midCode & 3) != 3)
						{
							v.mitte = Mitte.KURVE;
							v.drehe2();
						}
						break;
					case T:
						if((v.pfadCode & 4) > 0 && pfadAnz == 2 && wanditem[9] == 0)
							v.mitte = Mitte.BLOCKIERT;
						break;
					case LEER:
						if((midCode & 12) == 0)
						{
							wanditem[4] = 0;
							v.mitte = Mitte.KURVE;
						}
						else if((midCode & 3) == 0)
						{
							wanditem[4] = 0;
							v.mitte = Mitte.KURVE;
							v.drehe2();
						}
						break;
				}
				break;
		}
		return v;
	}
}