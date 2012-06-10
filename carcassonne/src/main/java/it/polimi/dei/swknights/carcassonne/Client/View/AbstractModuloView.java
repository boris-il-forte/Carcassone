package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ModuloViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.AbstractView;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class gives a common schema for the view. It basically gives a
 * structure for handling events with the visitors pattern
 * 
 * @author edoardopasi & dave
 * 
 */

public abstract class AbstractModuloView extends AbstractView
{
	public AbstractModuloView()
	{
		super(new ArrayList<Controller>());
		this.visitorHandlers = new ArrayList<ModuloViewHandler>();
	}

	/**
	 * Receive an event from Controller Makes the event accept to be visited by
	 * its handler as described in the visitor pattern
	 * 
	 * @param event
	 *            : the triggered event to handle
	 */
	public void riceviInput(ControllerEvent event)
	{
		for (ModuloViewHandler visitorHandler : this.visitorHandlers)
		{
			event.accept(visitorHandler);
		}
	}

	/**
	 * Run the view
	 */

	public abstract void run();

	/**
	 * Receive an event from the Model Makes the event accept to be visited by
	 * its handler as described in the visitor pattern
	 */
	public synchronized void riceviModificheModel(ControllerEvent event)
	{

		for (ModuloViewHandler handler : this.visitorHandlers)
		{
			event.accept(handler);
		}
	}

	/**
	 * Add a View Handler in the list of handlers
	 * 
	 * @param handler
	 *            , the handler to be added
	 * @see: ViewHandler
	 */
	protected void addVisitorHandler(ModuloViewHandler handler)
	{
		this.visitorHandlers.add(handler);
	}

	private List<ModuloViewHandler>	visitorHandlers;
}
