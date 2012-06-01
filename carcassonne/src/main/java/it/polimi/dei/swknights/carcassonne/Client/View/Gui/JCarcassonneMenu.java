package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JCarcassonneMenu extends JMenuBar
{
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
			menu.add(menuItem);
		}
		this.add(menu);
	}

	private static Map<String, String[]> inizializzaItemMap()
	{
		String elementiFile[] = { "NuovaPartita" };
		String elementiOpzioni[] = { "Modalità" };
		String elementiCredits[] = { "Credits" };
		Map<String, String[]> mappa = new HashMap<String, String[]>();
		mappa.put("file", elementiFile);
		mappa.put("opzioni", elementiOpzioni);
		mappa.put("credits", elementiCredits);
		return mappa;
	}

	private static final Map<String, String[]>	ITEM_MAP			= inizializzaItemMap();

	private static final long					serialVersionUID	= 4524292800026510575L;

}
