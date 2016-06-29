package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.harca.seg.garagem.control.Control;

import com.gargoylesoftware.htmlunit.javascript.host.Cache;

import net.miginfocom.layout.Grid;

public class JanGaragemListarTag extends JPanel{
	private JTable table;
	TagTabelaModelo ttm;
	public JanGaragemListarTag(){
		
		setLayout(new BorderLayout());
		
		ttm = new TagTabelaModelo();
		 table = new JTable();
		table.setModel(ttm);
		table.setLayout(new GridLayout(1,1)); // tantando dar um resize na tabela.
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		add(jsp,BorderLayout.CENTER);
		JPanel jpSul = new JPanel();
		jpSul.setLayout(new FlowLayout());
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
				
						try {
							table.print();
						} catch (PrinterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
				});
				
			
		}
			});
		
		JButton btnApagar = new JButton("Apagar selecionados");
		btnApagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Control c = new Control();
				for (int i : table.getSelectedRows()){
					//System.out.println(table.getValueAt(i, 0));
					c.delete(Integer.parseInt(table.getValueAt(i, 0).toString()));
					ttm = new TagTabelaModelo();
					table.setModel(ttm);
					//table.repaint();
					
					
				}
				
			}
		});
		jpSul.add(btnApagar);
		jpSul.add(btnImprimir);
		add(jpSul,BorderLayout.SOUTH);
		setVisible(true);
		
	}

}
