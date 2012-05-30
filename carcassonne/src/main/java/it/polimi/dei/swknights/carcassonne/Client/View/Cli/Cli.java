package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.AzioneGioco;
import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.FaseTurno;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
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
	public void run()
	{

		try
		{

			this.out.println("Carcassonne: Aspetto che la partita cominci");
			this.aspettaInizio();
			this.out.println("Carcassonne: Partita cominciata");
			this.out.flush();
			do
			{
				this.attendiInput();
				this.attendiRispostaController();

			} while (!this.statoPartita.isPartitaFinita());
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
	}

	@Override
	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void attendiInput()
	{
		FaseTurno faseCorrente= this.gestoreFasi.getCurrentFase();
		if (faseCorrente != FaseTurno.PreparazioneGioco && faseCorrente != FaseTurno.Attesa)
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
		if (this.gestoreFasi.getCurrentFase() == FaseTurno.Inizio)
		{
			this.fire(new PlaceEvent(this, coordinate));
			this.gestoreFasi.getNextFase(AzioneGioco.place);
			return true;
		}
		else
		{
			return false;
		}

	}

	boolean ruotaTessera()
	{
		FaseTurno currentFase = this.gestoreFasi.getCurrentFase();
		if (currentFase == FaseTurno.Inizio || currentFase == FaseTurno.Media)
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
		if (this.gestoreFasi.getCurrentFase() == FaseTurno.Media)
		{
			this.fire(new PassEvent(this));
			this.gestoreFasi.getNextFase(AzioneGioco.pass);
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
				this.gestoreFasi.getNextFase(AzioneGioco.tile);
				return true;
			}
		}
		return false;
	}

	private synchronized void aspettaInizio() throws InterruptedException
	{
		while (!this.statoPartita.isPartitaCominciata())
		{
			wait();
		}
	}

	private synchronized void attendiRispostaController() throws InterruptedException
	{
		while (!this.statoPartita.possoParlare())
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

	private Scanner				in;

	private PrintWriter			out;

	private ParserComandi		parser;

	private final AvvisiUser	informaUser;

	private static final int	ALTEZZA		= 5;

	private static final int	LARGHEZZA	= 10;

	private final Coordinate	coordinataRelativaSE;

}
