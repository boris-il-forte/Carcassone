package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

public class UpdatePositionEvent extends UpdateEvent
{
	public UpdatePositionEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(tessera, coordinate, giocatore, source);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setComando()
	{
		this.messaggio = MessaggiController.update_position;

	}

	private static final long	serialVersionUID	= -461479380615200557L;

}
