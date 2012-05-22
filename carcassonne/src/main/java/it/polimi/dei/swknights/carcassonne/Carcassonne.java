package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.FactoryTessereNormali;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

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
		fnTest.acquisisciMazzoDaFile("/Carcassonne.txt");
		Tessera tessera = fnTest.getTessera();
		String stringTessera1 = tessera.toString();
		tessera.setSegnalino(new Segnalino(Color.RED), PuntoCardinale.nord);
		String stringTessera2 = tessera.toString();
		System.out.println(stringTessera1);
		System.out.println(stringTessera2);
	}

}
