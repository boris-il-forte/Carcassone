package it.polimi.dei.swknights.carcassonne.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMI extends Remote
{
	void connect() throws RemoteException; 
}
