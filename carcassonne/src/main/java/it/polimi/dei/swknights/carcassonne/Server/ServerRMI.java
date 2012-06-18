package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the remote object ServerRMI that provides to the client a
 * method to connect the server
 * 
 * @author dave
 * 
 */
public interface ServerRMI extends Remote
{
	/**
	 * Method used to connect the server
	 * 
	 * @return The PortaleRMI object used as a channel for events
	 * @throws RemoteException
	 *             if something goes wrong with the connection
	 */
	PortaleRMI connect() throws RemoteException;
}
