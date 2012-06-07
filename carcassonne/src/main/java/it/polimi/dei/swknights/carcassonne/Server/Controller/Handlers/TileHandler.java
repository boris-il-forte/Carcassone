package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

public class TileHandler extends ModuloControllerHandler
{
	public TileHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	/**
	 * removes a marker from the current player and place it on the current card
	 * on the position specified in the event or fire invalidMove event if the
	 * building is already controlled by someone
	 */
	@Override
	public void visit(TileEvent event)
	{
		PuntoCardinale puntoCardinale = event.getPuntoDestinazione();
		try
		{
			if (this.controller.costruzioneLibera(puntoCardinale))
			{
				Segnalino segnalino = this.model.addSegnalinoTesseraCorrente(puntoCardinale);
				this.controller.addSegnalinoTessera(segnalino,puntoCardinale);
				this.controller.comunicaPosizionamentoTessera();
				this.sveglia();
			}
			else
			{
				this.model.fire(new MossaNonValidaEvent(this.model));
			}
		}
		catch (SegnaliniFinitiException e)
		{
			this.model.fire(new MossaNonValidaEvent(this.model));
		}

	}

	
}
