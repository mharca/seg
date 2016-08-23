package org.harca.seg.garagem.ui.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.harca.seg.util.HtmlParser;

public class Pvisitado extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tnome, tmatricula;
	JCheckBox checkVip;
	JButton jb = new JButton();
	JLabel lnome;
	//JComponent objNome;
	HtmlParser parser;
	public Pvisitado(){
		setLayout(new GridLayout(2,3));
		add(new JLabel("Matricula:"));
		tmatricula = new JTextField();
		add(tmatricula);
		lnome = new JLabel("Nome:");
		checkVip = new JCheckBox("VIP");
		//final JComponent objNome = new JComboBox<>(new String[]{"1","2","3"});
		//objNome = new JTextField();
		tnome = new JTextField();
		checkVip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//final JComponent objNome = new JComboBox<>(new String[]{"1","2","3"});
				String s = new String();	
				if(checkVip.isSelected()){
						lnome.setText("Diretor:");
						lnome.setForeground(new Color(255,0,0));
						//tmatricula.setBackground(Color.YELLOW);
						tmatricula.setEditable(false);
						s = tmatricula.getText();
						tmatricula.setText("");
				//		objNome = new JComboBox<String>(new String[]{"1","2"});
						
					}
					else{
						lnome.setText("Nome:");
						lnome.setForeground(new Color(0,0,0));
						tmatricula.setBackground(Color.WHITE);
						tmatricula.setEditable(true);
						tmatricula.setText(s);
					//	objNome = new JTextField();

					}
					
			}
		});
		
		add(checkVip);
		add(lnome);
		add(tnome);
		
		//add(objNome);
		jb = new JButton("Buscar");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				buscar();
				
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
				
		if(getCheckVip().equals(false)){
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					tnome.setText("AAA");
					System.out.println("---");
		
					tnome.setBackground(Color.RED);
					tnome.setText("Buscando nome");
					
					parser = new HtmlParser(tmatricula.getText());
					String s;
					
					s = parser.getNome();
					System.out.println(s);
					tnome.setText(s);
					tnome.setBackground(Color.WHITE);
					System.out.println("+++");

					
				}
			});
			t.start();
		}
	
	}
}
