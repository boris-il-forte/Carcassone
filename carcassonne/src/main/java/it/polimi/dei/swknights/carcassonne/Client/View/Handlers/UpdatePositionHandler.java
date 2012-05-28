package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;

public class UpdatePositionHandler extends ViewHandler
{
	@Override
	public void visit(UpdatePositionEvent  event)
	{
		UpdatePositionEvent upe = (UpdatePositionEvent) event;
		Coordinate coord = upe.getCoordinate();
		this.posizionaTessera(coord);
		this.aggiornaMappa();
	}
}
