package org.harca.seg.garagem.ui.panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pvisitante extends JPanel{
	JLabel lmatricula, lnome,lempresa;
	JTextField tmatricula,tnome,tempresa;
	public Pvisitante(){
		setLayout(new GridLayout(3,2));
		lmatricula = new JLabel("Matricula:");
		lnome = new JLabel("Nome:");
		lempresa = new JLabel("Empresa: ");
		tmatricula = new JTextField();
		tnome = new JTextField();
		tempresa = new JTextField();
		add(lmatricula);
		add(tmatricula);
		add(lnome);
		add(tnome);
		add(lempresa);
		add(tempresa);
		this.setBorder(BorderFactory.createTitledBorder("Visitante"));

		setVisible(true);
	}

}
