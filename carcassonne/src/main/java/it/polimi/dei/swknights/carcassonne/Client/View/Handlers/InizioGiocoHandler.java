package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.FasiTurno;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;

import java.awt.Color;

public class InizioGiocoHandler extends ViewHandler
{
	public InizioGiocoHandler(ModuloView view)
	{
		super(view);
	}

	@Override
	public void visit(InizioGiocoEvent event)
	{
		InizioGiocoEvent ige = (InizioGiocoEvent) event;

		Color coloreIniziale = ige.getGiocatore();
		AdapterTessera tessIniziale = ige.getTesseraIniziale();
		this.view.setFaseTurno(FasiTurno.PreparazioneGioco);
		this.view.mettiEMostraPrimaTessera(tessIniziale);
		this.view.aggiornaColoreCorrente(coloreIniziale);
		this.view.setPartitaCominciata();
		this.sveglia();
	}

}
