package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

public class UpdatePositionHandler extends ModuloViewHandler
{
	public UpdatePositionHandler(ModuloView view)
	{
		super(view);
	}

	/**
	 * Notify the view that a card has just been placed
	 */
	@Override
	public void visit(UpdatePositionEvent event)
	{
		Coordinate coord = event.getCoordinate();
		AdapterTessera tessera = event.getTessera();
		this.view.posizionaTessera(tessera, coord);
		this.view.aggiornaMappa();
		this.sveglia();
	}

}
