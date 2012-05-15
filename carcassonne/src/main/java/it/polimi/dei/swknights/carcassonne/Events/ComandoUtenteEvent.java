package it.polimi.dei.swknights.carcassonne.Events;

import java.util.EventObject;

import it.polimi.dei.swknights.carcassonne.Comandi;

public class ComandoUtenteEvent extends EventObject
{
	public ComandoUtenteEvent(Object source, Comandi comandi)
	{
		super(source);
		
	}

	private Comandi	comando;	// TODO sbagliato! servono classi e sottoclassi,
    					// no enum...
	
	private Object source;
}