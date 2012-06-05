package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.net.Socket;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;


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
		superStarDestroyer.execute(view);  //4)
		superStarDestroyer.execute(controller);
	}
	
	private Socket socket = null;
	private ProxyController controller = null;
	private View view;
}
