package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;

public abstract class ViewHandler
{
	public ViewHandler(ModuloView view)
	{
		this.view=view;
	}
	
	public void visit (UpdateTurnoEvent event) {}
	
	public void visit (UpdateRotationEvent event) {}
	
	public void visit (UpdatePositionEvent event) {}
	
	public void visit (MossaNonValidaEvent event) {}
	
	public void visit (InizioGiocoEvent event) {}
	
	public void visit (FinePartitaEvent event) {}
	
	public void visit (CostruzioneCompletataEvent event) {}

	public synchronized void sveglia()
	{
		this.view.notifyAll();

	}
	
	protected ModuloView view;
}
