package org.harca.seg.chaves.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;

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
		
		JButton btnTodos = new JButton("Todos");
		panel.add(btnTodos);
		add(panel, BorderLayout.NORTH);
	
		String colunas[]={"Nome", "Numero","Localizacao","Andar", "Torre","Matricula", "Hora emprestimo", "Data emprestimo"};
		List<List<String>> lista;
	
		Controle c = new Controle();
			
		lista = c.selectEmprestados();
		ModeloDynDevolver modelo = new ModeloDynDevolver(colunas,lista);
		
		jtable = new JTable(modelo);
		jsp = new JScrollPane(jtable);
		
		add(jsp,BorderLayout.CENTER);
		
	}
}
