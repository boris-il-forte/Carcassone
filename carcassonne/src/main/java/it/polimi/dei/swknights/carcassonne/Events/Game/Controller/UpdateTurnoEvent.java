package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

public class UpdateTurnoEvent extends UpdateEvent
{

	public UpdateTurnoEvent(Object source, Color color, Tessera tessera)
	{
		super(tessera, null, color, source);
		this.setComando(MessaggiController.turn);
		this.color = color;
	}

	public UpdateTurnoEvent(Object source, Color color, String tessera)
	{
		super(tessera, null, color, source);
		this.setComando(MessaggiController.turn);
		this.color = color;
	}

	@Override
	public void accept(ViewHandler handler)
	{
		handler.visit(this);
		
	}

	public Color getGiocatoreCorrente()
	{
		return this.color;
	}

	private final Color			color;
	private static final long	serialVersionUID	= -8277130187657287093L;

}
