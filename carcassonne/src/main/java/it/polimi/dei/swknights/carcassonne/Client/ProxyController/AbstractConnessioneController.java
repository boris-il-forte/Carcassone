package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Model;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Abstract class that represent the idea of a connection to the server
 * 
 * @author dave
 * 
 */
public abstract class AbstractConnessioneController implements Controller, Model
{

	/**
	 * Default constructor. initialize listener list
	 */
	public AbstractConnessioneController()
	{
		this.listeners = new ArrayList<View>();
	}

	/**
	 * Run method
	 */
	public abstract void run();

	public void riceviInput(ViewEvent event)
	{
	}

	/**
	 * Method used to add listeners
	 */

	public void addListener(EventListener eventListener)
	{
		if (eventListener instanceof View)
		{
			this.listeners.add((View) eventListener);
		}

	}

	/**
	 * Method used to remove listeners
	 */
	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}

	/**
	 * Method used to fire events
	 * 
	 * @param event
	 *            the evet to be fired
	 */
	public void fire(ControllerEvent event)
	{
		for (View listener : this.listeners)
		{
			listener.riceviModificheModel(event);
		}
	}

	private List<View>	listeners;

}
