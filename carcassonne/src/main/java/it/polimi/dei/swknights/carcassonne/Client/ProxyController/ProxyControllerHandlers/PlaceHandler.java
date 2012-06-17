package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * Class used to handle the place event
 * @author dave
 *
 */
public class PlaceHandler extends ProxyControllerHandler
{
	/**
	 * Default constructor. Calls superclass constructor.
	 */
	public PlaceHandler(ProxyController proxy)
	{
		super(proxy);
	}

	/**
	 * Visit method to handle the place event with visitors pattern.
	 */
	@Override
	public void visit(PlaceEvent event)
	{
		StringBuilder placeString = new StringBuilder();
		Coordinate coord = event.getCoordinateDestinazione();
		placeString.append("place:");
		placeString.append(coord.getX());
		placeString.append(",");
		placeString.append(coord.getY());

		this.proxy.setRequestString(placeString.toString());
	}
	
}
