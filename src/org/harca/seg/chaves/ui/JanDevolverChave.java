package org.harca.seg.chaves.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.harca.seg.chaves.control.Controle;

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
		String colunas[]={"Nome", "Numero","Localizacao","Andar", "Torre","Matricula", "Hora emprestimo", "Data emprestimo"};
	
		
		
		Controle c = new Controle();
		ModeloDynDevolver modeloDyn = new ModeloDynDevolver(colunas, c.selectEmprestados());
		//modeloDyn.setLista(c.selectEmprestados());
		jtable = new JTable(modeloDyn);
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				ArrayList<String> aux = new ArrayList<String>();
			//	aux.add(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
				int[] rows = jtable.getSelectedRows();
				
			
				for (int i = 0; i < rows.length; i++){
					aux.add(jtable.getValueAt(rows[i], 1).toString());
				/*	System.out.println(jtable.getValueAt(rows[i], 5).toString());
					if(jtable.getValueAt(rows[i], 5).toString().equals("verde")){
						System.out.println("ok");
						
					}
					*/
				}
				tnumero.setText(aux.toString());
				
				
			}
		});
		jsp = new JScrollPane(jtable);
		
		add(jp,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
	}
}
