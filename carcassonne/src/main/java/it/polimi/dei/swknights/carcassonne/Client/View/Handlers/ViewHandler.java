package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
/**
 * A basic schema for View events,
 * Used mainly to implement the visitor pattern
 * @author edoardopasi
 *
 */
public abstract class ViewHandler
{
	public ViewHandler(ModuloView view)
	{
		this.view=view;
	}
	
	public ViewHandler()
	{
		
	}
	
	public void visit (UpdateTurnoEvent event) {}
	
	public void visit (UpdateRotationEvent event) {}
	
	public void visit (UpdatePositionEvent event) {}
	
	public void visit (MossaNonValidaEvent event) {}
	
	public void visit (InizioGiocoEvent event) {}
	
	public void visit (FinePartitaEvent event) {}
	
	public void visit (CostruzioneCompletataEvent event) {}

	public void sveglia()
	{
		synchronized (this.view)
		{
			this.view.notifyAll();
		}

	}
	
	protected ModuloView view;
}

/*
Lorem ipsum dolor sit amet, 
consectetur adipisicing elit,
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
Ut enim ad minim veniam, 
quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
 
*/

