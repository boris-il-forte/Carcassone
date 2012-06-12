package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

public class MossaNonValidaHandler extends ProxyViewHandler
{

	public MossaNonValidaHandler(ProxyView proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(MossaNonValidaEvent event)
	{
		this.proxy.setCommandString("move not valid");
	}

}
