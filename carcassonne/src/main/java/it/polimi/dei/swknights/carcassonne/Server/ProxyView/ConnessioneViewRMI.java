package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

/**
 * This class is used to maage RMI conection with the client
 * 
 * @author dave
 * 
 */
public class ConnessioneViewRMI extends ConnessioneView
{
	/**
	 * Default constructor
	 * 
	 * @param portale
	 *            the RMI remote object used for this connection
	 * @param proxy
	 *            the proxy that manages this connection
	 * @param numeroConnessione
	 *            the number of this connection
	 */
	public ConnessioneViewRMI(PortaleRMIImpl portale, ProxyView proxy, int numeroConnessione)
	{
		super(numeroConnessione);
		this.portale = portale;
		this.proxy = proxy;
	}

	/**
	 * Method used to send event on this connection
	 * 
	 * @param event
	 */
	public void invia(ControllerEvent event)
	{
		this.portale.setServerEvent(event);
	}

	/**
	 * Method used to send the begin game event
	 * 
	 * @param adapterTessera
	 *            the first card to send
	 * @param idPartita
	 *            the Id of this game
	 * @param giocatoriConnessi
	 *            the number of players of this game
	 */
	public void inviaEventoIniziale(AdapterTessera adapterTessera, String idPartita, int giocatoriConnessi)
	{
		ControllerEvent event = new InizioGiocoEvent(this.proxy, adapterTessera, this.getColoreConnessione(),
				giocatoriConnessi, idPartita);
		this.portale.setServerEvent(event);
	}

	/**
	 * Run methods, wait for events from the view
	 */
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				ViewEvent event = this.portale.waitEventoDalClient();
				if (this.getColoreConnessione().equals(this.proxy.getColoreCorrente()))
				{
					this.proxy.fire(event);
				}

			}
		}
		catch (InterruptedException e)
		{
		}
	}

	private PortaleRMIImpl	portale;

	private ProxyView		proxy;

}
