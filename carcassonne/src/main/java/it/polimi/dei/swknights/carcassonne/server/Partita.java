package it.polimi.dei.swknights.carcassonne.server;

import it.polimi.dei.swknights.carcassonne.server.Controller.Controller;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.server.ViewProxy.ViewConnessione;

public class Partita
{

	public DatiPartita	model;

	public Integer		idPartita;

	public Controller	controller;

	public ViewConnessione			view;

	public Integer		newAttr;

}
