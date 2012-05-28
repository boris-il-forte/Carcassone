package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Exceptions.PartitaFinitaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuloModel extends AbstractModuloModel
{
	/**
	 * Default constructor. Initialize the data structures
	 */

	public ModuloModel()
	{
		super();
		this.datiPartita = new DatiPartita();
	}

	public void ruotaTessera()
	{
		this.tesseraCorrente.ruota();
		Color coloreGiocatoreCorrente = this.getColoreGiocatoreCorrente();
		
		this.fire(new UpdateRotationEvent(this.tesseraCorrente, coloreGiocatoreCorrente, this));
	}

	public void addSegnalinoTesseraCorrente(PuntoCardinale puntoCardinale) throws SegnaliniFinitiException
	{
		Segnalino segnalino = this.getGiocatoreCorrente().getSegnalino();
		this.tesseraCorrente.setSegnalino(segnalino, puntoCardinale);
		// TODO errore! null è sbagliato!
		this.fire(new UpdatePositionEvent(this.tesseraCorrente, null, this.getColoreGiocatoreCorrente(), this));
	}

	public Tessera getTesseraCorrente()
	{
		return this.tesseraCorrente;
	}

	public void creaGiocatori(int numeroGiocatori)
	{
		try
		{
			for (int i = 1; i < numeroGiocatori; i++)
			{
				this.datiPartita.addGiocatore();
			}
		}
		catch (FinitiColoriDisponibiliException e)
		{
			throw new IllegalArgumentException("hai chiesto troppi giocatori");
		}

	}

	public void iniziaGioco(int numeroGiocatori) throws PartitaFinitaException
	{
		Tessera primaTessera = this.datiPartita.pescaPrimaTessera();
		AdapterTessera tessera = new AdapterTesseraObject(primaTessera);
		this.creaGiocatori(numeroGiocatori);
		this.datiPartita.getAreaDiGioco().addTessera(new Coordinate(0, 0), primaTessera);
		this.fire(new InizioGiocoEvent(this, tessera, this.getGiocatoreCorrente().getColore()));
		this.cominciaTurno();
	}

	public void cominciaTurno() throws PartitaFinitaException
	{
		Color coloreGiocatore = this.getColoreGiocatoreCorrente();
		this.getTesseraDaMazzo();
		this.fire(new UpdateTurnoEvent(this, coloreGiocatore, this.tesseraCorrente));
		this.notifyAll();
	}

	public void posizionaTessera(Tessera tessera, Coordinate coordinate)
	{
		AreaDiGioco areaDiGioco = this.datiPartita.getAreaDiGioco();
		Giocatore giocatore = this.datiPartita.getGiocatoreCorrente();
		areaDiGioco.addTessera(coordinate, tessera);
		this.fire(new UpdatePositionEvent(tessera, coordinate, giocatore.getColore(), this));
	}

	public Tessera getTessera(Coordinate coordinate) throws TesseraNonTrovataException
	{
		return this.datiPartita.getAreaDiGioco().getTessera(coordinate);
	}

	public void getTesseraDaMazzo() throws PartitaFinitaException
	{
		this.tesseraCorrente = this.datiPartita.pescaTesseraDalMazzo();
	}

	public void notificaFinePartita()
	{
		// TODO: manca sommare i punteggi di fine partita!
		this.fire(new FinePartitaEvent(this, this.getMappaPunteggi()));
	}

	private List<Giocatore> getListaGiocatori()
	{
		return this.datiPartita.getListaGiocatori();
	}

	private Color getColoreGiocatoreCorrente()
	{
		Giocatore giocatore = this.getGiocatoreCorrente();
		return giocatore.getColore();
	}

	private Giocatore getGiocatoreCorrente()
	{
		return this.datiPartita.getGiocatoreCorrente();
	}

	private Map<Color, Integer> getMappaPunteggi()
	{
		Map<Color, Integer> mapPunteggi = new HashMap<Color, Integer>();
		List<Giocatore> giocatori = this.getListaGiocatori();
		for (Giocatore giocatore : giocatori)
		{
			mapPunteggi.put(giocatore.getColore(), giocatore.getPunti());
		}
		return mapPunteggi;
	}

	private DatiPartita	datiPartita;

	private Tessera		tesseraCorrente;

}
