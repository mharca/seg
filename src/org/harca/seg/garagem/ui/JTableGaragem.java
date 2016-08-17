package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JTableGaragem extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	public JTableGaragem(){
		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		//add(scrollPane);
		
		table = new JTable(new TabelaModelo());
		scrollPane.setViewportView(table);
		add(scrollPane);
	}

}
