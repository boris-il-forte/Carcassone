package it.polimi.dei.swknights.carcassonne.Client;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.AbstractConnessioneController;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.AbstractConnessioneView;

public class CarcassonneClient
{

	public void run()
	{
		
	}
	
	public static final int					MAX_TRY			= 100;

	public static final String				indirizzoServer	= "127.0.0.1";

	public static final int					PORTA_GF		= 1984;

	public AbstractConnessioneView			view;

	public AbstractConnessioneController	controller;

	public Integer							idPartita;

}
