package it.polimi.dei.swknights.carcassonne.Server;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMIImpl extends UnicastRemoteObject implements ServerRMI
{
	public ServerRMIImpl(CarcassonneServer server) throws RemoteException
	{
		super();
		System.setSecurityManager(new RMISecurityManager());
		this.server = server;
	}

	public void connect()
	{
		this.server.gestisciConnessione();
	}

	private CarcassonneServer	server;

	private static final long	serialVersionUID	= 1137692691618250483L;
}
