package org.harca.seg.chaves.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JanDevolverChave extends JPanel {
	private JTextField tnumero;
	JButton bdevolver;
	JTable jtable;
	JScrollPane jsp;
	public JanDevolverChave(){
		setLayout(new BorderLayout());
		JLabel lnumero = new JLabel("Numero: ");
		tnumero = new JTextField();
		JPanel jp = new JPanel(new GridLayout(1,2));
		bdevolver = new JButton("Devolver");
		jp.add(lnumero);
		jp.add(tnumero);
		jp.add(bdevolver);
		jtable = new JTable(new ModeloDevolverTabela());
		jsp = new JScrollPane(jtable);
		
		add(jp,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
	}
}
