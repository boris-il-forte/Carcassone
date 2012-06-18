package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
/**
 * This class is used to change turn  in the proxy view
 * @author edoardo & dave
 *
 */

public class CambioTurnoHandler extends ProxyViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */

	public CambioTurnoHandler(ProxyView proxy)
	{
		super(proxy);
	}
	
	@Override
	/**
	 */
	public void visit(UpdateTurnoEvent event)
	{
		this.proxy.setColoreCorrente(event.getGiocatoreCorrente());
	}

}
