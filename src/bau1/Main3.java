package bau1;

import com.jme3.app.*;
import com.jme3.input.*;
import com.jme3.input.controls.*;
import com.jme3.light.*;
import com.jme3.material.*;
import com.jme3.math.*;
import com.jme3.post.*;
import com.jme3.post.filters.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;
import com.jme3.system.*;
import java.awt.*;
import wugu.*;

public class Main3 extends SimpleApplication
{
	public static Main3 app;

	public static void main(String[] args)
	{
		app = new Main3();
		int scw = Toolkit.getDefaultToolkit().getScreenSize().width / 400;
		app.setShowSettings(false);
		AppSettings settings = new AppSettings(true);
		settings.put("Width", scw * 400);
		settings.put("Height", scw * 225);
		app.setSettings(settings);
		app.start();
	}

	@Override
	public void simpleInitApp()
	{
		flyCam.setMoveSpeed(10);
		flyCam.setZoomSpeed(10);
		inputManager.addMapping(CameraInput.FLYCAM_LOWER, new KeyTrigger(KeyInput.KEY_E));
		inputManager.addMapping(CameraInput.FLYCAM_STRAFELEFT, new KeyTrigger(KeyInput.KEY_J));
		inputManager.addMapping(CameraInput.FLYCAM_STRAFERIGHT, new KeyTrigger(KeyInput.KEY_L));
		inputManager.addMapping(CameraInput.FLYCAM_FORWARD, new KeyTrigger(KeyInput.KEY_I));
		inputManager.addMapping(CameraInput.FLYCAM_BACKWARD, new KeyTrigger(KeyInput.KEY_K));
		inputManager.addMapping(CameraInput.FLYCAM_RISE, new KeyTrigger(KeyInput.KEY_U));
		inputManager.addMapping(CameraInput.FLYCAM_LOWER, new KeyTrigger(KeyInput.KEY_O));
		getCamera().setRotation(new Quaternion(0, 10, -1.5f, 0).normalizeLocal());
		getCamera().setLocation(new Vector3f(0, 10, 20));

		DirectionalLight light = new DirectionalLight();
		light.setDirection(new Vector3f(-2, -3, -1).normalizeLocal());
		light.setColor(ColorRGBA.White.mult(1.5f));
		rootNode.addLight(light);
		AmbientLight light3 = new AmbientLight();
		light3.setColor(ColorRGBA.White.mult(0.2f));
		rootNode.addLight(light3);

		MaterialLager.init(assetManager);

		Material xsh = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		//xsh.setColor("Color", ColorRGBA.Blue);
		//xsh.setTexture("ColorMap", assetManager.loadTexture("Textures/N1.png"));
		//BauMesh1 bauMesh1 = new YHexoMesh(2, -1, 1, 6);
		BauMesh1 bauMesh1 = new ZCubeMesh(1);
		//xsh.setTexture("DiffuseMap", bauMesh1.textureMap(64, 64, 12));
		//bauMesh1.outputTextureMap(64, 64, 12);
		Geometry geometry = new Geometry("W", bauMesh1);
		geometry.setMaterial(xsh);
		rootNode.attachChild(geometry);
		//edge();

		Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);
		geom.setLocalTranslation(4, 0, 0);
		Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		geom.setMaterial(mat);

		rootNode.attachChild(geom);
	}

	public void edge()
	{
		if(renderer.getCaps().contains(Caps.GLSL100))
		{
			FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
			//fpp.setNumSamples(4);
			int numSamples = getContext().getSettings().getSamples();
			if(numSamples > 0)
				fpp.setNumSamples(numSamples);
			CartoonEdgeFilter toon = new CartoonEdgeFilter();
			toon.setEdgeWidth(1);
			toon.setEdgeIntensity(10);
			toon.setEdgeColor(ColorRGBA.White);
			fpp.addFilter(toon);
			viewPort.addProcessor(fpp);
		}
	}

	@Override
	public void simpleUpdate(float tpf){}

	@Override
	public void simpleRender(RenderManager rm){}
}