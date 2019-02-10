package wugu;

import backend.*;
import backend.feld.*;
import backend.set.*;
import com.jme3.app.*;
import com.jme3.input.*;
import com.jme3.input.controls.*;
import com.jme3.light.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.system.*;

public class Main extends SimpleApplication
{
	public static void main(String[] args)
	{
		Main app = new Main();
		app.setShowSettings(false);
		AppSettings settings = new AppSettings(true);
		settings.put("Width", 1200);
		settings.put("Height", 675);
		app.setSettings(settings);
		app.start();
	}

	@Override
	public void simpleInitApp()
	{
		flyCam.setMoveSpeed(100);
		inputManager.addMapping(CameraInput.FLYCAM_LOWER, new KeyTrigger(KeyInput.KEY_E));
		inputManager.addMapping(CameraInput.FLYCAM_STRAFELEFT, new KeyTrigger(KeyInput.KEY_J));
		inputManager.addMapping(CameraInput.FLYCAM_STRAFERIGHT, new KeyTrigger(KeyInput.KEY_L));
		inputManager.addMapping(CameraInput.FLYCAM_FORWARD, new KeyTrigger(KeyInput.KEY_I));
		inputManager.addMapping(CameraInput.FLYCAM_BACKWARD, new KeyTrigger(KeyInput.KEY_K));
		inputManager.addMapping(CameraInput.FLYCAM_RISE, new KeyTrigger(KeyInput.KEY_U));
		inputManager.addMapping(CameraInput.FLYCAM_LOWER, new KeyTrigger(KeyInput.KEY_O));
		getCamera().setRotation(new Quaternion(0, 10, -4, 0).normalizeLocal());
		getCamera().setLocation(new Vector3f(10, 100, 120));

		DirectionalLight light = new DirectionalLight();
		light.setDirection(new Vector3f(-2, -3, -1).normalizeLocal());
		light.setColor(ColorRGBA.White.mult(1.5f));
		rootNode.addLight(light);
		AmbientLight light3 = new AmbientLight();
		light3.setColor(ColorRGBA.White.mult(0.2f));
		rootNode.addLight(light3);

		MaterialLager.init(assetManager);

		Main1.init(new Set1(), new Feld1(), new Spieler(2), new Spieler(3));

		stateManager.attach(new XState());
		stateManager.attach(new InputState2());

		/*StapelControl deckControl = new StapelControl(new Set1());
		Node deck = new Node("Deck");
		deck.addControl(deckControl);
		rootNode.attachChild(deck);
		FeldControl feldControl = new FeldControl(new Feld1());
		Node feldNode = new Node("Feld");
		feldControl.feldNode = feldNode;
		feldControl.start(deck);
		rootNode.attachChild(feldNode);

		stateManager.attach(new InputState(feldControl, Arrays.asList(new Spieler(2), new Spieler(3))));*/
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		//rootNode.removeLight(light);
		//rootNode.addLight(light);
	}

	@Override
	public void simpleRender(RenderManager rm){}
}