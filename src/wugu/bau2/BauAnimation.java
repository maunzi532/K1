package wugu.bau2;

import com.jme3.animation.*;
import com.jme3.math.*;
import java.util.*;

public class BauAnimation
{
	public HashMap<AutoNumBone, AnimTeil> teile = new HashMap<>();

	public void keyframe(AutoNumBone bone, float time, Vector3f translate, Quaternion rotate)
	{
		keyframe(bone, time, translate, rotate, Vector3f.UNIT_XYZ);
	}

	public void keyframe(AutoNumBone bone, float time, Vector3f translate, Quaternion rotate, Vector3f scale)
	{
		if(!teile.containsKey(bone))
			teile.put(bone, new AnimTeil());
		teile.get(bone).keyframe(time, translate, rotate, scale);
	}

	public Animation createAnimation(String name, float length)
	{
		Animation a1 = new Animation(name, length);
		for(Map.Entry<AutoNumBone, AnimTeil> entry : teile.entrySet())
			a1.addTrack(new BoneTrack(entry.getKey().num, entry.getValue().times(), entry.getValue().translations(),
					entry.getValue().rotations(), entry.getValue().scales()));
		return a1;
	}
}