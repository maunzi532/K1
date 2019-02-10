package wugu.bau2;

import com.jme3.math.*;

public class Position2
{
	public Vector3f position;
	public Vector3f normal;
	public Vector2f texCoord;
	public BoneC2[] boneC;
	public int boneBind = 15;
	public int num = -1;

	public Position2(Vector3f position, Vector2f texCoord, AutoNumBone bone)
	{
		this.position = position;
		this.texCoord = texCoord;
		boneC = new BoneC2[]{new BoneC2(bone, 1)};
	}

	public Position2(Vector3f position, Vector3f normal, Vector2f texCoord, AutoNumBone bone)
	{
		this.position = position;
		this.normal = normal.normalizeLocal();
		this.texCoord = texCoord;
		boneC = new BoneC2[]{new BoneC2(bone, 1)};
	}

	public Position2(Vector3f position, Vector2f texCoord, BoneC2... boneC)
	{
		this.position = position;
		this.texCoord = texCoord;
		this.boneC = boneC;
	}

	public Position2(Vector3f position, Vector3f normal, Vector2f texCoord, BoneC2... boneC)
	{
		this.position = position;
		this.normal = normal.normalizeLocal();
		this.texCoord = texCoord;
		this.boneC = boneC;
	}

	public Position2(Vector3f position, Vector3f normal, Vector2f texCoord, BoneC2[] boneC, int boneBind)
	{
		this.position = position;
		this.normal = normal;
		this.texCoord = texCoord;
		this.boneC = boneC;
		this.boneBind = boneBind;
	}

	public Position2(Position2 source, Vector3f normal, Vector2f texCoord)
	{
		this(source.position, normal, texCoord, source.boneC, source.boneBind);
	}

	public Vector3f positionT()
	{
		if(boneBind == 0)
			return position;
		Vector3f re = new Vector3f();
		float w1 = 0f;
		for(int i = 0; i < boneC.length; i++)
			if((boneBind & (1 << i)) > 0)
			{
				re.addLocal(boneC[i].bone.t.transformVector(position, null).mult(boneC[i].weight));
				w1 += boneC[i].weight;
			}
		return re.divideLocal(w1);
	}

	public Vector3f normalT()
	{
		if(boneBind == 0)
			return normal;
		Vector3f re = new Vector3f();
		for(int i = 0; i < boneC.length; i++)
			if((boneBind & (1 << i)) > 0)
				re.addLocal(boneC[i].bone.t.getRotation().mult(normal).mult(boneC[i].weight));
		return re.normalizeLocal();
	}
}