package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;

/**
 * Class used to handle the marker-place event
 * @author dave
 *
 */
public class TileHandler extends ProxyControllerHandler
{
	/**
	 * Default constructor. Calls superclass constructor.
	 */
	public TileHandler(ProxyController proxy)
	{
		super(proxy);
	}

	/**
	 * Visit method to handle the marker-place event with visitors pattern.
	 */
	@Override
	public void visit(TileEvent event)
	{
		StringBuilder segnalinoString = new StringBuilder();
		segnalinoString.append("tile:");
		segnalinoString.append(event.getPuntoDestinazione().toString());
		this.proxy.setRequestString(segnalinoString.toString());
	}

}
