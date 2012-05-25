package it.polimi.dei.swknights.carcassonne.server;

import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.DatiPartita;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.AbstractConnessioneView;

public class Partita
{

	public DatiPartita	model;

	public Integer		idPartita;

	public ModuloController	controller;

	public AbstractConnessioneView			view;

	public Integer		newAttr;

}
