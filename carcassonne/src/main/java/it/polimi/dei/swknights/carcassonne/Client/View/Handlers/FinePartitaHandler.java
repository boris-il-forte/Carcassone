package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;

public class FinePartitaHandler extends ViewHandler
{

	@Override
	public void visit(FinePartitaEvent event)
	{
		this.notificaFinePartita();
	}
}
