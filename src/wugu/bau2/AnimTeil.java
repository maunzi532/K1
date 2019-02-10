package wugu.bau2;

import com.jme3.math.*;
import java.util.*;

public class AnimTeil
{
	public ArrayList<Float> times = new ArrayList<>();
	public ArrayList<Vector3f> translations = new ArrayList<>();
	public ArrayList<Quaternion> rotations = new ArrayList<>();
	public ArrayList<Vector3f> scales = new ArrayList<>();

	public void keyframe(float time, Vector3f translate, Quaternion rotate, Vector3f scale)
	{
		times.add(time);
		translations.add(translate);
		rotations.add(rotate);
		scales.add(scale);
	}

	public float[] times()
	{
		float[] times1 = new float[times.size()];
		for(int i = 0; i < times.size(); i++)
			times1[i] = times.get(i);
		return times1;
	}

	public Vector3f[] translations()
	{
		return translations.toArray(new Vector3f[translations.size()]);
	}

	public Quaternion[] rotations()
	{
		return rotations.toArray(new Quaternion[rotations.size()]);
	}

	public Vector3f[] scales()
	{
		return scales.toArray(new Vector3f[scales.size()]);
	}
}