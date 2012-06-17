package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Abstract class to define view. it's a schema for all views and implements
 * observer pattern
 * 
 * @author dave
 * 
 */
public abstract class AbstractView implements View
{

	/**
	 * Default constructor. initializes the listener list with an empty list
	 */
	public AbstractView()
	{
		this.listeners = new ArrayList<Controller>();
	}

	/**
	 * Add an EventListener, mainly used for controller or model listeners
	 */

	public void addListener(EventListener eventListener)
	{
		if (eventListener instanceof Controller)
		{
			this.listeners.add((Controller) eventListener);
		}
	}

	/**
	 * Remove an EventListener
	 */

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}

	/**
	 * Fire an Event
	 * 
	 * @param event
	 *            the event to be fired
	 */

	public void fire(ViewEvent event)
	{
		for (Controller listener : this.listeners)
		{
			listener.riceviInput(event);
		}
	}

	private List<Controller>	listeners;

}
