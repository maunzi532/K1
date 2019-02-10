package backend.feld;

public class Feld1 extends Feld
{
	public Feld1()
	{
		xw = 6;
		zw = 5;
		felder = new int[]
				{
						0, 0, 1, 1, 1,
						0, 1, 1, 1, 1,
						2, 1, 1, 1, 1,
						1, 1, 1, 1, 2,
						1, 1, 1, 1, 0,
						1, 1, 1, 0, 0
				};
	}
}