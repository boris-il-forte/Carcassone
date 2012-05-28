package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;

public class UpdateRotationHandler extends ViewHandler
{

	@Override
	public void visit(UpdatePositionEvent event)
	{
		this.aggiornaMappa();
		UpdateRotationEvent ure = (UpdateRotationEvent) event;
		AdapterTessera tesseraNuova = ure.getTessera();
		this.cambiaEMostraTesseraCorrente(tesseraNuova);
	}
	
}
