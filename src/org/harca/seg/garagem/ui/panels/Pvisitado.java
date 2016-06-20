package org.harca.seg.garagem.ui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pvisitado extends JPanel{
	JTextField tnome, tmatricula;
	JCheckBox checkVip;
	JButton jb = new JButton();
	JLabel lnome;
	JComponent objNome;
	public Pvisitado(){
		setLayout(new GridLayout(2,3));
		add(new JLabel("Matricula:"));
		tmatricula = new JTextField();
		add(tmatricula);
		lnome = new JLabel("Nome:");
		checkVip = new JCheckBox("VIP");
		//final JComponent objNome = new JComboBox<>(new String[]{"1","2","3"});
		objNome = new JTextField();
		checkVip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//final JComponent objNome = new JComboBox<>(new String[]{"1","2","3"});
					if(checkVip.isSelected()){
						lnome.setText("Diretor:");
						lnome.setForeground(new Color(255,0,0));
						tmatricula.setBackground(Color.YELLOW);
						
						objNome = new JComboBox<String>(new String[]{"1","2"});
						
					}
					else{
						lnome.setText("Nome:");
						lnome.setForeground(new Color(0,0,0));
						tmatricula.setBackground(Color.WHITE);

						objNome = new JTextField();

					}
					
			}
		});
		
		add(checkVip);
		add(lnome);
		tnome = new JTextField();
		add(objNome);
		jb = new JButton("Buscar");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					new Thread(){
						public void run(){
							buscar(); 
						}
				}.start();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		
		add(jb);
		this.setBorder(BorderFactory.createTitledBorder("Visitado"));

	}
	public String getNome(){
		return tnome.getText();
	}
	public String getMatricula(){
		return tmatricula.getText();
	}
	public Boolean getCheckVip(){
		
		return checkVip.isSelected();
	}
	private void buscar(){
		tnome.setText("AAA");
		while(true){
			System.out.println("_-");
		}
	}
}
