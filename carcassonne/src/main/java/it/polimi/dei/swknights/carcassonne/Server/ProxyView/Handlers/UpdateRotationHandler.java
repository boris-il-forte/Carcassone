package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
/**
 * This class handles the update position event in proxy mode
 * @author edoardo
 *
 */
public class UpdateRotationHandler extends ProxyViewHandler
{

	/**
	 * basic constructor
	 * @param proxy
	 */

	public UpdateRotationHandler(ProxyView proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(UpdateRotationEvent event)
	{
		StringBuilder stringCommand = new StringBuilder();
		stringCommand.append("rotated:");
		stringCommand.append(event.getTessera().toProtocolString());

		this.proxy.setCommandString(stringCommand.toString());

	}

}
