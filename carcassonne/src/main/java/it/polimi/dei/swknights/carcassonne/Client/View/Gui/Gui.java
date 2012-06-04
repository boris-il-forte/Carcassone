package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.Icon;

public class Gui extends ModuloView
{
	public Gui()
	{
		super();
		this.setDimensioni();
		this.finestra = new JCarcassoneFrame(this, this.altezza, this.larghezza);
		this.immagini = new IconGetter();
		this.setCoordinataNordOvest(new Coordinate(-larghezza / 2, -altezza / 2));
		this.setCoordinateRelativeSE(new Coordinate(larghezza, altezza));
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
		this.aggiornaCaselle(listaTessere);
	}

	@Override
	public void notificaFinePartita()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void notificaMossaNonValida()
	{
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void visualizzaPunteggi(Punteggi punteggio)
	{
		// TODO Auto-generated method stub

	}

	public void casellaCliccata(int numeroCasella, Coordinate coordinate)
	{
		Debug.print("cliccata casella " + numeroCasella + "in " + coordinate);

	}

	public void passCliccato()
	{
		Debug.print("pass");

	}

	public void rotateCliccato()
	{
		Debug.print("rotate");
		// TODO Auto-generated method stub

	}

	public void zoomModificato(int zoom)
	{
		Debug.print("modificato: " + zoom);
		// TODO Auto-generated method stub
	}

	private void setDimensioni()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = toolkit.getScreenSize();
		this.larghezza = (size.width - 2*this.dimesioneTessere)/this.dimesioneTessere;
		this.altezza = (size.height - 2*this.dimesioneTessere)/this.dimesioneTessere;
		this.altezza = (this.altezza % 2 == 0) ? (this.altezza+1) : (this.altezza);
		this.larghezza = (this.larghezza % 2 == 0) ? (this.larghezza+1) : (this.larghezza);
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

	private int					altezza	;
	// TODO: coordinare questa dim con quella delle vere caselle...
	private int					dimesioneTessere	= 100;

}
