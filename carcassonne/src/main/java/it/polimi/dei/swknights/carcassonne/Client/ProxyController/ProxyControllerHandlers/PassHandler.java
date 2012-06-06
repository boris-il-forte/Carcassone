package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;

public class PassHandler extends ProxyControllerHandler
{
	public PassHandler(ProxyController proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(PassEvent event)
	{
		this.proxy.setRequestString("pass");
	}

}
