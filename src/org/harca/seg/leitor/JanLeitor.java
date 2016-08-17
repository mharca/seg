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
					
				//	table.setValueAt(tMatricula.getText(), 1, 2);
					System.out.println(matriculas);
					TableModel tm = table.getModel();
					
					tm.setValueAt("sdfsdf", 1,2);
					//table.getModel().
					table.setModel(tm);
					tMatricula.setText("");
					
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
					case 1: return "scdc";
					case 2: return "Nome";
				}
				fireTableDataChanged();
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
			}
			@Override
			public void fireTableDataChanged() {
				// TODO Auto-generated method stub
				super.fireTableDataChanged();
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
					chaves.add(table.getValueAt(i, 0).toString());
					
				}
				String s = chaves.toString();
				String aux = s.replaceAll("\\s","");
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
