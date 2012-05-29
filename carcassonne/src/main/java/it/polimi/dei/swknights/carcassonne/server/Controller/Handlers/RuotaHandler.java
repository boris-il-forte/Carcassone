package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class RuotaHandler extends ControllerHandler
{
	public RuotaHandler(ModuloController controller, ModuloModel model, ModuloView view)
	{
		super(controller, model,view);
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
		this.sveglia();
	}
}
