package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;

public abstract class ViewHandler
{
	public void visit (UpdateTurnoEvent ute) {}
	
	public void visit (UpdateRotationEvent ure) {}
	
	public void visit(UpdatePositionEvent upe) {}
	
	public void visit (MossaNonValidaEvent mne) {}
	
	public void visit (InizioGiocoEvent ute) {}
	
	public void visit (FinePartitaEvent fpe) {}
	
	public void visit (CostruzioneCompletataEvent cce) {}

}
