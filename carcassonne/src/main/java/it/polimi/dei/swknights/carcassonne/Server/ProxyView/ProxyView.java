package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.CostruzioneCompletataHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.FinePartitaHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.InizioGiocoHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.MossaNonValidaHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.ProxyViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.UpdatePositionHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.UpdateRotationHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.UpdateTurnoHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProxyView extends AbstractConnessioneView
{
	public ProxyView()
	{
		this.listaConnessioniSocket = new ArrayList<ConnessioneViewSocket>();
		this.listaConnessioniRMI = new ArrayList<ConnessioneViewRMI>();
		this.codaComandi = new LinkedList<String>();
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
		this.codaComandi.add(commandString);
	}

	private void inizializzaHandlers()
	{
		this.listaHandlers = new ArrayList<ProxyViewHandler>();
		this.listaHandlers.add(new InizioGiocoHandler(this));
		this.listaHandlers.add(new UpdateTurnoHandler(this));
		this.listaHandlers.add(new UpdateRotationHandler(this));
		this.listaHandlers.add(new UpdatePositionHandler(this));
		this.listaHandlers.add(new CostruzioneCompletataHandler(this));
		this.listaHandlers.add(new MossaNonValidaHandler(this));
		this.listaHandlers.add(new FinePartitaHandler(this));
	}

	private void parseEvento(ControllerEvent event)
	{

	}

	private void inviaSocket()
	{
		String comando = this.codaComandi.poll();
		while (!this.codaComandi.isEmpty())
		{
			for (ConnessioneViewSocket connessione : this.listaConnessioniSocket)
			{
				connessione.invia(comando);
			}
		}
	}

	private void inviaRMI(ControllerEvent event)
	{
		for (ConnessioneViewRMI connessione : this.listaConnessioniRMI)
		{
			connessione.invia(event);
		}
	}

	private Queue<String>				codaComandi;

	private ArrayList<ProxyViewHandler>	listaHandlers;

	private List<ConnessioneViewSocket>	listaConnessioniSocket;

	private List<ConnessioneViewRMI>	listaConnessioniRMI;

}
