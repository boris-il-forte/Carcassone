package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProxyView extends AbstractConnessioneView
{
	public ProxyView()
	{
		this.listaConnessioniSocket = new ArrayList<ConnessioneViewSocket>();
		this.listaConnessioniRMI = new ArrayList<ConnessioneViewRMI>();
		this.codaComandi = new LinkedList<String>();
		this.inizializzaHandlers();
		this.starDestroyer = Executors.newFixedThreadPool(this.MAX_GIOCATORI);
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

	@Override
	public void fire(ViewEvent event)
	{
		// TODO do it
	}

	public void accettaConnessione(Socket socket) throws IOException
	{
		this.giocatoriConnessi++;
		// giocatori connessi diventa il numero di connessione
		ConnessioneViewSocket connessioneSocket = new ConnessioneViewSocket(socket, this,
				this.giocatoriConnessi);
		this.starDestroyer.execute(connessioneSocket);
		this.listaConnessioniSocket.add(connessioneSocket);

	}

	public void accettaConnessione()
	{
		this.giocatoriConnessi++;
		ConnessioneViewRMI connessione = new ConnessioneViewRMI(this.giocatoriConnessi);
		this.listaConnessioniRMI.add(connessione);

	}

	public void setCommandString(String commandString)
	{
		this.codaComandi.add(commandString);
	}

	public void mandaComandoAvvia()
	{

		for (ConnessioneViewRMI connRMI : this.listaConnessioniRMI)
		{
			int indiceMessaggio = connRMI.getNumeroConnessione();
			String messaggioIniziale = this.codaComandi.get(indiceMessaggio - 1);// coda
																					// base
																					// 0!
			connRMI.inviaEventoIniziale(messaggioIniziale);
		}
		for (ConnessioneViewSocket connSOCK : this.listaConnessioniSocket)
		{
			int indiceMessaggio = connSOCK.getNumeroConnessione();
			String messaggioIniziale = this.codaComandi.get(indiceMessaggio - 1);
			connSOCK.invia(messaggioIniziale);
		}

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

	private int							giocatoriConnessi	= 0;

	private final int					MAX_GIOCATORI		= 5;
	private Executor					starDestroyer;

	private LinkedList<String>			codaComandi;

	private ArrayList<ProxyViewHandler>	listaHandlers;

	private List<ConnessioneViewSocket>	listaConnessioniSocket;

	private List<ConnessioneViewRMI>	listaConnessioniRMI;

}
