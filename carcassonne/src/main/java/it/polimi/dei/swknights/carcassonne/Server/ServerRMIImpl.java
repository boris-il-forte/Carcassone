package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Server.ProxyView.PortaleRMIImpl;
import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMIImpl extends UnicastRemoteObject implements ServerRMI
{
	public ServerRMIImpl(CarcassonneServer server) throws RemoteException
	{
		this.server = server;
	}

	public PortaleRMI connect() throws RemoteException
	{
		PortaleRMIImpl portale = new PortaleRMIImpl();
		this.server.gestisciConnessione(portale);
		return portale;
	}

	private CarcassonneServer	server;

	private static final long	serialVersionUID	= 1137692691618250483L;
}
