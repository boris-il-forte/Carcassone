package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

/**
 * Handler class for the pass event coming from the client. It implements
 * visitor pattern to handle this event
 * 
 * @author dave
 * 
 */
public class PassHandler extends ModuloControllerHandler
{
	/**
	 * Default constructor
	 * 
	 * @param controller
	 *            the controller that uses this handler
	 * @param model
	 *            the model related to the controller
	 */
	public PassHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	/**
	 * Handler method that uses visitor's pattern for the pass event
	 */
	@Override
	public void visit(PassEvent event)
	{
		this.controller.comunicaPosizionamentoTessera();
		this.sveglia();
	}
}
