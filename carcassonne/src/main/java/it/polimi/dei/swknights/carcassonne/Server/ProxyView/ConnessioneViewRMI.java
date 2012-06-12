package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
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
		this.portale.setServerEvent(event);
	}

	public void inviaEventoIniziale(AdapterTessera adapterTessera, String idPartita, int giocatoriConnessi)
	{
		ControllerEvent event = new InizioGiocoEvent(this.proxy,adapterTessera,this.getColoreConnessione() , giocatoriConnessi, idPartita);
		this.portale.setServerEvent(event);
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
