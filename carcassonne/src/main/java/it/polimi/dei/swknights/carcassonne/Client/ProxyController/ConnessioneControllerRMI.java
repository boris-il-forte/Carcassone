package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.RemoteException;

/**
 * A class that implements an RMI connection
 * 
 * @author dave
 * 
 */
public class ConnessioneControllerRMI extends ConnessioneController
{
	/**
	 * Default constructor. Set up the connection
	 * 
	 * @param portale
	 *            the remote object used by the connection
	 * @param proxy
	 *            the proxy that manages the connection
	 */
	public ConnessioneControllerRMI(PortaleRMI portale, ProxyController proxy)
	{
		this.portale = portale;
		this.proxy = proxy;
	}

	/**
	 * Method that sent an event on the connection
	 * 
	 * @param event
	 *            the event to be send
	 */
	@Override
	public void invia(ViewEvent event)
	{
		try
		{
			this.portale.passaEvento(event);
		}
		catch (RemoteException e)
		{
		}
	}

	/**
	 * run method. waits events from the server
	 */
	@Override
	public void run()
	{
		ControllerEvent evento;
		try
		{
			while (this.portale != null)
			{
				evento = this.portale.waitEventoDalServer();
				this.proxy.fire(evento);
			}
		}
		catch (RemoteException e)
		{
		}

	}

	/**
	 * RMI connection closing
	 */
	@Override
	public void close()
	{
		this.portale = null;
	}

	private ProxyController	proxy;

	private PortaleRMI		portale;

}
