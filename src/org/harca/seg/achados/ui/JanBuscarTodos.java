package org.harca.seg.achados.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JanBuscarTodos extends JanBuscarGenerico{
	public JanBuscarTodos(){
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblData = new JLabel("Listar todos os achados e perdidos? ");
		panel.add(lblData);
		
		//JTextField texto = new JTextField(20);
		
		//panel.add(texto);
		JButton jb = new JButton("Procurar todos (pode ser lento)");
		panel.add(jb);
		
	}
}
