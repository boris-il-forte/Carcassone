package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.server.Controller.Controller;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class TileHandler extends ControllerHandler
{
	DatiPartita model;
	Controller controller;
	
	public TileHandler(DatiPartita datiPartita)
	{
		this.model = datiPartita;
	}
	
	/**
	 * removes a marker from the current player and place it on the 
	 * current card on the position specified in the event
	 * or fire invalidMove event if the building is already controlled by someone
	 */
	@Override
	public void visit(TileEvent event)
	{
		
		Color coloreGiocatore = controller.getGiocatoreCorrente();
		Giocatore giocatore = this.model.getGiocatore(coloreGiocatore);
		Tessera tesseraDaControllare = controller.getTesseraCorrente();
		
		try
		{
			Segnalino segnalino = giocatore.getSegnalino();
		}
		catch (SegnaliniFinitiException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	
	private boolean costruzioneLibera(Costruzione costruzione)
	{ 	
		//TODO: don't panic and ask explorer
		return false;
	}
	
	
}
