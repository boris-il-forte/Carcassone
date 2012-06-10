package it.polimi.dei.swknights.carcassonne.Server.Controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.AbstractView;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import org.junit.Before;
import org.junit.Test;

public class ModuloControllerTest
{
	@Before
	public void inizializza()
	{
		this.model = new TestModel();
		this.model.addPlayer();
		this.model.addPlayer();
		this.model.pilotaPartita();
		
		this.controller = new ModuloController(this.model);
		List<Controller> liste = new ArrayList<Controller>();
		liste.add(controller);
		this.view = new TestView(liste);
		Debug.print(controller.toString() + " view = " +this.view.toString());
		
		this.view.addListener(controller);
		this.model.addListener(this.view);
		new Thread(this.controller).start();
	}

	@Test
	public void stradaPiccola() throws Exception
	{
		this.view.testCostruzioneCompletata = true;
		this.view.fire(new PlaceEvent(this, new Coordinate(0, 1)));
		Debug.print(" numero mosse non valide : "+ this.view.mossaNonValida);
		assertTrue("Ha trovato più costruzioni o meno di quelle effettive: "
				+ this.view.costruzioniCompletate, this.view.costruzioniCompletate == 1);
	
	}

	private CostruzioneCoord[] stradella()
	{
		CostruzioneCoord[] stradaPiccola = new CostruzioneCoord[5];

		for (int i = 0; i < 5; i++)
		{
			stradaPiccola[i] = new CostruzioneCoord();
		}

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

	private Tessera getTesseraCittaGrande()
	{
		Tessera t3 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		return t3;
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

	private Link creaLinkCittaGrande() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { false, false, false, false, false, false };
		Link l = new Link(bl);
		return l;
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

	private TestView			view;

	private ModuloController	controller;

	private TestModel			model;

	class TestModel extends ModuloModel
	{

		@Override
		public void getTesseraDaMazzo() // throws PartitaFinitaException
		{
			int index = this.mazzoMoggi.size();
			if (index > 0)
			{
				index--;
				Debug.print("tessere rimanenti" + index);
				this.tesseraCorrente = this.mazzoMoggi.remove(index);
				Debug.print("get Tess mazzo:" + this.tesseraCorrente + "" );
			}
		}

		public void pilotaPartita()
		{
			if(mazzoMoggi == null)
			{
				mazzoMoggi = new ArrayList<Tessera>();
			}
			CostruzioneCoord [] stradella = stradella();
			for(int i=0; i<stradella.length; i++)
			{
			
				this.mazzoMoggi.add(stradella[i].tessera);
			}
			
		}
		
		@Override
		public void iniziaGioco() 
		{
			try
			{
				Tessera primaTessera = this.mazzoMoggi.remove( this.mazzoMoggi.size() -1);
				AdapterTessera tessera = new AdapterTesseraObject(primaTessera);
				this.tesseraCorrente = primaTessera;
				Debug.print("" + this.tesseraCorrente);
				
				this.posizionaTessera(primaTessera, new Coordinate(0, 0));
				Integer quanti=2;
				this.getTesseraDaMazzo();
				this.fire(new InizioGiocoEvent(this, tessera, null, quanti,	" dsfsdf"));

				this.cominciaTurno();

			}

			catch (PartitaFinitaException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (MossaNonValidaException e)
			{
				
				e.printStackTrace();
			}
		}
		
		@Override
		public void cominciaTurno() throws PartitaFinitaException
		{
			Color coloreGiocatore = ColoriGioco.getColor("red");
			this.fire(new UpdateTurnoEvent(this, coloreGiocatore, this.tesseraCorrente));
		}

		public List<Tessera>	mazzoMoggi;
		public Tessera			tesseraCorrente;

	}

	class TestView extends AbstractView
	{
		
		public TestView(List<Controller> listeners)
		{
			super(listeners);
			
		}
		
		public boolean	testMossaNonValida			= false;

		public boolean	testCostruzioneCompletata	= false;

		public int		mossaNonValida				= 0;

		public int		costruzioniCompletate		= 0;

		public boolean	nexturno					= true;

		public void riceviModificheModel(ControllerEvent event)
		{
			if (event instanceof MossaNonValidaEvent)
			{
				Debug.print("\n ricevi modifiche model - mossa non valida" );
				if (testMossaNonValida)
					fail("è stata noificata mossa non valida...");
				else this.mossaNonValida++;
			}
			else if (event instanceof CostruzioneCompletataEvent)
			{
				if (!testCostruzioneCompletata)
					fail("è stata noificata costruzioneCompletata...");
				else this.costruzioniCompletate++;
			}
			else if (event instanceof UpdateTurnoEvent)
			{
				this.nexturno = true;
			}

		}

		public void run()
		{
		}

	}
}

class CostruzioneCoord
{
	public Tessera		tessera;
	public Coordinate	coord;

	public void daiCoppia(Tessera t1, Coordinate c1)
	{
		this.tessera = t1;
		this.coord = c1;

	}

	public ConfineTessera getCofine(PuntoCardinale punto)
	{
		return this.tessera.getConfineA(punto);
	}

	public Tessera getTessera()
	{
		return this.tessera;
	}

	public Coordinate getCoordinate()
	{
		return this.coord;
	}

}
