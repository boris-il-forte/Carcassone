package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import java.awt.Color;
import java.util.Map;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class TileHandler extends ControllerHandler
{
	DatiPartita	model;
	ModuloController	controller;

	public TileHandler(DatiPartita datiPartita)
	{
		this.model = datiPartita;
	}

	/**
	 * removes a marker from the current player and place it on the current card
	 * on the position specified in the event or fire invalidMove event if the
	 * building is already controlled by someone
	 */
	@Override
	public void visit(TileEvent event)
	{
		// TODO: incompleto

		Color coloreGiocatore = controller.getGiocatoreCorrente();
		Giocatore giocatore = this.model.getGiocatore(coloreGiocatore);
		Tessera tesseraDaControllare = controller.getTesseraCorrente();
		PuntoCardinale puntoCardinale = event.getPuntoDestinazione();
		try
		{
			if(costruzioneLibera(null, puntoCardinale)) 
			{//TODO: il controller deve conoscere le ultime costruzioni
				Segnalino segnalino = giocatore.getSegnalino();
				tesseraDaControllare.setSegnalino(segnalino, puntoCardinale);
			}
		}
		catch (SegnaliniFinitiException e)
		{
			e.printStackTrace();
		}

	}

	private boolean costruzioneLibera(Costruzione costruzione, PuntoCardinale punto)
	{
		// TODO: incompleto   getMapCostruzioniUltimaTessera(); da fare!! ora return null;
		Map <PuntoCardinale, Costruzione> m = this.controller.getContapunti().getMapCostruzioniUltimaTessera();
		
		if (m.get(punto).controllataDa().size() == 0)
		{	
			return true;
		}
		else
		{
			return false;
		}
	}

}
