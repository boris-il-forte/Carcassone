package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Server.ProxyView.PortaleRMIImpl;
import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation for ServerRMI remote object
 * Is the only remote object published by the RMI registry. Can be used to retrieve a specific connection object PortaleRMI
 * @author dave
 *
 */
public class ServerRMIImpl extends UnicastRemoteObject implements ServerRMI
{
	/**
	 * Default constructor
	 * @param server the server instance running on the machine
	 * @throws RemoteException if something wrong happens with the connection
	 */
	public ServerRMIImpl(CarcassonneServer server) throws RemoteException
	{
		this.server = server;
	}

	/**
	 * Method called by client to connect to the server with an RMI connection
	 */
	public PortaleRMI connect() throws RemoteException
	{
		PortaleRMIImpl portale = new PortaleRMIImpl();
		this.server.gestisciConnessione(portale);
		return portale;
	}

	private CarcassonneServer	server;

	private static final long	serialVersionUID	= 1137692691618250483L;
}
