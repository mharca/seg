package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JanGaragemListarTag extends JPanel{
	
	public JanGaragemListarTag(){
		
		setLayout(new BorderLayout());
		
		TagTabelaModelo ttm = new TagTabelaModelo();
		JTable table = new JTable();
		table.setModel(ttm);
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		
		
		
		add(jsp,BorderLayout.CENTER);
		setVisible(true);
		
	}

}
