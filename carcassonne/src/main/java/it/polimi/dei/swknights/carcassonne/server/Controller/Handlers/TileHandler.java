package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

public class TileHandler extends ControllerHandler
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
				this.model.addSegnalinoTesseraCorrente(puntoCardinale);
				this.controller.addSegnalinoTessera(event.getColoreSegnalino(),puntoCardinale);
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
