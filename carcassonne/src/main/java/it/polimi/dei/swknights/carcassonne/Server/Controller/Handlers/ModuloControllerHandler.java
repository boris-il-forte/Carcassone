package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ControllerHandler;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public abstract class ModuloControllerHandler extends ControllerHandler
{
	public ModuloControllerHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
		this.model = model;
	}

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
