package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

/**
 * Event triggered by the controller after that card is rotated
 * @author edoardopasi & dave
 *
 */

public class UpdateRotationEvent extends UpdateEvent
{
	public UpdateRotationEvent(Tessera tessera, Color giocatore, Object source)
	{
		super(tessera, null, giocatore, source);
		this.setComando(MessaggiController.update_rotation);
	}

	private static final long	serialVersionUID	= -7350182684969632910L;

}
