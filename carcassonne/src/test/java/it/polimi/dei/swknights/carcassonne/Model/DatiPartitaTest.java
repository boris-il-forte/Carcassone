package it.polimi.dei.swknights.carcassonne.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneCitta;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneStrada;
import it.polimi.dei.swknights.carcassonne.Server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.Server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatiPartitaTest
{

	private static Map<Costruzione, List<PuntoCardinale>>	mappaExp;
	private static Map<Costruzione, List<PuntoCardinale>>	mappaGot;

	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}

	@Test
	public void addSquadraDiCalcio() throws Exception
	{
		DatiPartita dati = new DatiPartita();
		int i = 1;
		try
		{
			for (i = 1; i <= 11; i++)
			{
				dati.addGiocatore();
			}
		}
		catch (FinitiColoriDisponibiliException e)
		{
			if (i != 5)
				fail("l'errore finiti color disponibili dovrebbe scattare dopo "
						+ "il tentativo di aggiunta del quinto (5°)  " + " i = " + i);
			else assertTrue(true);
		}
	}

	/*
	 * java.lang.AssertionError: l'errore finiti color disponibili dovrebbe
	 * scattare dopo il tentativo di aggiunta del quinto (5°) i = 5
	 */

	@Test
	public void pescaPrimaTessera()
	{
		DatiPartita dati = new DatiPartita();
		TesseraNormale tessera = (TesseraNormale)dati.pescaPrimaTessera();

		if ( tessera.getElementoA(PuntoCardinale.nord) != Elemento.prato)
			fail("non prato a nord, prima tessera 0,0");
		
		if ( tessera.getElementoA(PuntoCardinale.est) != Elemento.strada)
			fail("non strada a est, prima tessera 0,0");
		
		if ( tessera.getElementoA(PuntoCardinale.ovest) != Elemento.strada)
			fail("non strada a ovest, prima tessera 0,0");
		
		if ( tessera.getElementoA(PuntoCardinale.sud) != Elemento.citta)
			fail("non citta a sud, prima tessera 0,0");
		
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
		if (g0.getColore() != Color.RED) fail("g0 non è rosso");

		Giocatore g1 = dati.getGiocatore(ColoriGioco.getListaColori().get(1));
		if (g1.getColore() != Color.BLUE) fail("g1 non è Blu");

		Giocatore g2 = dati.getGiocatore(ColoriGioco.getListaColori().get(2));
		if (g2.getColore() != Color.GREEN) fail("g2 non è verde");

		assertTrue(true);

	}
	
	@Test
	public void pesca56DalMazzo() throws Exception
	{
		DatiPartita dati = new DatiPartita();
		int cartePesc =0;
		try
		{
			while(true)	
			{
				dati.pescaTesseraDalMazzo();
				cartePesc ++;
			}
		}
		catch(PartitaFinitaException e)
		{
			if (cartePesc == 55)
				assertTrue(true);  // 1 pescata di base (la prima) + 55 
		}
		
	
	}
	
	

	private Tessera tesseraCitta()
	{
		Tessera t1 = new TesseraNormale(creaLatiCittaGrande(), creaLinkCittaGrande());
		return t1;
	}

	private boolean stesseListePuntiCardinali(List<Costruzione> CostruzioniExp,
			List<Costruzione> CostruzioniGot)
	{
		ordinaCostruzioni(CostruzioniGot);
		ordinaCostruzioni(CostruzioniExp);

		if (CostruzioniExp.size() == CostruzioniGot.size())
		{
			for (int i = 0; i < CostruzioniExp.size(); i++)
			{
				Costruzione costruzioneExp = CostruzioniExp.get(i);
				Costruzione costruzioneGot = CostruzioniGot.get(i);
				List<PuntoCardinale> puntiExp = mappaExp.get(costruzioneExp);
				List<PuntoCardinale> puntiGot = mappaGot.get(costruzioneGot);
				if (puntiExp.equals(puntiGot) == false) { return false; }
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private void ordinaCostruzioni(List<Costruzione> costruzioniGot)
	{
		Collections.sort(costruzioniGot, new Comparator<Costruzione>()
		{

			public int compare(Costruzione c1, Costruzione c2)
			{
				if (c1.contaElementi() != c2.contaElementi())
				{
					return c1.contaElementi() - c2.contaElementi();
				}
				else
				{
					if (c1 instanceof CostruzioneCitta && c2 instanceof CostruzioneStrada) { return 1; }
					if (c1 instanceof CostruzioneStrada && c2 instanceof CostruzioneCitta) { return -1; }

				}
				return 0;
			}
		});
	}

	private List<Costruzione> listaDa(Set<Costruzione> keySet)
	{
		List<Costruzione> listaC = new ArrayList<Costruzione>();
		for (Costruzione c : keySet)
		{
			listaC.add(c);
		}
		return listaC;
	}

	private Lati creaLatiCittaGrande()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.citta;
		Elemento sud = Elemento.citta;
		Elemento ovest = Elemento.citta;
		Elemento est = Elemento.citta;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkCittaGrande() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { true, true, true, true, true, true };
		Link l = new Link(bl);
		return l;

	}

}
