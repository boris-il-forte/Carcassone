package it.polimi.dei.swknights.carcassonne;

import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Events.Controller;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;


public class Carcassonne
{
	public static void main(String[] args)
	{

		// TODO:togliere questo baby-schifo test in luogo di migliori, ma fino
		// ad allora..

		/*FactoryTessereNormali fnTest = new FactoryTessereNormali();
		fnTest.acquisisciMazzoDaFile("/Carcassonne.txt");
		Tessera tessera = fnTest.getTessera();
		String stringTessera1 = tessera.toString();
		tessera.setSegnalino(new Segnalino(Color.RED), PuntoCardinale.nord);
		String stringTessera2 = tessera.toString();
		System.out.println(stringTessera1);
		System.out.println(stringTessera2);*/

		Executor superStarDestroyer = Executors.newCachedThreadPool();  
		View view = new Cli();
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
		while(risposta.compareToIgnoreCase("CLI") != 0);
		
		Controller controller = new ModuloController();
	    view = new Cli();		
		view.addListener(controller);
	 	controller.addListener(view);
	 	superStarDestroyer.execute(view);
	 	superStarDestroyer.execute(controller);	 	
	}

}












