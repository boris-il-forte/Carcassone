package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.CambioTurnoHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.CostruzioneCompletataHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.FinePartitaHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.InizioGiocoHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.MossaNonValidaHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.ProxyViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.UpdatePositionHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.UpdateRotationHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers.UpdateTurnoHandler;

import java.awt.Color;
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
		this.starDestroyer = Executors.newFixedThreadPool(MAX_GIOCATORI);
	}

	@Override
	public void riceviModificheModel(ControllerEvent event)
	{
		if (this.listaConnessioniRMI.size() != 0)
		{
			this.inviaRMI(event);
		}

		if (this.listaConnessioniSocket.size() != 0)
		{
			this.parseEvento(event);
			this.inviaSocket();
		}
		event.accept(this.cambioTurnoHandler);

	}

	@Override
	public void run()
	{
	}

	public Color getColoreCorrente()
	{
		return this.coloreCorrente;
	}

	public void setColoreCorrente(Color colore)
	{
		this.coloreCorrente = colore;
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
		codaComandi.clear();

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

		for (ProxyViewHandler handler : this.listaHandlers)
		{
			event.accept(handler);
		}
	}

	private void inviaSocket()
	{
		String comando = null;
		while (!this.codaComandi.isEmpty())
		{
			comando = this.codaComandi.poll();
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

	private Color						coloreCorrente;

	private int							giocatoriConnessi	= 0;

	private Executor					starDestroyer;

	private LinkedList<String>			codaComandi;

	private ArrayList<ProxyViewHandler>	listaHandlers;

	private List<ConnessioneViewSocket>	listaConnessioniSocket;

	private List<ConnessioneViewRMI>	listaConnessioniRMI;

	private final ProxyViewHandler		cambioTurnoHandler	= new CambioTurnoHandler(this);

	private static final int			MAX_GIOCATORI		= 5;

}
