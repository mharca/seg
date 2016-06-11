package org.harca.seg.garagem.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class JanGaragemListarVisitante extends JPanel {
	private JTextField textField;
	public JanGaragemListarVisitante(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula: ");
		panel.add(lblMatrcula);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		add(new JTableGaragem(), BorderLayout.CENTER);
	}
}
