package wugu.bau2;

import com.jme3.math.*;
import com.jme3.texture.*;
import com.jme3.texture.plugins.*;
import java.awt.*;
import java.awt.image.*;

public class AdvBauMesh extends BauMesh2
{
	public void indexQ(boolean inv, Position2... pZ)
	{
		indexPoly(inv, pZ[0], pZ[1], pZ[3], pZ[2]);
	}

	public void indexQ(boolean inv, DualP2 p0)
	{
		indexPoly(inv, p0, p0.sCl(1), p0.sCl(2), p0.sCl(3));
	}

	public void cylinderTex1DP(Integer field, boolean inv, DualP2 a1, DualP2 a2)
	{
		Position2[] array1 = new Position2[]{a1.sCl(2), a1.sCl(1), a1, a1.sCl(-1), a1.sCl(-2)};
		Position2[] array2 = new Position2[]{a2.sCl(2), a2.sCl(1), a2, a2.sCl(-1), a2.sCl(-2)};
		for(int i = 0; i < 4; i++)
			if(field == null || (field & (1 << i)) > 0)
				indexQ(inv, array1[i], array1[i + 1], array2[i], array2[i + 1]);
	}

	public void cylinderTex2DP(Integer field, boolean inv, DualP2 a1, DualP2 b1, DualP2 a2, DualP2 b2)
	{
		Position2[] array1 = new Position2[]{a1.sCl(2), a1.sCl(1), b1.sCl(1), b1, a1,
				a1.sCl(-1), b1.sCl(-1), b1.sCl(-2), a1.sCl(-2)};
		Position2[] array2 = new Position2[]{a2.sCl(2), a2.sCl(1), b2.sCl(1), b2, a2,
				a2.sCl(-1), b2.sCl(-1), b2.sCl(-2), a2.sCl(-2)};
		for(int i = 0; i < 8; i++)
			if(field == null || (field & (1 << i)) > 0)
				indexQ(inv, array1[i], array1[i + 1], array2[i], array2[i + 1]);
	}

	public void cylinderTexSpin(Integer field, boolean inv, int edgc, DualP2 a1, DualP2 a2)
	{
		for(int i = 0; i < edgc; i++)
			if(field == null || (field & (1 << i)) > 0)
				indexQ(inv, a1.sCl(edgc * DualP2.EDGEC + i), a1.sCl(edgc * DualP2.EDGEC + i + 1),
						a2.sCl(edgc * DualP2.EDGEC + i), a2.sCl(edgc * DualP2.EDGEC + i + 1));
	}

	public void abdeck(boolean inv, int edgc, DualP2 p2a)
	{
		Position2[] p = new Position2[edgc];
		for(int i = 0; i < edgc; i++)
			p[i] = p2a.sCl(i + edgc * DualP2.EDGEC);
		indexPoly(inv, p);
	}

	public void abdeckX(boolean inv, int edgc, DualP2 mid, DualP2 p2a)
	{
		Position2[] p = new Position2[edgc + 2];
		p[0] = mid;
		p[p.length - 1] = p2a;
		for(int i = 0; i < edgc; i++)
			p[i + 1] = p2a.sCl(i + edgc * DualP2.EDGEC);
		indexPoly(inv, p);
	}

	public void flatPoly(boolean inv, Position2[] poly, Vector3f normal, Vector2f... texa)
	{
		Position2[] poly1 = new Position2[poly.length];
		for(int i = 0; i < poly.length; i++)
			poly1[i] = new Position2(poly[i], normal, texa[i]);
		indexPoly(inv, poly1);
	}

	public Texture texfill(int w, int h, Vector2f texScale)
	{
		BufferedImage img1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gd = img1.createGraphics();
		gd.setColor(Color.BLACK);
		gd.fillRect(0, 0, w, h);
		gd.setColor(new Color(0, 255, 0, 40));
		int[] xe = new int[3];
		int[] ye = new int[3];
		for(Index3 index3 : indexes)
		{
			for(int j = 0; j < 3; j++)
			{
				xe[j] = (int)(texCoord.get(index3.data[j].num).getX() * texScale.getX() * w);
				ye[j] = (int)(texCoord.get(index3.data[j].num).getY() * texScale.getY() * h);
			}
			gd.fillPolygon(xe, ye, 3);
			gd.drawPolygon(xe, ye, 3);
		}
		/*try
		{
			ImageIO.write(img1, "png", new File("Tex7865"));
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}*/
		return new Texture2D(new AWTLoader().load(img1, false));
	}
}