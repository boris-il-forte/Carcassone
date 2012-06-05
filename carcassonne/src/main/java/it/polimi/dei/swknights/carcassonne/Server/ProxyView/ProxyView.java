package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.InizioGiocoHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.ProxyViewHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ProxyView extends AbstractConnessioneView
{
	public ProxyView()
	{
		this.listaConnessioniSocket = new ArrayList<ConnessioneViewSocket>();
		this.listaConnessioniRMI = new ArrayList<ConnessioneViewRMI>();
		this.inizializzaHandlers();
	}

	@Override
	public void riceviModificheModel(ControllerEvent event)
	{
		this.inviaRMI(event);
		this.parseEvento(event);
		this.inviaSocket();
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub	
	}

	public void accettaConnessione(Socket socket) throws IOException
	{
		ConnessioneViewSocket connessioneSocket = new ConnessioneViewSocket(socket);
		this.listaConnessioniSocket.add(connessioneSocket);
	}
	
	public void accettaConnessione()
	{
		ConnessioneViewRMI connessione = new ConnessioneViewRMI();
		this.listaConnessioniRMI.add(connessione);
	}

	public void setCommandString(String commandString)
	{
		this.commandString = commandString;
	}

	private void inizializzaHandlers()
	{
		this.listaHandlers = new ArrayList<ProxyViewHandler>();
		this.listaHandlers.add(new InizioGiocoHandler(this));		
	}

	private void parseEvento(ControllerEvent event)
	{
		
		
	}

	private void inviaSocket()
	{
		for(ConnessioneViewSocket connessione : this.listaConnessioniSocket)
		{
			connessione.invia(this.commandString);
		}
	}

	private void inviaRMI(ControllerEvent event)
	{
		for(ConnessioneViewRMI connessione : this.listaConnessioniRMI)
		{
			connessione.invia(event);
		}
	}

	private String	commandString;
	
	private ArrayList<ProxyViewHandler>	listaHandlers;

	private List<ConnessioneViewSocket>	listaConnessioniSocket;
	
	private List<ConnessioneViewRMI>	listaConnessioniRMI;

}
