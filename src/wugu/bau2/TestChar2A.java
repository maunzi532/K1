package wugu.bau2;

import com.jme3.animation.*;
import com.jme3.math.*;
import com.jme3.scene.*;
import java.util.*;

public class TestChar2A
{
	public static final float t81 = 0.5625f;
	public static final float t82 = 0.6875f;
	public static final float t41 = 0.625f;

	public ArrayList<Bone> boneList;
	public AdvGeometry mainGeo;
	public AdvGeometry tc2a;

	public void init(String name)
	{
		mainGeo = new AdvGeometry(name, new Vector2f(0.25f, 0.25f));
		boneList = mainGeo.boneList;
		k1 = new AutoNumBone(boneList, new Vector3f(0, 4, 0), Quaternion.IDENTITY, null);
		k2 = new AutoNumBone(boneList, new Vector3f(0, 1, 0), Quaternion.IDENTITY, k1);
		k3 = new AutoNumBone(boneList, new Vector3f(0, 1, 0), Quaternion.IDENTITY, k2);
		k4 = new AutoNumBone(boneList, new Vector3f(0, 2, 0), Quaternion.IDENTITY, k2);
		k1();
		l1(-1);
		l1(1);
		tc2a = new AdvGeometry(name + "_Unshaded", Vector2f.UNIT_XY);
		l2();
		a1(-1);
		a1(1);
		//f1(-1);
		//f1(1);
		z1();
		/*tc2b = new TC2b("H1", Vector2f.UNIT_XY, bau.indexes.subList(tc2b0, tc2b1), new Vector3f(0, -1, 0));
		tc2b.setup();
		tc2b.finish();*/
		mainGeo.finish();
		tc2a.finish();
		for(Bone b : boneList)
			b.setUserControl(true);
	}

	public void attachToNodes(Node r)
	{
		Node n0 = new Node();
		r.attachChild(n0);
		Node n1 = new Node();
		n0.attachChild(n1);
		n1.attachChild(mainGeo.geom);
		n1.attachChild(tc2a.geom);
		Node n2 = new Node();
		//n2.attachChild(tc2b.geom);
		n0.attachChild(n2);
	}

	public static final float KK1 = 1.1f;
	public static final float KK2 = 0.33f;
	public static final float KK3 = 0.9f;
	public static final float KK4 = 0.45f;
	public static final float KK5 = 1.05f;
	public static final float KK6 = 0.8f;
	public static final float KK7 = 0.9f;
	public static final float KK8 = 0.75f;
	public static final float KH1 = -0.3f;
	public static final float K41 = 0.3f;
	public static final float K42 = 0.2f;
	public static final float K51 = 1.02f;
	public static final float K52 = -0.2f;

	protected AutoNumBone k1;
	protected AutoNumBone k2;
	protected AutoNumBone k3;
	protected AutoNumBone k4;
	protected DualP2 zk3a;
	protected DualP2 zk3b;
	protected DualP2 zk4a;
	protected DualP2 zk4b;
	protected DualP2 zk0a;
	protected DualP2 zk0b;

	private void k1()
	{
		DualP2 zk1a = new DualP2(new Vector3f(-KK1, 0, -KK2), new Vector3f(-2f, 0, -1),
				new Vector2f(t81, 0.1f), k1);
		DualP2 zk1b = new DualP2(new Vector3f(-KK3, 0, -KK4), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 0.1f), k1);
		DualP2 zk2a = new DualP2(new Vector3f(-KK5, 0, -KK2), new Vector3f(-2f, 0, -1),
				new Vector2f(t81, 0.2f), k2);
		DualP2 zk2b = new DualP2(new Vector3f(-KK6, 0, -KK4), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 0.2f), k2);
		zk3a = new DualP2(new Vector3f(-KK7, KH1, -KK2), new Vector3f(-2f, 0, -0.5f),
				new Vector2f(t81, 0.3f), k3);
		zk3b = new DualP2(new Vector3f(-KK8, KH1, -KK4), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 0.3f), k3);
		zk4a = new DualP2(new Vector3f(-K41, 0.1f, -K42), new Vector3f(-1.5f, 1f, -0.5f),
				new Vector2f(t81, 0.4f), k3);
		zk4b = new DualP2(new Vector3f(-K42, 0.1f, -K41), new Vector3f(-1, 1f, -1.5f),
				new Vector2f(t82, 0.4f), k3);
		zk0a = new DualP2(new Vector3f(-KK1 * K51, K52, -KK2 * K51), new Vector3f(-2f, 0, -1),
				new Vector2f(t81, 0.0f), k1);
		zk0b = new DualP2(new Vector3f(-KK3 * K51, K52, -KK4 * K51), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 0.0f), k1);
		mainGeo.bau.cylinderTex2DP(null, false, zk0a, zk0b, zk1a, zk1b);
		mainGeo.bau.cylinderTex2DP(null, false, zk1a, zk1b, zk2a, zk2b);
		mainGeo.bau.cylinderTex2DP(null, false, zk2a, zk2b, zk3a, zk3b);
		mainGeo.bau.indexQ(true, zk3b, zk3b.sCl(1), zk4b, zk4b.sCl(1));
		mainGeo.bau.indexQ(true, zk3b.sCl(-2), zk3b.sCl(-1), zk4b.sCl(-2), zk4b.sCl(-1));
	}

	protected DualP2[] k3a0;

	private void l1(int xm)
	{
		AutoNumBone l1 = new AutoNumBone(boneList, new Vector3f(xm * 0.6f, 0, 0),
				new Quaternion().fromAngleAxis(-FastMath.HALF_PI * xm, Vector3f.UNIT_Y), k1);
		AutoNumBone l2 = new AutoNumBone(boneList, new Vector3f(0, -1.8f, 0),
				Quaternion.IDENTITY, l1);
		AutoNumBone l3 = new AutoNumBone(boneList, new Vector3f(0, -1.7f, 0),
				Quaternion.IDENTITY, l2);
		DualP2 k1a = new DualP2(new Vector3f(-0.41f, 0, 0),
				new Vector2f(1.1f, 0.3f), l1).autoNormal();
		DualP2 k2a = new DualP2(new Vector3f(-0.39f, 0, 0),
				new Vector2f(1.1f, 0.2f), new BoneC2(l2, 0.5f), new BoneC2(l1, 0.5f)).setBind(1).autoNormal();
		DualP2 k3a = new DualP2(new Vector3f(-0.38f, 0, 0),
				new Vector2f(1.1f, 0.1f), l3).autoNormal();
		if(k3a0 == null)
			k3a0 = new DualP2[2];
		k3a0[(xm + 1) / 2] = k3a;
		mainGeo.bau.cylinderTexSpin(null, true, 8, k1a, k2a);
		mainGeo.bau.cylinderTexSpin(null, true, 8, k2a, k3a);

		s1(xm, l3);
	}

	private void l2()
	{
		Position2[] array1 = new Position2[]{zk0a.sCl(2), zk0a.sCl(1), zk0b.sCl(1), zk0b, zk0a,
				zk0a.sCl(-1), zk0b.sCl(-1), zk0b.sCl(-2)};
		tc2a.bau.flatPoly(true, array1, new Vector3f(0, -1, 0),
				Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO,
				Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO, Vector2f.ZERO);
		tc2a.bau.abdeck(true, 8, k3a0[0]);
		tc2a.bau.abdeck(true, 8, k3a0[1]);
	}

	public static final float KS1 = 0.31f;
	public static final float KS2 = 0.1f;
	public static final float KS3 = 0f;
	public static final float KS4 = -0.1f;
	public static final float KS5 = -0.05f;
	public static final float KS6 = -0.4f;
	public static final float KS7 = -0.03f;
	public static final float KS8 = 0.55f;
	public static final float KS9 = 0.25f;
	public static final float KS10 = -0.03f;
	public static final float KSC1 = 0.33f;
	public static final float KSC2 = 0.18f;
	public static final float KSC3 = 0.32f;
	public static final float KSC4 = 0.24f;
	public static final float KSC5 = 0.19f;
	public static final float KSC6 = 0.28f;
	public static final float KSA1 = 0.20f;
	public static final float KSA2 = 0.13f;
	public static final float KSA3 = 0.15f;
	public static final float KSA4 = 0.22f;
	public static final float KSA5 = 0.19f;

	private void s1(int xm, AutoNumBone l3)
	{
		AutoNumBone l4 = new AutoNumBone(boneList, new Vector3f(0, KS4, 0), Quaternion.IDENTITY, l3);
		AutoNumBone l5 = new AutoNumBone(boneList, new Vector3f(0, KS6 - KS5, 0), Quaternion.IDENTITY, l4);
		AutoNumBone l6 = new AutoNumBone(boneList, new Vector3f(0, KS5, 0), Quaternion.IDENTITY, l5);
		AutoNumBone l7 = new AutoNumBone(boneList, new Vector3f(KS8 * xm, KS6 - KS7, 0), Quaternion.IDENTITY, l4);
		AutoNumBone l8 = new AutoNumBone(boneList, new Vector3f(KS9 * xm, 0, 0), Quaternion.IDENTITY, l7);
		DualP2 s1a = new DualP2(new Vector3f(-KS1 * xm, KS3, -KS2), new Vector3f(-2f * xm, 0, -1),
				new Vector2f(t81 + 2, 0f), l3);
		DualP2 s1b = new DualP2(new Vector3f(-KS2 * xm, KS3, -KS1), new Vector3f(-1 * xm, 0, -2f),
				new Vector2f(t82 + 2, 0f), l3);
		DualP2 s2a = new DualP2(new Vector3f(-KS1 * xm, 0, -KS2), new Vector3f(-2f * xm, 0, -1),
				new Vector2f(t81 + 2, 0.1f), l4);
		DualP2 s2b = new DualP2(new Vector3f(-KS2 * xm, 0, -KS1), new Vector3f(-1 * xm, 0, -2f),
				new Vector2f(t82 + 2, 0.1f), l4);
		DualP2 s2a1 = new DualP2(new Vector3f(-KS1 * xm, 0, -KS2), new Vector3f(1.5f * xm, 1, 1),
				new Vector2f(t81 + 2, 0.1f), l4);
		DualP2 s2b1 = new DualP2(new Vector3f(-KS2 * xm, 0, -KS1), new Vector3f(xm, 1, 1.5f),
				new Vector2f(t82 + 2, 0.1f), l4);
		DualP2 s2ca = new DualP2(new Vector3f(-KSC1 * xm, 0, -KSC2), new Vector3f(-1.5f * xm, 0, -1),
				new Vector2f(t81 + 2, 0.2f), l4);
		DualP2 s2cb = new DualP2(new Vector3f(-KSC2 * xm, 0, -KSC1), new Vector3f(-1 * xm, 0, -1.5f),
				new Vector2f(t82 + 2, 0.2f), l4);
		DualP2 s3ca = new DualP2(new Vector3f(-KSC1 * xm, 0, -KSC2), new Vector3f(-1.5f * xm, 0, -1),
				new Vector2f(t81 + 2, 0.3f), l5);
		DualP2 s3cb = new DualP2(new Vector3f(-KSC2 * xm, 0, -KSC1), new Vector3f(-1 * xm, 0, -1.5f),
				new Vector2f(t82 + 2, 0.3f), l5);
		DualP2 sbca = new DualP2(new Vector3f(-KSC1 * xm, 0, -KSC2), new Vector3f(-1.5f * xm, 0, -1),
				new Vector2f(t81 + 2, 0.4f), l6);
		DualP2 sbcb = new DualP2(new Vector3f(-KSC2 * xm, 0, -KSC1), new Vector3f(-1 * xm, 0, -1.5f),
				new Vector2f(t82 + 2, 0.4f), l6);
		mainGeo.bau.cylinderTex2DP(null, xm > 0, s1a, s1b, s2a, s2b);
		mainGeo.bau.cylinderTex2DP(0b1111100, xm > 0, s2a1, s2b1, s2ca, s2cb);
		mainGeo.bau.cylinderTex2DP(0b1111100, xm > 0, s2ca, s2cb, s3ca, s3cb);
		mainGeo.bau.cylinderTex2DP(0b1111100, xm > 0, s3ca, s3cb, sbca, sbcb);
		mainGeo.bau.flatPoly(xm < 0, new Position2[]{sbcb.sCl(2), sbcb.sCl(3), sbca.sCl(3), sbca, sbcb, sbcb.sCl(1)},
				new Vector3f(0, -1, 0), new Vector2f(2.4f, 0.4f), new Vector2f(2.3f, 0.4f), new Vector2f(2.2f, 0.45f),
				new Vector2f(2.2f, 0.55f), new Vector2f(2.3f, 0.6f), new Vector2f(2.4f, 0.6f));

		DualP2 s2bC11 = new DualP2(s2b1.sCl(1), new Vector3f(0, 1.5f, -0.5f), new Vector2f(3.0f, 0.9f));
		DualP2 s2bC12 = new DualP2(s2cb.sCl(1), new Vector3f(0, 0, -1f), new Vector2f(3.0f, 0.85f));
		DualP2 s2bC13 = new DualP2(s3cb.sCl(1), new Vector3f(0, 0, -1f), new Vector2f(3.0f, 0.8f));
		DualP2 s2bC21 = new DualP2(s2b1.sCl(2), new Vector3f(0, 1.5f, 0.5f), new Vector2f(2.0f, 0.9f));
		DualP2 s2bC22 = new DualP2(s2cb.sCl(2), new Vector3f(0, 0, 1f), new Vector2f(2.0f, 0.85f));
		DualP2 s2bC23 = new DualP2(s3cb.sCl(2), new Vector3f(0, 0, 1f), new Vector2f(2.0f, 0.8f));


		DualP2 sbdb = new DualP2(new Vector3f(KSC2 * xm, KS10, KSC1), new Vector3f(xm, 0, 1.5f),
				new Vector2f(2f, 0.65f), l5);
		DualP2 sbdbC = new DualP2(sbdb.sCl(3), new Vector3f(xm, 0, -1.5f), new Vector2f(3f, 0.65f));
		DualP2 s4ca = new DualP2(new Vector3f(0, 0, KSC3), new Vector3f(0, 0, 1),
				new Vector2f(2.25f, 0.7f), l8);
		DualP2 s4cb = new DualP2(new Vector3f(KSC4 * xm, 0, KSC4), new Vector3f(xm, 0, 1),
				new Vector2f(2.375f, 0.7f), l8);
		DualP2 s4cc = new DualP2(new Vector3f(KSC5 * xm, 0, -KSC5), new Vector3f(xm, 0, -1),
				new Vector2f(2.625f, 0.7f), l8);
		DualP2 s4cd = new DualP2(new Vector3f(KSC6 * xm, 0, 0), new Vector3f(xm, 0, 0),
				new Vector2f(2.5f, 0.7f), l8);
		DualP2 s4ce = new DualP2(new Vector3f(0, 0, KSC3), new Vector3f(0, 0, 1),
				new Vector2f(2.1f, 0.7f), l7);
		DualP2 s4ceC = new DualP2(s4ce.sCl(2), new Vector3f(0, 0, -1),
				new Vector2f(2.9f, 0.7f));
		DualP2 sfca = new DualP2(new Vector3f(0, KS7, KSC3), new Vector3f(0, 0, 1),
				new Vector2f(2.25f, 0.6f), l8);
		DualP2 sfcb = new DualP2(new Vector3f(KSC4 * xm, KS7, KSC4), new Vector3f(xm, 0, 1),
				new Vector2f(2.375f, 0.6f), l8);
		DualP2 sfcc = new DualP2(new Vector3f(KSC5 * xm, KS7, -KSC5), new Vector3f(xm, 0, -1),
				new Vector2f(2.625f, 0.6f), l8);
		DualP2 sfcd = new DualP2(new Vector3f(KSC6 * xm, KS7, 0), new Vector3f(xm, 0, 0),
				new Vector2f(2.5f, 0.6f), l8);
		DualP2 sfce = new DualP2(new Vector3f(0, KS7, KSC3), new Vector3f(0, 0, 1),
				new Vector2f(2.1f, 0.6f), l7);
		DualP2 sfceC = new DualP2(sfce.sCl(2), new Vector3f(0, 0, -1),
				new Vector2f(2.9f, 0.6f));
		mainGeo.bau.indexQ(xm > 0, s4ce, s4ca, sfce, sfca);
		mainGeo.bau.indexQ(xm > 0, s4ca, s4cb, sfca, sfcb);
		mainGeo.bau.indexQ(xm > 0, s4cb, s4cd, sfcb, sfcd);
		mainGeo.bau.indexQ(xm > 0, s4cd, s4cc, sfcd, sfcc);
		mainGeo.bau.indexQ(xm > 0, s4cc, s4ca.sCl(2), sfcc, sfca.sCl(2));
		mainGeo.bau.indexQ(xm > 0, s4ca.sCl(2), s4ceC, sfca.sCl(2), sfceC);
		mainGeo.bau.flatPoly(xm > 0, new Position2[]{sfce, sfca, sfcb, sfcd, sfcc, sfca.sCl(2), sfceC},
				new Vector3f(0, -1, 0), new Vector2f(2.7f, 0.4f), new Vector2f(2.8f, 0.4f), new Vector2f(2.88f, 0.42f),
				new Vector2f(2.9f, 0.5f), new Vector2f(2.87f, 0.57f), new Vector2f(2.8f, 0.6f), new Vector2f(2.7f, 0.6f));

		mainGeo.bau.indexQ(xm < 0, s4ce, s2bC23, sfce, sbdb);
		mainGeo.bau.indexQ(xm > 0, s4ceC, s2bC13, sfceC, sbdbC);
		mainGeo.bau.flatPoly(xm < 0, new Position2[]{sfce, sbdb, sbdbC, sfceC},
				new Vector3f(-0.05f * xm, -1, 0), new Vector2f(2.7f, 0.4f), new Vector2f(2.5f, 0.4f),
				new Vector2f(2.5f, 0.6f), new Vector2f(2.7f, 0.6f));
		mainGeo.bau.flatPoly(xm > 0, new Position2[]{sbcb.sCl(2), sbdb, sbdbC, sbcb.sCl(1)},
				new Vector3f(xm, 0, 0), new Vector2f(2.4f, 0.4f), new Vector2f(2.5f, 0.4f),
				new Vector2f(2.5f, 0.6f), new Vector2f(2.4f, 0.6f));

		DualP2 s5a = new DualP2(new Vector3f(KSA3 * xm, KSA2, KSA1), new Vector3f(KSA3 * xm / 4, KSA2, KSA1),
				new Vector2f(2.4f, 0.8f), l8);
		DualP2 s6a = new DualP2(new Vector3f(0, KSA5, KSA4), new Vector3f(0, KSA5, KSA4),
				new Vector2f(2.3f, 0.8f), l7);
		DualP2 s2a2 = new DualP2(new Vector3f(-KS1 * xm, 0, -KS2), new Vector3f(-0.2f * xm, 1, 0),
				new Vector2f(2.1f, 0.8f), l4);
		DualP2 s2b2 = new DualP2(new Vector3f(-KS2 * xm, 0, -KS1), new Vector3f(-0.1f * xm, 1, -0.2f),
				new Vector2f(2.1f, 0.8f), l4);
		mainGeo.bau.indexPoly(xm < 0, s4cd, s4cc, s5a.sCl(3), s5a, s4cb);
		mainGeo.bau.indexQ(xm < 0, s5a, s5a.sCl(3), s6a, s6a.sCl(3));
		mainGeo.bau.indexPoly(xm < 0, s4cb, s5a, s4ca);
		mainGeo.bau.indexPoly(xm > 0, s4cc, s5a.sCl(3), s4ca.sCl(2));
		mainGeo.bau.indexPoly(xm < 0, s4ca, s5a, s6a, s4ce);
		mainGeo.bau.indexPoly(xm > 0, s4ca.sCl(2), s5a.sCl(3), s6a.sCl(3), s4ceC);
		mainGeo.bau.indexPoly(xm < 0, s4ce, s6a, s2bC22, s2bC23);
		mainGeo.bau.indexPoly(xm > 0, s4ceC, s6a.sCl(3), s2bC12, s2bC13);
		mainGeo.bau.indexQ(xm < 0, s6a, s6a.sCl(3), s2a2.sCl(2), s2a2.sCl(1));
		mainGeo.bau.indexPoly(xm < 0, s2bC21, s2bC22, s6a);
		mainGeo.bau.indexPoly(xm > 0, s2bC11, s2bC12, s6a.sCl(3));
		mainGeo.bau.indexPoly(xm < 0, s2a2.sCl(2), s2b2.sCl(2), s6a);
		mainGeo.bau.indexPoly(xm > 0, s2a2.sCl(1), s2b2.sCl(1), s6a.sCl(3));
	}

	public static final float KA1 = 0.32f;
	public static final float KA2 = 0.17f;
	public static final float KA3 = 0.21f;
	public static final float KA3A = 0.3f;
	public static final float KA4c = 0.30f;
	public static final float KA4d = 0.15f;
	public static final float KA5 = 0.18f;
	public static final float KA6 = 0.7f;
	public static final float KA7 = 0.8f;
	public static final float HA1 = 0.065f;
	public static final float HA2 = 0.95f;

	private void a1(int xm)
	{
		AutoNumBone a1 = new AutoNumBone(boneList, new Vector3f(xm * 1.05f, 0, 0),
				new Quaternion().fromAngleAxis(-FastMath.HALF_PI * xm, Vector3f.UNIT_Y)
				.mult(new Quaternion().fromAngleAxis(FastMath.HALF_PI * 0.2f, Vector3f.UNIT_X)), k3);
		AutoNumBone a2 = new AutoNumBone(boneList, new Vector3f(0, -1.5f, 0),
				Quaternion.IDENTITY, a1);
		AutoNumBone a3 = new AutoNumBone(boneList, new Vector3f(0, -1.2f, 0),
				Quaternion.IDENTITY, a2);
		DualP2 mkt1 = new DualP2(new Vector3f(-KA3, 0.02f, -0.12f), new Vector3f(-1, 1, -1),
				new Vector2f(0.15f, 0.8f), new BoneC2(a1, 0.6f), new BoneC2(k3, 0.4f)).setBind(1);
		DualP2 mkt1C = new DualP2(mkt1.sCl(1), new Vector3f(1, 1, -1),
				new Vector2f(0.85f, 0.8f));
		DualP2 mkt2 = new DualP2(new Vector3f(-KA3A, 0.18f, 0.25f), new Vector3f(-1, 2, 0),
				new Vector2f(0.35f, 0.6f), new BoneC2(a1, 0.3f), new BoneC2(k3, 0.7f)).setBind(1);
		DualP2 mkt2C = new DualP2(mkt2.sCl(1), new Vector3f(1, 2, 0),
				new Vector2f(0.65f, 0.6f));
		DualP2 k1a = new DualP2(new Vector3f(-KA1, -0.3f, -KA2), new Vector3f(-2, 0, -1),
				new Vector2f(t81, 1.1f), new BoneC2(a1, 0.9f), new BoneC2(k3, 0.1f)).setBind(1);
		DualP2 k1b = new DualP2(new Vector3f(-KA2, -0.3f, -KA3), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 1.1f), new BoneC2(a1, 0.9f), new BoneC2(k3, 0.1f)).setBind(1);
		DualP2 k2a = new DualP2(new Vector3f(-KA1 * KA6, 0, -KA2 * KA6), new Vector3f(-2, 0, -1),
				new Vector2f(t81, 1.2f), new BoneC2(a2, 0.6f), new BoneC2(a1, 0.4f)).setBind(1);
		DualP2 k2b = new DualP2(new Vector3f(-KA2 * KA6, 0, -KA1 * KA6), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 1.2f), new BoneC2(a2, 0.6f), new BoneC2(a1, 0.4f)).setBind(1);
		DualP2 k2c = new DualP2(new Vector3f(-KA4c * KA7, -0.4f, -KA4d * KA7), new Vector3f(-2, 0, -1),
				new Vector2f(t81, 1.3f), a2);
		DualP2 k2d = new DualP2(new Vector3f(-KA4d * KA7, -0.4f, -KA4c * KA7), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 1.3f), a2);
		DualP2 k3c = new DualP2(new Vector3f(-KA4c, 0, -KA4d), new Vector3f(-2, 0, -1),
				new Vector2f(t81, 1.4f), a3);
		DualP2 k3d = new DualP2(new Vector3f(-KA4d, 0, -KA5), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 1.4f), a3);
		DualP2 k3ch = new DualP2(new Vector3f(-KA4c * HA2, 0, -KA4d * HA2), new Vector3f(-2, 0, -1),
				new Vector2f(t81, 1.5f), a3);
		DualP2 k3dh = new DualP2(new Vector3f(-KA4d * HA2, 0, -KA5 * HA2), new Vector3f(-1, 0, -1.5f),
				new Vector2f(t82, 1.5f), a3);

		mainGeo.bau.indexPoly(false, mkt1, mkt1C, k1a.sCl(1), k1b.sCl(1), k1b, k1a);
		mainGeo.bau.indexQ(true, mkt1, mkt1C, mkt2, mkt2C);
		mainGeo.bau.indexQ(true, mkt1, mkt2, k1a, k1a.sCl(-1));
		mainGeo.bau.indexQ(false, mkt1C, mkt2C, k1a.sCl(1), k1a.sCl(2));
		mainGeo.bau.indexPoly(true, mkt2, mkt2C, zk4a.sCl(-1 - xm), zk4a.sCl(xm), zk4b.sCl(xm));
		mainGeo.bau.indexPoly(true, mkt2C, zk4b.sCl(-1 - xm), zk4a.sCl(-1 - xm));

		mainGeo.bau.indexQ(false, zk3a.sCl(-xm - 1), zk3a.sCl(xm), k1b.sCl(-2), k1b.sCl(-1));
		mainGeo.bau.indexQ(true, zk3b.sCl(xm), zk3a.sCl(xm), k1a.sCl(-1), k1b.sCl(-1));
		mainGeo.bau.indexQ(true, zk3a.sCl(-1 - xm), zk3b.sCl(-1 - xm), k1b.sCl(-2), k1a.sCl(-2));
		mainGeo.bau.indexQ(false, zk3b.sCl(xm), zk4b.sCl(xm), k1a.sCl(-1), mkt2);
		mainGeo.bau.indexQ(true, zk3b.sCl(-1 - xm), zk4b.sCl(-1 - xm), k1a.sCl(-2), mkt2C);

		mainGeo.bau.cylinderTex2DP(null, true, k1a, k1b, k2a, k2b);
		mainGeo.bau.cylinderTex2DP(null, true, k2a, k2b, k2c, k2d);
		mainGeo.bau.cylinderTex2DP(null, true, k2c, k2d, k3c, k3d);
		mainGeo.bau.cylinderTex2DP(null, true, k3c, k3d, k3ch, k3dh);

		h1(xm, a3);
	}

	private void h1(int xm, AutoNumBone a3)
	{
		AutoNumBone[] h1 = new AutoNumBone[20];
		DualP2[] d1 = new DualP2[5];
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < (i == 0 ? 3 : 4); j++)
			{
				if(i == 0 && j == 0)
					h1[j * 5 + i] = new AutoNumBone(boneList, new Vector3f(0.4f * 0.9f * xm,
							-0.4f * 0.7f, 0.2f),
							new Quaternion().fromAngleAxis(-FastMath.HALF_PI * xm, Vector3f.UNIT_Y), a3);
				else
					h1[j * 5 + i] = new AutoNumBone(boneList, new Vector3f(i == 0 ? 0 : (0.4f - i * 0.2f) * (j == 0 ? 0.85f : 0.15f) * xm,
							-0.4f * (j == 0 ? 1.2f : i == 0 ? 0.5f : 0.4f), 0), Quaternion.IDENTITY, j == 0 ? a3 : h1[(j - 1) * 5 + i]);
			}
			DualP2 lastf = new DualP2(new Vector3f(HA1 * xm, 0, HA1),
					new Vector3f(HA1 * xm * 0.2f, 0, HA1),
					new Vector2f(t41 + 1, 1.6f), h1[i]);
			d1[i] = lastf;
			for(int j = 1; j < (i == 0 ? 2 : 3); j++)
			{
				DualP2 newf = new DualP2(new Vector3f(HA1 * xm, 0, HA1),
						new Vector2f(t41 + 1, 1.6f - 0.1f * j), h1[5 * j + i]).autoNormal();
				mainGeo.bau.cylinderTex1DP(null, xm > 0, lastf, newf);
				lastf = newf;
			}
			int ab = 5 * (i == 0 ? 2 : 3) + i;
			DualP2 end1 = new DualP2(new Vector3f(HA1 * xm * 0.85f, 0, HA1),
					new Vector3f(HA1 * xm * 0.85f, HA1 * 0.4f * xm, HA1),
					new Vector2f(t41 + 1, 1.3f), new BoneC2(h1[ab], 0.75f), new BoneC2(h1[ab - 5], 0.25f));
			DualP2 end2 = new DualP2(new Vector3f(HA1 * xm, 0, HA1),
					new Vector3f(HA1 * xm, HA1 * 0.8f * xm, HA1),
					new Vector2f(t41 + 1, 1.2f), h1[ab]);
			DualP2 end4 = new DualP2(new Vector3f(HA1 * xm * 0.9f, 0, HA1),
					new Vector3f(HA1 * xm * 0.1f, 0, HA1),
					new Vector2f(t41 + 1.1f, 1.25f), new BoneC2(h1[ab], 0.6f), new BoneC2(h1[ab - 5], 0.4f));
			mainGeo.bau.indexQ(xm < 0, end1, end1.sCl(1), end2.sCl(-1), end2.sCl(2));
			mainGeo.bau.indexQ(xm < 0, lastf, lastf.sCl(1), end1, end1.sCl(1));
			mainGeo.bau.indexQ(xm > 0, lastf, lastf.sCl(-1), end1, end2.sCl(-1));
			mainGeo.bau.indexQ(xm > 0, lastf.sCl(2), lastf.sCl(1), end2.sCl(2), end1.sCl(1));
			mainGeo.bau.indexPoly(xm > 0, lastf.sCl(-1), end4.sCl(-2), end4.sCl(-1), end2.sCl(-1));
			mainGeo.bau.indexPoly(xm > 0, lastf.sCl(-2), end2.sCl(-2), end4.sCl(-2), lastf.sCl(-1));

			DualP2 end2a = new DualP2(end2, new Vector3f(HA1 * xm * 0.1f, 0, HA1),
				new Vector2f(1f, 1.05f));
			DualP2 end3a = new DualP2(new Vector3f(HA1 * xm * 0.9f, -0.01f, HA1),
					new Vector3f(HA1 * xm * 0.1f, 0, HA1),
					new Vector2f(1f, 1f), h1[ab]);
			DualP2 end4a = new DualP2(end4, new Vector3f(HA1 * xm * 0.1f, 0, HA1),
					new Vector2f(1f, 1.2f));
			mainGeo.bau.indexPoly(xm < 0, end3a.sCl(3), end3a.sCl(2), end2a.sCl(2),
					end4a.sCl(2), end4a.sCl(3), end2a.sCl(3));
		}

		DualP2 khc = new DualP2(new Vector3f(-KA4c * HA2 * xm, 0, -KA4d * HA2), new Vector3f(-2 * xm, 0, -1),
				new Vector2f(t81 + 1, 1.8f), a3);
		DualP2 khd = new DualP2(new Vector3f(-KA4d * HA2 * xm, 0, -KA5 * HA2), new Vector3f(-1 * xm, 0, -1.5f),
				new Vector2f(t82 + 1, 1.8f), a3);

		for(int i = 0; i < 4; i++)
			mainGeo.bau.indexPoly(xm > 0, d1[i].sCl(1), d1[i].sCl(-2), d1[i + 1].sCl(-1), d1[i + 1]);
		mainGeo.bau.indexPoly(xm < 0, khc.sCl(1), khc.sCl(-2), d1[0].sCl(-2), d1[1].sCl(-1));
		mainGeo.bau.indexPoly(xm > 0, khc.sCl(-2), d1[0].sCl(-2), d1[0].sCl(-1));
		mainGeo.bau.indexPoly(xm > 0, khc.sCl(-2), d1[0].sCl(-1), d1[0]);

		mainGeo.bau.indexQ(xm > 0, khc.sCl(1), khd.sCl(1), d1[1].sCl(-1), d1[1].sCl(-2));
		mainGeo.bau.indexPoly(xm < 0, khd.sCl(1), d1[1].sCl(-2), d1[2].sCl(-1));
		mainGeo.bau.indexPoly(xm < 0, khd.sCl(1), d1[2].sCl(-1), d1[2].sCl(-2));
		mainGeo.bau.indexQ(xm > 0, khd.sCl(1), khd, d1[2].sCl(-2), d1[3].sCl(-1));
		mainGeo.bau.indexPoly(xm < 0, khd, d1[3].sCl(-1), d1[3].sCl(-2));
		mainGeo.bau.indexPoly(xm < 0, khd, d1[3].sCl(-2), d1[4].sCl(-1));
		mainGeo.bau.indexQ(xm > 0, khd, khc, d1[4].sCl(-1), d1[4].sCl(-2));

		mainGeo.bau.indexQ(xm > 0, khc, khc.sCl(-1), d1[4].sCl(-2), d1[4].sCl(1));
		mainGeo.bau.indexQ(xm > 0, khc.sCl(-1), khd.sCl(-1), d1[4].sCl(1), d1[4]);
		mainGeo.bau.indexPoly(xm < 0, khd.sCl(-1), d1[4], d1[3].sCl(1), d1[3], d1[2].sCl(1),
				d1[2], d1[1].sCl(1), d1[1], d1[0].sCl(1));
		mainGeo.bau.indexPoly(xm > 0, khd.sCl(-1), khd.sCl(-2), d1[0].sCl(1));
		mainGeo.bau.indexQ(xm > 0, khd.sCl(-2), khc.sCl(-2), d1[0].sCl(1), d1[0]);
	}

	private void f1(int xm)
	{
		AutoNumBone f1 = new AutoNumBone(boneList, new Vector3f(xm * 0.5f, 0, -0.5f),
				new Quaternion().fromAngleAxis(-FastMath.HALF_PI * xm, Vector3f.UNIT_Y)
				.mult(new Quaternion().fromAngleAxis(FastMath.HALF_PI * xm * 0.4f, Vector3f.UNIT_Z)), k2);
		AutoNumBone f2 = new AutoNumBone(boneList, new Vector3f(0, 1.8f, 0),
				Quaternion.IDENTITY, f1);
		AutoNumBone f3 = new AutoNumBone(boneList, new Vector3f(0, 2.0f, 0),
				Quaternion.IDENTITY, f2);
	}

	public static final float ZA1 = 0.3f;
	public static final float ZA2 = 0.7f;
	public static final float ZA3 = 0.65f;
	public static final float ZA4 = 0.4f;
	public static final float ZA2x = ZA2 * FastMath.sqrt(0.5f);
	public static final float ZA3x = ZA3 * FastMath.sqrt(0.5f);

	private void z1()
	{
		DualP2 zk4az = new DualP2(new Vector3f(-K41, 0.1f, -K42), new Vector3f(-1.5f, 1, -1),
				new Vector2f(0.5f, 2.4f), k3);
		DualP2 zk4bz = new DualP2(new Vector3f(-K42, 0.1f, -K41), new Vector3f(-1, 1, -1.5f),
				new Vector2f(0.5f, 2.4f), k3);
		DualP2 z1a = new DualP2(new Vector3f(-ZA1, -0.5f, 0),
				new Vector2f(0, 2.5f), k4).autoNormal();
		DualP2 z2a = new DualP2(new Vector3f(-ZA2, -0.2f, 0), new Vector3f(-1, 0, 0),
				new Vector2f(0, 2.55f), k4);
		DualP2 z3a = new DualP2(new Vector3f(-ZA3, 0.5f, 0), new Vector3f(-1, 0, 0),
				new Vector2f(0, 2.65f), k4);
		DualP2 z4a = new DualP2(new Vector3f(-ZA4, 0.7f, 0),
				new Vector2f(0, 2.7f), k4).autoNormal();
		DualP2 z4b = new DualP2(new Vector3f(0, 0.7f, 0), new Vector3f(0, 1, 0),
				new Vector2f(0, 2.75f), k4);
		int edgm = DualP2.EDGEC * 16;
		Position2[] zk4 = new Position2[]
				{
						zk4bz.sCl(2),
						zk4az.sCl(2),
						zk4az.sCl(1),
						zk4bz.sCl(1),
						zk4bz,
						zk4az,
						zk4az.sCl(-1),
						zk4bz.sCl(-1),
						zk4bz.sCl(-2)
				};
		for(int i = 0; i < 8; i++)
		{
			Position2[] a1 = new Position2[5];
			a1[0] = zk4[i + 1];
			a1[1] = zk4[i];
			for(int j = 0; j < 3; j++)
				a1[j + 2] = z1a.sCl(edgm + (i * 2 + j + 5) % 16);
			mainGeo.bau.indexPoly(true, a1);
		}

		mainGeo.bau.cylinderTexSpin(0b1111111111000011, false, 16, z1a, z2a);
		//tc2b0 = mainGeo.bau.indexes.size();
		mainGeo.bau.cylinderTexSpin(0b1111111111000011, false, 16, z2a, z3a);
		mainGeo.bau.cylinderTexSpin(0b1111111111000011, false, 16, z3a, z4a);
		mainGeo.bau.abdeckX(false, 16, z4b, z4a);
		//tc2b1 = mainGeo.bau.indexes.size();
		mainGeo.bau.indexPoly(true, z3a.sCl(edgm + 2), z4a.sCl(edgm + 2), z4a.sCl(edgm + 3));
		mainGeo.bau.indexPoly(true, z4a.sCl(edgm + 4), z4a.sCl(edgm + 5), z3a.sCl(edgm + 6),
				z3a.sCl(edgm + 2), z4a.sCl(edgm + 3));
		mainGeo.bau.indexPoly(true, z4a.sCl(edgm + 5), z4a.sCl(edgm + 6), z3a.sCl(edgm + 6));
		mainGeo.bau.indexPoly(false, z2a.sCl(edgm + 2), z1a.sCl(edgm + 2), z1a.sCl(edgm + 3));
		mainGeo.bau.indexPoly(false, z1a.sCl(edgm + 4), z1a.sCl(edgm + 5), z2a.sCl(edgm + 6),
				z2a.sCl(edgm + 2), z1a.sCl(edgm + 3));
		mainGeo.bau.indexPoly(false, z1a.sCl(edgm + 5), z1a.sCl(edgm + 6), z2a.sCl(edgm + 6));
		//mainGeo.bau.indexQ(true, z3a.sCl(edgm + 2), z3a.sCl(edgm + 6), z2a.sCl(edgm + 2), z2a.sCl(edgm + 6));
		z2(z3a.sCl(edgm + 2), z3a.sCl(edgm + 6), z2a.sCl(edgm + 2), z2a.sCl(edgm + 6));
	}

	public static final float ZB1 = 0.48f;
	public static final float ZB2 = 0.2f;
	public static final float ZB3 = 0.4f;
	public static final float ZB4 = 0.2f;

	private void z2(Position2 lo, Position2 ro, Position2 lu, Position2 ru)
	{
		AutoNumBone zk1 = new AutoNumBone(boneList, new Vector3f(0, -0.07f, 0), Quaternion.IDENTITY, k4);
		AutoNumBone zk2 = new AutoNumBone(boneList, new Vector3f(0, -0.07f, 0), Quaternion.IDENTITY, k4);
		AutoNumBone zk3 = new AutoNumBone(boneList, new Vector3f(0, -0.07f, 0), Quaternion.IDENTITY, k4);
		AutoNumBone zk4 = new AutoNumBone(boneList, new Vector3f(0, -0.09f, 0), Quaternion.IDENTITY, k4);
		AutoNumBone zk5 = new AutoNumBone(boneList, new Vector3f(0, -0.09f, 0), Quaternion.IDENTITY, k4);
		DualP2 z2a1 = new DualP2(new Vector3f(0, 0, ZB1), new Vector3f(0, 0, 1),
				new Vector2f(0.8f, 0.6f), zk1);
		DualP2 z2a2 = new DualP2(new Vector3f(ZB2, 0, ZB1), new Vector3f(0.2f, 0, 1),
				new Vector2f(0.8f, 0.6f), zk2);
		DualP2 z2a3 = new DualP2(new Vector3f(ZB3, 0, ZB1), new Vector3f(0.4f, 0, 1),
				new Vector2f(0.8f, 0.6f), zk3);
		DualP2 z2a4 = new DualP2(new Vector3f(ZB4, 0, ZB1), new Vector3f(0.2f, 0, 1),
				new Vector2f(0.8f, 0.6f), zk4);
		DualP2 z2a5 = new DualP2(new Vector3f(0, 0, ZB1), new Vector3f(0, 0, 1),
				new Vector2f(0.8f, 0.6f), zk5);
		mainGeo.bau.indexPoly(true, z2a1, z2a2.sCl(1), lo, ro, z2a2);
		mainGeo.bau.indexPoly(true, z2a5, z2a4, ru, lu, z2a4.sCl(1));
		mainGeo.bau.indexPoly(false, z2a3, z2a4, ru, ro, z2a2);
		mainGeo.bau.indexPoly(true, z2a3.sCl(1), z2a4.sCl(1), lu, lo, z2a2.sCl(1));
		//mainGeo.bau.indexQ(true, lo, ro, z2a1.sCl(1), z2a1.sCl(0));
	}
}