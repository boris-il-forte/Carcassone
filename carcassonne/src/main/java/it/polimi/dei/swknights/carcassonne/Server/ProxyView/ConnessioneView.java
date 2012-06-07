package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.io.IOException;
import java.net.Socket;

public class ConnessioneView extends AbstractConnessioneView
{

	public ConnessioneView(int numeroConnessione)
	{
		this.numeroConnessione = numeroConnessione;
	}

	public void accettaConnessione(Socket socket)
	{
		// TODO Auto-generated method stub

	}

	public void riceviInput() throws IOException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void riceviModificheModel(ControllerEvent event)
	{
		// TODO Auto-generated method stub

	}

	public int getNumeroConnessione()
	{
		return this.numeroConnessione;
	}

	private int	numeroConnessione;

}
