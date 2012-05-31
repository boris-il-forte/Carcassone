package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Command Line Interface
 * 
 * @author edoardopasi & dave
 * 
 */
public class Cli extends ModuloView
{
	public Cli()
	{
		super();
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
		this.coordinataRelativaSE = new Coordinate(LARGHEZZA, ALTEZZA);
		this.setCoordinataNordOvest(new Coordinate(-LARGHEZZA / 2, -ALTEZZA / 2));
		this.parser = new ParserComandi(this);
		this.informaUser = new AvvisiUser(out);
	}

	@Override
	public void run() // OK
	{

		try
		{
			this.aspettaInizio(); // aspetta che la partita cominci...
			do
			{
				this.attendiInput(); // attende e gestisce l'input
				this.attendiRispostaController(); // attente il ritorno della
													// fase inizio
			} while (this.gestoreFasi.partitaOk());
		}

		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

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
	public void posizionaTessera(Coordinate coordinatePosizione)
	{
		this.getScenario().setTessera(coordinatePosizione, this.getTesseraCorrente());
		this.gestoreFasi.nextFase();
	}

	@Override
	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		Coordinate coordinate;
		do
		{
			coordinate = this.getCoordinataNordOvest().getCoordinateA(puntoCardinale);
		} while (coordinate.getX() < quantita && coordinate.getY() < quantita && nelBoundingBox(coordinate));
		this.setCoordinataNordOvest(coordinate);
		this.aggiornaMappa();
	}

	public void muoviViewA(Coordinate coordinate)
	{
		if(this.nelBoundingBox(coordinate))
		{
			this.setCoordinataNordOvest(coordinate);
		}
		this.aggiornaMappa();
	}

	@Override
	public void attendiInput()
	{
		if (this.gestoreFasi.inputOk())
		{
			this.informaUser.setPhase(this.gestoreFasi.getCurrentFase());
			this.getInput();
		}

	}

	@Override
	public void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereCostruzioneFinita)
	{
		ScenarioDiGioco scenario = this.getScenario();
		for (Entry<AdapterTessera, Coordinate> entryAdapterCoord : tessereCostruzioneFinita.entrySet())
		{
			Coordinate coord = entryAdapterCoord.getValue();
			AdapterTessera tessera = entryAdapterCoord.getKey();
			scenario.setTessera(coord, tessera);

		}

	}

	@Override
	public void mettiPrimaTessera(AdapterTessera tessIniziale)
	{
		this.informaUser.setPhase(this.gestoreFasi.getCurrentFase());
		this.setTesseraCorrente(tessIniziale);
		this.posizionaTessera(centroScenario);
	}

	@Override
	public void notificaFinePartita()
	{
		this.gestoreFasi.finePartita();
		this.informaUser.notificaFinePartita();
	}

	@Override
	public void notificaMossaNonValida()
	{
		this.informaUser.notificaMossaNonValida();

	}

	@Override
	public void aggiornaColoreCorrente(Color colore)
	{
		this.informaUser.setColore(colore);
	}

	@Override
	public void cambiaEMostraTesseraCorrente(AdapterTessera tessera)
	{
		this.setTesseraCorrente(tessera);
		this.informaUser.setTesseraCorrente(tessera);
		this.informaUser.mostraTesseraCorrente();
	}

	/**
	 * Executed in response of x,y place the card only if the command is given
	 * in a appropriate game phase
	 * 
	 * @param coordinate
	 * @return true if the card is placed
	 */

	// TODO: per tutti questi sotto: PULIZIAAAAA. codice duplicato... Male!
	boolean provaPosizionareTessera(Coordinate coordinate)
	{
		if (this.gestoreFasi.posizionaOk())
		{
			this.fire(new PlaceEvent(this, coordinate));
			return true;
		}
		else
		{
			return false;
		}

	}

	boolean provaRuotareTessera()
	{
		if (this.gestoreFasi.ruotaOk())
		{
			this.fire(new RotateEvent(this));
			return true;
		}
		else
		{
			return false;
		}
	}

	boolean provaNonMettereSegnalino()
	{
		if (this.gestoreFasi.fineTurnoOk())
		{
			this.fire(new PassEvent(this));
			this.gestoreFasi.nextFase();
			return true;
		}
		else
		{
			return false;
		}
	}

	boolean provaPosizionareSengalino(String stringComando)
	{
		if (this.gestoreFasi.fineTurnoOk())
		{
			String elementi[] = this.getTesseraCorrente().toCliString().split(" ");
			for (PuntoCardinale punto : PuntoCardinale.values())
			{
				if (elementi[punto.toInt()].equalsIgnoreCase(stringComando))
				{
					this.fire(new TileEvent(this, this.getColoreGiocatore(), punto));
					return true;
				}
			}
		}
		return false;
	}

	private synchronized void aspettaInizio() throws InterruptedException
	{
		while (!this.gestoreFasi.partitaCominciata())
		{
			wait();
		}
	}

	private synchronized void attendiRispostaController() throws InterruptedException
	{
		while (!this.gestoreFasi.inputOk())
		{
			wait();
		}

	}

	private void getInput()
	{
		boolean valido;
		do
		{
			this.informaUser.chiediComando();

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

	private boolean nelBoundingBox(Coordinate coordinate)
	{
		ScenarioDiGioco scenario = this.getScenario();
		Coordinate min = scenario.getMin();
		Coordinate max = scenario.getMax();
		boolean isIn = min.getX() <= coordinate.getX();
		isIn = isIn && min.getY() <= coordinate.getY();
		isIn = isIn && max.getX() >= coordinate.getX();
		return isIn && max.getY() >= coordinate.getY();
	}

	private Scanner				in;

	private PrintWriter			out;

	private ParserComandi		parser;

	private final AvvisiUser	informaUser;

	private static final int	ALTEZZA		= 5;

	private static final int	LARGHEZZA	= 10;

	private final Coordinate	coordinataRelativaSE;

}
