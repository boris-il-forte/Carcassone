package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

/**
 * abstract class representing a general connection
 * 
 * @author dave
 * 
 */
public abstract class ConnessioneController extends AbstractConnessioneController
{

	/**
	 * Method to send strings on a connection. It does nothing to implement a
	 * pseudo-visitor pattern.
	 * 
	 * @param message
	 *            the message to be send
	 */
	public void invia(String message)
	{
	}

	/**
	 * Method to send events on a connection. It does nothing to implement a
	 * pseudo-visitor pattern.
	 * 
	 * @param event
	 *            the event to be send
	 */
	public void invia(ViewEvent event)
	{
	}

	/**
	 * Abstract method to close connections
	 */
	public abstract void close();

}
