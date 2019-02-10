package backend;

import backend.struktur.*;

public class Karte
{
	public KPosition xz;
	public Struktur struktur;
	public Struktur vereinfacht;
	public Connecter connecter;

	public Karte(KPosition xz, Struktur struktur)
	{
		this.xz = xz;
		this.struktur = struktur;
		vereinfacht = struktur;
		connecter = new Connecter(this, struktur);
	}

	public void vereinfachen(int freiCode)
	{
		vereinfacht = struktur.vereinfache(dreheK(freiCode, xz.drehung));
		connecter = new Connecter(this, vereinfacht);
	}

	public static int dreheK(int code, int drehung)
	{
		return ((code + (code << 4)) >>> (4 - drehung)) & 15;
	}

	public static int dreheI(int code, int drehung)
	{
		return ((code + (code << 4)) >>> drehung) & 15;
	}

	public static char[] drawK(Karte k)
	{
		if(k == null)
			return "         ".toCharArray();
		int code1 = dreheK(k.vereinfacht.pfadCode, k.xz.drehung);
		return (("X" + (code1 & 2) + "X" + (code1 & 4) + " " + (code1 & 1) + "X" + (code1 & 8) + "X")).toCharArray();
	}
}