package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

import java.util.EventObject;

public abstract class ControllerEvent extends EventObject
{

	public ControllerEvent(Object source)
	{
		super(source);

	}

	public String toString()
	{
		return messaggio.toString();
	}

	protected abstract void setComando();

	protected MessaggiController	messaggio;
	
	private static final long	serialVersionUID	= 3191102477186388256L;

}
