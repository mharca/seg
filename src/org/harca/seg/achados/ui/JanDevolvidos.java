package org.harca.seg.achados.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JanDevolvidos extends JFrame {
	private JTable table;
	public JanDevolvidos() {
		setTitle("Devolvidos");
		setSize(931, 450);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Devolvidos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(4, 9, 822, 406);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 792, 384);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 772, 362);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new ModeloTabelaDevolvido());
		scrollPane.setViewportView(table);
	}
}
