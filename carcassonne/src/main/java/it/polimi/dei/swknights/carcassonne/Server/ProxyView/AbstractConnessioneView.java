package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.AbstractView;

/**
 * Abstract class representing a connection to the clients
 * @author dave
 *
 */
public abstract class AbstractConnessioneView extends AbstractView
{
	/**
	 * abstract run method
	 */
	public abstract void run();

	/**
	 * abstract method to receive the events from the controller
	 */
	public abstract void riceviModificheModel(ControllerEvent event);

}
//TODO: controllare...