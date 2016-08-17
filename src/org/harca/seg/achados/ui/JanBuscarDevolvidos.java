package org.harca.seg.achados.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JanBuscarDevolvidos extends JanBuscarGenerico {
		JTable table;
	public JanBuscarDevolvidos() {
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
		final JScrollPane scrollp = new JScrollPane();
		
		table = new JTable();
		scrollp.add(table);
		
		JButton jb = new JButton("Listar devolvidos");
		jb.addActionListener(new ActionListener() {
		
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new ModeloTabelaDevolvido());
				
				
			}
		});
		//scrollp.setViewportView(table);
		this.add(BorderLayout.CENTER,table);
		panel.add(jb);
		
	}
}
