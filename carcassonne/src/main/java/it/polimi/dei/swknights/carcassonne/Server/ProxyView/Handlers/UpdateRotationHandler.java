package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

public class UpdateRotationHandler extends ProxyViewHandler
{

	public UpdateRotationHandler(ProxyView proxy)
	{
		super(proxy);
		// TODO Auto-generated constructor stub
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
