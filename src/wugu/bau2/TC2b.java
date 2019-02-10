package wugu.bau2;

import com.jme3.animation.*;
import com.jme3.collision.*;
import com.jme3.math.*;
import java.util.*;

public class TC2b extends AdvGeometry
{
	private List<BauMesh2.Index3> indexesZ;
	private Vector3f mov;
	private int za = 2;
	private int edge = 3;
	private float w = 0.02f;
	private float[] la = new float[]{0.04f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f};
	private int nlen = 5;
	private ArrayList<AutoNumBone[]> h2 = new ArrayList<>();

	public TC2b(String name, Vector2f texScale, List<BauMesh2.Index3> indexesZ, Vector3f mov)
	{
		super(name, texScale);
		this.indexesZ = indexesZ;
		this.mov = mov;
	}

	@Override
	protected void setup()
	{
		for(BauMesh2.Index3 i3 : indexesZ)
		{
			Vector3f[] positions1 = new Vector3f[3];
			Quaternion[] normals1 = new Quaternion[3];
			for(int j = 0; j < 3; j++)
			{
				Position2 p2 = i3.data[j];
				positions1[j] = p2.positionT();
				Quaternion q = new Quaternion();
				q.lookAt(p2.normalT().add(mov), Vector3f.UNIT_Y);
				normals1[j] = q;
			}
			for(int z1 = 0; z1 <= za; z1++)
				for(int z2 = 0; z2 <= za - z1; z2++)
					//for(int z3 = za - z1 - z2; z3 < za - z1 - z2; z3++)
					{
						AutoNumBone[] h2t = new AutoNumBone[nlen + 2];
						h2.add(h2t);
						int z3 = za - z1 - z2;
						AutoNumBone a1 = new AutoNumBone(boneList, inlc3(positions1, z1, z2, z3),
								slerp3(normals1, z1, z2, z3), null);
						h2t[0] = a1;
						DualP2 d1 = new DualP2(new Vector3f(w, 0, 0), new Vector3f(w, 0, 0), new Vector2f(), a1);
						for(int k = 0; k <= nlen; k++)
						{
							AutoNumBone a2 = new AutoNumBone(boneList, new Vector3f(0, la[k], 0), Quaternion.IDENTITY, a1);
							h2t[k + 1] = a2;
							DualP2 d2 = new DualP2(new Vector3f(k == nlen ? 0 : w, 0, 0), new Vector3f(w, 0, 0), new Vector2f(), a2);
							bau.cylinderTexSpin(null, false, edge, d1, d2);
							a1 = a2;
							d1 = d2;
						}
					}
		}
		System.out.println(boneList.size());
		for(Bone b : boneList)
			b.setUserControl(true);
	}

	private Quaternion slerp3(Quaternion[] s3, float... sides)
	{
		/*if(sides[0] <= 0 && sides[1] <= 0)
			return new Quaternion(s3[2]);
		if(sides[0] <= 0 && sides[2] <= 0)
			return new Quaternion(s3[1]);
		if(sides[1] <= 0 && sides[2] <= 0)
			return new Quaternion(s3[0]);*/
		float s0 = sides[0] / (sides[0] + sides[1]);
		float s2 = sides[2] / (sides[0] + sides[1] + sides[2]);
		Quaternion q1 = new Quaternion(s3[0], s3[1], s0);
		return new Quaternion(s3[2], q1, s2);
	}

	private Vector3f inlc3(Vector3f[] s3, float... sides)
	{
		/*if(sides[0] <= 0 && sides[1] <= 0)
			return new Vector3f(s3[2]);
		if(sides[0] <= 0 && sides[2] <= 0)
			return new Vector3f(s3[1]);
		if(sides[1] <= 0 && sides[2] <= 0)
			return new Vector3f(s3[0]);*/
		float s0 = sides[0] / (sides[0] + sides[1]);
		float s2 = sides[2] / (sides[0] + sides[1] + sides[2]);
		Vector3f v1 = new Vector3f().interpolateLocal(s3[0], s3[1], s0);
		return new Vector3f().interpolateLocal(s3[2], v1, s2);
	}

	public void castPhysics()
	{
		for(int k = 1; k <= nlen; k++)
			for(int i = 0; i < h2.size(); i++)
			{
				boolean inwards = false;
				for(int err = 0; err < 8; err++)
				{
					AutoNumBone a0 = h2.get(i)[k];
					AutoNumBone a1 = h2.get(i)[k + 1];
					Vector3f v0 = a0.t.transformVector(Vector3f.ZERO, null);
					Vector3f v1 = a1.t.transformVector(Vector3f.ZERO, null);
					Vector3f diff = v0.add(v1.negate());
					float maxDistance = v0.distance(v1);
					Ray ray = new Ray(v0, diff);
					CollisionResults results = new CollisionResults();
					geom.getParent().collideWith(ray, results);
					boolean collided = false;
					for(CollisionResult result : results)
						if(result.getGeometry() != geom && result.getDistance() <= maxDistance)
							collided = true;
					if(collided)
					{
						a0.b.getLocalRotation().addLocal(new Quaternion().fromAngleAxis(0.1f, Vector3f.UNIT_X)).normalizeLocal();
					}
					else if(inwards)
						break;
					else
					{
						a0.b.getLocalRotation().addLocal(new Quaternion().fromAngleAxis(-0.1f, Vector3f.UNIT_Y)).normalizeLocal();
						inwards = true;
					}
				}
			}
	}
}