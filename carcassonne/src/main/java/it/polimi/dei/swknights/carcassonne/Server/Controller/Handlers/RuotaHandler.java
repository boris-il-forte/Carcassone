package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

/**
 * Handler class for the rotate event coming from the client. It implements
 * visitor pattern to handle this event
 * 
 * @author dave
 * 
 */

public class RuotaHandler extends ModuloControllerHandler
{
	/**
	 * Default constructor
	 * 
	 * @param controller
	 *            the controller using this handler
	 * @param model
	 *            the model related to this controller
	 */
	public RuotaHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	/**
	 * Handler method that uses visitor's pattern for rotate event
	 */
	@Override
	public void visit(RotateEvent event)
	{
		if (this.controller.getGestoreFasi().ruotaOk())
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
