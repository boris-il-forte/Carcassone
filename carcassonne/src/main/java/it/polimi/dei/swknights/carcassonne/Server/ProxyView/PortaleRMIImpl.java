package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;

public class PortaleRMIImpl extends UnicastRemoteObject implements PortaleRMI
{
	public PortaleRMIImpl() throws RemoteException
	{
		this.clientEvents = new LinkedList<ViewEvent>();
		this.serverEvents = new LinkedList<ControllerEvent>();
	}

	public synchronized void passaEvento(ViewEvent event) throws RemoteException
	{
		this.clientEvents.add(event);
		this.notifyAll();
	}

	public synchronized ControllerEvent waitEventoDalServer() throws RemoteException
	{
		try
		{
			while (this.serverEvents.size() == 0)
			{
				this.wait();
			}
			return this.serverEvents.poll();
		}
		catch (InterruptedException e)
		{
			throw new RemoteException();
		}
	}

	protected synchronized void setServerEvent(ControllerEvent event)
	{
		this.serverEvents.add(event);
		this.notifyAll();
	}

	protected synchronized ViewEvent waitEventoDalClient() throws InterruptedException
	{
		while (this.clientEvents.size() == 0)
		{
			this.wait();
		}
		return this.clientEvents.poll();
	}

	private Queue<ViewEvent>		clientEvents;

	private Queue<ControllerEvent>	serverEvents;

	private static final long		serialVersionUID	= 5746659670043537383L;
}
