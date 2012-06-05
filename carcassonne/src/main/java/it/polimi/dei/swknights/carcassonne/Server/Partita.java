package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

import java.sql.Timestamp;

/**
 * This class is used to start a new on-line game
 **/
public class Partita
{
	/**
	 * Cretes a new game: instanciate a model, a controller and a (proxy)view
	 */
	public Partita()
	{
		this.model = new ModuloModel();
		this.controller = new ModuloController(model);

		java.util.Date date = new java.util.Date();
		this.idPartita = ("GAME" + new Timestamp(date.getTime()));

		this.model.setIdPartita(this.idPartita);

		this.view = new ProxyView();

		this.view.addListener(this.controller);
		this.model.addListener(this.view);

	}

	public ProxyView getProxyView()
	{
		return this.view;
	}

	public void addPlayer()
	{
		this.model.addPlayer();

	}

	private ProxyView			view;

	private ModuloModel			model;

	private String				idPartita;

	private ModuloController	controller;

}
