package it.polimi.dei.swknights.carcassonne.ModuliAstratti;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;

/**
 * Standard class used to implement visitor pattern on controller Handlers. all
 * implemented methods do NOP, to make possible override the methods of interest
 * for the handler in order to handle the correct event
 * 
 * @author dave
 * 
 */

public abstract class ControllerHandler
{
	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(PassEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(PlaceEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(RotateEvent event)
	{
	}

	/**
	 * default visitor's handler, does nothing
	 */
	public void visit(TileEvent event)
	{
	}

}
