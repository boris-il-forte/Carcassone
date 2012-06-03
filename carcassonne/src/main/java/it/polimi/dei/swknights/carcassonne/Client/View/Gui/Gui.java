package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.ImageLoader.IconGetter;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.Icon;

public class Gui extends ModuloView
{
	public Gui()
	{
		super(new Coordinate(LARGHEZZA, ALTEZZA ));
		this.finestra = new JCarcassoneFrame();
		this.immagini = new IconGetter();
		this.setCoordinataNordOvest(new Coordinate(-LARGHEZZA / 2, -ALTEZZA / 2));
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

	private void aggiornaCaselle(List<EntryTessera> listaTessere)
	{
		AggiornaMappaGui aggiornaMappa = new AggiornaMappaGui(listaTessere, this.getCoordinataNordOvest(),
				this.getCoordinateRelativeSE());
		while(aggiornaMappa.hasNextTessera())
		{
			Entry<String, Integer> entry = aggiornaMappa.nextTessera();
			Icon tessera = this.immagini.getIcon(entry.getKey());
			int numeroTessera = entry.getValue();
			this.finestra.aggiornaMappa(numeroTessera, tessera);
		}	
	}

	private JCarcassoneFrame	finestra;

	private IconGetter			immagini;

	private static final int	LARGHEZZA	= 12; //TODO calcola da risoluzione...

	private static final int	ALTEZZA	= 6;

}
