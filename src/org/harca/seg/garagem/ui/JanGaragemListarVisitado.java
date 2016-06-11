package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanGaragemListarVisitado extends JanModeloGaragem {
	JPanel panelNorth;
	public JanGaragemListarVisitado(){
	panelNorth = new JPanel(new FlowLayout());
	panelNorth.add(new JLabel("Documento do visitado:"));
	panelNorth.add(new JTextField(13));
	add(panelNorth, BorderLayout.NORTH);
	
	}
}
