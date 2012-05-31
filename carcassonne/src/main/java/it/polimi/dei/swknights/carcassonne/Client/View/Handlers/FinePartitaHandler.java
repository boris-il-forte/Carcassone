package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;

public class FinePartitaHandler extends ViewHandler
{
	public FinePartitaHandler(ModuloView view)
	{
		super(view);
	}
	/**
	 * Just notify the view the end of the game
	 */
	@Override
	public void visit(FinePartitaEvent event)
	{
		this.view.notificaFinePartita();
		this.sveglia();
	}

}
