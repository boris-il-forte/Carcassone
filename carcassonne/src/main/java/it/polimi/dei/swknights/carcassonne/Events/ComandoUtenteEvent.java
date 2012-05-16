package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventObject;

import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

public class ComandoUtenteEvent extends EventObject
{
	public ComandoUtenteEvent(Object source, MessaggiController comandi)
	{
		super(source);
		
	}

	private MessaggiController	comando;	
	
	private Object source;
}