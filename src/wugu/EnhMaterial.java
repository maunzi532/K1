package wugu;

import com.jme3.asset.*;
import com.jme3.material.*;

public class EnhMaterial extends Material
{
	public Material[] enh;

	public EnhMaterial(AssetManager contentMan, String defName)
	{
		super(contentMan, defName);
	}

	public void setEnh(Material... enh)
	{
		this.enh = enh;
	}
}