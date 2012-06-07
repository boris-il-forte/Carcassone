package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ModuloViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Color;

/**
 * Event triggered by the controller after that a new card is correctely placed
 * 
 * @author edoardopasi & dave
 * 
 */

public class UpdatePositionEvent extends UpdateEvent
{
	public UpdatePositionEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(tessera, coordinate, giocatore, source);

	}

	public UpdatePositionEvent(String tessera, Coordinate coordinate, Color coloreGiocatore, Object source)
	{
		super(tessera, coordinate, coloreGiocatore, source);
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
	public void accept(ModuloViewHandler handler)
	{
		handler.visit(this);

	}

}
