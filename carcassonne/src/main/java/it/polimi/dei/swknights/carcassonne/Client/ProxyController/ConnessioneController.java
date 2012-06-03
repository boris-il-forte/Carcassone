package it.polimi.dei.swknights.carcassonne.Client.ProxyController;


import java.io.IOException;
import java.util.List;

public abstract class ConnessioneController extends AbstractConnessioneController
{

	public List			myServerSocket;
	public List			myConnessione;

	@Override
	public void request()
	{
	}

	public abstract void riceviInput() throws IOException;

	public abstract void close() ;

}
