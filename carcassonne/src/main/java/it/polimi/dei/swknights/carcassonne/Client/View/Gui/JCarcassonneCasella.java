package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;

public class JCarcassonneCasella extends JLayeredPane
{
	public JCarcassonneCasella(Rectangle bounds)
	{
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.bounds = bounds;
		this.cella = new JLabel();
		this.segnalino = new JLabel();
		this.add(this.cella, DEFAULT_LAYER);
		this.add(this.segnalino, PALETTE_LAYER);
		this.cella.setBounds(this.bounds);
		this.cella.setHorizontalTextPosition(JLabel.CENTER);
		this.stato = StatoCasella.nonUsata;
	}

	public JCarcassonneCasella(int dimLato)
	{
		this(new Rectangle(0, 0, dimLato, dimLato));
	}

	public void setTessera(Icon icon, boolean aggiornaStato)
	{
		this.cella.setIcon(icon);
		if(aggiornaStato)
		{
			this.stato = StatoCasella.conTessera;
		}
	}

	public void setVuota(Coordinate coordinateVuota)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.cella.setText(coordinateVuota.toString());
		Rectangle newBounds = (Rectangle) this.bounds.clone();
		newBounds.grow(-1, -1);
		this.cella.setBounds(newBounds);
		this.stato = StatoCasella.vuota;
	}

	public void svuota()
	{
		this.cella.setText("");
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.cella.setIcon(null);
		this.segnalino.setIcon(null);
		this.stato = StatoCasella.nonUsata;
	}

	public void setSegnalino(Icon icon, Coordinate coordinateSegnalino)
	{
		this.segnalino.setIcon(icon);
		this.stato = StatoCasella.conTessera;
		int x = coordinateSegnalino.getX() - this.bounds.width/6;
		int y = coordinateSegnalino.getX() - this.bounds.height/6;
		this.segnalino.setBounds(x,y, this.bounds.width/3, this.bounds.height/3);
	}
	
	public StatoCasella getStato()
	{
		return this.stato;
	}

	private Rectangle			bounds;

	private JLabel				cella;

	private JLabel				segnalino;

	private StatoCasella		stato;

	private static final long	serialVersionUID	= -3373418009078875015L;

}

enum StatoCasella {
	nonUsata, conTessera, vuota, attendeSegnalino;
}
