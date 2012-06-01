package it.polimi.dei.swknights.carcassonne.Client.View;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * This abstract class gives a common schema for the view.
 * It basically gives a structure for the observer pattern
 * @author edoardopasi & dave
 *
 */

public abstract class AbstractView implements View
{
	public AbstractView()
	{
		this.visitorHandlers = new ArrayList<ViewHandler>();
		this.listeners = new ArrayList<Controller>();
	}
	/**
	 * Receive an event from Controller
	 * Makes the event accept to be visited by its handler as described in the visitor pattern
	 * @param event: the triggered event to handle
	 */
	public void riceviInput(ControllerEvent event)
	{
		for (ViewHandler visitorHandler : this.visitorHandlers)
		{
			event.accept(visitorHandler);
		}
	}

	public abstract void run();
	/**
	 * Receive an event from the Model
	 * Makes the event accept to be visited by its handler as described in the visitor pattern
	 */
	public synchronized void riceviModificheModel(ControllerEvent event)
	{
		
		for (ViewHandler handler : this.visitorHandlers)
		{
			event.accept(handler);
		}
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

	public void removeListener(EventListener eventListener)
	{
		this.listeners.remove(eventListener);
	}
	/**
	 * Fire an Event
	 * @param event the event to be fired
	 */
	public void fire(ViewEvent event)
	{
		for (Controller listener : listeners)
		{
			listener.riceviInput(event);
		}
	}

	public abstract void attendiInput();
	/**
	 * Add a View Handler in the list of handlers
	 * @param handler, the handler to be added
	 * @see: ViewHandler
	 */
	protected void addVisitorHandler(ViewHandler handler)
	{
		this.visitorHandlers.add(handler);
	}


	private List<Controller>	listeners;

	private List<ViewHandler>	visitorHandlers;
}
