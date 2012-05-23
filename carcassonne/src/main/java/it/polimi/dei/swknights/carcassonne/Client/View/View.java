package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Events.EventSource;
import it.polimi.dei.swknights.carcassonne.Events.ViewListener;


import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class View implements EventSource
{

	public View()
	{
		this.listeners = new ArrayList<ViewListener>();
		this.scenario = new ScenarioDiGioco();
	}
	
	public void addListener(EventListener eventListener)
	{
		if (eventListener instanceof ViewListener)
		{
			this.listeners.add((ViewListener) eventListener);
		}
		
	}

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}
	
	public abstract void aggiornaMappa();


	private ScenarioDiGioco		scenario;

	private List<ViewListener>	listeners;
}
