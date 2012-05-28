package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;

public class MossaNonValidaHandler extends ViewHandler
{
	public MossaNonValidaHandler(ModuloView view)
	{
		this.view = view;
	}

	@Override
	public void visit(MossaNonValidaEvent event)
	{
		this.view.notificaMossaNonValida();
	}

	private ModuloView	view;
}
