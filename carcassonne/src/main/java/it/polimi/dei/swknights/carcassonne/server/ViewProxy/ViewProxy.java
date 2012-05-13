package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import it.polimi.dei.swknights.carcassonne.Events.ControllerListener;

import java.util.List;

/*
 */
public class ViewProxy extends View
{

	public ConnessioneAView	realSubject;

	public List				myConnessione;
	public List				myConnessioneAView;

	public void request()
	{
	}

}