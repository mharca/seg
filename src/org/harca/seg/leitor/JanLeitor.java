package org.harca.seg.leitor;

import java.awt.BorderLayout;
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
		
		tMatricula.selectAll();
		tMatricula.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					matriculas.add(tMatricula.getText());
					
					String cAux = new String();
					String mAux = new String();
					
					mAux = tMatricula.getText().substring(6, 12);
					//cAux = 
					for(int i=0; i<10;i++){
						//mAux = mAux+Integer.toString(i);
						try{
							parser = new HtmlParser(mAux+Integer.toString(i));
							if(parser.getNome()!=null){
								System.out.println(parser.getNome()+"-"+parser.getChave());
								
								tNome.setText(parser.getNome());
								tChave.setText(parser.getChave());
								List<String> listaEmpregado = new ArrayList<>();
									listaEmpregado.add(tMatricula.getText());
									listaEmpregado.add(parser.getChave());
									listaEmpregado.add(parser.getNome());
								//table.getModel().setValueAt(parser.getNome(),1, 2);
								table.revalidate();
								l2.add(listaEmpregado);
								break;
							}
						
						}catch(Exception e){
							
						}
						
						
					}
				//	mAux = mAux+"2";
				//	parser = new HtmlParser(mAux);
				//	System.out.println(parser.getNome());
					
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] colunas = {"Cracha", "Chave", "Nome"};
			@Override
			public Object getValueAt(int linha, int coluna) {
				// TODO Auto-generated method stub
				switch(coluna){
					case 0: return matriculas.get(linha).toString();
					case 1: return l2.get(linha).get(1).toString();
					case 2: return l2.get(linha).get(2).toString();
				}
				
				return null;
			}
			@Override
			public String getColumnName(int arg0) {
				return colunas[arg0];
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return matriculas.size();
				//return 4;
			}
			@Override
			public void fireTableDataChanged() {
				// TODO Auto-generated method stub
				//super.
				//revalidate();
				fireTableDataChanged();
			}
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return colunas.length;
			}
		});
		JScrollPane jsp = new JScrollPane(table);
		add(jsp,BorderLayout.CENTER);
		
		JPanel panelMail = new JPanel();
		btnEnviarCorreio = new JButton("Enviar correio");
		btnEnviarCorreio.addActionListener(new enviarCorreio());
		panelMail.add(btnEnviarCorreio);
		
		add(panelMail,BorderLayout.SOUTH);
	}
	//******************************************************************************************************//
	private class enviarCorreio implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("teste");
			table.setValueAt(matriculas, 0, 0);
		//	table.firePropertyChange(null, 1, 1);
			
			
			
			String subject = "Cracha novo.";
			String body = "Prezado,%20Seu%20novo%20cracha%20esta%20disponivel%20para%20ser%20retirado%20na%20seguranca%20corporativa";
		//	String body="teste";
			try {
				List<String> chaves = new ArrayList<String>();
				String mail;
				for(int i =0; i < table.getRowCount();i++){
					chaves.add(table.getValueAt(i, 1).toString());
					
				}
				String s = chaves.toString();
				String aux2 = s.replaceAll("\\[","");
				//String aux = aux2.toString();
				//String aux = aux2.replaceAll("\\]","");
			//	String aux = aux2.toString();
				String aux = s.toString();
				URI uri = new URI("mailto:"+aux+"?Subject=Cracha%20para%20retirada?body="+body+"");
				Desktop.getDesktop().browse(uri);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//table.setModel(table.getModel());
		
			
			
		}
		
	}
}
