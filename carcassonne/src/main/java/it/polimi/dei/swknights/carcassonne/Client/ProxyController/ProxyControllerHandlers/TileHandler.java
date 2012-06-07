package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;

public class TileHandler extends ProxyControllerHandler
{
	public TileHandler(ProxyController proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(TileEvent event)
	{
		StringBuilder segnalinoString = new StringBuilder();
		segnalinoString.append("tile: ");
		segnalinoString.append(event.getPuntoDestinazione().toString());
		this.proxy.setRequestString(segnalinoString.toString());
	}

}
