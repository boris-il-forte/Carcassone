package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;

public class MossaNonValidaHandler extends ViewHandler
{
	@Override
	public void visit(MossaNonValidaEvent event)
	{
		this.notificaMossaNonValida();
	}
}
