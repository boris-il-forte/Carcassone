package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.*;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public abstract class ControllerHandler
{
	public ControllerHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
		this.model = model;
	}

	public void visit(PassEvent event)
	{
	}

	public void visit(PlaceEvent event)
	{
	}

	public void visit(RotateEvent event)
	{
	}

	public void visit(TileEvent event)
	{
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
