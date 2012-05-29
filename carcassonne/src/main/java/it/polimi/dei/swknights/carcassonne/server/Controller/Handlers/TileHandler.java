package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;

import java.util.Map;

public class TileHandler extends ControllerHandler
{
	public TileHandler(ModuloController controller, ModuloModel model, ModuloView view)
	{
		super(controller, model, view);
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
			if (costruzioneLibera(puntoCardinale))
			{
				this.model.addSegnalinoTesseraCorrente(puntoCardinale);
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
		this.sveglia();

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

}
