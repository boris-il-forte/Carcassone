package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class PassHandler extends ControllerHandler
{
	public PassHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	@Override
	public void visit(PassEvent event)
	{
		this.sveglia();
	}
}
