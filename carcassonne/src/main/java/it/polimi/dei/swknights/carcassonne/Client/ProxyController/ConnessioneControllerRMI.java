package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.rmi.RemoteException;

public class ConnessioneControllerRMI extends ConnessioneController
{
	public ConnessioneControllerRMI(PortaleRMI portale, ProxyController proxy)
	{
		this.portale = portale;
		this.proxy = proxy;
	}

	@Override
	public void invia(ViewEvent event)
	{
		try
		{
			this.portale.passaEvento(event);
		}
		catch (RemoteException e)
		{
		}
	}

	@Override
	public void run()
	{
		ControllerEvent evento;
		try
		{
			while(this.portale != null)
			{
				evento = this.portale.waitEventoDalServer();
				this.proxy.fire(evento);
			}
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	
	}

	@Override
	public void close()
	{
		this.portale = null;
	}

	private ProxyController	proxy;
	private PortaleRMI	portale;

}
