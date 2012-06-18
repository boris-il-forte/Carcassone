package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
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

/**
 * Class that provides a proxy between the server and all connections. It
 * manages up to 5 connection in an optimized way, trying to make all operation
 * as much efficient as possible. it also allow to mask the connection tecnology
 * to the server, allowing decoupling of the controller and the model from the
 * view
 * 
 * @author dave
 * 
 */
public class ProxyView extends AbstractConnessioneView
{
	/**
	 * Default constructor
	 */
	public ProxyView()
	{
		this.listaConnessioniSocket = new ArrayList<ConnessioneViewSocket>();
		this.listaConnessioniRMI = new ArrayList<ConnessioneViewRMI>();
		this.codaComandi = new LinkedList<String>();
		this.inizializzaHandlers();
		this.starDestroyer = Executors.newFixedThreadPool(MAX_GIOCATORI);
	}

	/**
	 * method used to receive events from the model
	 */
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

	/**
	 * Run method, does nothing, only to mantain the same interface as
	 * superclass
	 */
	@Override
	public void run()
	{
	}

	/**
	 * getter method
	 * 
	 * @return the current player's color
	 */
	public Color getColoreCorrente()
	{
		return this.coloreCorrente;
	}

	/**
	 * Setter method for the current player color
	 * 
	 * @param colore
	 *            the color to be set
	 */
	public void setColoreCorrente(Color colore)
	{
		this.coloreCorrente = colore;
	}

	/**
	 * method used to accept a new socket connection
	 * 
	 * @param socket
	 *            the socket used by this connection
	 * @throws IOException
	 *             if something goes wrong with the connection
	 */
	public void accettaConnessione(Socket socket) throws IOException
	{
		this.giocatoriConnessi++;
		ConnessioneViewSocket connessioneSocket = new ConnessioneViewSocket(socket, this,
				this.giocatoriConnessi);
		this.starDestroyer.execute(connessioneSocket);
		this.listaConnessioniSocket.add(connessioneSocket);

	}

	/**
	 * method used to accept a new RMI connection
	 * 
	 * @param portale
	 *            the RMI remote object used to sent events
	 */
	public void accettaConnessione(PortaleRMIImpl portale)
	{
		this.giocatoriConnessi++;
		ConnessioneViewRMI connessioneRMI = new ConnessioneViewRMI(portale, this, this.giocatoriConnessi);
		this.starDestroyer.execute(connessioneRMI);
		this.listaConnessioniRMI.add(connessioneRMI);
	}

	/**
	 * Set the command string to be send to all socket connections
	 * 
	 * @param commandString
	 */
	public void setCommandString(String commandString)
	{
		this.codaComandi.add(commandString);
	}

	/**
	 * Method used to send all connection the begin game event
	 * 
	 * @param event
	 */
	public void mandaComandoAvvia(InizioGiocoEvent event)
	{
		for (ConnessioneViewRMI connRMI : this.listaConnessioniRMI)
		{
			connRMI.inviaEventoIniziale(event.getTesseraIniziale(), event.getIdPartita(),
					this.giocatoriConnessi);
		}
		for (ConnessioneViewSocket connSOCK : this.listaConnessioniSocket)
		{
			int indiceMessaggio = connSOCK.getNumeroConnessione();
			String messaggioIniziale = this.codaComandi.get(indiceMessaggio - 1);
			connSOCK.invia(messaggioIniziale);
		}
		this.codaComandi.clear();
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

	private List<ProxyViewHandler>		listaHandlers;

	private List<ConnessioneViewSocket>	listaConnessioniSocket;

	private List<ConnessioneViewRMI>	listaConnessioniRMI;

	private final ProxyViewHandler		cambioTurnoHandler	= new CambioTurnoHandler(this);

	private static final int			MAX_GIOCATORI		= 5;

}
