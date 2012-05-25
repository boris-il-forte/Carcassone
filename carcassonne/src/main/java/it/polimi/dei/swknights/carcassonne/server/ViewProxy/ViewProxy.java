package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import it.polimi.dei.swknights.carcassonne.Client.View.View;

import java.util.List;



/*
 */
public class ViewProxy extends ViewConnessione
{

	public ConnessioneAView	realSubject;

	public List				myConnessione;
	public List				myConnessioneAView;
	

	@Override
	public void request()
	{
	}

}
