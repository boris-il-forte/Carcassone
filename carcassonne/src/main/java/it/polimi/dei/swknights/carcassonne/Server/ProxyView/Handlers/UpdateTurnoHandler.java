package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

/**
 * This class handles the update position event in proxy mode
 * @author edoardo
 *
 */

public class UpdateTurnoHandler extends ProxyViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */


	public UpdateTurnoHandler(ProxyView proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(UpdateTurnoEvent event)
	{
		StringBuilder stringCommand = new StringBuilder();
		stringCommand.append("turn:");
		stringCommand.append(ColoriGioco.getProtocolName(event.getGiocatoreCorrente()));
		this.proxy.setCommandString(stringCommand.toString());

		stringCommand = new StringBuilder();
		stringCommand.append("next:");
		stringCommand.append(event.getTessera().toProtocolString());

		this.proxy.setCommandString(stringCommand.toString());
		
	}

}
