package it.polimi.dei.swknights.carcassonne.Server.RMI;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PortaleRMI extends Remote
{
	void passaEvento(ViewEvent event) throws RemoteException;
	
	ControllerEvent waitEventoDalServer() throws RemoteException;
}
