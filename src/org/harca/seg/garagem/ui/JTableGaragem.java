package org.harca.seg.garagem.ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class JTableGaragem extends JPanel{
	private JTable table;
	public JTableGaragem(){
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Visitante", "Documento", "Visitado", "Carro", "Placa", "cor", "Hor\u00E1rio", "Solicitante"
			}
		));
		scrollPane.setViewportView(table);
		
	}

}
