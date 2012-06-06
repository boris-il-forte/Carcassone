package it.polimi.dei.swknights.carcassonne.Client.ProxyController;


import java.io.IOException;

public abstract class ConnessioneController extends AbstractConnessioneController
{
	@Override
	public void request()
	{
	}

	public  void invia() {}
	
	public  void invia(Object o) {}
	
	public abstract void riceviInput() throws IOException;

	public abstract void close() ;

}
