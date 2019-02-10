package wugu.struktur;

import backend.struktur.*;
import com.jme3.math.*;
import wugu.*;

public class StrukturMesh extends BauMesh
{
	public static final float kh = 4;
	public static final float kw = 10;
	public static final float ew = 3;
	public static final float iw = 5;
	public static final float en = 8.5f;

	public static Vector3f ort(int ortcode)
	{
		int code1 = ortcode / 4;
		int code2 = ortcode % 4;
		if(code1 == 3)
			code1 += code2;
		switch(code1)
		{
			case 0:
				return spin(code2, (kw + iw) / 2);
			case 1:
				return spin(code2, iw);
			case 2:
				return spin(code2, en);
			case 3:
				return new Vector3f(iw / 2, 0, -iw / 2);
			case 4:
				return new Vector3f(-iw / 2, 0, iw / 2);
			case 5:
				return new Vector3f();
			case 6:
				return new Vector3f(0, kh, 0);
			default:
				throw new RuntimeException();
		}
	}

	public static Vector3f spin(int s, float x)
	{
		switch(s)
		{
			case 0:
				return new Vector3f(x, 0, 0);
			case 1:
				return new Vector3f(0, 0, -x);
			case 2:
				return new Vector3f(-x, 0, 0);
			case 3:
				return new Vector3f(0, 0, x);
			default:
				throw new RuntimeException();
		}
	}

	public StrukturMesh(Kartenteil typ, Mitte mitte, int nummer)
	{
		switch(typ)
		{
			case LOCH:
				//Loch-Boden
				addq(Vector3f.UNIT_Y, true, 1, -kw, 0, -kw, kw, 0, kw, 0, 0, 1, 1);
				break;
			case STRUKTUR:
				//Aussenkanten
				spinaddq(new Vector3f(0, 0, 1), false, 2, kw, 0, kw, ew, kh, kw, 0, 0, 1, 1);
				spinaddq(new Vector3f(0, 0, 1), false, 2, -ew, 0, kw, -kw, kh, kw, 0, 0, 1, 1);
				//Innenkanten
				spinaddq(new Vector3f(0, 0, 1), false, 2, kw, 0, -ew, iw, kh, -ew, 0, 0, 1, 1);
				spinaddq(new Vector3f(0, 0, -1), false, 2, iw, 0, ew, kw, kh, ew, 0, 0, 1, 1);
				spinaddv(new Vector3f(-1, 0, -1), true,
						ew, 0, iw, 0, 0, iw, 0, ew, 0, 1,
						ew, kh, iw, 1, 0, iw, kh, ew, 1, 1);
				//Oben
				spinaddd(Vector3f.UNIT_Y, false, kw, kh, kw, iw, kh, ew, ew, kh, iw, 0, 0, 1, 0, 0, 1);
				spinaddd(Vector3f.UNIT_Y, true, kw, kh, kw, iw, kh, ew, kw, kh, ew, 0, 0, 1, 0, 0, 1);
				spinaddd(Vector3f.UNIT_Y, true, kw, kh, kw, ew, kh, kw, ew, kh, iw, 0, 0, 1, 0, 0, 1);
				//Enden
				spinaddq(new Vector3f(1, 0, 0), 0, 15 - nummer, false, kw, 0, -ew, kw, kh, ew, 0, 0, 1, 1);
				spinaddq(new Vector3f(-1, 0, 0), 0, 15 - nummer, true, iw, 0, -ew, iw, kh, ew, 0, 0, 1, 1);
				spinaddq(Vector3f.UNIT_Y, 1, 15 - nummer, false, kw, kh, -ew, iw, kh, ew, 0, 0, 1, 1);
				//Mitte
				switch(mitte)
				{
					case BLOCKIERT:
						spinaddq(new Vector3f(1, 0, 0), false, 0, iw, 0, -ew, iw, kh, ew, 0, 0, 1, 1);
						addv(Vector3f.UNIT_Y, false,
								-ew, kh, -iw, 0, 0, iw, kh, ew, 0, 1,
								ew, kh, -iw, 1, 0, iw, kh, -ew, 1, 1);
						addv(Vector3f.UNIT_Y, false,
								ew, kh, iw, 0, 0, -iw, kh, -ew, 0, 1,
								-ew, kh, iw, 1, 0, -iw, kh, ew, 1, 1);
						addv(Vector3f.UNIT_Y, true,
								-ew, kh, -iw, 0, 0, iw, kh, ew, 0, 1,
								-iw, kh, -ew, 1, 0, ew, kh, iw, 1, 1);
						break;
					case KURVE:
						spinaddq(new Vector3f(1, 0, 0), 0, 12, false, iw, 0, -ew, iw, kh, ew, 0, 0, 1, 1);
						addv(new Vector3f(1, 0, -1), false,
								iw, 0, ew, 0, 0, -ew, 0, -iw, 0, 1,
								iw, kh, ew, 1, 0, -ew, kh, -iw, 1, 1);
						addv(Vector3f.UNIT_Y, true,
								ew, kh, iw, 0, 0, -iw, kh, -ew, 0, 1,
								iw, kh, ew, 1, 0, -ew, kh, -iw, 1, 1);
						addv(Vector3f.UNIT_Y, false,
								ew, kh, iw, 0, 0, -iw, kh, -ew, 0, 1,
								-ew, kh, iw, 1, 0, -iw, kh, ew, 1, 1);
						break;
					case DOPPELKURVE:
						addv(new Vector3f(1, 0, -1), false,
								iw, 0, ew, 0, 0, -ew, 0, -iw, 0, 1,
								iw, kh, ew, 1, 0, -ew, kh, -iw, 1, 1);
						addv(new Vector3f(-1, 0, 1), true,
								ew, 0, iw, 0, 0, -iw, 0, -ew, 0, 1,
								ew, kh, iw, 1, 0, -iw, kh, -ew, 1, 1);
						addv(Vector3f.UNIT_Y, true,
								ew, kh, iw, 0, 0, -iw, kh, -ew, 0, 1,
								iw, kh, ew, 1, 0, -ew, kh, -iw, 1, 1);
						break;
					case GERADE:
						addq(new Vector3f(1, 0, 0), false, 0, iw, 0, -ew, iw, kh, ew, 0, 0, 1, 1);
						addq(new Vector3f(-1, 0, 0), true, 0, ew, 0, -iw, ew, kh, iw, 0, 0, 1, 1);
						addv(Vector3f.UNIT_Y, true,
								iw, kh, -ew, 0, 0, iw, kh, ew, 0, 1,
								ew, kh, -iw, 1, 0, ew, kh, iw, 1, 1);
					case T:
						addq(new Vector3f(-1, 0, 0), true, 0, -iw, 0, -ew, -iw, kh, ew, 0, 0, 1, 1);
						addq(new Vector3f(1, 0, 0), false, 0, -ew, 0, -iw, -ew, kh, iw, 0, 0, 1, 1);
						addv(Vector3f.UNIT_Y, false,
								-iw, kh, -ew, 0, 0, -iw, kh, ew, 0, 1,
								-ew, kh, -iw, 1, 0, -ew, kh, iw, 1, 1);
						break;
				}
				break;
			case BODEN:
				if(nummer < 4)
					spinaddq(Vector3f.UNIT_Y, 1, 1 << nummer, false, kw, 0, -ew, iw, 0, ew, 0, 0, 1, 1);
				else switch(mitte)
				{
					case T:
						if(nummer == 4)
						{
							addq(Vector3f.UNIT_Y, true, 1, ew, 0, iw, -ew, 0, -iw, 0, 0, 1, 1);
							addv(Vector3f.UNIT_Y, true,
									iw, 0, -ew, 0, 0, iw, 0, ew, 0, 1,
									ew, 0, -iw, 1, 0, ew, 0, iw, 1, 1);
						}
						break;
					case GERADE:
						if(nummer == 4)
							addq(Vector3f.UNIT_Y, true, 1, ew, 0, iw, -ew, 0, -iw, 0, 0, 1, 1);
						if(nummer == 5)
							addq(Vector3f.UNIT_Y, true, 1, ew, 0, iw, -ew, 0, 0, 0, 0, 1, 1);
						if(nummer == 6)
							addq(Vector3f.UNIT_Y, true, 1, ew, 0, 0, -ew, 0, -iw, 0, 0, 1, 1);
						break;
					case DOPPELKURVE:
						if(nummer == 4)
							addv(Vector3f.UNIT_Y, false,
									-ew, 0, -iw, 0, 0, iw, 0, ew, 0, 1,
									ew, 0, -iw, 1, 0, iw, 0, -ew, 1, 1);
						if(nummer == 5)
							addv(Vector3f.UNIT_Y, false,
									ew, 0, iw, 0, 0, -iw, 0, -ew, 0, 1,
									-ew, 0, iw, 1, 0, -iw, 0, ew, 1, 1);
						break;
					case KURVE:
						if(nummer == 4)
							addv(Vector3f.UNIT_Y, false,
									-ew, 0, -iw, 0, 0, iw, 0, ew, 0, 1,
									ew, 0, -iw, 1, 0, iw, 0, -ew, 1, 1);
						if(nummer == 5)
							addv(Vector3f.UNIT_Y, false,
									-ew, 0, -iw, 0, 0, (iw - ew) / 2, 0, (ew - iw) / 2, 0, 1,
									ew, 0, -iw, 1, 0, (iw + ew) / 2, 0, (-ew - iw) / 2, 1, 1);
						if(nummer == 6)
							addv(Vector3f.UNIT_Y, false,
									(iw - ew) / 2, 0, (ew - iw) / 2, 0, 0, iw, 0, ew, 0, 1,
									(ew + iw) / 2, 0, (-iw - ew) / 2, 1, 0, iw, 0, -ew, 1, 1);
						break;
					case LEER:
						if(nummer == 4)
						{
							addv(Vector3f.UNIT_Y, false,
									-ew, 0, -iw, 0, 0, iw, 0, ew, 0, 1,
									ew, 0, -iw, 1, 0, iw, 0, -ew, 1, 1);
							addv(Vector3f.UNIT_Y, false,
									ew, 0, iw, 0, 0, -iw, 0, -ew, 0, 1,
									-ew, 0, iw, 1, 0, -iw, 0, ew, 1, 1);
							addv(Vector3f.UNIT_Y, true,
									-ew, 0, -iw, 0, 0, iw, 0, ew, 0, 1,
									-iw, 0, -ew, 1, 0, ew, 0, iw, 1, 1);
						}
						if(nummer == 5)
						{
							addv(Vector3f.UNIT_Y, false,
									-ew, 0, -iw, 0, 0, iw, 0, ew, 0, 1,
									ew, 0, -iw, 1, 0, iw, 0, -ew, 1, 1);
							addv(Vector3f.UNIT_Y, true,
									-ew, 0, -iw, 0, 0, iw, 0, ew, 0, 1,
									-(ew + iw) / 2, 0, -(iw + ew) / 2, 1, 0, (iw + ew) / 2, 0, (ew + iw) / 2, 1, 1);
						}
						if(nummer == 6)
						{
							addv(Vector3f.UNIT_Y, false,
									ew, 0, iw, 0, 0, -iw, 0, -ew, 0, 1,
									-ew, 0, iw, 1, 0, -iw, 0, ew, 1, 1);
							addv(Vector3f.UNIT_Y, false,
									-iw, 0, -ew, 0, 0, ew, 0, iw, 0, 1,
									-(ew + iw) / 2, 0, -(iw + ew) / 2, 1, 0, (iw + ew) / 2, 0, (ew + iw) / 2, 1, 1);
						}
						break;
				}
				break;
			case WAND:
				if(nummer < 4)
				{
					spinaddq(new Vector3f(-1, 0, 0), 0, 1 << nummer, true, iw, 0, -ew, iw, kh, ew, 0, 0, 1, 1);
					spinaddq(new Vector3f(1, 0, 0), 0, 1 << nummer, false, iw, 0, -ew, iw, kh, ew, 0, 0, 1, 1);
				}
				else if(nummer == 4)
					switch(mitte)
					{
						case LEER:
							addv(new Vector3f(-1, 0, 1), false,
									-(ew + iw) / 2, 0, -(iw + ew) / 2, 0, 0, (iw + ew) / 2, 0, (ew + iw) / 2, 0, 1,
									-(ew + iw) / 2, kh, -(iw + ew) / 2, 1, 0, (iw + ew) / 2, kh, (ew + iw) / 2, 1, 1);
							addv(new Vector3f(1, 0, -1), true,
									-(ew + iw) / 2, 0, -(iw + ew) / 2, 0, 0, (iw + ew) / 2, 0, (ew + iw) / 2, 0, 1,
											-(ew + iw) / 2, kh, -(iw + ew) / 2, 1, 0, (iw + ew) / 2, kh, (ew + iw) / 2, 1, 1);
							break;
						case KURVE:
							addv(new Vector3f(1, 0, 1), false,
									(iw - ew) / 2, 0, (ew - iw) / 2, 0, 0, (iw + ew) / 2, 0, (-ew - iw) / 2, 0, 1,
									(iw - ew) / 2, kh, (ew - iw) / 2, 1, 0, (iw + ew) / 2, kh, (-ew - iw) / 2, 1, 1);
							addv(new Vector3f(-1, 0, -1), true,
									(iw - ew) / 2, 0, (ew - iw) / 2, 0, 0, (iw + ew) / 2, 0, (-ew - iw) / 2, 0, 1,
									(iw - ew) / 2, kh, (ew - iw) / 2, 1, 0, (iw + ew) / 2, kh, (-ew - iw) / 2, 1, 1);
							break;
						case GERADE:
							addv(new Vector3f(0, 0, -1), false,
									ew, 0, 0, 0, 0, -ew, 0, 0, 0, 1,
									ew, kh, 0, 1, 0, -ew, kh, 0, 1, 1);
							addv(new Vector3f(0, 0, 1), true,
									ew, 0, 0, 0, 0, -ew, 0, 0, 0, 1,
									ew, kh, 0, 1, 0, -ew, kh, 0, 1, 1);
							break;
					}
				break;
		}
		fertig();
	}
}