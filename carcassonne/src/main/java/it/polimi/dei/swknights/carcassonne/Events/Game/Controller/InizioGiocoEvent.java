package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

public class InizioGiocoEvent extends ControllerEvent
{

	public InizioGiocoEvent(Object source, AdapterTessera tesseraInziale)
	{
		super(source);
		this.tesseraIniziale = tesseraInziale;
		this.setComando( MessaggiController.startGame);
		
	}
	
	public AdapterTessera getTesseraIniziale()
	{
		return this.tesseraIniziale;
	}
	
	private AdapterTessera tesseraIniziale;
	private String idPartita;
	private int numeroGiocatori;
	private Color coloreGiocatoreIniziale;

	private static final long	serialVersionUID	= 35988189L;

}