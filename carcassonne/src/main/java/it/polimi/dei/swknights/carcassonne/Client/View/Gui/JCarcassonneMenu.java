package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Class representing a menu
 * 
 * @author dave
 * 
 */
public class JCarcassonneMenu extends JMenuBar implements ActionListener
{
	/**
	 * default constructor, creates the menu
	 */
	public JCarcassonneMenu()
	{
		this.creaMenuBar();
	}

	private void creaMenuBar()
	{

		for (Entry<String, String[]> entry : ITEM_MAP.entrySet())
		{
			this.creaMenu(entry.getKey(), entry.getValue());
		}
	}

	private void creaMenu(String testo, String etichetteItem[])
	{
		JMenu menu = new JMenu(testo);
		for (int i = 0; i < etichetteItem.length; i++)
		{
			JMenuItem menuItem = new JMenuItem(etichetteItem[i]);
			menuItem.addActionListener(this);
			menu.add(menuItem);
		}
		this.add(menu);
	}

	private static Map<String, String[]> inizializzaItemMap()
	{
		String elementiFile[] = { "NuovaPartita" };
		String elementiComandi[] = { "Comandi da tastiera" };
		String elementiCredits[] = { "Credits" };
		Map<String, String[]> mappa = new HashMap<String, String[]>();
		mappa.put("file", elementiFile);
		mappa.put("comandi", elementiComandi);
		mappa.put("credits", elementiCredits);
		return mappa;
	}

	private static final Map<String, String[]>	ITEM_MAP			= inizializzaItemMap();

	private static final long					serialVersionUID	= 4524292800026510575L;

	public void actionPerformed(ActionEvent e)
	{
		String item = e.getActionCommand();
		if (item.equals("Credits"))
		{
			JOptionPane
					.showMessageDialog(
							this,
							"<html>Gioco creato da Edoardo Pasi e Davide Tateo.<br/><br/>"
									+ " Questo gioco è stato testato con il metodo dell'amico di Ghezzi<br/><br/>"
									+ "Si ringrazia il (buon) Fabio Santi Venuto per i pulsanti<br/>"
									+ "Grazie a Santi per i pulSanti!<br/> "
									+ "<br/><br/><br/>"
									+ "Aggiungere Dave a un progetto in anticipo, lo rende in ritardo, e lo ritarda pure ulteriormente. Ma ci mette i Pulsanti Fighi!<br/> "
									+ "<br/><br/><br/>"
									+ "Si ringraziano anche Marco Funaro e soprattutto Mario Sangiorgio per il grande aiuto nello sviluppo del progetto. <br/> "
									+ "Si ringrazia anche chi non programma in java! ;)"
									+ "<br/><br/><br/>"
									+ "swKnights - perchè il male si annida anche nel cattivo sw... e va combattuto! - swKnights"
									+ "<br/>" + "Grazie Maestro!<html/>", "Carcassonne - swKnights -credits",
							JOptionPane.INFORMATION_MESSAGE);
		}
		if (item.equals("Comandi da tastiera"))
		{
			JOptionPane.showMessageDialog(this, "<html>Comandi da tastiera<br/><br/>"
					+ "Arrow UP -> mappa su <br/>" + "Arrow Down -> mappa giù <br/>"
					+ "Arrow Left -> mappa sinistra <br/>" + "Arrow Right -> mappa destra <br/>"
					+ "R -> ruota tessera<br/>" + "P -> Pass", "Carcassonne - swKnights -comandi",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (item.equals("NuovaPartita"))
		{
			JOptionPane.showMessageDialog(this, "<html>Implementato in carcassonne 2.0<br/><br/>",
					"Carcassonne - swKnights -comandi", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
