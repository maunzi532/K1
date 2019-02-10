package wugu.bau2;

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
import wugu.*;

public class Main2 extends SimpleApplication
{
	TestChar2 tc;
	Quaternion rotation = new Quaternion();
	public static Main2 app;

	public static void main(String[] args)
	{
		app = new Main2();
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

		tc = new TestChar2("Wugu");
		tc.setup();
		tc.finish();
		tc.geom.setMaterial(MaterialLager.wand1);
		Material xsh = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		xsh.setColor("Color", ColorRGBA.Black);
		tc.tc2a.geom.setMaterial(xsh);
		//tc.tc2b.geom.setMaterial(MaterialLager.item1);
		//tc.tc2b.controlSetup();
		//tc.tc2b.debugger(assetManager);
		//tc.geom.setMaterial(xsh);
		//tc.geom.getMaterial().setTransparent(true);
		//tc.geom.getMaterial().setTexture("DiffuseMap", assetManager.loadTexture("Textures/N1.png"));
		//Texture2D t = (Texture2D) assetManager.loadTexture(new TextureKey("Textures/Tex7865.png", false));
		//tc.geom.getMaterial().setTexture("DiffuseMap", t);
		//tc.geom.getMaterial().getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
		//tc.geom.setQueueBucket(RenderQueue.Bucket.Transparent);
		//tc.geom.getMaterial().getAdditionalRenderState().setWireframe(true);
		//tc.geom.getMaterial().getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
		rootNode.attachChild(tc.node);
		tc.controlSetup();
		//tc.debugger(assetManager);
		Quad quad = new Quad(20, 20);
		Geometry qg = new Geometry("quad", quad);
		qg.setMaterial(new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"));
		qg.getMaterial().setTexture("ColorMap", tc.bau.texfill(2560, 2560, new Vector2f(0.25f, 0.25f)));
		qg.setLocalTranslation(-10, 0, 0);
		qg.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
		rootNode.attachChild(qg);
		//edge();
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
			toon.setEdgeColor(ColorRGBA.Blue);
			fpp.addFilter(toon);
			viewPort.addProcessor(fpp);
		}
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		Quaternion rotate = new Quaternion();
		rotate.fromAngleAxis(tpf * 0.2f, Vector3f.UNIT_Z);
		rotation.multLocal(rotate);
		//tc.sk.getBone(0).setUserTransforms(Vector3f.ZERO, rotation, Vector3f.UNIT_XYZ);
		//tc.sk.updateWorldVectors();
		//tc.tc2b.castPhysics();
		//tc.tc2b.sk.updateWorldVectors();
	}

	@Override
	public void simpleRender(RenderManager rm){}
}