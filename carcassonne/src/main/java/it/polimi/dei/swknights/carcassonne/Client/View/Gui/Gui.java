package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import java.util.List;

import javax.swing.Icon;

public class Gui extends ModuloView
{
	public Gui()
	{
		super(new Coordinate(12, 6));
		this.finestra = new JCarcassoneFrame();
		this.immagini = new IconGetter();
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
		Coordinate base = this.getCoordinataNordOvest();
		List<EntryTessera> listaTessere = scenario.getEntryList(base,base.getCoordinateA(getCoordinateRelativeSE()));
		this.finestra.aggiornaMappa(listaTessere);
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

	private JCarcassoneFrame finestra;
	
	private IconGetter	immagini;

}
