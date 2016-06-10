package org.harca.seg.chaves.ui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JanDevolverChave extends JPanel {
	private JTextField textField;
	public JanDevolverChave(){
		setLayout(null);
		
		JLabel lblNmero = new JLabel("N\u00FAmero: ");
		lblNmero.setBounds(109, 35, 76, 14);
		add(lblNmero);
		
		textField = new JTextField();
		textField.setBounds(195, 32, 292, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.setBounds(264, 65, 91, 23);
		add(btnDevolver);
		
	}
}
