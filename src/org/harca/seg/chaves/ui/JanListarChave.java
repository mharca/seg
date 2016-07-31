package org.harca.seg.chaves.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import org.harca.seg.chaves.control.Controle;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class JanListarChave extends JPanel {
	private JPanel jpanel1;
	private JScrollPane jsp;
	private JTable jtable;
	//List<List<String>> lista;
	
	public JanListarChave(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		
		
		
		JButton btnHoje = new JButton("Hoje");
		panel.add(btnHoje);
		
		btnHoje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//new JanHistoricoPessoa();
				
			}
		});
		JButton btnTodos = new JButton("Todos");
		panel.add(btnTodos);
		add(panel, BorderLayout.NORTH);
	
		//String[] colunas = {"Nome","Matricula","Chave", "Localizacao", "Hora emprestimo", "Data emprestimo","Hora devolvido", "Data devolvido"};
		String colunas[]={"Nome", "Numero","Localizacao","Andar", "Torre","Matricula", "Hora emprestimo", "Data emprestimo"};
		List<List<String>> lista;
		Controle c = new Controle();
		lista = c.selectEmprestados();
		ModeloDynDevolver modelo = new ModeloDynDevolver(colunas,lista);
		
		JPopupMenu popmenu = new JPopupMenu();
		JMenuItem menuHistoricoPessoa = new JMenuItem("Historico da pessoa");
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
		popmenu.add(menuHistoricoPessoa);
		
		//modelo.addLista(lista);
		jtable = new JTable(modelo);
		
		jtable.setComponentPopupMenu(popmenu);
		
		jsp = new JScrollPane(jtable);
		
		add(jsp,BorderLayout.CENTER);
		
	}
}
