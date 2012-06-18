package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
/**
 * This class is used to handle a non-valid move in proxy mode
 * @author edoardo
 *
 */
public class MossaNonValidaHandler extends ProxyViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */
	public MossaNonValidaHandler(ProxyView proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(MossaNonValidaEvent event)
	{
		this.proxy.setCommandString("move not valid");
	}

}
