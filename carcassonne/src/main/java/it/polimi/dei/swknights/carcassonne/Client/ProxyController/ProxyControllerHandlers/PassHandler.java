package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;

/**
 * Class used to handle the pass event
 * @author dave
 *
 */
public class PassHandler extends ProxyControllerHandler
{
	/**
	 * Default constructor. Calls superclass constructor.
	 */
	public PassHandler(ProxyController proxy)
	{
		super(proxy);
	}

	/**
	 * Visit method to handle the pass event with visitors pattern.
	 */
	@Override
	public void visit(PassEvent event)
	{
		this.proxy.setRequestString("pass");
	}

}
