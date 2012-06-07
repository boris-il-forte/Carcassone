package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

public class PlaceHandler extends ProxyControllerHandler
{
	public PlaceHandler(ProxyController proxy)
	{
		super(proxy);

	}

	@Override
	public void visit(PlaceEvent event)
	{
		StringBuilder placeString = new StringBuilder();
		Coordinate coord = event.getCoordinateDestinazione();
		placeString.append("place: ");
		placeString.append(coord.getX());
		placeString.append(", ");
		placeString.append(coord.getY());

		this.proxy.setRequestString(placeString.toString());
	}

}
