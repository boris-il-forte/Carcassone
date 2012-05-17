package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandoView;
import it.polimi.dei.swknights.carcassonne.server.Controller.Handlers.ControllerHandler;

/**
 * Event triggered by the view to notify that the current player wants to  place a card
 * @author edoardopasi & dave
 *
 */
public class PlaceEvent extends ViewEvent
{

	public PlaceEvent(Object source, Coordinate coordinate)
	{
		super(source);
		this.coordinateDestinazione = coordinate;
	}
    /**
     * The coordinates where the card may be placed
     * @return
     */
	public Coordinate getCoordinateDestinazione()
	{
		return this.coordinateDestinazione;
	}

	@Override
	protected void setComando()
	{
		this.comando = ComandoView.place;
	
	}

	private final Coordinate	coordinateDestinazione;

	private static final long	serialVersionUID	= 2085506187547788810L;

	@Override
	public void accept(ControllerHandler handler)
	{
		handler.visit(this);
	}

}
