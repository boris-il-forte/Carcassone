package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

public class ConnessioneViewRMI extends ConnessioneView
{
	public ConnessioneViewRMI(PortaleRMIImpl portale, ProxyView proxy, int numeroConnessione)
	{
		super(numeroConnessione);
		this.portale = portale;
		this.proxy = proxy;
	}

	public void invia(ControllerEvent event)
	{
		this.portale.setClientEvent(event);
	}

	public void inviaEventoIniziale(String messaggioIniziale)
	{
		//TODO finire!!!
		ControllerEvent event = new InizioGiocoEvent(this.proxy, null, this.getColoreConnessione(), null, null);
		this.portale.setClientEvent(event);
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				ViewEvent event = this.portale.waitEventoDalClient();
				this.proxy.fire(event);
			}
		}
		catch (InterruptedException e)
		{
		}
	}

	private PortaleRMIImpl	portale;

	private ProxyView		proxy;

}
