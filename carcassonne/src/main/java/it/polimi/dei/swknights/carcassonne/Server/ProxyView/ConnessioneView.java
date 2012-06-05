package it.polimi.dei.swknights.carcassonne.Server.ProxyView;


import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.io.IOException;
import java.net.Socket;
import java.util.EventObject;

public abstract class ConnessioneView extends AbstractConnessioneView
{
	
	public abstract void inizializza();
	
	public abstract EventObject generaEvento();

	public abstract void riceviInput() throws IOException;

	public abstract void close();

	public abstract void inviaProtocolloPerEvento(ControllerEvent event);

	public abstract void addGiocatore(Socket socket);
	
	public abstract void addGiocatore(Object o); //RMI
	
}
