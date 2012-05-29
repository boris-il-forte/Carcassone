package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.*;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public abstract class ControllerHandler
{
	public ControllerHandler(ModuloController controller, ModuloModel model,
			ModuloView view)
	{
		this.controller = controller;
		this.model = model;
		this.view = view;
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

	protected synchronized void sveglia()
	{
		System.out.println("notifico");
		this.view.notifyAll();
	}

	protected ModuloController	controller;

	protected ModuloModel		model;
	
	protected ModuloView   view;

}
