package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;

import java.util.List;



/*
 */
public class ProxyView extends AbstractConnessioneView
{

	public ConnessioneView	realSubject;

	public List				myConnessione;
	public List				myConnessioneAView;
	

	@Override
	public void request()
	{
	}

}
