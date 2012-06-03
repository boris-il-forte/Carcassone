package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
/**
 * Class to handl the turn update event
 * @author edoardopasi
 *
 */
public class UpdateTurnoHandler extends ViewHandler
{	
	/**
	 * This constructor just calls super on view
	*/
	public UpdateTurnoHandler(ModuloView view)
	{
		super(view);
	}
	/**
	 * Notify the view that a new turn has begun
	 * It updates the color and notify the view about the new card
	 */
	@Override
	public void visit(UpdateTurnoEvent event)
	{
		Color colGiocatoreCorrente = event.getGiocatoreCorrente();
		AdapterTessera tesseraNuova = event.getTessera();
		tesseraNuova.toString();
		this.view.setColore(event.getGiocatoreCorrente());
		this.view.visualizzaColoreCorrente();
		this.view.aggiornaTurno(colGiocatoreCorrente,tesseraNuova);
		this.sveglia();
	}

}
