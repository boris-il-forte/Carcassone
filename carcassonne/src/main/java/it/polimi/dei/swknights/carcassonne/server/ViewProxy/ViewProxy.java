package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import java.util.List;

/*
 */
public class ViewProxy extends View
{

	public ConnessioneAView	realSubject;

	public List				myConnessione;
	public List				myConnessioneAView;

	@Override
	public void request()
	{
	}

}
