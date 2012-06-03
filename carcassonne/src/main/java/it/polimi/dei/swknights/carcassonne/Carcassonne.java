package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.server.CarcassonneServer;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.ProxyView;

import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Carcassonne
{
	public static void main(String[] args)
	{
		Executor superStarDestroyer = Executors.newCachedThreadPool();
		String risposta = "";
		View view;
		
		JCarcassonneBegin begin = new JCarcassonneBegin();
		

		risposta = begin.ShowDialog();
		
		System.out.println(" risposta = " + risposta);
		
		Socket socket = null;
		ProxyController proxyController = null;
		ProxyView proxyView = null; 
		

		
	
		if (risposta.equalsIgnoreCase("CLI"))
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
		else if (risposta.equalsIgnoreCase("GUI"))
		{
			ModuloModel model = new ModuloModel();
			Controller controller = new ModuloController(model);
			
			view = new Gui();
			
			superStarDestroyer.execute(view);
			superStarDestroyer.execute(controller);
		}
		else if ( risposta.equalsIgnoreCase("Server"))
		{
			ModuloModel model = new ModuloModel();
			Controller controller = new ModuloController(model);
			
			System.out.println("solo server");
			CarcassonneServer cs = new CarcassonneServer();
			socket = CarcassonneSocket.dammiSocket();			
			proxyView = new ProxyView(socket);
			model.addListener(proxyView);
			superStarDestroyer.execute(controller);			
		}
		else if( risposta.equalsIgnoreCase("SERVER+CLI"))
		{
			System.out.println("SERVER+CLI");
			view = new Cli(); //1)
			socket = CarcassonneSocket.dammiSocket();	//2)
			proxyController = new ProxyController(socket);  //2
			view.addListener(proxyController);  //2)
			
			//3) da farsi su server
			
			superStarDestroyer.execute(view);  //4)
			
		}
		


	}
}
