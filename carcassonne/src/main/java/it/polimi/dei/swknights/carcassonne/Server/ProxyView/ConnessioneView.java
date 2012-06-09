package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
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

	public Color getColoreConnessione()
	{
		return ColoriGioco.getListaColori().get(this.numeroConnessione -1);
	}
	
	public int getNumeroConnessione()
	{
		return this.numeroConnessione;
	}

	private int	numeroConnessione;

}
