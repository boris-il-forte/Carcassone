package it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;


public class ProxyControllerHandler extends ControllerHandler
{

	public ProxyControllerHandler(ProxyController proxy)
	{
		this.proxy = proxy;
	}
	
	ProxyController proxy;
}
