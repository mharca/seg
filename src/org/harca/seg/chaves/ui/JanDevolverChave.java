package org.harca.seg.chaves.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.harca.seg.chaves.control.Controle;

import javax.swing.JButton;

public class JanDevolverChave extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tnumero;
	JButton bdevolver;
	JTable jtable;
	JScrollPane jsp;
	ModeloDynDevolver modeloDyn;
	//String colunas[];
	public JanDevolverChave(){
		setLayout(new BorderLayout());
		JLabel lnumero = new JLabel("Numero: ");
		tnumero = new JTextField();
		tnumero.setEditable(false);
		JPanel jp = new JPanel(new GridLayout(1,2));
		bdevolver = new JButton("Devolver");
		jp.add(lnumero);
		jp.add(tnumero);
		jp.add(bdevolver);
		final String colunas[]={"Nome", "Numero","Localizacao","Andar", "Torre","Matricula", "Hora emprestimo", "Data emprestimo","ID"};
	
		
		
		Controle c = new Controle();
		modeloDyn = new ModeloDynDevolver(colunas,c.selectEmprestadosNaoDevolvidos());
		//modeloDyn.setLista(c.selectEmprestados());
		jtable = new JTable(modeloDyn);
		jsp = new JScrollPane(jtable);
		
		jtable.getColumnModel().getColumn(2).setWidth(500);
		
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
		JPopupMenu popmenu = new JPopupMenu();
		JMenuItem menuHistoricoPessoa = new JMenuItem("Historico da pessoa");
		JMenuItem menuDevolver = new JMenuItem("Devolver");
		menuHistoricoPessoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nome = jtable.getValueAt(jtable.getSelectedRow(), 0).toString();
				String matricula = jtable.getValueAt(jtable.getSelectedRow(), 5).toString();
				final String n = nome;
				final String m = matricula;
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						new JanHistoricoPessoa(n,m);		
					}
				});
				t.run();
				
			}
		});
		
		JMenuItem menuHistoricoChave = new JMenuItem("Historico da chave");
		menuHistoricoChave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Sid = jtable.getValueAt(jtable.getSelectedRow(), 8).toString(); // 9 = ID
				int id = Integer.parseInt(Sid);
				String num = jtable.getValueAt(jtable.getSelectedRow(), 1).toString();
				String local = jtable.getValueAt(jtable.getSelectedRow(), 2).toString();
				Controle c = new Controle();
				int chaveID = c.getChaveIdByEmprestimoID(id);
				new JanHistoricoChave(chaveID,local,num);
				
			}
		});
		
		popmenu.add(menuHistoricoChave);
		menuDevolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bdevolver.doClick();
				
			}
		});
		
		popmenu.add(menuHistoricoPessoa);
		popmenu.add(menuDevolver);
		jtable.setComponentPopupMenu(popmenu);
		
		
		bdevolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controle c = new Controle();
				int[] rows = jtable.getSelectedRows();
				
				for(int i:rows){
					c.devolverChave(Integer.parseInt( jtable.getModel().getValueAt(i, 8).toString() ) ); // manda ID
					//System.out.print(jtable.getModel().getValueAt(i, 8).toString());
				}
				modeloDyn = new ModeloDynDevolver(colunas,c.selectEmprestadosNaoDevolvidos());
				jtable.setModel(modeloDyn);
				
			}
		});
		
		add(jp,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
	}
	
	
}
