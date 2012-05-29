package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class PassHandler extends ControllerHandler
{
	public PassHandler(ModuloController controller, ModuloModel model, ModuloView view)
	{
		super(controller, model, view);
	}

	@Override
	public void visit(PassEvent event)
	{
		this.sveglia();
	}
}
