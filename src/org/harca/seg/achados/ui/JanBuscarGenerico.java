package org.harca.seg.achados.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.text.MaskFormatter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.Border;

public class JanBuscarGenerico extends JPanel{
	JTable table;
	JButton btDevolver;
	public JanBuscarGenerico() {
		setLayout(new BorderLayout(0, 0));
		
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem mItem = new JMenuItem("Devolver");
		popMenu.add(mItem);
		mItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
							btDevolver.doClick();	
			}
		});
		
		table = new JTable();
		ModeloTabela  tmc = new ModeloTabela();
		tmc.limpar();
		table.setModel(tmc);
		
		table.setComponentPopupMenu(popMenu);
		
		
		JScrollPane jsp = new JScrollPane();
		//jsp.add(table);
		jsp.setViewportView(table);
		add(jsp, BorderLayout.CENTER);
		
		JPanel panelBaixo = new JPanel();
		btDevolver = new JButton("Devolver selecionado");
		btDevolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<String> listaTabela = new ArrayList<>();
				
				listaTabela.add((String) table.getModel().getValueAt(table.getSelectedRow(), 0)); // id
				listaTabela.add((String) table.getModel().getValueAt(table.getSelectedRow(), 1)); // tipo
				listaTabela.add((String) table.getModel().getValueAt(table.getSelectedRow(), 2)); // descricao
				
				
				JanDevolver janDevolver = new JanDevolver(listaTabela);
				janDevolver.setVisible(true);
				
			}
		});
		panelBaixo.add(btDevolver);
		add(panelBaixo,BorderLayout.SOUTH);
		
	}

}
