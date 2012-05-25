package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;


/**
 * Hello world!
 * 
 */
public class Carcassonne
{
	public static void main(String[] args)
	{
		System.out.println("Hello World!");

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

		
		ModuloView cli = new Cli();
		
		String risposta="";
		do
		{
			System.out.println("Interfaccia grafica a finestra o Interfaccia testuale?");
			System.out.println("Scrivi CLI per testuale o GUI per grafica");
			java.util.Scanner scannerIO = new java.util.Scanner(System.in);
			risposta = scannerIO.nextLine();
		}
		while(risposta.compareToIgnoreCase("CLI") != 0);
		
		ModuloController controllerGame = new ModuloController();
		
	    cli = new Cli();		
		cli.addListener(controllerGame);
	 	
	 	controllerGame.addListener(cli);
	
		controllerGame.cominciaGioco();
		
	}

}












