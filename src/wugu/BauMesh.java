package wugu;

import com.jme3.math.*;
import com.jme3.scene.*;
import com.jme3.util.*;
import java.util.*;

public class BauMesh extends Mesh
{
	private ArrayList<Vector3f> positions = new ArrayList<>();
	private ArrayList<Index3> indexes = new ArrayList<>();
	private ArrayList<Vector3f> normals = new ArrayList<>();
	private ArrayList<Vector2f> texCoord = new ArrayList<>();

	protected void fertig()
	{
		setBuffer(VertexBuffer.Type.Position, 3,
				BufferUtils.createFloatBuffer(positions.toArray(new Vector3f[positions.size()])));
		int[] indexes2 = new int[indexes.size() * 3];
		for(int i = 0; i < indexes.size(); i++)
			System.arraycopy(indexes.get(i).data, 0, indexes2, i * 3, 3);
		setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(indexes2));
		setBuffer(VertexBuffer.Type.Normal, 3, BufferUtils.createFloatBuffer(normals.toArray(new Vector3f[positions.size()])));
		//setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord.toArray(new Vector2f[positions.size()])));
		updateBound();
	}

	private void indexIV(int i0, boolean inv)
	{
		indexes.add(new Index3(i0 + 2, i0 + (inv ? 3 : 0), i0 + 1));
		indexes.add(new Index3(i0 + 1, i0 + (inv ? 0 : 3), i0 + 2));
	}

	private Vector3f spinNormal(Vector3f normal)
	{
		return new Vector3f(normal.z, normal.y, -normal.x);
	}

	protected void addv(Vector3f normal, boolean inv, float... data)
	{
		normal.normalizeLocal();
		indexIV(positions.size(), inv);
		for(int i = 0; i < 4; i++)
		{
			normals.add(normal);
			positions.add(new Vector3f(data[i * 5], data[i * 5 + 1], data[i * 5 + 2]));
			texCoord.add(new Vector2f(data[i * 5 + 3], data[i * 5 + 4]));
		}
	}

	protected void addq(Vector3f normal, boolean inv, int v, float... data1)
	{
		normal.normalizeLocal();
		switch(v)
		{
			case 0:
				addv(normal, inv, data1[0], data1[1], data1[2], data1[6], data1[7],
						data1[0], data1[4], data1[2], data1[8], data1[7],
						data1[0], data1[1], data1[5], data1[6], data1[9],
						data1[0], data1[4], data1[5], data1[8], data1[9]);
				break;
			case 1:
				addv(normal, inv, data1[0], data1[1], data1[2], data1[6], data1[7],
						data1[3], data1[1], data1[2], data1[8], data1[7],
						data1[0], data1[1], data1[5], data1[6], data1[9],
						data1[3], data1[1], data1[5], data1[8], data1[9]);
				break;
			case 2:
				addv(normal, inv, data1[0], data1[1], data1[2], data1[6], data1[7],
						data1[0], data1[4], data1[2], data1[8], data1[7],
						data1[3], data1[1], data1[2], data1[6], data1[9],
						data1[3], data1[4], data1[2], data1[8], data1[9]);
				break;
			case 3:
				addv(normal, inv, data1[0], data1[1], data1[2], data1[6], data1[7],
						data1[0], data1[1], data1[5], data1[8], data1[7],
						data1[0], data1[4], data1[2], data1[6], data1[9],
						data1[0], data1[4], data1[5], data1[8], data1[9]);
				break;
			case 4:
				addv(normal, inv, data1[0], data1[1], data1[2], data1[6], data1[7],
						data1[0], data1[1], data1[5], data1[8], data1[7],
						data1[3], data1[1], data1[2], data1[6], data1[9],
						data1[3], data1[1], data1[5], data1[8], data1[9]);
				break;
			case 5:
				addv(normal, inv, data1[0], data1[1], data1[2], data1[6], data1[7],
						data1[3], data1[1], data1[2], data1[8], data1[7],
						data1[0], data1[4], data1[2], data1[6], data1[9],
						data1[3], data1[4], data1[2], data1[8], data1[9]);
				break;
		}
	}

	protected void addd(Vector3f normal, boolean inv, float... data)
	{
		normal.normalizeLocal();
		int i0 = positions.size();
		indexes.add(new Index3(i0, i0 + (inv ? 2 : 1), i0 + (inv ? 1 : 2)));
		for(int i = 0; i < 3; i++)
		{
			normals.add(normal);
			positions.add(new Vector3f(data[i * 3], data[i * 3 + 1], data[i * 3 + 2]));
			texCoord.add(new Vector2f(data[9 + i * 2], data[10 + i * 2]));
		}
	}

	protected void spinaddv(Vector3f normal, boolean inv, float... data1)
	{
		float[] data2 = new float[20];
		System.arraycopy(data1, 0, data2, 0, 20);
		addv(normal, inv, data2);
		for(int i = 0; i < 4; i++)
		{
			data2[i * 5] = data1[i * 5 + 2];
			data2[i * 5 + 2] = -data1[i * 5];
		}
		normal = spinNormal(normal);
		addv(normal, inv, data2);
		for(int i = 0; i < 4; i++)
		{
			data2[i * 5] = -data1[i * 5];
			data2[i * 5 + 2] = -data1[i * 5 + 2];
		}
		normal = spinNormal(normal);
		addv(normal, inv, data2);
		for(int i = 0; i < 4; i++)
		{
			data2[i * 5] = -data1[i * 5 + 2];
			data2[i * 5 + 2] = data1[i * 5];
		}
		normal = spinNormal(normal);
		addv(normal, inv, data2);
	}

	protected void spinaddq(Vector3f normal, boolean inv, int v, float... data1)
	{
		spinaddq(normal, v, 15, inv, data1);
	}

	protected void spinaddq(Vector3f normal, int v, int vis, boolean inv, float... data1)
	{
		int va = v >= 3 ? 3 : 0;
		v -= va;
		float[] data2 = new float[10];
		System.arraycopy(data1, 0, data2, 0, 10);
		if((vis & 1) > 0)
			addq(normal, inv, v + va, data2);
		if(v == 1)
			va = 3 - va;
		data2[0] = data1[2];
		data2[2] = -data1[0];
		data2[3] = data1[5];
		data2[5] = -data1[3];
		normal = spinNormal(normal);
		if((vis & 2) > 0)
			addq(normal, inv, 2 - v + va, data2);
		if(v == 1)
			va = 3 - va;
		data2[0] = -data1[0];
		data2[2] = -data1[2];
		data2[3] = -data1[3];
		data2[5] = -data1[5];
		normal = spinNormal(normal);
		if((vis & 4) > 0)
			addq(normal, inv, v + va, data2);
		if(v == 1)
			va = 3 - va;
		data2[0] = -data1[2];
		data2[2] = data1[0];
		data2[3] = -data1[5];
		data2[5] = data1[3];
		normal = spinNormal(normal);
		if((vis & 8) > 0)
			addq(normal, inv, 2 - v + va, data2);
	}

	protected void spinaddd(Vector3f normal, boolean inv, float... data1)
	{
		float[] data2 = new float[15];
		System.arraycopy(data1, 0, data2, 0, 15);
		addd(normal, inv, data2);
		for(int i = 0; i < 4; i++)
		{
			data2[i * 3] = data1[i * 3 + 2];
			data2[i * 3 + 2] = -data1[i * 3];
		}
		normal = spinNormal(normal);
		addd(normal, inv, data2);
		for(int i = 0; i < 4; i++)
		{
			data2[i * 3] = -data1[i * 3];
			data2[i * 3 + 2] = -data1[i * 3 + 2];
		}
		normal = spinNormal(normal);
		addd(normal, inv, data2);
		for(int i = 0; i < 4; i++)
		{
			data2[i * 3] = -data1[i * 3 + 2];
			data2[i * 3 + 2] = data1[i * 3];
		}
		normal = spinNormal(normal);
		addd(normal, inv, data2);
	}

	private class Index3
	{
		public int[] data;

		public Index3(int i0, int i1, int i2)
		{
			data = new int[]{i0, i1, i2};
		}
	}
}