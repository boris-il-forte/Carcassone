package it.polimi.dei.swknights.carcassonne.modoInizio;

import java.net.Socket;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Events.View;


public class IniziaCliOnLine extends Inizio
{

	@Override
	public void inizia()
	{
		System.out.println("cli on line");
		view = new Cli(); //1)
		socket = CarcassonneSocket.dammiSocket();	//2)
		controller = new ProxyController(socket);  //2
		view.addListener(controller);  //2)
		
		//3) model . addlistener (view) da farsi su server
		
		superStarDestroyer.execute(view);  //4)
		superStarDestroyer.execute(controller);
	}
	
	Socket socket = null;
	ProxyController controller = null;
	View view;
}
