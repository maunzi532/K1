package wugu.karte;

import backend.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;

public class StapelControl extends AbstractControl
{
	public Node deckNode;
	public Deck deck;
	public boolean hand;

	public StapelControl(Deck deck, boolean hand)
	{
		this.deck = deck;
		this.hand = hand;
	}

	@Override
	public void setSpatial(Spatial spatial)
	{
		super.setSpatial(spatial);
		deckNode = (Node) spatial;
		for(int i = 0; i < deck.karten.size(); i++)
		{
			Node karte = new Node();
			karte.setLocalTranslation(0, (deck.karten.size() - 1 - i) * 2f, 0);
			karte.setUserData("XZ", deck.karten.get(i).xz);
			karte.addControl(new KarteControl(deck.karten.get(i)));
			deckNode.attachChild(karte);
		}
	}

	public Node gibKarte(SendeK ziel1, boolean detach)
	{
		ziel1.sendeK(deck.karten.remove(0));
		Node re = (Node) deckNode.getChild(0);
		if(detach)
			deckNode.detachChild(re);
		return re;
	}

	public Node getKarte(KPosition xz)
	{
		return (Node) deckNode.getChild(xz.y);
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