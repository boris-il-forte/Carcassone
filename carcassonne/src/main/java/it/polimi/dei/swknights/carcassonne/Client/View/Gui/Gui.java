package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.PosizionaMentoInvalidoException;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Gui extends ModuloView
{
	public Gui()
	{
		super();
		this.setDimensioni();
		this.immagini = new IconGetter();
		this.finestra = new JCarcassoneFrame(this, this.altezza, this.larghezza);
		this.setCoordinataNordOvest(new Coordinate(-larghezza / 2, -altezza / 2));
		this.setCoordinateRelativeSE(new Coordinate(larghezza - 1, altezza - 1));
		this.coordinateInserimentoSegnalini = new HashMap<Coordinate, Coordinate>();
	}

	@Override
	public void run()
	{
		this.finestra.showGui();
	}

	@Override
	public void aggiornaMappa()
	{
		ScenarioDiGioco scenario = this.getScenario();
		Coordinate nordOvest = this.getCoordinateNordOvest();
		Coordinate sudEst = nordOvest.getCoordinateA(this.getCoordinateRelativeSE());
		List<EntryTessera> listaTessere = scenario.getEntryList(nordOvest, sudEst);
		this.aggiornaCaselle(listaTessere);
	}

	@Override
	public void notificaFinePartita()
	{
		// TODO: visualizza vincitore e cambia immagine
		JOptionPane.showMessageDialog(this.finestra, "Fine partita!!", "Carcassonne - swKnights",
				JOptionPane.INFORMATION_MESSAGE, this.immagini.getTileIcon("", this.dimesioneTessere));
	}

	@Override
	public void notificaMossaNonValida()
	{
		// TODO: cambia immagine
		JOptionPane.showMessageDialog(this.finestra, "Mossa non consentita!", "Carcassonne - swKnights",
				JOptionPane.INFORMATION_MESSAGE, this.immagini.getTileIcon("", this.dimesioneTessere));
	}

	@Override
	public void visualizzaTesseraCorrente(AdapterTessera tessera)
	{
		if (!this.getGestoreFasi().ruotaOk())
		{
			this.getGestoreFasi().cominciaTurno();
		}
		this.setTesseraCorrente(tessera);
		Icon iconaTessera = this.immagini.getOriginalTileIcon(tessera.toProtocolString());
		this.finestra.aggiornaTesseraCorrente(iconaTessera);
	}

	@Override
	public void visualizzaColoreCorrente()
	{
		// TODO numero segnalini corretto
		this.finestra.aggiornaGiocatoreCorrente(this.getColoreGiocatore(), 0);

	}

	@Override
	public void visualizzaPunteggi(Punteggi punteggio)
	{
		this.finestra.aggiornaPunteggi(punteggio);
	}

	public Map<Color, Icon> getMappaSegnalini()
	{
		Map<Color, Icon> mappaSegnalini = new HashMap<Color, Icon>();
		for (Color colore : ColoriGioco.getListaColori())
		{
			Icon icon = this.immagini.getOriginalSegnalinoIcon(ColoriGioco.getSigla(colore));
			mappaSegnalini.put(colore, icon);
		}
		return mappaSegnalini;
	}

	public void casellaCliccata(int numeroCasella, Coordinate coordinateMouse)
	{
		Debug.print("cliccana casella n " + numeroCasella + "coordinate" + coordinateMouse);
		Coordinate coordReale = this.convertiCoordinate(numeroCasella);
		if (this.getGestoreFasi().posizionaOk())
		{
			Debug.print("fire place event");
			this.fire(new PlaceEvent(this, coordReale));
		}
		else if (this.getGestoreFasi().fineTurnoOk())
		{
			PuntoCardinale puntoInserimento;
			try
			{
				Debug.print("Fire segnalino");
				puntoInserimento = this.getPuntocardinaleIserimento(coordinateMouse);
				this.coordinateInserimentoSegnalini.put(coordReale, coordinateMouse);
				this.fire(new TileEvent(this, this.getColoreGiocatore(), puntoInserimento));
			}
			catch (PosizionaMentoInvalidoException e)
			{
				Debug.print("Posizionamento Invalido");
			}
		}

	}

	public void passCliccato()
	{
		if (this.getGestoreFasi().fineTurnoOk())
		{
			this.fire(new PassEvent(this));
			this.getGestoreFasi().nextFase();
		}
	}

	public void rotateCliccato()
	{
		if (this.getGestoreFasi().ruotaOk())
		{
			this.fire(new RotateEvent(this));
		}
	}

	public void zoomModificato(int zoom)
	{
		Debug.print("modificato: " + zoom);
		// TODO Auto-generated method stub
	}

	public void overlayImmagine(int numeroCasella)
	{
		if (this.getGestoreFasi().posizionaOk())
		{
			Icon icon = this.immagini.getAlphaTileIcon(this.getTesseraCorrente().toProtocolString());
			this.finestra.overlayTessera(numeroCasella, icon);
		}
	}

	public void togliOverlay(JCarcassonneCasella casella, int numeroCasella)
	{
		Coordinate coordinateVuota = this.convertiCoordinate(numeroCasella);
		casella.svuota();
		casella.setVuota(coordinateVuota);
	}

	private PuntoCardinale getPuntocardinaleIserimento(Coordinate coordinateMouse)
			throws PosizionaMentoInvalidoException
	{
		int x = coordinateMouse.getX() - this.dimesioneTessere / 2;
		int y = coordinateMouse.getY() - this.dimesioneTessere / 2;
		Debug.print("x = " + x + "y = " + y);
		Debug.print("condizione1 = " + (x * x - y * y));
		Debug.print("condizione2 = " + (y * y - x * x));
		if (x * x - y * y >= 1) { return (x > 0) ? PuntoCardinale.est : PuntoCardinale.ovest; }
		if (y * y - x * x >= 1) { return (y > 0) ? PuntoCardinale.nord : PuntoCardinale.sud; }
		throw new PosizionaMentoInvalidoException(coordinateMouse);
	}

	private void setDimensioni()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = toolkit.getScreenSize();
		this.larghezza = (size.width - 4 * this.dimesioneTessere) / this.dimesioneTessere;
		this.altezza = (size.height - 3 * this.dimesioneTessere) / this.dimesioneTessere;
		this.altezza = (this.altezza % 2 == 0) ? (this.altezza + 1) : (this.altezza);
		this.larghezza = (this.larghezza % 2 == 0) ? (this.larghezza + 1) : (this.larghezza);
	}

	private Coordinate convertiCoordinate(int numeroCasella)
	{
		int x = numeroCasella % this.larghezza;
		int y = numeroCasella / this.larghezza;
		Coordinate coordRelativa = new Coordinate(x, y);
		return this.getCoordinateNordOvest().getCoordinateA(coordRelativa);

	}

	private void aggiornaCaselle(List<EntryTessera> listaTessere)
	{
		AggiornaMappaGui aggiornaMappa = new AggiornaMappaGui(listaTessere, this.getCoordinateNordOvest(),
				this.getCoordinateRelativeSE());
		this.finestra.svuotaMappa();
		this.aggiornaPieni(aggiornaMappa);
		this.aggiornaVuoti(aggiornaMappa);
	}

	private void aggiornaPieni(AggiornaMappaGui aggiornaMappa)
	{
		while (aggiornaMappa.hasNextTessera())
		{
			Entry<Integer, String> entry = aggiornaMappa.nextTessera();
			String stringTessera = entry.getValue();
			Icon tessera = this.immagini.getTileIcon(stringTessera, this.dimesioneTessere);
			int numeroTessera = entry.getKey();
			this.finestra.aggiornaTessera(numeroTessera, tessera);
			this.addSegnalini(numeroTessera, stringTessera);
		}

	}

	private void addSegnalini(int numeroTessera, String stringTessera)
	{
		Icon segnalini = this.immagini.getSegnalinoIcon(stringTessera, this.dimensioneSegnalino);
		if (segnalini != null)
		{
			Coordinate coordinateTessera = this.convertiCoordinate(numeroTessera);
			Coordinate coordinateSegnalino = this.coordinateInserimentoSegnalini.get(coordinateTessera);
			this.finestra.aggiornaSegnalinoTessera(numeroTessera, segnalini, coordinateSegnalino);
		}
	}

	private void aggiornaVuoti(AggiornaMappaGui aggiornaMappa)
	{
		while (aggiornaMappa.hasNextVuoto())
		{
			Entry<Coordinate, Integer> entry = aggiornaMappa.nextVuoto();
			Coordinate coordinateVuota = entry.getKey();
			Coordinate min = this.getCoordinateNordOvest();
			Coordinate max = this.getCoordinateNordOvest().getCoordinateA(this.getCoordinateRelativeSE());
			if (this.isIn(coordinateVuota, max, min))
			{
				int numeroVuota = entry.getValue();
				this.finestra.aggiornaMappa(numeroVuota, coordinateVuota);
			}

		}
	}

	private Map<Coordinate, Coordinate>	coordinateInserimentoSegnalini;

	private JCarcassoneFrame			finestra;

	private IconGetter					immagini;

	private int							larghezza;

	private int							altezza;
	// TODO: coordinare questa dim con quella delle vere caselle...
	private int							dimesioneTessere	= 100;

	private int							dimensioneSegnalino	= 30;

}
