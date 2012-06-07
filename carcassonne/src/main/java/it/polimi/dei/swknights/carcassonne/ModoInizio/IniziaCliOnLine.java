package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.io.IOException;
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
		try
		{
		this.printer.println("cli on line");
		View view = new Cli(); //1)
		Socket socket = CarcassonneSocket.dammiSocket();	//2)
		ProxyController controller;
		
			controller = new ProxyController(socket);

		  //2
		view.addListener(controller);  //2)		
		this.superStarDestroyer.execute(view);  //4)
		this.superStarDestroyer.execute(controller);
		}
		catch(IOException e)
		{
			
		}
	}
	
}
