package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class RuotaHandler extends ControllerHandler
{
	private ModuloController	controller;

	private ModuloModel				model;

	public RuotaHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
	}

	@Override
	public void visit(RotateEvent event)
	{
		Tessera tessera = this.controller.getTesseraCorrente();
		tessera.ruota();
		this.model.fire(new UpdateRotationEvent(tessera, this.controller.getGiocatoreCorrente(), controller));
	}
}
