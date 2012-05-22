package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Events.EventSource;

import java.util.EventListener;

public abstract class View implements EventSource
{

	public void addListener(EventListener eventListener)
	{
		// TODO Auto-generated method stub
		
	}

	public void removeListener(EventListener eventListener)
	{
		// TODO Auto-generated method stub
		
	}

	public abstract void aggiornaMappa();


}
