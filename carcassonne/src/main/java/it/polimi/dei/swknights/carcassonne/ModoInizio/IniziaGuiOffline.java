package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

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
