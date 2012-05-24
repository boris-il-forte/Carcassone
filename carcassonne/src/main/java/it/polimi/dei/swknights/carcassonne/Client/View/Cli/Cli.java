package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Client.View.View;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.TesseraNormale;

public class Cli extends View
{
	public Cli()
	{
		super();
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
		this.coordinataRelativaSE = new Coordinate(ALTEZZA, LARGHEZZA);
		this.setCoordinataNordOvest(new Coordinate(-LARGHEZZA / 2, -ALTEZZA / 2));
		this.currentPhase = fasiTurno.Inzio;
	}

	@Override
	public void aggiornaMappa()
	{
		ScenarioDiGioco scenario = this.getScenario();
		Stampante stampante = this.inizializzaStampante();
		Coordinate base = this.getCoordinataNordOvest();
		List<EntryTessera> listaTessere = scenario.getEntryList(base,
				base.getCoordinateA(this.coordinataRelativaSE));
		stampante.addListTessera(listaTessere);
		String mappa = stampante.toString();
		this.out.print(mappa);
		this.out.flush();
	}

	@Override
	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void posizionaTessera()
	{
		// TODO
	}

	public void CANCELLAMI_DOPO_TEST()
	{
		// TODO: kill that
		Lati latiCreandi;
		Elemento nord = Elemento.citta;
		Elemento sud = Elemento.citta;
		Elemento ovest = Elemento.citta;
		Elemento est = Elemento.citta;
		latiCreandi = new Lati(nord, sud, ovest, est);

		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { true, true, true, true, true, true };
		Link l = new Link(bl);

		TesseraNormale tn = new TesseraNormale(latiCreandi, l);
		AdapterTessera at = new AdapterTesseraObject(tn);

		this.getScenario().SetTessera(new Coordinate(0, 0), at);
		this.aggiornaMappa();
	}

	public boolean provaPosizionareTessera(Coordinate coordinate)
	{
		if (currentPhase == fasiTurno.Inzio)
		{
			this.fire(new PlaceEvent(this, coordinate));
			return true;
		}
		else
		{
			return false;
		}

	}

	public boolean ruotaTessera()
	{
		if (currentPhase == fasiTurno.Inzio || currentPhase == fasiTurno.Media)
		{
			this.fire(new RotateEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean nonMettereSegnalino()
	{
		if (currentPhase == fasiTurno.Media)
		{
			this.fire(new PassEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean posizionaSengalino(String stringComando)
	{
		String elementi[] = this.getTesseraCorrente().toCliString().split(" ");
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			if (elementi[punto.toInt()].equals(stringComando))
			{
				this.fire(new TileEvent(this, this.getColoreGiocatore(), punto));
				return true;
			}
		}
		return false;
	}

	public void getInput()
	{
		boolean valido;
		do
		{
			this.out.println("Inserisci comando");
			String comando = this.in.nextLine();
			valido = this.parser.eseguiComando(comando);
		} while (!valido);
	}

	private Stampante inizializzaStampante()
	{
		Coordinate min = this.getCoordinataNordOvest();
		Coordinate max = this.getCoordinataNordOvest().getCoordinateA(coordinataRelativaSE);
		DatiMappa datiMappa = new DatiMappa(min, max);
		return new Stampante(datiMappa);
	}

	private Scanner				in;

	private PrintWriter			out;

	private ParserComandi		parser;

	private static final int	ALTEZZA		= 10;

	private static final int	LARGHEZZA	= 5;

	private final Coordinate	coordinataRelativaSE;

	private fasiTurno			currentPhase;

	public enum fasiTurno {
		Inzio("Place card or rotate"), Media("Rotate"), Attesa("Tile or pass");
		fasiTurno(String s)
		{

		}

	}

}
