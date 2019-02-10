package wugu;

import backend.*;
import backend.feld.*;
import com.jme3.app.*;
import com.jme3.app.state.*;
import com.jme3.scene.*;
import wugu.karte.*;

public class XState extends BaseAppState
{
	public StapelControl deckC;
	public StapelControl ablageC;
	public FeldControl spielfeldC;
	public StapelControl handC;
	public Node rootNode;

	@Override
	protected void initialize(Application app)
	{
		rootNode = ((SimpleApplication)app).getRootNode();
		Node deckNode = new Node("Deck");
		deckC = new StapelControl(Main1.deck, false);
		deckNode.addControl(deckC);
		rootNode.attachChild(deckNode);
		Node ablageNode = new Node("Ablage");
		ablageC = new StapelControl(Main1.ablage, false);
		ablageNode.addControl(ablageC);
		rootNode.attachChild(ablageNode);
		Node spielfeldNode = new Node("Spielfeld");
		spielfeldC = new FeldControl(Main1.spielfeld);
		spielfeldNode.addControl(spielfeldC);
		rootNode.attachChild(spielfeldNode);
		Node handNode = new Node("Hand");
		handC = new StapelControl(Main1.hand, true);
		handNode.addControl(handC);
		rootNode.attachChild(handNode);
	}

	@Override
	protected void cleanup(Application app)
	{

	}

	@Override
	protected void onEnable()
	{
		spielfeldC.start(deckC);
		Spielfeld.argh(Main1.spielfeld);
		Main1.neuerZug();
	}

	@Override
	protected void onDisable()
	{

	}

	public Node xz2t(KPosition xz)
	{
		switch(xz.t)
		{
			case SPIELFELD:
				return spielfeldC.getKarte(xz);
			case DECK:
				return deckC.getKarte(xz);
			case ABLAGE:
				return ablageC.getKarte(xz);
			case HAND:
				return handC.getKarte(xz);
		}
		return null;
	}
}