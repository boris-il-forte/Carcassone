package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

public class ProxyViewHandler extends ViewHandler
{
	public ProxyViewHandler(ProxyView proxy)
	{
		this.proxy = proxy;
	}

	protected ProxyView	proxy;
	
}
