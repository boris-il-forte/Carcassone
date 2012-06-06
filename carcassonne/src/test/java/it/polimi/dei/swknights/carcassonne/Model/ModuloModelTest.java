package it.polimi.dei.swknights.carcassonne.Model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

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
		model.addPlayer();
		model.addPlayer();
		
		model.iniziaGioco();
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
