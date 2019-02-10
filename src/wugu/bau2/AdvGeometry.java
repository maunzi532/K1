package wugu.bau2;

import com.jme3.animation.*;
import com.jme3.asset.*;
import com.jme3.material.*;
import com.jme3.math.*;
import com.jme3.scene.*;
import com.jme3.scene.debug.*;
import java.util.*;

public class AdvGeometry
{
	public String name;
	public Geometry geom;
	public Skeleton sk;
	public AdvBauMesh bau;
	public boolean ysk;
	public ArrayList<Bone> boneList;
	public Vector2f texScale;

	public AdvGeometry(String name, Vector2f texScale)
	{
		this.name = name;
		this.texScale = texScale;
		ysk = true;
		boneList = new ArrayList<>();
		bau = new AdvBauMesh();
	}

	public AdvGeometry(String name, Vector2f texScale, ArrayList<Bone> boneList)
	{
		this.name = name;
		this.texScale = texScale;
		this.boneList = boneList;
		bau = new AdvBauMesh();
	}

	protected void setup()
	{

	}

	public void finish()
	{
		if(ysk)
			sk = new Skeleton(boneList.toArray(new Bone[boneList.size()]));
		Mesh m = new Mesh();
		bau.fertig(m, texScale);
		geom = new Geometry(name, m);
	}

	public SkeletonControl controlSetup()
	{
		SkeletonControl skc = new SkeletonControl(sk);
		//AnimControl ac = new AnimControl(sk);
		//geom.addControl(ac);
		geom.getParent().addControl(skc);
		return skc;
	}

	public SkeletonDebugger debugger(AssetManager am)
	{
		SkeletonDebugger skd = new SkeletonDebugger("skd", sk);
		Material skdmat = new Material(am, "Common/MatDefs/Misc/Unshaded.j3md");
		skdmat.getAdditionalRenderState().setWireframe(true);
		skdmat.setColor("Color", ColorRGBA.Green);
		skdmat.getAdditionalRenderState().setDepthTest(false);
		skd.setMaterial(skdmat);
		geom.getParent().attachChild(skd);
		return skd;
	}
}