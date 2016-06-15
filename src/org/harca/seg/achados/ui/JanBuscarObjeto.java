package org.harca.seg.achados.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.harca.seg.achados.control.Control;

public class JanBuscarObjeto extends JanBuscarGenerico{
	public JanBuscarObjeto(){
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblData = new JLabel("Nome do objeto: ");
		panel.add(lblData);
		
		final JTextField textoObjeto = new JTextField(20);
		
		panel.add(textoObjeto);
		JButton jb = new JButton("Procurar");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
Control control = new Control();
				
				try{
					List<List<String>> ls2 = control.selectByTipo(textoObjeto.getText()); 
					ModeloTabela mt = new ModeloTabela(ls2.get(0));
					
					mt.addLista(ls2);
					table.setModel(mt);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Erro");
				}
				//ls2.add(ls);
				//mt.addRow(ls2);
				//mt.addRow();
			//	mt.fireTableDataChanged();
				table.repaint();
				
			}
		});
		
		panel.add(jb);
	}

}
