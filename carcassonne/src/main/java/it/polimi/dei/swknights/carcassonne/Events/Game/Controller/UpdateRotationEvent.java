package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

/**
 * Event triggered by the controller after that card is rotated
 * 
 * @author edoardopasi & dave
 * 
 */

public class UpdateRotationEvent extends UpdateEvent
{
	public UpdateRotationEvent(Tessera tessera, Object source)
	{
		super(tessera, null, null, source);
	}

	public UpdateRotationEvent(String tessera, Object source)
	{
		super(tessera, null, null, source);
	}

	private static final long	serialVersionUID	= -7350182684969632910L;

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);

	}
}
