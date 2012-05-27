package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;
import it.polimi.dei.swknights.carcassonne.server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;
import java.util.Map;

public class TileHandler extends ControllerHandler
{
	public TileHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
		this.model = model;
	}

	/**
	 * removes a marker from the current player and place it on the current card
	 * on the position specified in the event or fire invalidMove event if the
	 * building is already controlled by someone
	 */
	@Override
	public void visit(TileEvent event)
	{
		Giocatore giocatore = this.model.getGiocatoreCorrente();
		Color coloreGiocatore = giocatore.getColore();
		Tessera tesseraDaControllare = this.controller.getTesseraCorrente();
		PuntoCardinale puntoCardinale = event.getPuntoDestinazione();
		try
		{
			if (costruzioneLibera(puntoCardinale))
			{
				Segnalino segnalino = giocatore.getSegnalino();
				tesseraDaControllare.setSegnalino(segnalino, puntoCardinale);
				this.model.fire(new UpdatePositionEvent(tesseraDaControllare, null, coloreGiocatore,
						this.controller));
			}
		}
		catch (SegnaliniFinitiException e)
		{
			this.model.fire(new MossaNonValidaEvent(this.controller));
		}

	}

	private boolean costruzioneLibera(PuntoCardinale punto)
	{
		Map<PuntoCardinale, Costruzione> mappaCostruzioni;
		mappaCostruzioni = this.controller.getContapunti().getMapCostruzioniUltimaTessera();

		if (mappaCostruzioni.get(punto).controllataDa().size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private ModuloModel			model;

	private ModuloController	controller;

}
