package it.polimi.dei.swknights.carcassonne;

import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;


class Carcassonne
{
	public static void main(String[] args)
	{
		Executor superStarDestroyer = Executors.newCachedThreadPool();  
		PrintWriter out = new PrintWriter(System.out);
		String risposta="";
		do
		{
			out.println("Interfaccia grafica a finestra o Interfaccia testuale?");
			out.println("Scrivi CLI per testuale o GUI per grafica");
			out.flush();
			java.util.Scanner scannerIO = new java.util.Scanner(System.in);
			risposta = scannerIO.nextLine();
		}
		while(!risposta.equalsIgnoreCase("CLI"));

		
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);

		View view = new Cli();		
		view.addListener(controller);
	 	model.addListener(view);
	 	superStarDestroyer.execute(view);
	 	superStarDestroyer.execute(controller);	 	
	}

}












