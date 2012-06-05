package it.polimi.dei.swknights.carcassonne.Server.Model;

import it.polimi.dei.swknights.carcassonne.Events.Model;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class AbstractModel implements Model
{
	public AbstractModel()
	{
		this.listeners = new ArrayList<View>();
	}
	
	/**
	 * Add Listener to the listerner list
	 * 
	 * @param listener
	 *            to be added, if it is not a ViewListener, it would not be
	 *            added to the list
	 * @see it.polimi.dei.swknights.carcassonne.Events.EventSource#addListener(java.util.EventListener)
	 */

	public void addListener(EventListener eventListener)
	{
		View controllerListener;
		if (eventListener instanceof View)
		{
			controllerListener = (View) eventListener;
			this.listeners.add(controllerListener);
		}
	}

	/**
	 * Remove listener to the listener list
	 * 
	 * @param listener
	 *            to be removed from the listener list
	 * @see it.polimi.dei.swknights.carcassonne.Events.EventSource#removeListener(java.util.EventListener)
	 */

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}

	public void fire(ControllerEvent event)
	{
		for (View listener : this.listeners)
		{
			listener.riceviModificheModel(event);
		}
	}
	
	private List<View>	listeners;
}
