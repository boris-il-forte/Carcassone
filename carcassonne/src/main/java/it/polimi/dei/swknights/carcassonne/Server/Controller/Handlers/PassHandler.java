package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public class PassHandler extends ModuloControllerHandler
{
	public PassHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	@Override
	public void visit(PassEvent event)
	{
		this.controller.comunicaPosizionamentoTessera();
		this.sveglia();
	}
}
