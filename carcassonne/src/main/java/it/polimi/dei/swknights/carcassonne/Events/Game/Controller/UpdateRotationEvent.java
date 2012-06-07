package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ModuloViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

import java.awt.Color;

/**
 * Event triggered by the controller after that card is rotated
 * 
 * @author edoardopasi & dave
 * 
 */

public class UpdateRotationEvent extends UpdateEvent
{
	public UpdateRotationEvent(Tessera tessera, Color giocatore, Object source)
	{
		super(tessera, null, giocatore, source);
	}

	public UpdateRotationEvent(String tessera, Color giocatore, Object source)
	{
		super(tessera, null, giocatore, source);
	}

	private static final long	serialVersionUID	= -7350182684969632910L;

	@Override
	public void accept(ModuloViewHandler handler)
	{
		handler.visit(this);
		
	}
}
