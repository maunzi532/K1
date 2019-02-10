package wugu.bau2;

import com.jme3.animation.*;
import com.jme3.math.*;
import java.util.*;

public class TC2a extends AdvGeometry
{
	//zk0a, zk0b, k3a0, k3a1
	private DualP2[] k;

	public TC2a(String name, Vector2f texScale, ArrayList<Bone> boneList, DualP2... k)
	{
		super(name, texScale, boneList);
		this.k = k;
	}

	@Override
	protected void setup()
	{
		Position2[] array1 = new Position2[]{k[0].sCl(2), k[0].sCl(1), k[1].sCl(1), k[1], k[0],
				k[0].sCl(-1), k[1].sCl(-1), k[1].sCl(-2)};
		bau.flatPoly(true, array1, new Vector3f(0, -1, 0),
				Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO,
				Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO);
		bau.abdeck(true, 8, k[2]);
		bau.abdeck(true, 8, k[3]);
	}
}