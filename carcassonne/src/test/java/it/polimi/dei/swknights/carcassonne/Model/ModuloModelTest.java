package it.polimi.dei.swknights.carcassonne.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModuloModelTest
{

	private static ModuloModel	model;

	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}

	@Before
	public void initModel()
	{
		model = new ModuloModel();
		model.addPlayer();
	}

	@After
	public void deleteModel()
	{
		model = null;
	}

	@Test
	public void ruotaTessera() throws Exception
	{
		TesseraNormale t1 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		model.posizionaTessera(t1, new Coordinate(2, 2));

		model.getTesseraDaMazzo();

		t1 = (TesseraNormale) model.getTesseraCorrente().clone();

		model.ruotaTessera();

		TesseraNormale t2 = (TesseraNormale) model.getTesseraCorrente().clone();

		assertTrue(t1.equals(t2) == false);
	}

	@Test
	public void notificaFinePartita()
	{
		Punteggi punti = new Punteggi();
		punti.addPunteggi(Color.green, 10);
		model.notificaFinePartita(punti);
	}
	
	
	@Test
	public void notificaCostruzione()
	{
		TesseraNormale t1 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		TesseraNormale t2 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		List<Tessera> tessere = new ArrayList<Tessera>();
		
		t2.setSegnalino(new Segnalino(Color.green), PuntoCardinale.nord);
		
		tessere.add(t1);
		tessere.add(t2);
		
		Punteggi punti = new Punteggi();
		punti.addPunteggi(Color.green, 10);
		model.notificaFinePartita(punti);		
		model.notificaCostruzioneCompletata(tessere, punti);
	}
	
	
	
	@Test
	public void getCoordinateTessera() throws Exception
	{
		TesseraNormale t1 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		model.posizionaTessera(t1, new Coordinate(2, 2));
		model.getCoordinateTessera(model.getTesseraCorrente());
		assertTrue(model.getCoordinateTessera(t1).equals(new Coordinate(2, 2)));

		model.getTesseraDaMazzo();

		Tessera t2 = model.getTesseraCorrente().clone();
		model.posizionaTesseraCorrente(new Coordinate(0, 0));
		Tessera t3 = model.getTessera(new Coordinate(0, 0));
		assertEquals(t3.toString(), t2.toString());

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

	@Test
	public void posizionaTesseraNull() throws Exception
	{
		try
		{
			ModuloModelTest.model.posizionaTessera(null, new Coordinate(0, 0));
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}

	}

	@Test
	public void addSegnalino() throws Exception
	{
		model.getTesseraDaMazzo();
		Tessera tesseraSenza = model.getTesseraCorrente();
		String stringTesseraSenzaSengalino = tesseraSenza.toString();

		model.addSegnalinoTesseraCorrente(PuntoCardinale.est);
		Tessera tesseraCon = model.getTesseraCorrente();

		if (tesseraCon.toString().equals(stringTesseraSenzaSengalino))
		{
			fail(" " + tesseraCon.toString() + " @@@@ " + tesseraSenza.toString());
		}
		else
		{
			assertTrue(" " + tesseraCon.toString() + tesseraSenza.toString(), true);
		}

	}

	@Test
	public void iniziaGioco() throws Exception
	{
		model.addPlayer();
		model.addPlayer();

		model.iniziaGioco();
		TesseraNormale tessera = (TesseraNormale) model.getTessera(new Coordinate(0, 0));

		if (this.isPrimaTessera(tessera) == false)
		{
			fail("all'inizio del gioco non viene pescata la prima tessera");
		}

		model.cominciaTurno();

		// model si esprime tramite eventi.. devo testare qua la gestione degli
		// eventi??

	}

	private boolean isPrimaTessera(TesseraNormale tessera)
	{
		boolean prima = true;

		if (tessera.getElementoA(PuntoCardinale.nord) != Elemento.prato)
		{
			prima = false;
		}

		if (tessera.getElementoA(PuntoCardinale.est) != Elemento.strada)
		{
			prima = false;
		}

		if (tessera.getElementoA(PuntoCardinale.ovest) != Elemento.strada)
		{
			prima = false;
		}

		if (tessera.getElementoA(PuntoCardinale.sud) != Elemento.citta)
		{
			prima = false;
		}

		return prima;
	}

}
