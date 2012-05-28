package it.polimi.dei.swknights.carcassonne.Client.View.Handlers;

import java.util.Map;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;

public class CostruzioneCompletataHandler extends ViewHandler
{

	@Override
	public void visit(CostruzioneCompletataEvent event)
	{
		CostruzioneCompletataEvent cce = (CostruzioneCompletataEvent) event;
		Map<AdapterTessera, Coordinate> tessereAggiornate = cce.getTessereAggiornate();
		this.ridaiSegnaliniDiTessere(tessereAggiornate);
	}
	
}
