package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;

/**
 * Abstract class for proxy handlers
 * 
 * @author dave
 * 
 */
public abstract class ProxyControllerHandler extends ControllerHandler
{
	/**
	 * Default constructor. Initializes the handler
	 * 
	 * @param proxy
	 *            the proxy that use the handler
	 */
	public ProxyControllerHandler(ProxyController proxy)
	{
		this.proxy = proxy;
	}

	protected ProxyController	proxy;
}
