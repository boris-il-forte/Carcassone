package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Gui extends ModuloView
{
	public Gui()
	{
		super();
		this.setDimensioni();
		this.finestra = new JCarcassoneFrame(this, this.altezza, this.larghezza);
		this.immagini = new IconGetter();
		this.setCoordinataNordOvest(new Coordinate(-larghezza / 2, -altezza / 2));
		this.setCoordinateRelativeSE(new Coordinate(larghezza -1, altezza -1 ));
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
		Coordinate base = this.getCoordinateNordOvest();
		List<EntryTessera> listaTessere = scenario.getEntryList(base,
				base.getCoordinateA(getCoordinateRelativeSE()));
		Debug.print("listatessere "+listaTessere.size());
		this.aggiornaCaselle(listaTessere);
	}

	@Override
	public void notificaFinePartita()
	{
		//TODO: visualizza vincitore e cambia immagine
		JOptionPane.showMessageDialog(this.finestra, "Fine partita!!", "Carcassonne - swKnights",
				JOptionPane.INFORMATION_MESSAGE, this.immagini.getIcon("", this.dimesioneTessere));
	}

	@Override
	public void notificaMossaNonValida()
	{
		//TODO: cambia immagine
		JOptionPane.showMessageDialog(this.finestra, "Mossa non consentita!", "Carcassonne - swKnights",
				JOptionPane.INFORMATION_MESSAGE, this.immagini.getIcon("", this.dimesioneTessere)); 
	}

	@Override
	public void visualizzaTesseraCorrente(AdapterTessera tessera)
	{
		Icon iconaTessera = this.immagini.getOriginalIcon(tessera.toProtocolString());
		this.finestra.aggiornaTesseraCorrente(iconaTessera);
	}

	@Override
	public void visualizzaColoreCorrente()
	{
		//TODO numero segnalini corretto
		this.finestra.aggiornaGiocatoreCorrente(this.getColoreGiocatore(), 0);

	}

	@Override
	public void visualizzaPunteggi(Punteggi punteggio)
	{
		this.finestra.aggiornaPunteggi(punteggio);
	}
	public void casellaCliccata(int numeroCasella, Coordinate coordinateMouse)
	{
		Debug.print("cliccata casella " + numeroCasella + "in " + coordinateMouse);

	    if(this.getGestoreFasi().posizionaOk())
	    {
	    	Coordinate coordReale = this.convertiCoordinate(numeroCasella);
	    	Debug.print(" coord = " + coordReale);
	    	this.fire(new PlaceEvent(this, coordReale));
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

	private Coordinate convertiCoordinate(int numeroCasella)
	{
		int x = numeroCasella % this.larghezza;
		int y = numeroCasella / this.larghezza;	    	
		Coordinate coordRelativa = new Coordinate(x, y);
		return  this.getCoordinateNordOvest().getCoordinateA(coordRelativa);
		
	}

	private void setDimensioni()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = toolkit.getScreenSize();
		this.larghezza = (size.width - 4 * this.dimesioneTessere) / this.dimesioneTessere;
		this.altezza = (size.height - 4 * this.dimesioneTessere) / this.dimesioneTessere;
		this.altezza = (this.altezza % 2 == 0) ? (this.altezza + 1) : (this.altezza);
		this.larghezza = (this.larghezza % 2 == 0) ? (this.larghezza + 1) : (this.larghezza);
	}

	private void aggiornaCaselle(List<EntryTessera> listaTessere)
	{
		AggiornaMappaGui aggiornaMappa = new AggiornaMappaGui(listaTessere, this.getCoordinateNordOvest(),
				this.getCoordinateRelativeSE());
		this.svuotaTessere();
		while (aggiornaMappa.hasNextTessera())
		{
			Entry<String, Integer> entry = aggiornaMappa.nextTessera();
			Icon tessera = this.immagini.getIcon(entry.getKey(), dimesioneTessere);
			int numeroTessera = entry.getValue();
			this.finestra.aggiornaMappa(numeroTessera, tessera);
		}
	}

	private void svuotaTessere()
	{
		for (int i = 0; i < altezza * larghezza; i++)
		{
			this.finestra.aggiornaMappa(i, null);
		}

	}

	private JCarcassoneFrame	finestra;

	private IconGetter			immagini;

	private int					larghezza;

	private int					altezza;
	// TODO: coordinare questa dim con quella delle vere caselle...
	private int					dimesioneTessere	= 100;

}
