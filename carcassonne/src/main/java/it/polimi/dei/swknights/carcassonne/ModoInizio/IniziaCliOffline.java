package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public class IniziaCliOffline extends Inizio
{

	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);
		
		System.out.println("cli!!");
		view = new Cli();  // 1)
		
		view.addListener(controller);  //2)
		model.addListener(view);   //3)
		
		superStarDestroyer.execute(view);  //4) 
		superStarDestroyer.execute(controller);

	}
	View view;

}
