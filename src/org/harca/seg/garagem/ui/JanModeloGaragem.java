package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class JanModeloGaragem extends JPanel{
	public JanModeloGaragem(){
		this.setLayout(new BorderLayout());
		add(new JTableGaragem(), BorderLayout.CENTER);
	}
}
