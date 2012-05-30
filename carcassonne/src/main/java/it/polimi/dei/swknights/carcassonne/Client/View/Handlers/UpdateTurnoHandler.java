package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.awt.Color;

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
	public void visit(UpdateTurnoEvent event)
	{
		Color colGiocatoreCorrente = event.getGiocatoreCorrente();
		AdapterTessera tesseraNuova = event.getTessera();
		tesseraNuova.toString();
		this.view.cominciaTurno();
		this.view.cambiaEMostraTesseraCorrente(tesseraNuova);
		this.view.aggiornaColoreCorrente(colGiocatoreCorrente);
		this.sveglia();
		System.out.println("Update turno event finito");
	}

}
