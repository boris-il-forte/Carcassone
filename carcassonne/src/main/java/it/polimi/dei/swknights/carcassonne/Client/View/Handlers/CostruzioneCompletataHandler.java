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
		super(view);
	}

	@Override
	public void visit(CostruzioneCompletataEvent event)
	{
		Map<AdapterTessera, Coordinate> tessereAggiornate = event.getTessereAggiornate();
		this.view.ridaiSegnaliniDiTessere(tessereAggiornate);
		this.sveglia();
	}
	
}
