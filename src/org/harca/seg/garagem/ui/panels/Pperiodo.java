package org.harca.seg.garagem.ui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class Pperiodo extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFormattedTextField tdia,tate;
	JCheckBox checkExt;
	public Pperiodo(){
		setLayout(new GridLayout(3,2));
		add(new JLabel("Dia: "));
		tdia = new JFormattedTextField();
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(tdia);
		}catch(Exception e){
			e.printStackTrace();
		}
		tdia.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				tdia.selectAll();
			}
		});
		add(tdia);
		add(new JLabel("Até:"));
		tate = new JFormattedTextField();
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(tate);
		}catch(Exception e){
			e.printStackTrace();
		}
		tate.setEditable(false);
		tate.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				tate.selectAll();
			}
		});
		add(tate);
		checkExt = new JCheckBox("Extendida");
		checkExt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if(checkExt.isSelected()){
						tate.setEditable(true);
					}else{
						tate.setEditable(false);
					}
			}
		});
		add(checkExt);
		this.setBorder(BorderFactory.createTitledBorder("Periodo"));

	}
	public String getDia(){
		return tdia.getText();
	}
	public String getAte(){
		return tate.getText();
	}
	public  boolean getCheckExt(){
		return checkExt.isSelected();
	}
	
}
