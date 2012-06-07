package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.ModoInizio.Fine;
import it.polimi.dei.swknights.carcassonne.ModoInizio.IniziaCliOffline;
import it.polimi.dei.swknights.carcassonne.ModoInizio.IniziaCliOnLine;
import it.polimi.dei.swknights.carcassonne.ModoInizio.IniziaGuiOffline;
import it.polimi.dei.swknights.carcassonne.ModoInizio.IniziaServer;
import it.polimi.dei.swknights.carcassonne.ModoInizio.Inizio;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class JCarcassonneBegin
{
	public JCarcassonneBegin()
	{
		URL url = JCarcassonneBegin.class.getResource("/error.jpg");
		try
		{
			this.icon = new ImageIcon(ImageIO.read(url));
		}
		catch (IOException e)
		{
			this.icon = null;
		}
	}

	public String ShowDialog()
	{
		String risposta = (String) JOptionPane.showInputDialog(null, DOMANDA, NOME_DIALOGO,
				JOptionPane.PLAIN_MESSAGE, this.icon, TIPO_AVVIO, PREDEFINITO);

		if (risposta != null)
		{
			return this.parseRisposta(risposta);
		}
		else
		{
			return "";
		}

	}

	public Inizio getIniziatore(String risposta)
	{
		Inizio iniziatore = new Fine();
		if (risposta.equalsIgnoreCase("CLI"))
		{
			iniziatore = new IniziaCliOffline();
		}
		else if (risposta.equalsIgnoreCase("GUI"))
		{
			iniziatore = new IniziaGuiOffline();
		}
		else if (risposta.equalsIgnoreCase("Server"))
		{
			iniziatore = new IniziaServer();
		}
		else if (risposta.equalsIgnoreCase("CLI on line"))
		{
			iniziatore = new IniziaCliOnLine();

		}
		return iniziatore;
	}

	private String parseRisposta(String risposta)
	{
		if (risposta.equals("CLI off line"))
		{
			return "Cli";
		}
		else if (risposta.equals("GUI off line"))
		{
			return "Gui";
		}
		else if (risposta.equals("Server"))
		{
			return "Server";
		}
		else if (risposta.equals("CLI on line"))
		{
			return "Cli on line";
		}
		else if (risposta.equals("GUI on line"))
		{
			return "Gui on line";
		}
		else
		{
			return "";
		}

	}

	private Icon					icon;

	private static final String		DOMANDA			= "Che cosa desideri avviare?";

	private static final String		NOME_DIALOGO	= "Carcassonne Init";

	private static final String		PREDEFINITO		= "CLI";

	private static final String[]	TIPO_AVVIO		= { "CLI off line", "GUI off line", "Server",
			"CLI on line", "GUI on line"			};

}
