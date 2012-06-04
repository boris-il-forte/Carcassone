package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class IniziaGuiOffline extends Inizio
{

	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);
	
		view = new Gui();
		view.addListener(controller);
		model.addListener(view);
		
		
		superStarDestroyer.execute(view);
		superStarDestroyer.execute(controller);
		
	}
	View view;

}
