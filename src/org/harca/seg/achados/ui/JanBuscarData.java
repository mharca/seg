package org.harca.seg.achados.ui;

import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class JanBuscarData extends JPanel{
	private JTable table;
	public JanBuscarData() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblData = new JLabel("Data: ");
		panel.add(lblData);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(66, 51, 68, 20);
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(formattedTextField);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		panel.add(formattedTextField);
		
		
		JButton btnProcurar = new JButton("Procurar");
		panel.add(btnProcurar);
		
		table = new JTable();
		ModeloTabela  tmc = new ModeloTabela();
		tmc.limpar();
		table.setModel(tmc);
		add(table, BorderLayout.CENTER);
	}

}
