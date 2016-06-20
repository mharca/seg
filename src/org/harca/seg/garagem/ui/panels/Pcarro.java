package org.harca.seg.garagem.ui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.bcel.generic.Select;

public class Pcarro extends JPanel{
	JFormattedTextField tplaca;
	JTextField tmodelo;
	JTextField tcor;
	public Pcarro(){
		setLayout(new GridLayout(3,2));
		add(new JLabel("Placa: "));
		tplaca = new JFormattedTextField();
		try{
			MaskFormatter mf = new MaskFormatter("UUU-####");
			mf.install(tplaca);
		}catch(Exception e){
			e.printStackTrace();
		}
		tplaca.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				tplaca.selectAll();
			}
		});
		add(tplaca);
		add(new JLabel("Modelo:"));
		tmodelo = new JTextField();
		
		add(tmodelo);
		add(new JLabel("Cor:"));
		tcor = new JTextField();
		add(tcor);
		this.setBorder(BorderFactory.createTitledBorder("Carro"));

	}
	public String getPlaca(){
		return tplaca.getText();
	}
	public String getModelo(){
		return tmodelo.getText();
	}
	public String getCor(){
		return tcor.getText();
	}
	public void setPlaca(String s){
		tplaca.setText(s);
	}
	public void setModelo(String s){
		tmodelo.setText(s);
	}
	public void setCor(String s){
		tcor.setText(s);
	}
}
