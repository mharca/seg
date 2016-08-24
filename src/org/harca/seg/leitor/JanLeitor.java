package org.harca.seg.leitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.lf5.viewer.FilteredLogTableModel;
import org.harca.seg.util.HtmlParser;


public class JanLeitor extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tNome, tMatricula, tChave;
	JLabel lNome, lMatricula, lChave;
	JTable table;
	JButton btnEnviarCorreio;
	List<String> matriculas;
	List<List<String>> l2;
	HtmlParser parser;
	
	public JanLeitor(){
		tNome = new JTextField();		tNome.setEditable(false);
		tMatricula = new JTextField(); 	
		tChave = new JTextField();		tChave.setEditable(false);
		
		matriculas = new ArrayList<String>();
		l2 = new ArrayList<>();
		
		tMatricula.setBackground(Color.YELLOW);
		tMatricula.selectAll();
		tMatricula.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					matriculas.add(tMatricula.getText());
					String mAux = new String();
					String mAuxTerceiro = new String();
					mAux = tMatricula.getText().substring(6, 12);
					mAuxTerceiro = "4"+tMatricula.getText().substring(6, 12);
					for(int i=0; i<10;i++){
					
						try{
							parser = new HtmlParser(mAux+Integer.toString(i));
						
							if(parser.getNome()!=null){
							/*
								if(parser.getEmpresa() != "PETROBRAS") // outra empresa
									parser = new HtmlParser(mAuxTerceiro+Integer.toString(i)); // tenta matricula terceiro
						*/		
								tNome.setText(parser.getNome());
								tChave.setText(parser.getChave());
								
								final List<String> listaEmpregado = new ArrayList<>();
									listaEmpregado.add(tMatricula.getText());
									Thread t = new Thread(new Runnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
											listaEmpregado.add(parser.getChave());
											listaEmpregado.add(parser.getEmail());
											listaEmpregado.add(parser.getNome());
											l2.add(listaEmpregado);
										}
									});
									t.run();
									
								table.revalidate();
								Thread t2 = new Thread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										tMatricula.setText("");
										tMatricula.selectAll();
												
									}
								});
								t2.run();
								break;
							}
						
						}catch(Exception e){
							
						}
						
						
					}
				
					System.out.println(matriculas+" - "+ mAux);
					tMatricula.setText("");
					table.repaint();
					table.revalidate();
						
			}
		});
		lMatricula = new JLabel("Matricula: ");
		lNome = new JLabel("Nome: ");
		lChave = new JLabel("Chave: ");
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
				
		panel1.setLayout(new GridLayout(3,2));
		panel1.add(lMatricula); 	panel1.add(tMatricula);
		panel1.add(lChave);			panel1.add(tChave);
		panel1.add(lNome);			panel1.add(tNome);
		
		add(panel1,BorderLayout.NORTH);
				
		table = new JTable(new AbstractTableModel() {
			private static final long serialVersionUID = 1L;
			String[] colunas = {"Cracha", "Chave", "Correio", "Nome"};
			@Override
			public Object getValueAt(int linha, int coluna) {

				switch(coluna){
					case 0: return matriculas.get(linha).toString();	// cracha
					case 1: return l2.get(linha).get(1).toString();		// chave
					case 2: return l2.get(linha).get(2).toString(); 	//correio
					case 3: return l2.get(linha).get(3).toString();		// nome
				}
				
				return null;
			}
			@Override
			public String getColumnName(int arg0) {
				return colunas[arg0];
			}
			
			@Override
			public int getRowCount() {
				return matriculas.size();	
			}
			@Override
			public void fireTableDataChanged() {
				fireTableDataChanged();
			}
			@Override
			public int getColumnCount() {
				return colunas.length;
			}
		});
		JScrollPane jsp = new JScrollPane(table);
		add(jsp,BorderLayout.CENTER);
		
		JPanel panelMail = new JPanel();
		btnEnviarCorreio = new JButton("Criar correio");
		btnEnviarCorreio.addActionListener(new enviarCorreio());
		panelMail.add(btnEnviarCorreio);
		
		add(panelMail,BorderLayout.SOUTH);
		tMatricula.selectAll();
	}
	//******************************************************************************************************//
	private class enviarCorreio implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("teste");
			table.setValueAt(matriculas, 0, 0);
			
			String body = "Prezado,%20Seu%20novo%20crachá%20está%20disponível%20para%20ser%20retirado%20na%20segurança%20corporativa,%20localizada%20no%20térreo%20da%20torre%20A";
		
			try {
				String mail="";
				for(int i =0; i < table.getRowCount();i++){
					if(mail.length()>1)
						mail = mail+","+table.getValueAt(i, 2).toString();
					else mail=table.getValueAt(i, 2).toString();
				}
				System.out.println("--->"+mail);
			
				URI uri = new URI("mailto:"+mail+"?Subject=Crachá%20para%20retirada?body="+body+"");
				Desktop.getDesktop().browse(uri);
			} catch (URISyntaxException e) {
		
				e.printStackTrace();
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		} // action performed
		
	}
}
