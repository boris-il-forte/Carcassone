package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class RuotaHandler extends ControllerHandler
{
	public RuotaHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	@Override
	public void visit(RotateEvent event)
	{
		if(this.controller.getGestoreFasi().ruotaOk())
		{
			this.model.ruotaTessera();
		}
		else
		{
			this.model.fire(new MossaNonValidaEvent(this.model));
		}
		this.sveglia();
	}
}
