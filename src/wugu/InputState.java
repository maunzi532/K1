package wugu;

public class InputState// extends BaseAppState implements ActionListener
{
	/*InputManager inputManager;
	Targeting targeting;
	FeldControl feld;
	List<Spieler> spieler;
	List<Integer> farbenSpieler;
	int spielerN;
	Spieler ak;
	Status status;

	public InputState(FeldControl feld, List<Spieler> spieler)
	{
		this.feld = feld;
		this.spieler = spieler;
		farbenSpieler = spieler.stream().map(e -> e.farbe).distinct().collect(Collectors.toList());
		spielerN = 0;
		ak = spieler.get(spielerN);
		status = Status.AMZUG;
	}

	@Override
	protected void initialize(Application app)
	{
		inputManager = app.getInputManager();
		targeting = new Targeting();
		app.getStateManager().attach(targeting);
		inputManager.addMapping("LK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addMapping("RK", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
		inputManager.addListener(this, "LK", "RK");
		for(Spieler sp : spieler)
		{
			Box b = new Box(1, 1, 1);
			Geometry g = new Geometry("Box", b);
			g.setMaterial(MaterialLager.wand1);
			feld.feldNode.attachChild(g);
			//g.addControl(sp);
		}
		spieler().z = 3;
		//spieler().boden = karten.spielerKarte(spieler()).getControl(StrukturControl.class).connectors.get(0);
	}

	@Override
	protected void cleanup(Application app)
	{
		app.getStateManager().detach(targeting);
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
	public void onAction(String name, boolean isPressed, float tpf)
	{
		boolean lk = "LK".equals(name);
		//if(!lk)
			//spieler().getSpatial().setLocalTranslation(karten.spielerKarte(spieler()).getLocalTranslation().add(StrukturMesh.ort(spieler().pak)));
		if(!isPressed)
		{
			switch(status)
			{
				case ANFANG:
					break;
				case AMZUG:
					if(lk && targeting.last1 != null)
						xen(targeting.last1);
					break;
				case AUFDECKEN:
					break;
				case HAND:
					break;
				case ENDE:
					break;
			}
		}
	}

	private Spieler spieler()
	{
		return spieler.get(spielerN);
	}

	private void xen(ConnectedBoden cb)
	{
		Integer wand = spieler().boden.connections.get(cb);
		if(wand == null)
			return;
		int wtyp = 0;
		if(wand < 5)
		{
			Spatial w1 = spieler().boden.strukturNode.getChild("Wand" + wand);
			if(!((boolean) w1.getUserData("Offen")))
				wtyp = w1.getUserData("WTyp");
		}
		if(farbenSpieler.contains(wtyp))
		{
			if(spieler().farbe != wtyp)
				return;
		}
		else if(wtyp == 1)
		{
			//OMG
		}
		else if(wtyp > 0)
		{
			//Check G
		}
		StrukturControl sk = spieler().boden.strukturNode.getParent().getControl(StrukturControl.class);
		List<Integer> pfad = sk.pfad(sk.midOrt(spieler().boden), sk.midOrt(cb));
		MotionPath mp = new MotionPath();
		mp.addWayPoint(spieler().getSpatial().getWorldTranslation());
		Vector3f add = spieler().boden.strukturNode.getWorldTranslation();
		for(Integer p1 : pfad)
			mp.addWayPoint(StrukturMesh.ort(p1).add(add));
		Vector3f add2 = cb.strukturNode.getWorldTranslation();
		for(Integer p2 : pfad)
			mp.addWayPoint(StrukturMesh.ort(p2).add(add2));
		spieler().boden = cb;
		new MotionEvent(spieler().getSpatial(), mp, mp.getLength() * 0.1f).setEnabled(true);
	}

	private void xen(Node verd)
	{
		for(int i = 0; i < 4; i++)
			if((ak.boden.pfadCode & (1 << i)) > 0)
			{
				int x1 = i % 2 == 0 ? ak.x + 1 - i : ak.x;
				int z1 = i % 2 == 1 ? ak.z - 2 + i : ak.z;
				if(feld.getKarte(new KPosition(x1, z1)) == verd)
				{
					//Aufdecken
					return;
				}
			}
	}

	public enum Status
	{
		ANFANG,
		AMZUG,
		AUFDECKEN,
		HAND,
		ENDE
	}*/
}