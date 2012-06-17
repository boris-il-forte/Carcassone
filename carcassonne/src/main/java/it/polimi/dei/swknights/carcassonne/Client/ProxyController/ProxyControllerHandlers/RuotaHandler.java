package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;

/**
 * Class used to handle the rotate event
 * @author dave
 *
 */
public class RuotaHandler extends ProxyControllerHandler
{
	/**
	 * Default constructor. Calls superclass constructor.
	 */
	public RuotaHandler(ProxyController proxy)
	{
		super(proxy);

	}

	/**
	 * Visit method to handle the rotate event with visitors pattern.
	 */
	@Override
	public void visit(RotateEvent event)
	{
		this.proxy.setRequestString("rotate");
	}

}
