package it.polimi.dei.swknights.carcassonne.modoInizio;

import java.net.Socket;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.ProxyView;

public class IniziaCliOnLine extends Inizio
{

	@Override
	public void inizia()
	{
		System.out.println("cli on line");
		view = new Cli(); //1)
		socket = CarcassonneSocket.dammiSocket();	//2)
		proxyController = new ProxyController(socket);  //2
		view.addListener(proxyController);  //2)
		
		//3) da farsi su server
		
		superStarDestroyer.execute(view);  //4)
		
	}
	
	Socket socket = null;
	ProxyController proxyController = null;
	ProxyView proxyView = null; 
	View view;
}
