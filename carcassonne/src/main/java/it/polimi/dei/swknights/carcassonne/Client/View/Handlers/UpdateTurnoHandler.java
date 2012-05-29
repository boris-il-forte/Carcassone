package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Client.View.FasiTurno;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;

public class UpdateTurnoHandler extends ViewHandler
{
	public UpdateTurnoHandler(ModuloView view)
	{
		super(view);
	}

	@Override
	public void visit(UpdateTurnoEvent ute)
	{

		this.view.aggiornaMappa();

		UpdateTurnoEvent utev = (UpdateTurnoEvent) ute;
		Color colGiocatoreCorrente = utev.getGiocatoreCorrente();
		AdapterTessera tesseraNuova = utev.getTessera();

		this.view.setFaseTurno(FasiTurno.Inizio);
		this.view.cambiaEMostraTesseraCorrente(tesseraNuova);
		this.view.aggiornaColoreCorrente(colGiocatoreCorrente);
		this.view.notificaPuoiParlare();
		this.sveglia();
		System.out.println("Update turno event finito");
	}

}
