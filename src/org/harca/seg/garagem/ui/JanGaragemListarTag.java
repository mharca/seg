package org.harca.seg.garagem.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import org.apache.log4j.helpers.FileWatchdog;
import org.harca.seg.garagem.control.Control;

import com.gargoylesoftware.htmlunit.javascript.host.Cache;

//import jdk.internal.jfr.events.FileWriteEvent;
import net.miginfocom.layout.Grid;

public class JanGaragemListarTag extends JPanel{
	private JTable table;
	TagTabelaModelo ttm;
	JTextField tBusca;
	public JanGaragemListarTag(){
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(1,3));
		JLabel lBuscar = new JLabel("Filtrar nome/matricula/chave:");
		 tBusca = new JTextField();
		tBusca.setBackground(Color.YELLOW);
		JButton bbuscar = new JButton("Procurar");
		
		tBusca.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				procurar();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(lBuscar);
		panel.add(tBusca); 
		panel.add(bbuscar);
		
		ttm = new TagTabelaModelo();
		 table = new JTable();
		table.setModel(ttm);
		table.setLayout(new GridLayout(1,1)); // tantando dar um resize na tabela.
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		add(panel,BorderLayout.NORTH);
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
							JOptionPane.showMessageDialog(null, "Erro na impressora");
						}
					
					}
				});
				
			t.run();
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
		
		JButton btnToExcel = new JButton("Salvar como planilha");
		btnToExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File fp = new File("Tag.xls");
				
				toExcel(table, fp);
				try{
					Desktop.getDesktop().open(fp);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Arquivo não pode ser aberto");
				}
			}
		});
		jpSul.add(btnApagar);
		jpSul.add(btnImprimir);
		jpSul.add(btnToExcel);
		add(jpSul,BorderLayout.SOUTH);
		setVisible(true);
		
	}
	
	public void toExcel(JTable table, File file){
		try{
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);
			
			for(int i=0; i< model.getColumnCount();i++){
				excel.write(model.getColumnName(i)+"\t");
				
			}
			excel.write("\n");
			
			for(int i=0;i<model.getRowCount(); i++){
				for(int j=0; j < model.getColumnCount(); j++){
					excel.write(model.getValueAt(i, j).toString()+"\t");
				}
				excel.write("\n");
			}
			
		//	JFileChooser saveFile = new JFileChooser("9999.xls");
		//	saveFile.showSaveDialog(null); 
			
			excel.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Erro ao criar arquivo");
			e.printStackTrace();
		}
	}
	
	public void procurar(){
		List<List<String>> l2;
		
		Control c = new Control();
		//l2 = c.filtrarTag(tBusca.getText());
	
		
<<<<<<< HEAD
		//table.setModel(new TagTabelaModelo(l2));
=======
		table.setModel(new TagTabelaModelo(tBusca.getText()));
>>>>>>> 93ad7acafb937b2870e78871a8641dee40a79bdd
		table.repaint();
		
	}

}
