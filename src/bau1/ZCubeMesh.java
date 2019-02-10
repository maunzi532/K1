package bau1;

import com.jme3.math.*;

public class ZCubeMesh extends BauMesh1
{
	public ZCubeMesh(float s)
	{
		//oben
		quad(false, new Vector3f(-s, s, s), new Vector3f(s, -s, s));
		texFlat4(0f, 0f, 0.5f, 0.5f);
		//unten
		quad(true, new Vector3f(-s, s, -s), new Vector3f(s, -s, -s));
		texFlat4(0.5f, 0f, 1f, 0.5f);
		//seiten
		cylinder(true, new Vector3f(0, 0, s), new Vector3f(s, -s, s),
				new Vector3f(0, 0, -s), new Vector3f(s, -s, -s), 4);
		texCylinder(0f, 0.5f, 0.25f, 0.75f, 4);
		//ende
		finish();
	}
}