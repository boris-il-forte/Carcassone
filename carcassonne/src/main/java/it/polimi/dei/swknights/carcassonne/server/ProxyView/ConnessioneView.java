package it.polimi.dei.swknights.carcassonne.server.ProxyView;


import java.io.IOException;
import java.util.EventObject;
import java.util.List;

public abstract class ConnessioneView extends AbstractConnessioneView
{
	
	public abstract void inizializza();
	
	public abstract EventObject generaEvento();

	public abstract void riceviInput() throws IOException;

	public abstract void close();
	
}
