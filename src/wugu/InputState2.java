package wugu;

import backend.*;
import com.jme3.app.*;
import com.jme3.app.state.*;
import com.jme3.input.*;
import com.jme3.input.controls.*;

public class InputState2 extends BaseAppState implements ActionListener
{
	InputManager inputManager;
	Targeting targeting;
	XState xState;

	@Override
	protected void initialize(Application app)
	{
		inputManager = app.getInputManager();
		targeting = new Targeting();
		app.getStateManager().attach(targeting);
		xState = app.getStateManager().getState(XState.class);
		inputManager.addMapping("LK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addMapping("RK", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
		inputManager.addListener(this, "LK", "RK");
		/*for(Spieler sp : spieler)
		{
			Box b = new Box(1, 1, 1);
			Geometry g = new Geometry("Box", b);
			g.setMaterial(MaterialLager.wand1);
			feld.feldNode.attachChild(g);
			//g.addControl(sp);
		}
		spieler().z = 3;*/
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
		InputCode ic = InputCode.valueOf(name);
		if(targeting.last1 != null)
			Main1.process(isPressed, ic, targeting.last1.karte, targeting.last1);
		else if(targeting.last2 != null)
			Main1.process(isPressed, ic, Main1.xz2k(targeting.last2.getUserData("XZ")), null);
		else
			Main1.process(isPressed, ic, null, null);
	}
}