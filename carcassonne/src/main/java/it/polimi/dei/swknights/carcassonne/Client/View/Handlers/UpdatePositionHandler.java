package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

public class UpdatePositionHandler extends ViewHandler
{
	public UpdatePositionHandler(ModuloView view)
	{
		super(view);
	}
	
	@Override
	public void visit(UpdatePositionEvent  event)
	{
		Coordinate coord = event.getCoordinate();
		this.view.posizionaTessera(coord);
		this.view.aggiornaMappa();
		this.sveglia();
	}
	
}
