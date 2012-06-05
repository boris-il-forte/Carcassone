package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

public class InizioGiocoHandler extends ProxyViewHandler
{

	public InizioGiocoHandler(ProxyView proxy)
	{
		super(proxy);
	}
	
	@Override
	public void visit (InizioGiocoEvent event) 
	{
		StringBuilder stringCommand = new StringBuilder();
		this.proxy.setCommandString(stringCommand.toString());
	}
}
