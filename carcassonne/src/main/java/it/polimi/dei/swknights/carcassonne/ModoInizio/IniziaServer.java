package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.server.CarcassonneServer;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.ProxyView;

public class IniziaServer extends Inizio
{

	
	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);
		
		System.out.println("Server");
		new CarcassonneServer(); //TODO: così fa abbastanza schifo...
		
		ServerSocket serverSocket;
		Socket socket=null;
		boolean fatto = false;
		while(fatto == false)
		{
		try
		{
			serverSocket = new ServerSocket(1984);
			socket = serverSocket.accept();
			fatto = true;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		}
		
		view = new ProxyView(socket);
		model.addListener(view);

		superStarDestroyer.execute(view);
		
	}
	
	
	Socket socket = null;
	ProxyController controller = null;
	ProxyView view = null; 

}
