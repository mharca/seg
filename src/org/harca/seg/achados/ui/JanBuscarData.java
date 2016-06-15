package org.harca.seg.achados.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import org.harca.seg.achados.control.Control;

public class JanBuscarData extends JanBuscarGenerico{
	public JanBuscarData(){
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblData = new JLabel("Data  : ");
		panel.add(lblData);
		
		final JFormattedTextField formattedTextField = new JFormattedTextField();
		//formattedTextField.setBounds(660, 91, 68, 40);
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(formattedTextField);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		panel.add(formattedTextField);
		
		JButton jb = new JButton("Procurar");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("botao data clicado");
				Control control = new Control();
				control.selectByDate(formattedTextField.getText());
				
				List<String> ls = new ArrayList<String>();
				
				List<List<String>> ls2 = control.selectByDate(formattedTextField.getText());
				
				ModeloTabela mt = new ModeloTabela(ls2.get(0));
																			
				table.setModel(mt);
							
				mt.fireTableDataChanged();
				table.repaint();
				
			}
		});
		panel.add(jb);
		
		
	}
}
