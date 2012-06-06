package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;

public class RuotaHandler extends ProxyControllerHandler
{
	public RuotaHandler(ProxyController proxy)
	{
		super(proxy);
		
	}

	@Override
	public void visit(RotateEvent event)
	{
		proxy.setRequestString("rotate");
	}
	
}
