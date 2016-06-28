package org.harca.seg.garagem.ui.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.harca.seg.util.HtmlParser;

public class Pvisitante extends JPanel{
	JLabel lmatricula, lnome,lempresa;
	JTextField tmatricula,tnome,tempresa;
	HtmlParser parser;
	public Pvisitante(){
		setLayout(new GridLayout(3,2));
		lmatricula = new JLabel("Matricula:");
		lnome = new JLabel("Nome:");
		lempresa = new JLabel("Empresa: ");
		tmatricula = new JTextField();
		tmatricula.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if (tmatricula.getText().length() > 3)
					perdeFoco();
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				
				
			}
		});
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
	public void perdeFoco(){
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				tnome.setBackground(Color.RED);
				tnome.setText("Buscando nome");
				parser = new HtmlParser(tmatricula.getText());
				String s;
				s = parser.getNome();
				
				tnome.setText(s);
				tnome.setBackground(Color.WHITE);
				s = parser.getEmpresa();
				tempresa.setText(s);
	
			}
			
		});
		t.start();
	}

}
