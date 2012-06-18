package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public abstract class ModuloControllerHandler extends ControllerHandler
{
	/**
	 * Default constructor for generic ModuloController handler
	 * 
	 * @param controller
	 *            the contrroller that manage this handler
	 * @param model
	 *            the model managed by the handler and controller
	 */
	public ModuloControllerHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
		this.model = model;
	}

	/**
	 * method that wakes up the controller
	 */

	public void sveglia()
	{
		synchronized (this.controller)
		{
			this.controller.notifyAll();
		}
	}

	protected ModuloController	controller;

	protected ModuloModel		model;

}
