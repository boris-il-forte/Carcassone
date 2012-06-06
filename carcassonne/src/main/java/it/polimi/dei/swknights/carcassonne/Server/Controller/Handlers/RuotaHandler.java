package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public class RuotaHandler extends ModuloControllerHandler
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
