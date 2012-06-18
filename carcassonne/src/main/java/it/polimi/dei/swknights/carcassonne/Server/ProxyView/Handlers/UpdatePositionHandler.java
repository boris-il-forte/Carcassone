package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
/**
 * This class handles the update position event in proxy mode
 * @author edoardo
 *
 */
public class UpdatePositionHandler extends ProxyViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */

	public UpdatePositionHandler(ProxyView proxy)
	{
		super(proxy);

	}

	@Override
	public void visit(UpdatePositionEvent event)
	{
		String tile = event.getTessera().toProtocolString();
		Coordinate coordinata = event.getCoordinate();
		String stringaUpdate = this.getStringaUpdate(tile, coordinata);
		this.proxy.setCommandString(stringaUpdate);
	}

}
