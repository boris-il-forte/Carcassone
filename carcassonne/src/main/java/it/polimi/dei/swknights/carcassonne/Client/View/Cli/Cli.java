package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.FasiTurno;
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

	/**
	 * Executed in response of x,y place the card only if the command is given
	 * in a appropriate game phase
	 * 
	 * @param coordinate
	 * @return true if the card is placed
	 */
	public boolean provaPosizionareTessera(Coordinate coordinate)
	{
		if (this.getFaseTurno() == FasiTurno.Inizio)
		{
			this.fire(new PlaceEvent(this, coordinate));
			return true;
		}
		else
		{
			return false;
		}

	}

	@Override
	public void aggiornaMappa()
	{

		System.out.println("AGGIORNO MAPPA");

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
		System.out.println("CLI POSIZIONO TESSERA");
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
		if (this.getFaseTurno() != FasiTurno.PreparazioneGioco)
		{
			System.out.println("GIOCA FASE " + this.getFaseTurno());
			this.informaUser.setPhase(this.getFaseTurno());
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
	public void mettiEMostraPrimaTessera(AdapterTessera tessIniziale)
	{
		System.out.println("cli METTO LA PRIMA TESSERA IN CENTRO");

		// in modulo View viene messo fase=inizio
		this.informaUser.setPhase(this.getFaseTurno());
		this.setTesseraCorrente(tessIniziale);
		this.posizionaTessera(this.centroScenario);
		this.aggiornaMappa();

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
		System.out.println("CAMBIO TESSERA CORRENTE E LA MOSTRO");
		this.setTesseraCorrente(tessera);
		this.informaUser.setTesseraCorrente(tessera);
		this.informaUser.mostraTesseraCorrente();
	}

	boolean ruotaTessera()
	{
		if (this.getFaseTurno() == FasiTurno.Inizio || this.getFaseTurno() == FasiTurno.Media)
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
		if (this.getFaseTurno() == FasiTurno.Media)
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

	private synchronized void aspettaInizio() throws InterruptedException
	{
		while( this.statoPartita.isPartitaCominciata() == false)
		{
			wait();
		}
	}

	private synchronized void attendiRispostaController() throws InterruptedException
	{
		while (! this.statoPartita.possoParlare() )
		{	
			wait();
		}
		
	}

	synchronized private void getInput()
	{
		System.out.println("GET INPUT");
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

	private static final int	ALTEZZA				= 5;

	private static final int	LARGHEZZA			= 10;

	private final Coordinate	coordinataRelativaSE;

}
