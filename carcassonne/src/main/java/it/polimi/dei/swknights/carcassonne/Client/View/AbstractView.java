package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class AbstractView implements View
{
	public AbstractView()
	{
		this.visitorHandlers = new ArrayList<ViewHandler>();
		this.listeners = new ArrayList<Controller>();
	}
	
	public void riceviInput(ControllerEvent event)
	{
		for (ViewHandler visitorHandler : this.visitorHandlers)
		{
			event.accept(visitorHandler);
		}
	}

	public void run()
	{
		System.out.println("sono la view vera. che bello. ah no... sono astratta!");
	}

	public synchronized void riceviModificheModel(ControllerEvent event)
	{
		System.out.println("sono la view, ho ricevuto " + event.toString());
		for(ViewHandler handler : this.visitorHandlers)
		{
			event.accept(handler);
		}
		this.attendiInput();
	}
	
	public void addListener(EventListener eventListener)
	{
		if (eventListener instanceof Controller)
		{
			this.listeners.add((Controller) eventListener);
		}
	}

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}

	public void fire(ViewEvent event)
	{
		for (Controller listener : listeners)
		{
			listener.riceviInput(event);
		}
	}
	
	public abstract void attendiInput();
	
	protected void addVisitorHandler(ViewHandler handler)
	{
		this.visitorHandlers.add(handler);
	}

	private List<Controller>		listeners;
	
	private List<ViewHandler>		visitorHandlers;
}
