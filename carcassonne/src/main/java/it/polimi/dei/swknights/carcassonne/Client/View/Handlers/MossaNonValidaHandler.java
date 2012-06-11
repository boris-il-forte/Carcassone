package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;

public class MossaNonValidaHandler extends ModuloViewHandler
{
	public MossaNonValidaHandler(ModuloView view)
	{
		super(view);
	}

	/**
	 * Notify the View about a non-valid command was given
	 */
	@Override
	public void visit(MossaNonValidaEvent event)
	{
		this.view.notificaMossaNonValida();
		this.sveglia();
		
	}

}
