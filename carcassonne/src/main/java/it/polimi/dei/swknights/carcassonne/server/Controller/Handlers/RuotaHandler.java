package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class RuotaHandler extends ControllerHandler
{
	private ModuloController	controller;

	private ModuloModel				model;

	public RuotaHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
		
		this.model = model;
	}

	@Override
	public void visit(RotateEvent event)
	{
		if(this.controller.ruotaOk())
		{
			this.model.ruotaTessera();
		}
		else
		{
			this.model.fire(new MossaNonValidaEvent(this.model));
		}
	}
}
