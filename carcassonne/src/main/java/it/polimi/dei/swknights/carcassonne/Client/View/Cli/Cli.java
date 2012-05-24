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
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;

public class Cli extends View
{
	public Cli()
	{
		super();
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
		this.coordinataRelativaSE = new Coordinate(ALTEZZA, LARGHEZZA);
		this.setCoordinataNordOvest(new Coordinate(-LARGHEZZA / 2, -ALTEZZA / 2));
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
			valido =this.parser.eseguiComando(comando);
		}while(!valido);
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

}
