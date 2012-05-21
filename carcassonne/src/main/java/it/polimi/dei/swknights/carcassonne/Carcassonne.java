package it.polimi.dei.swknights.carcassonne;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;
import it.polimi.dei.swknights.carcassonne.Client.View.CLI.StringBuilder2D;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.*;

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
		FactoryTessereNormali fnTest = new FactoryTessereNormali();
		fnTest.acquisisciMazzoDaFile(PathGioco.BASE_PATH + "Carcassonne.txt");
		Tessera tessera = fnTest.getTessera();
		String stringTessera1 = tessera.toString();
		tessera.setSegnalino(new Segnalino(Color.RED), PuntoCardinale.nord);
		String stringTessera2 = tessera.toString();
		System.out.println(stringTessera1);
		System.out.println(stringTessera2);
		DatiMappa dati = new DatiMappa(new Coordinate(-5, -6), new Coordinate(5, 6));
		StringBuilder2D builder = new StringBuilder2D(dati);
		builder.addTessera(new Coordinate(0, 0), "", new Vicinato());
		
		String stringhe[] = builder.toString().split(";");
		for(String string : stringhe)
		{
			System.out.println(string);
		}
	}

}
