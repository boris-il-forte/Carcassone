package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.ServerRMI;
import it.polimi.dei.swknights.carcassonne.Util.IPAddressValidator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IniziaGuiOnLine extends Inizio
{

	@Override
	public void inizia()
	{
		try
		{
			ProxyController controller;
			View view = new Gui();
			this.print("gui on line");
			Risposta risposte = this.askConnessione();
			String ip = risposte.getIp();
			if (risposte.vuoleRMI())
			{
				ServerRMI server = (ServerRMI) Naming.lookup("//"+ip+"/ServerRMI");
				controller = new ProxyController(server);
			}
			else
			{
				int porta = risposte.getPort();
				Socket socket = CarcassonneSocket.dammiSocket(ip, porta);
				if(socket == null)
				{
					throw new IOException();
				}
				controller = new ProxyController(socket);
			}

			controller.addListener(view);
			view.addListener(controller);

			this.execute(view);
			this.execute(controller);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Server non trovato", "Carcassonne - Errore!",JOptionPane.ERROR_MESSAGE);
		}
		catch (InterruptedException e)
		{
		}
		catch (NotBoundException e)
		{
		}
	}

	private Risposta askConnessione() throws InterruptedException
	{
		JCarcassonneDialogo inizioGrafico = new JCarcassonneDialogo();
		synchronized (this)
		{
			while (!inizioGrafico.risposto())
			{
				this.wait();
			}
		}
		return inizioGrafico;
	}

	private interface Risposta
	{
		String getIp();

		boolean vuoleRMI();

		int getPort();
	}

	private class JCarcassonneDialogo extends JDialog implements ActionListener, Risposta
	{

		public JCarcassonneDialogo()
		{
			this.risposto = false;
			final int altezza = 300, larghezza = 400;
			this.setTitle("Carcassonne Online Gui Init");
			this.setPreferredSize(new Dimension(larghezza, altezza));
			this.setMinimumSize(new Dimension(larghezza, altezza));
			this.getContentPane().setLayout(new BorderLayout());
			this.box = Box.createVerticalBox();
			this.creaLateraleVuoto();
			this.getContentPane().add(this.box, BorderLayout.CENTER);
			this.creaLabel();
			this.creaIpEntry();
			this.creaPortEntry();
			this.creaRMICheckBox();
			this.creaOk();
			this.setVisible(true);
		}

		private void creaLateraleVuoto()
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(25,25));
			this.add(panel,BorderLayout.WEST);
		}

		public boolean risposto()
		{
			synchronized(IniziaGuiOnLine.this)
			{
				return this.risposto;
			}
		}

		public String getIp()
		{
			return this.ipEntry.getText();
		}

		public int getPort()
		{
			return Integer.parseInt(this.portEntry.getText());
		}

		public boolean vuoleRMI()
		{
			return this.rmiCheck.isSelected();
		}

		public void actionPerformed(ActionEvent e)
		{
			final int maxPorta = 65536;
			IPAddressValidator ipValidator = new IPAddressValidator();
			try
			{
				int numeroPorta = Integer.parseInt(this.portEntry.getText());
				if (e.getSource() == this.ok && ipValidator.validate(this.getIp()) && numeroPorta < maxPorta)
				{
					synchronized (IniziaGuiOnLine.this)
					{
						IniziaGuiOnLine.this.notifyAll();
						this.risposto = true;
					}
					this.dispose();
				}
			}
			catch(NumberFormatException exc)
			{
			}
		}

		private void creaLabel()
		{
			JLabel label = new JLabel("Inserisci i dati della connessione");
			this.getContentPane().add(label, BorderLayout.NORTH);
		}

		private final Dimension	size	=  new Dimension(300, 20);


		private void creaIpEntry()
		{
			this.box.add(Box.createVerticalGlue());
			JLabel label = new JLabel("Indirizzo Ip");
			this.box.add(label);
			this.ipEntry = new JTextField("127.0.0.1");
			this.ipEntry.setMaximumSize(this.size);
			this.box.add(this.ipEntry);
		}

		private void creaPortEntry()
		{
			this.box.add(Box.createVerticalGlue());
			JLabel label = new JLabel("Numero di porta");
			this.box.add(label);
			this.portEntry = new JTextField("1984");
			this.portEntry.setMaximumSize(this.size);
			this.box.add(this.portEntry);
		}

		private void creaRMICheckBox()
		{
			this.box.add(Box.createVerticalGlue());
			this.rmiCheck = new JCheckBox("Usa RMI");
			this.box.add(this.rmiCheck);
		}

		private void creaOk()
		{
			this.ok = new JButton("Ok");
			this.getContentPane().add(this.ok, BorderLayout.SOUTH);
			this.ok.addActionListener(this);
		}

		private JTextField			ipEntry;

		private Box					box;

		private JTextField			portEntry;

		private JButton				ok;

		private JCheckBox			rmiCheck;

		private boolean				risposto;

		private static final long	serialVersionUID	= 1650136031667534413L;

	}

}
