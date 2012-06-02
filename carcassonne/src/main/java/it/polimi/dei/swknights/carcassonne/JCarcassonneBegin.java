package it.polimi.dei.swknights.carcassonne;

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
		
		if(risposta != null)
		{
			return this.parseRisposta(risposta);
		}
		else 
		{
			return "";
		}
		
	}

	private String parseRisposta(String risposta)
	{
		if( risposta.equals("solo la CLI"))
		{
			return "Cli";
		}
		else if(risposta.equals("solo la GUI"))
		{
			return "Gui";
		}
		else if(risposta.equals("Solo Server"))
		{
			return "Server";
		}
		else if(risposta.equals("Server e CLI"))
		{
			return "Server+Cli";
		}
		else if(risposta.equals("Server e GUI"))
		{
			return "Server+Gui";
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

	private static final String[]	TIPO_AVVIO		= { "solo la CLI", "solo la GUI", "Solo Server", "Server e CLI",  "Server e GUI" };
}
