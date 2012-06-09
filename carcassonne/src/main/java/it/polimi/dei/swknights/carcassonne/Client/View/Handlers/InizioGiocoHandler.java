package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;

import java.awt.Color;

public class InizioGiocoHandler extends ModuloViewHandler
{
	public InizioGiocoHandler(ModuloView view)
	{
		super(view);
	}

	/**
	 * At the begin of the game the BeginGameEvent is triggered, then this set
	 * the initial color, place the first card in center of game board
	 * 
	 */
	@Override
	public void visit(InizioGiocoEvent event)
	{
		Color coloreInterfaccia = event.getGiocatore();
		AdapterTessera tessIniziale = event.getTesseraIniziale();
		this.view.mettiPrimaTessera(tessIniziale);
		this.view.setColore(coloreInterfaccia);
		
		this.view.aggiornaMappa();
		this.sveglia();
	}

}
