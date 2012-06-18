package it.polimi.dei.swknights.carcassonne.Server.RMI;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for PortaleRMI remote object, the object used to send and receive
 * events to and from a remote server
 * 
 * @author dave
 * 
 */
public interface PortaleRMI extends Remote
{
	/**
	 * Method used to sent events to the server
	 * 
	 * @param event
	 *            the event to be sent
	 * @throws RemoteException
	 *             if something goes wrong with this connection
	 */
	void passaEvento(ViewEvent event) throws RemoteException;

	/**
	 * Method that waits events from the server
	 * 
	 * @return the event received from the server
	 * @throws RemoteException
	 *             if something wrong happens to this connection
	 */
	ControllerEvent waitEventoDalServer() throws RemoteException;
}
