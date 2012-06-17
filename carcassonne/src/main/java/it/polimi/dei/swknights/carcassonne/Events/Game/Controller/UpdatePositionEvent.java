package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * Event triggered by the controller after that a new card is correctely placed
 * 
 * @author edoardopasi & dave
 * 
 */

public class UpdatePositionEvent extends UpdateEvent
{
	/**
	 * Constructor to be called when you have the card object
	 * 
	 * @param tessera
	 *            the placed card
	 * @param coordinate
	 *            the placed card's coordinates
	 * @param source
	 *            the event source
	 */
	public UpdatePositionEvent(Tessera tessera, Coordinate coordinate, Object source)
	{
		super(tessera, coordinate, null, source);

	}

	/**
	 * Constructor to be called when you have the card string
	 * 
	 * @param tessera
	 *            the placed card
	 * @param coordinate
	 *            the placed card's coordinates
	 * @param source
	 *            the event source
	 */
	public UpdatePositionEvent(String tessera, Coordinate coordinate, Object source)
	{
		super(tessera, coordinate, null, source);
	}

	/**
	 * accept method for visitor's pattern
	 */
	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}

	/**
	 * {@link Coordinate} of the placed card
	 * 
	 * @return
	 */
	public Coordinate getCoordinate()
	{
		return this.getDati().getCoordinate();
	}

	private static final long	serialVersionUID	= -461479380615200557L;

}
