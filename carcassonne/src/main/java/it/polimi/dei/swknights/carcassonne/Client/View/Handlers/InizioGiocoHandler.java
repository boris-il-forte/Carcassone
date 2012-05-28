package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.FasiTurno;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;

import java.awt.Color;

public class InizioGiocoHandler extends ViewHandler
{
	public InizioGiocoHandler(ModuloView moduloView)
	{
		this.moduloView = moduloView;
	}
	
	
	@Override
	public void visit (InizioGiocoEvent event) {
		
		InizioGiocoEvent ige = (InizioGiocoEvent) event;

		Color coloreIniziale = ige.getGiocatore();
		AdapterTessera tessIniziale = ige.getTesseraIniziale();
		this.moduloView.faseTurno = FasiTurno.PreparazioneGioco;
		this.moduloView.mettiEMostraPrimaTessera(tessIniziale);
		this.moduloView.aggiornaColoreCorrente(coloreIniziale);
	}

	private final ModuloView moduloView;
}
