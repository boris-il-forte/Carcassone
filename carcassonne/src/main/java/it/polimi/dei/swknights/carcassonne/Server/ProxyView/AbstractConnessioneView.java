package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.AbstractView;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;

import java.util.ArrayList;

public abstract class AbstractConnessioneView extends AbstractView
{

	public AbstractConnessioneView()
	{
		super(new ArrayList<Controller>());
	}

	public void run()
	{
	}

	public abstract void riceviModificheModel(ControllerEvent event);

}
