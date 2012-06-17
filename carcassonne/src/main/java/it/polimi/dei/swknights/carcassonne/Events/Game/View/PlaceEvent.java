package it.polimi.dei.swknights.carcassonne.Events.Game.View;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * Event triggered by the view to notify that the current player wants to place
 * a card
 * 
 * @author edoardopasi & dave
 * 
 */
public class PlaceEvent extends ViewEvent
{

	/**
	 * Default constructor
	 * 
	 * @param source
	 *            the event source
	 * @param coordinate
	 *            the coordinate where the user want to place teh card
	 */
	public PlaceEvent(Object source, Coordinate coordinate)
	{
		super(source);
		this.coordinateDestinazione = coordinate;
	}

	/**
	 * Getter method
	 * 
	 * @return the coordinates where the card may be placed
	 */
	public Coordinate getCoordinateDestinazione()
	{
		return this.coordinateDestinazione;
	}

	/**
	 * Accept method for visitor's pattern
	 */
	@Override
	public void accept(ControllerHandler handler)
	{
		handler.visit(this);
	}

	private final Coordinate	coordinateDestinazione;

	private static final long	serialVersionUID	= 2085506187547788810L;

}
