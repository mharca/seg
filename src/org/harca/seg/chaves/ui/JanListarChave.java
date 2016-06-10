package org.harca.seg.chaves.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class JanListarChave extends JPanel {
	private JPanel jpanel1;
	public JanListarChave(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JButton btnHoje = new JButton("Hoje");
		panel.add(btnHoje);
		
		JButton btnTodos = new JButton("Todos");
		panel.add(btnTodos);
		
	}
}
