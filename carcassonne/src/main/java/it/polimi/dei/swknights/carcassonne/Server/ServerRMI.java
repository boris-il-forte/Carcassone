package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMI extends Remote
{
	PortaleRMI connect() throws RemoteException; 
}
