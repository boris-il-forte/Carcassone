package it.polimi.dei.swknights.carcassonne.Model;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.TesseraNormale;

import java.util.List;
import java.util.Map;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModuloModelTest
{

	private static ModuloModel model;
	
	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}
	
	@Before
	public void initModel()
	{
		model = new ModuloModel();
	}
	@After
	public void deleteModel()
	{
		model = null;
	}

	@Test
	public void addSegnalino() throws Exception
	{		
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
	
	@Test
	public void iniziaGioco() throws Exception
	{
		
		model.iniziaGioco(2);
		TesseraNormale tessera = (TesseraNormale)  model.getTessera(new Coordinate(0, 0));
		
		if(isPrimaTessera(tessera) == false)
			fail("all'inizio del gioco non viene pescata la prima tessera");
		
		model.cominciaTurno();
		
		//model si esprime tramite eventi.. devo testare qua la gestione degli eventi??
		
	}
	
	
	private boolean isPrimaTessera(TesseraNormale tessera)
	{
		boolean prima = true;
		
		if ( tessera.getElementoA(PuntoCardinale.nord) != Elemento.prato)
			prima = false;
		
		if ( tessera.getElementoA(PuntoCardinale.est) != Elemento.strada)
			prima = false;
		
		if ( tessera.getElementoA(PuntoCardinale.ovest) != Elemento.strada)
			prima = false;
		
		if ( tessera.getElementoA(PuntoCardinale.sud) != Elemento.citta)
			prima = false;
		
		return prima;
	}
	
	


}
