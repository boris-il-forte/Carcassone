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
	public UpdatePositionEvent(Tessera tessera, Coordinate coordinate, Object source)
	{
		super(tessera, coordinate, null, source);

	}

	public UpdatePositionEvent(String tessera, Coordinate coordinate, Object source)
	{
		super(tessera, coordinate,null, source);
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

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}

}
