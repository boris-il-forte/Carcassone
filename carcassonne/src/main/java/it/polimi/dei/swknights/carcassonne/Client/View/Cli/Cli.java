package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Cli extends ModuloView 
{
	public Cli()
	{
		super();
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
		this.coordinataRelativaSE = new Coordinate(LARGHEZZA, ALTEZZA);
		this.setCoordinataNordOvest(new Coordinate(-LARGHEZZA / 2, -ALTEZZA / 2));
		this.currentPhase = FasiTurno.Inizio;
		this.parser = new ParserComandi(this);
		
	}

	public void giocaFase()
	{
		this.aggiornaMappa();
		this.getInput();
		//mettiti in attesa
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
	public void posizionaTessera(Coordinate coordinatePosizione)
	{
		this.getScenario().SetTessera(coordinatePosizione, this.getTesseraCorrente());
		//TODO: va aggiornato vicinato??
	}

	public boolean provaPosizionareTessera(Coordinate coordinate)
	{
		if (currentPhase == FasiTurno.Inizio)
		{
			this.fire(new PlaceEvent(this, coordinate));
			return true;
		}
		else
		{
			return false;
		}

	}

	boolean ruotaTessera()
	{
		if (currentPhase == FasiTurno.Inizio || currentPhase == FasiTurno.Media)
		{
			this.fire(new RotateEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	boolean nonMettereSegnalino()
	{
		if (currentPhase == FasiTurno.Media)
		{
			this.fire(new PassEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	boolean posizionaSengalino(String stringComando)
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

	private void getInput()
	{
		boolean valido;
		do
		{
			this.out.println(this.currentPhase.toString());
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

	private static final int	ALTEZZA		= 5;

	private static final int	LARGHEZZA	= 10;

	private final Coordinate	coordinataRelativaSE;

	private FasiTurno			currentPhase;

	private enum FasiTurno {
		Inizio("Place card or rotate"), Media("Tile or pass"), Attesa("wait server response...");
		
		private FasiTurno(String messaggio)
		{
			this.messaggioUtente = messaggio;
		}

		public String toString()
		{
			return this.messaggioUtente;
		}
		
		//TODO: controllare
		public FasiTurno nextPhase()
		{
			switch(this)
			{
				case Inizio:
					return Media;
				case Media:
					return Attesa;
				case Attesa:
					return Inizio;
					
				default:
					return Attesa;
			}
		}
		

		private String messaggioUtente;

	}

}
