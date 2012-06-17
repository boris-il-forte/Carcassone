package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
/**
 * Starter for the GUI offline
 * @author dave
 *
 */
public class IniziaGuiOffline extends Inizio
{

	/**
	 * starts the GUI offline
	 */
	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);

		View view = new Gui();
		view.addListener(controller);
		model.addListener(view);

		int quanti;
		try
		{
			quanti = this.askQuanti();
			for (int i = 0; i < quanti; i++)
			{
				model.addPlayer();
			}
			this.execute(view);
			this.execute(controller);
		}
		catch (InterruptedException e)
		{
		}

	}

	private int askQuanti() throws InterruptedException
	{
		JCarcassonneDialogo inizioGrafico = new JCarcassonneDialogo();
		synchronized (this)
		{
			while (!inizioGrafico.risposto())
			{
				this.wait();
			}
		}
		return inizioGrafico.getRisposta();
	}

	private class JCarcassonneDialogo extends JDialog implements ActionListener
	{

		public JCarcassonneDialogo()
		{
			final int altezza = 150, larghezza = 300;
			this.setPreferredSize(new Dimension(larghezza, altezza));
			this.setTitle("Carcassonne offLine Gui Init");
			this.setMinimumSize(new Dimension(larghezza, altezza));
			this.getContentPane().setLayout(new BorderLayout());
			this.creaLabel();
			this.creaSpinner();
			this.creaOk();
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			this.setVisible(true);
		}

		public boolean risposto()
		{
			return risposta != 0;
		}

		public int getRisposta()
		{
			return risposta;
		}

		private void creaLabel()
		{
			JLabel label = new JLabel("Quanti siete?");
			this.getContentPane().add(label, BorderLayout.NORTH);
		}

		private void creaSpinner()
		{
			final int min = 2, max = 5;
			final int larghezza =50, altezza =30;
			SpinnerModel model = new SpinnerNumberModel(min, min, max, 1);
			this.spinner = new JSpinner(model);
			Box box = Box.createHorizontalBox();
			this.spinner.setMinimumSize(new Dimension(larghezza,altezza));
			this.spinner.setPreferredSize(new Dimension(larghezza,altezza));
			this.spinner.setMaximumSize(new Dimension(larghezza,altezza));
			box.add(Box.createHorizontalGlue());
			box.add(this.spinner);
			box.add(Box.createHorizontalGlue());
			this.getContentPane().add(box, BorderLayout.CENTER);
		}

		private void creaOk()
		{
			this.ok = new JButton("Ok");
			this.getContentPane().add(this.ok, BorderLayout.SOUTH);
			this.ok.addActionListener(this);

		}

		private JButton				ok;

		private JSpinner			spinner;

		private int					risposta			= 0;

		private static final long	serialVersionUID	= 1650136031667534413L;

		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == this.ok)
			{
				this.risposta = (Integer) this.spinner.getValue();
				synchronized (IniziaGuiOffline.this)
				{
					IniziaGuiOffline.this.notifyAll();
				}
				this.dispose();
			}
		}

	}

}
