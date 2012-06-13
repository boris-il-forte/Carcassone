package it.polimi.dei.swknights.carcassonne.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.Server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatiPartitaTest
{

	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}
	
	@Test
	public void getSetCoordinateVuotee()
	{
		DatiPartita dati = new DatiPartita();
		Set<Coordinate> setCoordVuote =  dati.getSetCoordinateVuote();
		assertTrue( setCoordVuote instanceof Set );
		//testato in area di gioco
	}
	
	@Test
	public void getAreaGioco()
	{
		DatiPartita dati = new DatiPartita();
		AreaDiGioco area =  dati.getAreaDiGioco();
		assertTrue( area instanceof AreaDiGioco );
	}
	
	@Test
	public void addSquadraDiCalcio() throws Exception
	{
		DatiPartita dati = new DatiPartita();
		int i = 1;
		try
		{
			for (i = 0; i <= 11; i++)
			{
				dati.addGiocatore();
			}
		}
		catch (FinitiColoriDisponibiliException e)
		{
			if (i != 5)
			{
				fail("l'errore finiti color disponibili dovrebbe scattare dopo "
						+ "il tentativo di aggiunta del quinto (5°)  " + " i = " + i);
			}
			else
			{
				assertTrue(true);
			}
		}
	}

	@Test
	public void aggiornaPunteggiGiocatori() throws Exception
	{
		DatiPartita dati = new DatiPartita();
		dati.addGiocatore();
		dati.addGiocatore();
		
	
		Punteggi punti = new Punteggi();
		
		punti.addPunteggi(Color.red, 20);
		punti.addPunteggi(Color.blue, 10);
		
		dati.aggiornaPunteggioGiocatori(punti);
		assertTrue(" ok 20 punti " +
				"" + dati.getGiocatoreCorrente().getPunti(),
				dati.getGiocatoreCorrente().getPunti() == 20) ;
		assertTrue(" ok 10 punti ",  dati.getGiocatore(Color.red).getPunti() == 20) ;
		
		punti.addPunteggi(Color.blue, 15);
		
		dati.aggiornaPunteggioGiocatori(punti);
		
		assertTrue(" ok 35 punti, attesi =  " + dati.getGiocatore(Color.blue).getPunti(),  dati.getGiocatore(Color.blue).getPunti() == 35) ;
		
	}
	
	/*
	 * java.lang.AssertionError: l'errore finiti color disponibili dovrebbe
	 * scattare dopo il tentativo di aggiunta del quinto (5°) i = 5
	 */

	@Test
	public void pescaPrimaTessera()
	{
		DatiPartita dati = new DatiPartita();
		TesseraNormale tessera = (TesseraNormale) dati.pescaPrimaTessera();

		if (tessera.getElementoA(PuntoCardinale.nord) != Elemento.prato)
		{
			fail("non prato a nord, prima tessera 0,0");
		}

		if (tessera.getElementoA(PuntoCardinale.est) != Elemento.strada)
		{
			fail("non strada a est, prima tessera 0,0");
		}

		if (tessera.getElementoA(PuntoCardinale.ovest) != Elemento.strada)
		{
			fail("non strada a ovest, prima tessera 0,0");
		}

		if (tessera.getElementoA(PuntoCardinale.sud) != Elemento.citta)
		{
			fail("non citta a sud, prima tessera 0,0");
		}

		assertTrue(true);
	}

	@Test
	public void coordTesseraFromTessera() throws Exception
	{
		DatiPartita dati = new DatiPartita();
		Tessera tessera = dati.pescaPrimaTessera();

		AreaDiGioco area = dati.getAreaDiGioco();
		area.addTessera(new Coordinate(3, 4), tessera);

		Coordinate coord = dati.getCoordinateTessera(tessera);

		assertEquals(new Coordinate(3, 4), coord);
	}

	@Test
	public void getGiocatore() throws Exception
	{
		DatiPartita dati = new DatiPartita();

		do
		{
			dati.addGiocatore();
		} while (dati.getListaGiocatori().size() < 3);

		Giocatore g0 = dati.getGiocatore(ColoriGioco.getListaColori().get(0));
		if (g0.getColore() != Color.RED)
		{
			fail("g0 non è rosso");
		}

		Giocatore g1 = dati.getGiocatore(ColoriGioco.getListaColori().get(1));
		if (g1.getColore() != Color.BLUE)
		{
			fail("g1 non è Blu");
		}

		Giocatore g2 = dati.getGiocatore(ColoriGioco.getListaColori().get(2));
		if (g2.getColore() != Color.GREEN)
		{
			fail("g2 non è verde");
		}

		assertTrue(true);

	}

	@Test
	public void pesca56DalMazzo() throws Exception
	{
		DatiPartita dati = new DatiPartita();
		int cartePesc = 0;
		try
		{
			while (true)
			{
				dati.pescaTesseraDalMazzo();
				cartePesc++;
			}
		}
		catch (PartitaFinitaException e)
		{
			if (cartePesc == 55)
			{
				assertTrue(true); // 1 pescata di base (la prima) + 55
			}
		}

	}

	

	
}
