package backend.struktur;

public enum Mitte
{
	LEER(7, 15, new int[]{4, 4, 4, 4}, new int[]{5, 5, 6, 6}),
	BLOCKIERT(4, 0, new int[]{-1, -1, -1, -1}, null),
	KURVE(7, 3, new int[]{4, 4, -1, -1}, new int[]{6, 5, -1, -1}),
	DOPPELKURVE(6, 15, new int[]{4, 4, 5, 5}, null),
	GERADE(7, 10, new int[]{-1, 4, -1, 4}, new int[]{-1, 6, -1, 5}),
	T(5, 11, new int[]{4, 4, -1, 4}, null);

	public int anzBoden;
	public int connectCode;
	public int[] connectMid;
	public int[] connectW4;

	Mitte(int anzBoden, int connectCode, int[] connectMid, int[] connectW4)
	{
		this.anzBoden = anzBoden;
		this.connectCode = connectCode;
		this.connectMid = connectMid;
		this.connectW4 = connectW4;
	}
}