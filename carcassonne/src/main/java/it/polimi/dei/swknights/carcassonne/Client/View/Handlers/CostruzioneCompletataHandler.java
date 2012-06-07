package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

public class CostruzioneCompletataHandler extends ModuloViewHandler
{
	public CostruzioneCompletataHandler(ModuloView view)
	{
		super(view);
	}

	/**
	 * When CompletedBuildingEvent is triggered, the visit method notify the
	 * view about which Cards update
	 */
	@Override
	public void visit(CostruzioneCompletataEvent event)
	{
		Map<AdapterTessera, Coordinate> tessereAggiornate = event.getTessereAggiornate();
		this.view.ridaiSegnaliniDiTessere(tessereAggiornate);
		this.view.visualizzaPunteggi(event.getPunteggi());
		this.sveglia();
	}

}
