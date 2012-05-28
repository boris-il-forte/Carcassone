package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;

public class FinePartitaHandler extends ViewHandler
{
	public FinePartitaHandler(ModuloView view)
	{
		this.view = view;
	}

	@Override
	public void visit(FinePartitaEvent event)
	{
		this.view.notificaFinePartita();
	}

	private ModuloView	view;
}
