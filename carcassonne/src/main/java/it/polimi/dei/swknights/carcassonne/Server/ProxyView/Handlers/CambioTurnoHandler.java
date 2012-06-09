package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

public class CambioTurnoHandler extends ProxyViewHandler
{

	public CambioTurnoHandler(ProxyView proxy)
	{
		super(proxy);
	}
	
	@Override
	public void visit(UpdateTurnoEvent event)
	{
		this.proxy.setColoreCorrente(event.getGiocatoreCorrente());
	}

}
