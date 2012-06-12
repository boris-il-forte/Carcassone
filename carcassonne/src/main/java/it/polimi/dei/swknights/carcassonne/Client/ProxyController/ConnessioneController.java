package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

public abstract class ConnessioneController extends AbstractConnessioneController
{

	public void invia(String message)
	{
	}

	public void invia(ViewEvent event)
	{
	}

	public abstract void close();

}
