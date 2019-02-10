package wugu.karte;

import com.jme3.math.*;
import wugu.*;

public class KarteMesh extends BauMesh
{
	public static final float kw = 10;
	public static final float kt = 1;
	public static final float sw = 9;

	public KarteMesh(boolean verdeckt)
	{
		addq(new Vector3f(0, -1, 0), false, 1, -sw, -kt, -sw, sw, -kt, sw, 0, 0, 1, 1);
		spinaddv(new Vector3f(0, -1, 1), true, -kw, 0, kw, 0, 0, kw, 0, kw, 0, 1,
				-sw, -kt, sw, 1, 0, sw, -kt,  sw, 1, 1);
		if(verdeckt)
			addq(new Vector3f(0, 1, 0), true, 1, -kw, 0, -kw, kw, 0, kw, 0, 0, 1, 1);
		fertig();
	}
}