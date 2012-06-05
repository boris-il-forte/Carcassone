package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;

public class ViewHandler
{
	public void visit(UpdateTurnoEvent event)
	{
	}

	public void visit(UpdateRotationEvent event)
	{
	}

	public void visit(UpdatePositionEvent event)
	{
	}

	public void visit(MossaNonValidaEvent event)
	{
	}

	public void visit(InizioGiocoEvent event)
	{
	}

	public void visit(FinePartitaEvent event)
	{
	}

	public void visit(CostruzioneCompletataEvent event)
	{
	}
}
