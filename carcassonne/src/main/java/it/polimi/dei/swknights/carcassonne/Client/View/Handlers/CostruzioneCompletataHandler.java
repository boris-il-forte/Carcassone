package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;

public class CostruzioneCompletataHandler extends ViewHandler
{
	public CostruzioneCompletataHandler(ModuloView view)
	{
		this.view = view;
	}

	@Override
	public void visit(CostruzioneCompletataEvent event)
	{
		CostruzioneCompletataEvent cce = (CostruzioneCompletataEvent) event;
		Map<AdapterTessera, Coordinate> tessereAggiornate = cce.getTessereAggiornate();
		this.view.ridaiSegnaliniDiTessere(tessereAggiornate);
	}

	private ModuloView	view;
	
}
