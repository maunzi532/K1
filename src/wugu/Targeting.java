package wugu;

import backend.*;
import backend.struktur.*;
import com.jme3.app.*;
import com.jme3.app.state.*;
import com.jme3.collision.*;
import com.jme3.material.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;
import java.util.*;

public class Targeting extends BaseAppState
{
	private XState xState;
	private Node mainNode;
	private Camera cam;
	public ConnectedBoden last1;
	public Node last2;
	public ArrayList<Geometry> marked = new ArrayList<>();

	@Override
	protected void initialize(Application app)
	{
		mainNode = ((SimpleApplication) app).getRootNode();
		xState = app.getStateManager().getState(XState.class);
		cam = app.getCamera();
		Geometry q = new Geometry("W");
		q.setMesh(new Quad(6, 6));
		q.setMaterial(new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md"));
		q.setLocalTranslation(app.getContext().getSettings().getWidth() / 2f, app.getContext().getSettings().getHeight() / 2f, 0);
		((SimpleApplication) app).getGuiNode().attachChild(q);
	}

	@Override
	protected void cleanup(Application app)
	{

	}

	@Override
	protected void onEnable()
	{

	}

	@Override
	protected void onDisable()
	{

	}

	@Override
	public void update(float tpf)
	{
		//if(true)
			//return;
		/*System.out.println(inputManager.getCursorPosition());
		Vector3f origin    = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f);
		Vector3f direction = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f);
		direction.subtractLocal(origin).normalizeLocal();

		Ray ray = new Ray(origin, direction);*/
		Ray ray = new Ray(cam.getLocation(), cam.getDirection());
		CollisionResults results = new CollisionResults();
		mainNode.collideWith(ray, results);
		ConnectedBoden new1 = null;
		Node new2 = null;
		label68: if(results.size() > 0)
			for(CollisionResult result : results)
				switch(Main1.targetingType)
				{
					case BODEN:
						String typ = result.getGeometry().getUserData("Typ");
						if("Boden".equals(typ))
						{
							Karte k1 = Main1.xz2k(result.getGeometry().getParent().getParent().getUserData("XZ"));
							if(k1 != null)
								new1 = k1.connecter.connected[(int)result.getGeometry().getUserData("Num")];
							break label68;
						}
						if("Seite".equals(result.getGeometry().getName()))
						{
							new2 = result.getGeometry().getParent();
							break label68;
						}
						if("Struktur".equals(typ))
						{
							new2 = result.getGeometry().getParent().getParent();
							break label68;
						}
						break;
					case KARTE:
						if("Seite".equals(result.getGeometry().getName()))
						{
							new2 = result.getGeometry().getParent();
							break label68;
						}
						if("StrukturNode".equals(result.getGeometry().getParent().getName()))
						{
							new2 = result.getGeometry().getParent().getParent();
							break label68;
						}
						break label68;
				}
		changeMaterials(new1, new2);
	}

	private void changeMaterials(ConnectedBoden new1, Node new2)
	{
		if(new1 == last1 && new2 == last2 && !Main1.updateMarked)
			return;
		for(Geometry geom : marked)
			geom.setMaterial(geom.getUserData("MaterialEnh"));
		marked.clear();
		HashMap<Geometry, Integer> mark = new HashMap<>();

		if(new1 != null)
		{
			for(ConnectedBoden cb : new1.connections.keySet())
				for(Integer n : cb.glow())
					mapAdd(mark, (Geometry) ((Node) xState.xz2t(cb.karte.xz).getChild("StrukturNode")).getChild("Boden" + n), 2);
			for(Integer n : new1.glow())
				mapAdd(mark, (Geometry) ((Node) xState.xz2t(new1.karte.xz).getChild("StrukturNode")).getChild("Boden" + n), 1);
		}
		if(new2 != null)
		{
			mapAdd(mark, (Geometry) new2.getChild("Seite"), 1);
			if(new2.getChild("Struktur") != null)
				mapAdd(mark, (Geometry) ((Node)new2.getChild("StrukturNode")).getChild("Struktur"), 1);
		}
		for(ConnectedBoden cb : Main1.marked1)
		{
			for(Integer n : cb.glow())
				mapAdd(mark, (Geometry) ((Node) xState.xz2t(cb.karte.xz).getChild("StrukturNode")).getChild("Boden" + n), 4);
		}
		for(KPosition xz : Main1.marked2)
		{
			Node node = xState.xz2t(xz);
			mapAdd(mark, (Geometry) node.getChild("Seite"), 4);
			if(node.getChild("Struktur") != null)
				mapAdd(mark, (Geometry) ((Node)node.getChild("StrukturNode")).getChild("Struktur"), 4);
		}

		for(Map.Entry<Geometry, Integer> entry : mark.entrySet())
			enhance(entry.getKey(), entry.getValue());
		last1 = new1;
		last2 = new2;
		Main1.updateMarked = false;

		/*if(new1 != last1)
		{
			if(last1 != null)
			{
				Stream.concat(last1.connections.keySet().stream(), Stream.of(last1)).forEach(e ->
				{
					for(Integer n : e.glow())
						((Node) xState.xz2t(e.karte.xz).getChild("StrukturNode")).getChild("Boden" + n).setMaterial(MaterialLager.boden1);
				});
			}
			if(new1 != null)
			{
				for(ConnectedBoden cb : new1.connections.keySet())
					for(Integer n : cb.glow())
						((Node) xState.xz2t(cb.karte.xz).getChild("StrukturNode")).getChild("Boden" + n).setMaterial(MaterialLager.boden2);
				for(Integer n : new1.glow())
					((Node) xState.xz2t(new1.karte.xz).getChild("StrukturNode")).getChild("Boden" + n).setMaterial(MaterialLager.boden3);
			}
		}
		if(new2 != last2)
		{
			if(last2 != null)
			{
				last2.getChild("Seite").setMaterial(MaterialLager.seite1);
				if(last2.getChild("Struktur") != null)
					((Node)last2.getChild("StrukturNode")).getChild("Struktur").setMaterial(MaterialLager.struktur1);
			}
			if(new2 != null)
			{
				new2.getChild("Seite").setMaterial(MaterialLager.seite2);
				if(new2.getChild("Struktur") != null)
					((Node)new2.getChild("StrukturNode")).getChild("Struktur").setMaterial(MaterialLager.struktur2);
			}
		}
		last1 = new1;
		last2 = new2;*/
	}

	public void mapAdd(Map<Geometry, Integer> map, Geometry geometry, int val)
	{
		if(map.containsKey(geometry))
			map.put(geometry, map.get(geometry) + val);
		else
			map.put(geometry, val);
	}

	public void enhance(Geometry geometry, int matNum)
	{
		if(geometry.getMaterial() instanceof EnhMaterial && matNum >= 0 && matNum < ((EnhMaterial) geometry.getMaterial()).enh.length)
		{
			geometry.setUserData("MaterialEnh", geometry.getMaterial());
			geometry.setMaterial(((EnhMaterial) geometry.getMaterial()).enh[matNum]);
			marked.add(geometry);
		}
	}
}