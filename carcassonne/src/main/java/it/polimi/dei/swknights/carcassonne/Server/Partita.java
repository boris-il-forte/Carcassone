package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

import java.sql.Timestamp;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

		java.util.Date date = new java.util.Date();
		this.idPartita = ("GAME" + new Timestamp(date.getTime()));
		this.idPartita = this.idPartita.replace(':', '@');
		this.model = new ModuloModel(this.idPartita);
		this.controller = new ModuloController(this.model);
		this.view = new ProxyView();

		this.view.addListener(this.controller);
		this.model.addListener(this.view);

		this.executor = Executors.newCachedThreadPool();

	}

	public void cominciaPartita()
	{
		this.executor.execute(this.controller);
	}

	public ProxyView getProxyView()
	{
		return this.view;
	}

	public void addPlayer()
	{
		this.model.addPlayer();

	}

	Executor					executor;

	private ProxyView			view;

	private ModuloModel			model;

	private String				idPartita;

	private ModuloController	controller;

}
