package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.server.Controller.Controller;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class RuotaHandler extends ControllerHandler
{
	private Controller	controller;

	public RuotaHandler(Controller controller)
	{
		this.controller = controller;
	}

	@Override
	public void visit(RotateEvent event)
	{
		Tessera tessera = this.controller.getTesseraCorrente();
		tessera.ruota();
		controller.fire(new UpdateRotationEvent(tessera, this.controller.getGiocatoreCorrente(), controller));
	}
}
