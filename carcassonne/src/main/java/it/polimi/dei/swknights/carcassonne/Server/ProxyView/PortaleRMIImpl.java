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
		this.clientEvent = null;
		this.serverEvent = new LinkedList<ControllerEvent>();
	}

	public synchronized void passaEvento(ViewEvent event) throws RemoteException
	{
		this.clientEvent = event;
		this.notifyAll();
	}

	public synchronized ControllerEvent waitEventoDalServer() throws RemoteException
	{
		try
		{
			while (this.serverEvent.size() == 0)
			{
				this.wait();
			}
			return this.serverEvent.poll();
		}
		catch (InterruptedException e)
		{
			throw new RemoteException();
		}
	}

	protected synchronized void setServerEvent(ControllerEvent event)
	{
		this.serverEvent.add(event);
		this.notifyAll();
	}

	protected synchronized ViewEvent waitEventoDalClient() throws InterruptedException
	{
		while (this.clientEvent == null)
		{
			this.wait();
		}
		ViewEvent event = this.clientEvent;
		this.clientEvent = null;
		return event;
	}

	private ViewEvent				clientEvent;

	private Queue<ControllerEvent>	serverEvent;

	private static final long		serialVersionUID	= 5746659670043537383L;
}
