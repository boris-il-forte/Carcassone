package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Connessioni.Connessione;

import java.util.List;

/*
 */
public class ProxyController extends AbstractConnessioneController
{

	public Connessione	realSubject;

	public List			myConnessioneAController;

	@Override
	public void request()
	{
	}

}
