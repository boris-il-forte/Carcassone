package it.polimi.dei.swknights.carcassonne.Model;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.TesseraNormale;

import java.util.List;
import java.util.Map;


import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModuloModelTest
{

	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}

	@Test
	public void addSegnalino() throws Exception
	{
		ModuloModel model = new ModuloModel();
		
		model.getTesseraDaMazzo();
		Tessera tesseraSenza = model.getTesseraCorrente();	
		String stringTesseraSenzaSengalino = tesseraSenza.toString();
		
		model.addSegnalinoTesseraCorrente(PuntoCardinale.est);		
		Tessera tesseraCon = model.getTesseraCorrente();
		
		if (tesseraCon.toString().equals( stringTesseraSenzaSengalino ) )
			fail(" " + tesseraCon.toString() + " @@@@ "+ tesseraSenza.toString());
		else
			assertTrue(" " + tesseraCon.toString() + tesseraSenza.toString(), true);
		
		
	}
	


}
