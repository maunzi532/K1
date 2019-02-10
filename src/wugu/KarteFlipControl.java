package wugu;

import backend.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;

public class KarteFlipControl extends AbstractControl
{
	private Quaternion localStart;
	private Quaternion localEnd;
	private float time;
	private float timeLeft;

	public KarteFlipControl(float time)
	{

		this.time = time;
		timeLeft = time;
	}

	@Override
	public void setSpatial(Spatial spatial)
	{
		super.setSpatial(spatial);
		if(spatial == null)
			return;
		Quaternion global = spatial.getUserData("StartDrehung");
		if(global == null)
			localStart = spatial.getLocalRotation().clone();
		else
			localStart = global.subtract(spatial.getParent().getWorldRotation()).addLocal(Quaternion.IDENTITY);
		localEnd = drehFlip(spatial.getUserData("XZ"));
	}

	@Override
	protected void controlUpdate(float tpf)
	{
		timeLeft -= tpf;
		if(timeLeft <= 0)
		{
			spatial.setLocalRotation(localEnd);
			spatial.removeControl(this);
			return;
		}
		float time2 = timeLeft / time;
		spatial.setLocalRotation(localStart.mult(time2).addLocal(localEnd.mult(1 - time2)).normalizeLocal());
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{

	}

	public static Quaternion drehFlip(KPosition xz)
	{
		return new Quaternion().fromAngleAxis(xz.verdeckt ? FastMath.PI : 0, Vector3f.UNIT_X)
				.mult(new Quaternion().fromAngleAxis(FastMath.HALF_PI * xz.drehung, Vector3f.UNIT_Y));
	}
}