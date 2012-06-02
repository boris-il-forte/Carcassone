package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Icon;

public class Gui extends ModuloView implements ActionListener
{
	public Gui()
	{
		this.finestra = new JCarcassoneFrame();
		this.immagini = new IconGetter();
	}

	@Override
	public void run()
	{
		this.finestra.showGui();
	}

	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaMappa()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void posizionaTessera(Coordinate coordinatePosizione)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ridaiSegnaliniDiTessere(Map<AdapterTessera, Coordinate> tessereAggiornate)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mettiPrimaTessera(AdapterTessera tessIniziale)
	{
		// TODO Auto-generated method stub

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
	public void cambiaEMostraTesseraCorrente(AdapterTessera tessera)
	{
		Icon iconaTessera = this.immagini.getIcon(tessera.toProtocolString());
		this.finestra.aggiornaTesseraCorrente(iconaTessera);
	}

	@Override
	public void attendiInput()
	{
		// TODO Auto-generated method stub

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

	private JCarcassoneFrame finestra;
	private IconGetter	immagini;

}
