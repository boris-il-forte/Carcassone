package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

public class UpdateRotationEvent extends UpdateEvent
{
	public UpdateRotationEvent(Tessera tessera, Coordinate coordinate, Color giocatore, Object source)
	{
		super(tessera, coordinate, giocatore, source);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setComando()
	{
		// TODO Auto-generated method stub

	}

	private static final long	serialVersionUID	= -7350182684969632910L;

}
