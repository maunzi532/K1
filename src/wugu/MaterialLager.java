package wugu;

import com.jme3.asset.*;
import com.jme3.material.*;
import com.jme3.math.*;

public class MaterialLager
{
	private static final String lighted = "Common/MatDefs/Light/Lighting.j3md";
	private static final String unshaded = "Common/MatDefs/Misc/Unshaded.j3md";
	public static EnhMaterial seite1;
	public static Material seite2;
	public static Material loch1;
	public static EnhMaterial struktur1;
	public static Material struktur2;
	public static EnhMaterial boden1;
	public static Material boden2;
	public static Material boden3;
	public static Material wand1;
	public static Material item1;

	public static void init(AssetManager am)
	{
		seite1 = new EnhMaterial(am, lighted);
		light(seite1, ColorRGBA.DarkGray);
		seite2 = shine(seite1);
		loch1 = new Material(am, lighted);
		light(loch1, ColorRGBA.DarkGray);
		struktur1 = new EnhMaterial(am, lighted);
		light(struktur1, ColorRGBA.Gray);
		struktur2 = new Material(am, lighted);
		struktur2 = shine(struktur1);
		boden1 = new EnhMaterial(am, lighted);
		light(boden1, ColorRGBA.White);
		boden2 = new Material(am, lighted);
		light(boden2, ColorRGBA.Green);
		boden3 = new Material(am, lighted);
		light(boden3, ColorRGBA.Red);
		wand1 = new Material(am, lighted);
		//wand1.setTransparent(true);
		light(wand1, ColorRGBA.Red);
		item1 = new Material(am, lighted);
		light(item1, ColorRGBA.Blue);

		seite1.setEnh(null, seite2, null, null, seite2, seite2, null, null);
		struktur1.setEnh(null, struktur2, null, null, struktur2, struktur2, null, null);
		boden1.setEnh(null, boden3, boden2, null, boden2, boden3, boden2, null);
	}

	public static void color(Material mat, ColorRGBA clr)
	{
		mat.setColor("Color", clr);
	}

	public static void light(Material mat, ColorRGBA clr)
	{
		mat.setBoolean("UseMaterialColors", true);
		mat.setColor("Ambient", clr);
		mat.setColor("Diffuse", clr);
	}

	public static Material shine(Material mat)
	{
		Material shine = mat.clone();
		shine.setColor("Ambient", ColorRGBA.White);
		return shine;
		/*mat.setBoolean("UseMaterialColors", true);
		mat.setColor("Ambient", clr);
		mat.setColor("Diffuse", clr);*/
	}
}