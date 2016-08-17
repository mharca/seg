package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanGaragemListarPlaca extends JanModeloGaragem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelNorth;
	public JanGaragemListarPlaca(){
		panelNorth = new JPanel(new FlowLayout());
		panelNorth.add(new JLabel("Placa:"));
		panelNorth.add(new JTextField(13));
		add(panelNorth, BorderLayout.NORTH);
		
	}
}
