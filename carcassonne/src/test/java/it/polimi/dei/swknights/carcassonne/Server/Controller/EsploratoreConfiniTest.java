package it.polimi.dei.swknights.carcassonne.Server.Controller;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EsploratoreConfiniTest
{
	public ModuloModel	model;

	@Before
	public void initializeTest() throws Exception
	{
		this.model = new ModuloModel();
		this.model.addPlayer();
	}

	@Test
	public void atLeastStart() throws Exception
	{
		@SuppressWarnings("unused")
		ContatoreCartografo contatore = new ContatoreCartografo(this.model);

	}

	@Test
	public void esplora() throws Exception
	{
		CostruzioneCoord[] stradella = this.stradella();

		for (int i = 0; i < stradella.length; i++)
		{
			System.out.println(stradella[i].tessera.toString() + "   " + stradella[i].coord + "  ");
			System.out.println(this.model.toString());
			this.model.posizionaTessera(stradella[i].tessera, stradella[i].coord);
		}

		Coordinate c2 = new Coordinate(1, 0);
		EsploratoreConfini esploratore = new EsploratoreConfini(c2, this.model);
		Set<Costruzione> costruzioni = esploratore.getCostruzioni();

		Map<Costruzione, List<ConfineTessera>> mappaVicVuoti = esploratore.getViciniVuoti();

		Map<Costruzione, List<ConfineTessera>> mapConfinanti = esploratore.getConfinantiScoperti();

		System.out.println("_________TUTTE LE COSTRUZIONI___________");

		for (Costruzione c : costruzioni)
		{
			System.out.println(c.toString());
		}
		System.out.println("____________VICINI VUOTI_________________");
		for (Entry<Costruzione, List<ConfineTessera>> entry : mappaVicVuoti.entrySet())
		{
			System.out.println(" key = " + entry.getKey().toString());
			System.out.println(" value = lista di " + entry.getValue().size() + " elementi");
			System.out.println(" la lista è " + entry.getValue().toString());
		}

		System.out.println("____________CONFINI EFFETTIVI_________________");
		for (Entry<Costruzione, List<ConfineTessera>> entry : mapConfinanti.entrySet())
		{
			System.out.println(" key = " + entry.getKey().toString());
			System.out.println(" value = lista di " + entry.getValue().size() + " elementi");
			System.out.println(" la lista è " + entry.getValue().toString());
			for (ConfineTessera ct : entry.getValue())
			{
				System.out.println(" confine tessera = " + ct.toString());

			}
		}

		System.out.println("\n \n _______________________________________");

	}

	@Test
	public void stradaPiccola() throws Exception
	{
		ContatoreCartografo contatore = new ContatoreCartografo(this.model);
		System.out.println("\n " + " cont = " + contatore);

		CostruzioneCoord[] stradella;
		stradella = this.stradella();

		System.out.println("\n stradella  =  " + stradella.toString());
		System.out.println("========");

		for (int i = 0; i < stradella.length; i++)
		{
			System.out.println(" tessera numero : " + i + "stradella. coordinata = " + stradella[i].coord);
			stradella[i].toString();

			this.model.posizionaTessera(stradella[i].tessera, stradella[i].coord);
			contatore.riceviCoordinateTessera(stradella[i].coord);
		}

	}

	// @Test
	public void testCostruzioniCorrette() throws Exception
	{

	}

	// @Test
	public void testCostruzioniScorrette() throws Exception
	{

	}

	public CostruzioneCoord[] stradella()
	{
		CostruzioneCoord[] stradaPiccola = this.predisponiVuotiCostruzione(5);

		Tessera incrocio0 = new TesseraNormale(this.creaLatiIncrocioAQuattro(),
				this.creaLinkIncrocioAQuattro());
		Coordinate c0 = new Coordinate(-1, 0);

		Tessera t1 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		Coordinate c1 = new Coordinate(0, 0);

		Tessera t2 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		Coordinate c2 = new Coordinate(1, 0);

		Tessera t3 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		Coordinate c3 = new Coordinate(2, 0);

		Tessera incrocio4 = new TesseraNormale(this.creaLatiIncrocioAQuattro(),
				this.creaLinkIncrocioAQuattro());
		Coordinate c4 = new Coordinate(3, 0);

		stradaPiccola[0].daiCoppia(incrocio0, c0);
		stradaPiccola[1].daiCoppia(t1, c1);
		stradaPiccola[2].daiCoppia(t2, c2);
		stradaPiccola[3].daiCoppia(t3, c3);
		stradaPiccola[4].daiCoppia(incrocio4, c4);

		return stradaPiccola;
	}

	private CostruzioneCoord[] predisponiVuotiCostruzione(int quanti)
	{
		CostruzioneCoord[] c = new CostruzioneCoord[quanti];
		for (int i = 0; i < quanti; i++)
		{
			c[i] = new CostruzioneCoord();
		}
		return c;
	}

	private Lati creaLatiStradaEO()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.prato;
		Elemento sud = Elemento.prato;
		Elemento ovest = Elemento.strada;
		Elemento est = Elemento.strada;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkStradaEO() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { false, false, false, true, false, false };
		Link l = new Link(bl);
		return l;

	}

	private Lati creaLatiIncrocioAQuattro()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.strada;
		Elemento sud = Elemento.strada;
		Elemento ovest = Elemento.strada;
		Elemento est = Elemento.strada;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkIncrocioAQuattro() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { false, false, false, false, false, false };
		Link l = new Link(bl);
		return l;

	}

	static class CostruzioneCoord
	{

		public Tessera		tessera;
		public Coordinate	coord;

		public void daiCoppia(Tessera t1, Coordinate c1)
		{
			this.tessera = t1;
			this.coord = c1;

		}

	}

}
