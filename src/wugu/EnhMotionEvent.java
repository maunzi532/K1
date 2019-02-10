package wugu;

import backend.*;
import com.jme3.animation.*;
import com.jme3.cinematic.*;
import com.jme3.cinematic.events.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;
import wugu.karte.*;

public class EnhMotionEvent extends AbstractControl implements MotionPathListener
{
	private Node zone;
	private int d;
	private boolean verdeckt;
	private float zeit;
	private Vector3f[] orte;

	public EnhMotionEvent(int d, boolean verdeckt, float zeit, Vector3f... orte)
	{
		this.d = d;
		this.verdeckt = verdeckt;
		this.zeit = zeit;
		this.orte = orte;
	}

	public EnhMotionEvent(Node zone, int d, boolean verdeckt, float zeit, Vector3f... orte)
	{
		this.zone = zone;
		this.d = d;
		this.verdeckt = verdeckt;
		this.zeit = zeit;
		this.orte = orte;
	}

	@Override
	public void setSpatial(Spatial spatial)
	{
		super.setSpatial(spatial);
		if(spatial == null || orte.length == 0)
			return;
		spatial.setUserData("ZielOrt", orte[orte.length - 1]);
		spatial.setUserData("ZielIndex", orte.length);
		spatial.setUserData("StartDrehung", spatial.getWorldRotation());
		MotionPath m = new MotionPath();
		m.addListener(this);
		if(zone != null)
		{
			Vector3f current = spatial.getWorldTranslation().clone();
			spatial.getParent().detachChild(spatial);
			zone.attachChild(spatial);
			m.addWayPoint(spatial.getParent().worldToLocal(current, null));
		}
		else
			m.addWayPoint(spatial.getLocalTranslation());
		for(int i = 0; i < orte.length; i++)
			m.addWayPoint(orte[i]);
		MotionEvent ev = new MotionEvent(spatial, m, zeit, LoopMode.DontLoop);
		ev.setDirectionType(MotionEvent.Direction.None);
		/*ev.setDirectionType(MotionEvent.Direction.Rotation);
		ev.setRotation(new Quaternion().fromAngleAxis(FastMath.HALF_PI * d, Vector3f.UNIT_Y)
				.mult(new Quaternion().fromAngleAxis(verdeckt ? FastMath.PI : 0, Vector3f.UNIT_X)));*/
		ev.setEnabled(true);
		((KPosition) spatial.getUserData("XZ")).drehung = d;
		((KPosition) spatial.getUserData("XZ")).verdeckt = verdeckt;
		//spatial.addControl(new KarteFlipControl(spatial.getWorldRotation(), d, verdeckt, zeit));
		spatial.getControl(KarteControl.class).updateV(zeit);
	}

	@Override
	public void onWayPointReach(MotionEvent motionControl, int wayPointIndex)
	{
		if(wayPointIndex == (int) spatial.getUserData("ZielIndex"))
		{
			spatial.setLocalTranslation(spatial.getUserData("ZielOrt"));
			spatial.removeControl(this);
		}
	}

	@Override
	protected void controlUpdate(float tpf)
	{

	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{

	}
}