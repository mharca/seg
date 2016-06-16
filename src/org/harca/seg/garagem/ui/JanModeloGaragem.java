package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JanModeloGaragem extends JPanel{
	public JanModeloGaragem(){
		this.setLayout(new BorderLayout());
		JScrollPane jsp = new JScrollPane();//new JTableGaragem());
		JTableGaragem tg = new JTableGaragem();
		//jsp.setViewportView(tg);
		add(tg, BorderLayout.CENTER);
	}
}
