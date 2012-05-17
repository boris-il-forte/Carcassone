package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

/**
 * Event triggered by the controller after that a new card is correctely placed
 * @author edoardopasi & dave
 *
 */

public class UpdatePositionEvent extends UpdateEvent
{
	public UpdatePositionEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(tessera, coordinate, giocatore, source);
		this.setComando(MessaggiController.update_position);
	}
	
	public Coordinate getCoordinate()
	{
		return this.getDati().getCoordinate();
	}

	private static final long	serialVersionUID	= -461479380615200557L;

}
