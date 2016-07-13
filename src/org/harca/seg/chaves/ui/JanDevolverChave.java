package org.harca.seg.chaves.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	static private int ID=3;
	ModeloDynDevolver modeloDyn;
	public JanDevolverChave(){
		setLayout(new BorderLayout());
		JLabel lnumero = new JLabel("Numero: ");
		tnumero = new JTextField();
		JPanel jp = new JPanel(new GridLayout(1,2));
		bdevolver = new JButton("Devolver");
		bdevolver.addActionListener(new cadastrar());
		jp.add(lnumero);
		jp.add(tnumero);
		jp.add(bdevolver);
		String colunas[]={"Nome", "Numero","Localizacao","Andar", "Torre","Matricula", "Hora emprestimo", "Data emprestimo","id"};
	
		
		
		Controle c = new Controle();
		modeloDyn = new ModeloDynDevolver(colunas, c.selectEmprestados());
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
					
					System.out.println("val"+jtable.getValueAt(rows[i], 1).toString());
				//	if(jtable.getValueAt(rows[i], 5).toString().equals("verde")){
					//	System.out.println("ok");
						
					//}
					// 
				}
				tnumero.setText(aux.toString());
				
				
			}
		});
		jsp = new JScrollPane(jtable);
		
		add(jp,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
	}

	
	
	class cadastrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Controle c= new Controle();
			
			//c.inserirEmprestimo(Integer.parseInt(modeloTabela.getValueAt(2, 0).toString()), Integer.parseInt(tmat.getText()), tnome.getText());
		//	 numchaves = Integer.parseInt(tNumero.getText());
			
			int[] rows = jtable.getSelectedRows();
			for(int i=0;i<6;i++)
				System.out.println(modeloDyn.getColumnName(i));
			
			int aux =0;
			for (int i:rows){
					System.out.println("DFDF"+modeloDyn.getValueAt(i, 8).toString());
					//c.devolver(( Integer.parseInt(modeloDyn.getValueAt(i, ID-2).toString())));
					aux = Integer.parseInt((modeloDyn.getValueAt(i, 8).toString()));
					c.devolver(( aux));
				}
			}
							
		}
			
		
		
}

