package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneCitta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;


public class TesseraTest
{
	private static Map<Costruzione, List<PuntoCardinale>>   mappaExp;
	private static Map<Costruzione, List<PuntoCardinale>>   mappaGot;
	
	@BeforeClass
	public static void initializeTest() throws Exception
	{
	 
	}
	
	private Lati creaLatiCittaGrande()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.citta;
		Elemento sud = Elemento.citta;
		Elemento ovest = Elemento.citta;
		Elemento est = Elemento.citta;
		latiCreandi = new  Lati(nord, sud, ovest, est);
		return latiCreandi;
	}
	
	private Link creaLinkCittaGrande() throws IllegalArgumentException
	{
		/*NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl =  { true, true, true, true, true, true }; 
		Link l = new  Link(bl);
		return l;
				
	}
	
	
	@Test
	public void testCittaGrande() throws Exception
	{
		TesseraNormale tessera = new TesseraNormale(creaLatiCittaGrande(), creaLinkCittaGrande());
		
		List<PuntoCardinale> punti = new ArrayList<PuntoCardinale>();
		for(PuntoCardinale p : PuntoCardinale.values() ) //tutti connessi e unica città
		{
			punti.add(p);
		}
		
		mappaExp = new HashMap<Costruzione, List<PuntoCardinale>>();
		mappaExp.put( new CostruzioneCitta(tessera), punti);
		
		mappaGot = tessera.getCostruzioni();
		
		if(! stesseCostruzioniMappe(mappaGot, mappaExp))
		{
			assertTrue("nemmeno le stesse costruzioni!", false);
		}
		else
		{
			assertTrue(true);
		}
	}
	
	
	
	private boolean stesseCostruzioniMappe
	(Map<Costruzione, List<PuntoCardinale>> m1, Map<Costruzione, List<PuntoCardinale>> m2)
	{
		//DELIRIO!
		Set<Costruzione> costruzioniM1 = m1.keySet();
		Set<Costruzione> costruzioniM2 = m2.keySet();
		
		if (costruzioniM1.containsAll(costruzioniM2) && costruzioniM2.containsAll(costruzioniM1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
}
