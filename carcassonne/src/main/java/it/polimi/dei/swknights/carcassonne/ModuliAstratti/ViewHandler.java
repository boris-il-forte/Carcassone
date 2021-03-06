package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;

/**
 * Standard class used to implement visitor pattern on view handlers. all
 * implemented methods do NOP, to make possible override the methods of interest
 * for the handler in order to handle the correct event
 * 
 * @author dave
 * 
 */

public class ViewHandler
{
	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(UpdateTurnoEvent event)
	{
	}
	
	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(UpdateRotationEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(UpdatePositionEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(MossaNonValidaEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(InizioGiocoEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(FinePartitaEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(CostruzioneCompletataEvent event)
	{
	}
}
