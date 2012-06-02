package it.polimi.dei.swknights.carcassonne.Util;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;


public class BussolaTest
{
	
	private static Bussola direzione;
	
	@BeforeClass
	public static void setUp()
	{
		
	}
	
	@Test
	public void componiPuntiCardinale()
	{
		direzione = Bussola.WE;
		
		direzione = Bussola.componi(PuntoCardinale.ovest, PuntoCardinale.est);
		if (direzione != Bussola.WE)
			fail(" composizione doveva ritornare WE,  invece ha ritornato :" + direzione.toString());
		
		direzione = Bussola.componi(PuntoCardinale.nord, PuntoCardinale.est);
		if (direzione != Bussola.NE)
			fail(" composizione doveva ritornare NE,  invece ha ritornato :" + direzione.toString());
		
		direzione = Bussola.componi(PuntoCardinale.nord, PuntoCardinale.ovest);
		if (direzione != Bussola.NW)
			fail(" composizione doveva ritornare NW,  invece ha ritornato :" + direzione.toString());
		
		direzione = Bussola.componi(PuntoCardinale.nord, PuntoCardinale.sud);
		if (direzione != Bussola.NS)
			fail(" composizione doveva ritornare NS,  invece ha ritornato :" + direzione.toString());
		
		
		direzione = Bussola.componi(PuntoCardinale.est, PuntoCardinale.sud);
		if (direzione != Bussola.SE)
			fail(" composizione doveva ritornare SE,  invece ha ritornato :" + direzione.toString());
		
		direzione = Bussola.componi(PuntoCardinale.ovest, PuntoCardinale.sud);
		if (direzione != Bussola.SW)
			fail(" composizione doveva ritornare SW,  invece ha ritornato :" + direzione.toString());
		
		
		assertTrue(true);
	}
	
	
	
}

