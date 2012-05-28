package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Client.View.FasiTurno;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;

public class UpdateTurnoHandler extends ViewHandler
{
	public UpdateTurnoHandler(ModuloView moduloView)
	{
		this.moduloView = moduloView;
	}
	
	
	@Override
	public void visit (UpdateTurnoEvent ute) {
		
		this.moduloView.aggiornaMappa();

		UpdateTurnoEvent utev = (UpdateTurnoEvent) ute;
		Color colGiocatoreCorrente = utev.getGiocatoreCorrente();
		AdapterTessera tesseraNuova = utev.getTessera();

		this.moduloView.faseTurno = FasiTurno.Inizio;
		this.moduloView.cambiaEMostraTesseraCorrente(tesseraNuova);
		this.moduloView.aggiornaColoreCorrente(colGiocatoreCorrente);
	}

	private final ModuloView moduloView;

}
